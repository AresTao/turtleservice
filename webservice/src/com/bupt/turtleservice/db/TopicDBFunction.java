package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.model.Reply;
import com.bupt.turtleservice.model.Topic;

public class TopicDBFunction {

	private Logger logger = Logger.getLogger(TopicDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public TopicDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean createTopic(String classId, String title, String userId, String description, String dateTime) throws Exception
	{
		String sql = "INSERT INTO `topic` (`classId`, `title`, `userId`, `description`, `dateTime`) VALUES (?,?,?,?,?);";
		List<Object> values = new ArrayList<Object>();
		values.add(classId);
		values.add(title);
		values.add(userId);
		values.add(description);
		values.add(dateTime);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
	    
		return true;
	}
	
	public boolean updateTopic(String topicId, String title, String description) throws Exception
	{
		String sql = "update `topic` set `title` = ? ,`description` = ? where id = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(title);
		values.add(description);
		values.add(topicId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	public List<Topic> getTopic(String classId)
	{
		String sql = "SELECT * FROM `topic` where `classId` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(classId);
		
		List<Topic> result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql, values);
			result = new ArrayList<Topic>();
			while(res.next())
			{
				Topic item = new Topic();
				item.setTitle(res.getString("title"));
				item.setCreateTime(res.getString("dateTime"));
				item.setDescription(res.getString("description"));
				
				List<Reply> replyList = getReplyList(res.getInt("id"));
				item.setReplyList(replyList);
				result.add(item);
			}
		}
		catch (Exception e) {
			logger.error("select topic error");
		}
		
		return result;
	}
	
	private List<Reply> getReplyList(int topicId)
	{
		String sql = "SELECT * FROM `reply` where `topicId` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(topicId);
		
		List<Reply> result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql, values);
			result = new ArrayList<Reply>();
			while(res.next())
			{
				Reply item = new Reply();
				item.setUserId(res.getInt("userId"));
				item.setReplyTime(res.getString("dateTime"));
				item.setMessage(res.getString("message"));
				
				result.add(item);
			}
		}
		catch (Exception e) {
			logger.error("select replyList error");
		}
		
		return result;
	}
	
	public boolean deleteTopic(String topicId) throws Exception
	{
		String sql = "DELETE FROM TABLE `topic` WHERE `id` = ?;";
		List<Object> values = new ArrayList<Object>();
		values.add(topicId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
}
