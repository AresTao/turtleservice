package com.bupt.turtleservice.action;

import java.util.List;

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
		
		String classId = jsonData.getString("classId");
		String title = jsonData.getString("title");
		String userId = jsonData.getString("userId");
		String description = jsonData.getString("description");
		String dateTime = jsonData.getString("dateTime");
		func.createUser(classId, title, userId, description, dateTime);
		return true;
	}
	
	public boolean updateUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		String title = jsonData.getString("title");
		String description = jsonData.getString("description");
		String topicId = jsonData.getString("topicId");
		
		func.updateUser(topicId, title, description);
		return true;
	}
	
	public boolean deleteUser(JSONObject jsonData) throws Exception
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		String topicId = jsonData.getString("topicId");
		func.deleteUser(topicId);
		return true;
	}
	
	public User getUser(String account)
	{
		UserDBFunction func = new UserDBFunction(this.transactionOperation);
		User res = func.getUser(account);
		return res;
	}
}
