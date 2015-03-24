package com.bupt.turtleservice.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.db.TopicDBFunction;
import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;
import com.bupt.turtleservice.model.Policy;
import com.bupt.turtleservice.model.Topic;
import com.bupt.turtleservice.utils.DateUtil;

public class TopicAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(TopicAction.class);
	
	public TopicAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	
	public boolean createTopic(int classId, String title, int userId, String description) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		
		func.createTopic(classId, title, userId, description);
		return true;
	}
	
	public boolean updateTopic(String title, String description, int topicId) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
				
		func.updateTopic(title, description, topicId);
		return true;
	}
	
	public boolean replyTopic(int userId, int topicId, String message, String respTo, int respUserId) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		
		boolean res = func.replyTopic(userId, topicId, message, respTo, respUserId);
		return res;
	}
	
	public boolean deleteTopic(int topicId) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		
		func.deleteTopic(topicId);
		return true;
	}
	
	public List<Topic> getTopicListByClassId(int classId)
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		List<Topic> res = func.getTopicListByClassId(classId);
		return res;
	}
	
	public List<Topic> getTopicListByClassIdAndKey(int classId, String key)
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		List<Topic> res = func.getTopicListByClassIdAndKey(classId, key);
		return res;
	}
	
	public Topic getTopic(int topicId)
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		Topic res = func.getTopic(topicId);
		return res;
	}
	
	public static void main(String args[]) throws Exception
	{
		JSONObject jsonData = new JSONObject();
		jsonData.accumulate("classId", 1);
		jsonData.accumulate("title", "test");
		jsonData.accumulate("userId", 4);
		jsonData.accumulate("dateTime", DateUtil.getDatetime(new Date()));
		jsonData.accumulate("description", "test");
		
		TopicAction action = new TopicAction();
		System.out.println(jsonData.toString());
		//action.createTopic(jsonData);
		
		List<Topic> res = action.getTopicListByClassIdAndKey(1,"test");
		for (Topic item : res)
		{
			System.out.println(item.getDescription());
		}
	}
}
