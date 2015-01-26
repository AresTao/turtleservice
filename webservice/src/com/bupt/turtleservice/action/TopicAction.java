package com.bupt.turtleservice.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.db.TopicDBFunction;
import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;
import com.bupt.turtleservice.model.Topic;

public class TopicAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(TopicAction.class);
	
	public TopicAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	
	public boolean createTopic(JSONObject jsonData) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		
		String classId = jsonData.getString("classId");
		String title = jsonData.getString("title");
		String userId = jsonData.getString("userId");
		String description = jsonData.getString("description");
		String dateTime = jsonData.getString("dateTime");
		func.createTopic(classId, title, userId, description, dateTime);
		return true;
	}
	
	public boolean updateTopic(JSONObject jsonData) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		String title = jsonData.getString("title");
		String description = jsonData.getString("description");
		String topicId = jsonData.getString("topicId");
		
		func.updateTopic(topicId, title, description);
		return true;
	}
	
	public boolean deleteTopic(JSONObject jsonData) throws Exception
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		String topicId = jsonData.getString("topicId");
		func.deleteTopic(topicId);
		return true;
	}
	
	public List<Topic> getTopic(String key)
	{
		TopicDBFunction func = new TopicDBFunction(this.transactionOperation);
		List<Topic> res = func.getTopic(key);
		return res;
	}
}
