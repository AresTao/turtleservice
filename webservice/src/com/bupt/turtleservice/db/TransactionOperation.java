package com.bupt.turtleservice.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.constants.DBConstants;

/**
 * Operate database transactionally.
 * 
 * @author ztwu
 *
 */
public class TransactionOperation {	
	private Logger logger = Logger.getLogger(TransactionOperation.class);	
	private DBFunction transactionDBFC = null;
	private TransactionState transactionState = TransactionState.COMMITTED;
	
	public TransactionOperation() throws TransactionException
	{
		this.transactionDBFC = new DBFunction();
	}
	
	public void beginTransaction() throws TransactionException
	{
		if (TransactionState.COMMITTED != this.transactionState)
		{
			throw new TransactionException("Transaction nested.");
		}
		
		this.transactionState = TransactionState.INPROCESS;
	}
	
	public void rollbackTransaction() throws TransactionException
	{
		if (TransactionState.INPROCESS != this.transactionState)
		{
			logger.info("No transaction to rollback");
			return;
		}
		
		this.transactionDBFC.rollback();
		this.transactionState = TransactionState.COMMITTED;
	}
	
	public void commitTransaction() throws TransactionException
	{
		if (TransactionState.INPROCESS != this.transactionState)
		{
			throw new TransactionException("No transaction to commit");
		}
		
		this.transactionDBFC.commit();
		this.transactionState = TransactionState.COMMITTED;
	}
	
	public enum TransactionState {INPROCESS, COMMITTED;}
	
	public ResultSet exec(String strSQL, List<Object> listValueObject) throws Exception
	{
		if (null == strSQL || strSQL.isEmpty())
		{
			throw new Exception("Invalid sql statement, null or empty.");
		}
		
		PreparedStatement preparedStatement = this.transactionDBFC.getPreparedStatement(strSQL);
		ResultSet resultSet = null;
		
		try
		{
			int counter = 1;
			for (Object objVal : listValueObject)
			{
				preparedStatement.setObject(counter ++, objVal);
			}

			if (preparedStatement.execute())
			{
				// resultSet should be closed by its receiver
				resultSet = preparedStatement.getResultSet();
			}
			else
			{
				if (null != preparedStatement)
				{
					preparedStatement.close();
				}
			}
		}
		catch (Exception e)
		{
			logger.warn(String.format("Fail to execute [%s]. Details [%s].", strSQL, e.getMessage()));
			if (null != preparedStatement)
			{
				preparedStatement.close();
			}
			throw new Exception(String.format("Fail to execute [%s]", strSQL), e);
		}
		return resultSet;
	}
	
	public ResultSet exec(String strSQL) throws Exception
	{
		if (null == strSQL || strSQL.isEmpty())
		{
			throw new Exception("Invalid sql statement, null or empty.");
		}
		
		PreparedStatement preparedStatement = this.transactionDBFC.getPreparedStatement(strSQL);
		ResultSet resultSet = null;
		
		try
		{
			if (preparedStatement.execute())
			{
				// resultSet should be closed by its receiver
				resultSet = preparedStatement.getResultSet();
			}
			else
			{
				if (null != preparedStatement)
				{
					preparedStatement.close();
				}
			}
		}
		catch (Exception e)
		{
			logger.warn(String.format("Fail to execute [%s]. Details [%s].", strSQL, e.getMessage()));
			if (null != preparedStatement)
			{
				preparedStatement.close();
			}
			throw new Exception(String.format("Fail to execute [%s]", strSQL), e);
		}
		return resultSet;
	}
	
	public Object getValue(String strSQL, List<Object> listValueObject) throws Exception
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = exec(strSQL, listValueObject);
			while (resultSet.next())
			{
				return resultSet.getObject(1);
			}
			return null;
		}
		catch (Exception e)
		{
			logger.warn(String.format("Fail to call getValue. Details [%s]", e.getMessage()));
			throw new Exception ("Fail to call getValue function.");
		}
		finally
		{
			TransactionOperation.freeResultSet(resultSet);
		}
	}
	
	public static void freeResultSet (ResultSet resultSet)
	{
		try
		{
			if (null != resultSet)
			{
				resultSet.getStatement().close();	
			}	
		}
		catch (Exception e)
		{
			//Endure and do nothing more.
		}
		
		try
		{
			if (null != resultSet)
			{
				resultSet.close();
			}
		}
		catch (Exception e)
		{
			//Endure and do nothing more.
		}
	}
	
	public void lockTable(String strTableName, List<List<Object>> listFilter) throws Exception
	{
		try
		{
			List<Object> listFormatFilter = this.parseFilter(listFilter);
			String strCondition = (String) listFormatFilter.get(DBConstants.FORMAT_FILTER_CONDITION_INDEX);
			@SuppressWarnings("unchecked")
			List<Object> listObject = (List<Object>) listFormatFilter.get(DBConstants.FORMAT_FILTER_ARGS_INDEX);
			
			String strSQL = String.format("SELECT * FROM %s WHERE %s FOR UPDATE", strTableName, strCondition);
			this.exec(strSQL, listObject);
		}
		catch (Exception e)
		{
			logger.warn(String.format("Fail to lock table [%s] details [%s]", strTableName, e.getMessage()));
			throw new Exception(String.format("Fail to lock table [%s]", strTableName));
		}
	}

	public List<Object> parseFilter(List<List<Object>> listFilter) throws Exception
	{
		List<Object> listFormatFilter = null;
		try
		{
			listFormatFilter = new LinkedList<Object>();
			String strCondition = "1 = 1";
			List<Object> listValueObject = new LinkedList<Object>();
			String strOperator = null;
			String strField = null;
			
			for (List<Object> listFilterItem : listFilter)
			{
				strField = (String) listFilterItem.get(DBConstants.RAW_FILTER_FIELD_INDEX);
				strOperator = (String) listFilterItem.get(DBConstants.RAW_FILTER_OPERATOR_INDEX);
				
				if (DBConstants.CONDITION_OPERATOR_TYPE_SINGLE == getOperatorType(strOperator))
				{
					strCondition = String.format("%s AND %s %s ?", strCondition, strField, strOperator);
					listValueObject.add(listFilterItem.get(DBConstants.RAW_FILTER_ARGS_START_INDEX));
				}
				else if (DBConstants.CONDITION_OPERATOR_TYPE_MULTI == getOperatorType(strOperator))
				{
					String strPlaceHolder = "";
					for (int i = DBConstants.RAW_FILTER_ARGS_START_INDEX; i < listFilterItem.size(); i ++)
					{
						if (DBConstants.RAW_FILTER_ARGS_START_INDEX == i)
						{
							strPlaceHolder += "?";
							listValueObject.add(listFilterItem.get(i));
							continue;
						}
						strPlaceHolder += ", ?";
						listValueObject.add(listFilterItem.get(i));
					}
					// common case
					if (!strPlaceHolder.isEmpty())
					{
						strCondition = String.format("%s AND %s %s (%s)", strCondition, strField, strOperator, strPlaceHolder);
					}
					// no argument for this operator
					else
					{
						// force to false
						strCondition = String.format("%s AND 1 = 0", strCondition);
					}
				}
			}
			
			listFormatFilter.add(strCondition);
			listFormatFilter.add(listValueObject);	
		}
		catch (Exception e)
		{
			logger.warn(String.format("Fail to call parseFilter. Details [%s].", e.getMessage()));
			throw new Exception("Fail to call parseFilter");
		}
		return listFormatFilter;
	}
	
	private String getOperatorType(String strOperator) throws Exception
	{
		if (null == strOperator || strOperator.isEmpty())
		{
			throw new Exception("Empty operator");
		}
		
		for (String strSingleOperator : DBConstants.ARRAY_SINGLE_CONDITION_OPERATOR)
		{
			if (strOperator.equals(strSingleOperator))
			{
				return DBConstants.CONDITION_OPERATOR_TYPE_SINGLE;
			}
		}
		
		for (String strMultiOperator : DBConstants.ARRAY_MULTI_CONDITION_OPERATOR)
		{
			if (strOperator.equals(strMultiOperator))
			{
				return DBConstants.CONDITION_OPERATOR_TYPE_MULTI;
			}
		}
		
		throw new Exception(String.format("Unsupported operator [%s]", strOperator));
	}
}
