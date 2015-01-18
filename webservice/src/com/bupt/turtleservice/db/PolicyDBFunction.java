package com.bupt.turtleservice.db;

import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.model.Policy;

public class PolicyDBFunction {

	private Logger logger = Logger.getLogger(PolicyDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public PolicyDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean createPolicy()
	{
		return true;
	}
	
	public boolean updatePolicy()
	{
		return true;
	}
	
	public List<Policy> getPolicy()
	{
		return null;
	}
	
	public boolean deletePolicy()
	{
		return true;
	}
		
}
