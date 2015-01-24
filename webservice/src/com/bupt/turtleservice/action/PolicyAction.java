package com.bupt.turtleservice.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.db.PolicyDBFunction;
import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;
import com.bupt.turtleservice.model.Policy;
import com.bupt.turtleservice.utils.DateUtil;

public class PolicyAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(PolicyAction.class);
	
	public PolicyAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	
	public boolean createPolicy(JSONObject jsonData) throws Exception
	{
		PolicyDBFunction func = new PolicyDBFunction(this.transactionOperation);
		String name = jsonData.getString("name");
		String content = jsonData.getString("content");
		String topic = jsonData.getString("topic");
		String createTime = jsonData.getString("createTime");
		String comment = jsonData.getString("comment");
		func.createPolicy(name, content, topic, createTime, comment);
		return true;
	}
	
	public boolean updatePolicy(JSONObject jsonData) throws Exception
	{
		PolicyDBFunction func = new PolicyDBFunction(this.transactionOperation);
		String name = jsonData.getString("name");
		String content = jsonData.getString("content");
		String topic = jsonData.getString("topic");
		String policyId = jsonData.getString("policyId");
		
		func.updatePolicy(policyId, name, content, topic);
		return true;
	}
	
	public boolean deletePolicy(JSONObject jsonData) throws Exception
	{
		PolicyDBFunction func = new PolicyDBFunction(this.transactionOperation);
		String policyId = jsonData.getString("policyId");
		func.deletePolicy(policyId);
		return true;
	}
	
	public List<Policy> getPolicy(String key)
	{
		
		return null;
		
	}
	
	public static void main(String args[]) throws Exception
	{
		JSONObject jsonData = new JSONObject();
		jsonData.accumulate("name", "test");
		jsonData.accumulate("content", "test");
		jsonData.accumulate("topic", "test");
		jsonData.accumulate("createTime", DateUtil.getDatetime(new Date()));
		jsonData.accumulate("comment", "test");
		
		PolicyAction action = new PolicyAction();
		System.out.println(jsonData.toString());
		action.createPolicy(jsonData);
	}
}
