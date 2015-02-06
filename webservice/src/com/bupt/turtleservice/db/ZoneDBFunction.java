package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ZoneDBFunction {

	private Logger logger = Logger.getLogger(ZoneDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public ZoneDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public List<String> getZoneList()
	{
		String sql = "SELECT * FROM `class`;";
		List<String> result = null;
		try{
			ResultSet res = this.transactionOperation.exec(sql);
			result = new ArrayList<String>(); 
			while(res.next())
			{
				result.add(res.getString("name"));
			}
		}
		catch (Exception e) {
			logger.error("select zone error");
		}
		return result;
	}
	
}
