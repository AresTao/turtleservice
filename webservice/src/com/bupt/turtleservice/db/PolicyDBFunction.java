package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.model.Policy;

public class PolicyDBFunction {

	private Logger logger = Logger.getLogger(PolicyDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public PolicyDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean createPolicy(String name, String content, String topic, String createTime, String comment) throws Exception
	{
		String sql = "INSERT INTO `policy` (`name`, `content`, `topic`, `create_time`, `comment`) VALUES (?,?,?,?,?);";
		List<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(content);
		values.add(topic);
		values.add(createTime);
		values.add(comment);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
	    logger.info("add policy");
		return true;
	}
	
	public boolean updatePolicy(String policyId, String content) throws Exception
	{
		String sql = "update `policy` set content = ? where id = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(content);
		values.add(policyId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	public List<Policy> getPolicy(String policyId)
	{
		return null;
	}
	
	public boolean deletePolicy(String policyId) throws Exception
	{
		String sql = "DELETE FROM TABLE `policy` WHERE `id` = ?;";
		List<Object> values = new ArrayList<Object>();
		values.add(policyId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
		
	
}
