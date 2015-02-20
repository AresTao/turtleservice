package com.bupt.turtleservice.action;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.bupt.turtleservice.db.AdminDBFunction;
import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;

public class AdminAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(AdminAction.class);
	
	public AdminAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	public boolean registerAdmin(JSONObject jsonData) throws Exception
	{
		AdminDBFunction func = new AdminDBFunction(this.transactionOperation);
		String name = jsonData.getString("account");
		String passwd = jsonData.getString("passwd");
		boolean res = func.registerAdmin(name, passwd);
		return res;
	}
}
