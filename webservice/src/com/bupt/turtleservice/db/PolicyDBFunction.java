package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.model.Policy;

public class PolicyDBFunction {

	private Logger logger = Logger.getLogger(PolicyDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public PolicyDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean checkExist(int policyId) throws Exception
	{
		String sql= "select * from `policy` where `id` = ?;";
		List<Object> values = new ArrayList<Object>();
		values.add(policyId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return res.next();
	}
	
	public boolean createPolicy(String title, String content, String topic, String comment) throws Exception
	{
		String sql = "INSERT INTO `policy` (`name`, `content`, `topic`, `create_time`, `comment`) VALUES (?,?,?,?,?);";
		List<Object> values = new ArrayList<Object>();
		values.add(title);
		values.add(content);
		values.add(title);
		values.add(new Date().toString());
		values.add(comment);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
	    
		return true;
	}
	
	public boolean updatePolicy(int policyId, String title, String topic, String content, String comment) throws Exception
	{
		String sql = "update `policy` set `name` = ? ,`content` = ? ,`topic`= ?, `comment` where id = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(title);
		values.add(content);
		values.add(topic);
		values.add(policyId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	public List<Policy> getPolicy(String key)
	{
		String sql = "SELECT * FROM `policy` where `topic` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(key);
		
		List<Policy> result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql, values);
			result = new ArrayList<Policy>();
			while(res.next())
			{
				Policy item = new Policy();
				System.out.println(res.getString("content"));
				item.setContent(res.getString("content"));
				item.setCreateTime(res.getString("create_time"));
				item.setName(res.getString("name"));
				
				result.add(item);
			}
		}
		catch (Exception e) {
			logger.error("select policy error");
		}
		
		return result;
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
