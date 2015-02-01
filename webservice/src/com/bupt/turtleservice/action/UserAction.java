package com.bupt.turtleservice.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.db.UserDBFunction;
import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;
import com.bupt.turtleservice.model.User;

public class UserAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(UserAction.class);
	
	public UserAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	
	public boolean createUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		
		String account = jsonData.getString("account");
		String passwd = jsonData.getString("passwd");
		String name = jsonData.getString("name");
		
		func.createUser(account, passwd, name);
		return true;
	}
	
	public boolean updateUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		String name = jsonData.getString("name");
		String account = jsonData.getString("account");
		
		func.updateUser(account, name);
		return true;
	}
	
	public boolean deleteUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		String account = jsonData.getString("account");
		func.deleteUser(account);
		return true;
	}
	
	public User getUser(String account)
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		User res = func.getUser(account);
		return res;
	}
	
	public boolean registerUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		String account = jsonData.getString("account");
		String passwd = jsonData.getString("passwd");
		boolean res = func.registerUser(account, passwd);
		return res;
	}
	
	public boolean unRegisterUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		String account = jsonData.getString("account");
		String passwd = jsonData.getString("passwd");
		boolean res = func.unRegisterUser(account, passwd);
		return res;
	}
}
