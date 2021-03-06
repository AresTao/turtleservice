package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.model.Reply;
import com.bupt.turtleservice.model.Topic;
import com.bupt.turtleservice.utils.DateUtil;

public class TopicDBFunction {

	private Logger logger = Logger.getLogger(TopicDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public TopicDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean createTopic(int classId, String title, int userId, String description) throws Exception
	{
		String sql = "INSERT INTO `topic` (`classId`, `title`, `userId`, `description`, `dateTime`) VALUES (?,?,?,?,?);";
		List<Object> values = new ArrayList<Object>();
		values.add(classId);
		values.add(title);
		values.add(userId);
		values.add(description);
		values.add(DateUtil.getDatetime(new Date()));
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
	    
		return true;
	}
	
	public boolean updateTopic(String title, String description, int topicId) throws Exception
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
	
	public List<Topic> getTopicListByClassIdAndKey(int classId, String key)
	{
		String sql = "select * from topic where `classId` = "+classId+" AND `title` LIKE '%"+ key +"%';"; 
		
		List<Topic> result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql);
			result = new ArrayList<Topic>();
			while(res.next())
			{
				Topic item = new Topic();
				item.setTopicId(res.getInt("id"));
				item.setTitle(res.getString("title"));
				item.setCreateTime(res.getString("dateTime"));
				item.setDescription(res.getString("description"));
				
				result.add(item);
			}
		}
		catch (Exception e) {
			logger.error("select topic error");
		}
		
		return result;
	}
	
	public List<Topic> getTopicListByClassId(int classId)
	{
		//String sql = "select * from topic left join class on topic.classId = (select id from class where name = ?);";
		String sql = "select * from topic where `classId` = ?";
		List<Object> values = new ArrayList<Object>();
		values.add(classId);
		
		List<Topic> result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql, values);
			result = new ArrayList<Topic>();
			while(res.next())
			{
				Topic item = new Topic();
				item.setTopicId(res.getInt("id"));
				item.setTitle(res.getString("title"));
				item.setCreateTime(res.getString("dateTime"));
				item.setDescription(res.getString("description"));
				
				result.add(item);
			}
		}
		catch (Exception e) {
			logger.error("select topic error");
		}
		
		return result;
	}
	
	public Topic getTopic(int topicId)
	{
		String sql = "select * from `topic` where `id` = ?;";
		List<Object> values = new ArrayList<Object>();
		values.add(topicId);
		
		Topic result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql, values);
			result = new Topic();
			while(res.next())
			{
				result.setTopicId(res.getInt("id"));
				result.setTitle(res.getString("title"));
				result.setCreateTime(res.getString("dateTime"));
				result.setDescription(res.getString("description"));
				
				List<Reply> replyList = getReplyList(result.getTopicId());
				result.setReplyList(replyList);
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
	
	public boolean replyTopic(int userId, int topicId, String message, String respTo, int respUserId) throws Exception
	{
		String sql = "INSERT INTO `reply` (`userId`,`topicId`,`dateTime`,`message`,`respTo`, `respUserId`) VALUES (?,?,?,?,?,?);";
		List<Object> values = new ArrayList<Object>();
		values.add(userId);
		values.add(topicId);
		values.add(DateUtil.getDatetime(new Date()));
		values.add(message);
		values.add(respTo);
		values.add(respUserId);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	
	public boolean deleteTopic(int topicId) throws Exception
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
