package com.bupt.turtleservice.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;
import com.bupt.turtleservice.model.Policy;

public class PolicyAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(PolicyAction.class);
	
	public PolicyAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	public boolean createPolicy()
	{
		return true;
	}
	public boolean updatePolicy()
	{
		return true;
	}
	public boolean deletePolicy()
	{
		return true;
	}
	public ArrayList<Policy> getPolicy()
	{
		return null;
		
	}
}
