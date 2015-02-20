package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class AdminDBFunction {

	private Logger logger = Logger.getLogger(AdminDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public AdminDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean registerAdmin(String name, String passwd) throws Exception
	{
		String sql = "select * from `admin` where `name` = ? and `passwd` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(passwd);
			
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return res.next();
	}
	
	
}
