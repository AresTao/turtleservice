package com.bupt.turtleservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.apache.log4j.Logger;

import com.bupt.turtleservice.constants.DBConstants;

/**
 * 
 * DataBase Foundation Class
 * 
 * @author wang cong
 *
 */
public class DBFunction {
	private Logger logger = Logger.getLogger(DBFunction.class);	
	private Connection conn = null;
	
	public DBFunction() throws TransactionException
	{
		loadDriver();
		connect();
	}

	private void loadDriver() throws TransactionException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e)
		{
			logger.fatal(String.format("Fail to load jdbc driver. Details [%s].", e.getMessage()));
			throw new TransactionException("Fail to load jdbc driver.", e);
		}
	}
	
	private void connect() throws TransactionException
	{
		try
		{
			boolean done = false;
			int tryTimes = DBConstants.RETRY_TIMES + 1;
			while (!done && 0 < tryTimes --)
			{
				try
				{
					String strServerUrl = String.format("jdbc:mysql://%s:%s/%s", DBConstants.DB_HOST, DBConstants.DB_PORT, DBConstants.DB_NAME);
					String strUserName = DBConstants.DB_USER;
					String strPassword = DBConstants.DB_PASS;
					this.conn = DriverManager.getConnection(strServerUrl, strUserName, strPassword);
					this.conn.setAutoCommit(false);
					// set isolation level repeatable read explicitly
					this.conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
					done = true;
				}
				catch (Exception e)
				{
					if (0 >= tryTimes)
					{
						throw e;
					}
					logger.debug("Fail to connect database once and retry");
				}
			}
		}
		catch (Exception e)
		{
			logger.fatal(String.format("Fail to connect database. Details [%s].", e.getMessage()));
			throw new TransactionException ("Fail to connect database", e);
		}		
	}
	
	public void rollback() throws TransactionException
	{
		try
		{
			boolean done = false;
			int tryTimes = DBConstants.RETRY_TIMES + 1;
			while (!done && 0 < tryTimes --)
			{
				try
				{
					this.conn.rollback();
					done = true;
				}
				catch (Exception e)
				{
					if (0 >= tryTimes)
					{
						throw e;
					}
					logger.debug("Fail to roll back once and retry");
				}
			}
		}
		catch (Exception e)
		{
			logger.fatal(String.format("Fail to roll back. Details [%s].", e.getMessage()));
			throw new TransactionException("Fail to roll back", e);
		}
	}
	
	public void commit() throws TransactionException
	{
		try
		{
			boolean done = false;
			int tryTimes = DBConstants.RETRY_TIMES + 1;
			while (!done && 0 < tryTimes --)
			{
				try
				{
					this.conn.commit();
					done = true;
				}
				catch (Exception e)
				{
					if (0 >= tryTimes)
					{
						throw e;
					}
					logger.debug("Fail to commit once and retry");
				}
			}
		}
		catch (Exception e)
		{
			logger.fatal(String.format("Fail to commit. Details [%s].", e.getMessage()));
			throw new TransactionException ("Fail to commit", e);
		}
	}
	
	PreparedStatement getPreparedStatement(String strSQL) throws Exception
	{
		return this.conn.prepareStatement(strSQL);
	}
	
	private void close()
	{
		
		if (null != this.conn)
		{
			try
			{
				this.conn.close();
			}
			catch (Exception e)
			{
				logger.debug(String.format("Exception occur while close connection. Details [%s]", e.getMessage()));
				// Endure and do nothing more.
			}
		}
	}
	
	protected void finalizer()
	{
		this.close();
	}
}
