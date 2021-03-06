package com.bupt.turtleservice.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.model.User;

public class UserDBFunction {

	private Logger logger = Logger.getLogger(UserDBFunction.class);
	private TransactionOperation transactionOperation = null;
	
	public UserDBFunction(TransactionOperation transactionOperation) {
		this.transactionOperation = transactionOperation;
	}
	
	public boolean createUser( String account, String passwd, String name) throws Exception//,String photo, String birthday, String school, String major, String homeTown, boolean isSingle) throws Exception
	{
		String sql = "INSERT INTO `user` (`name`, `account`, `passwd`) VALUES (?,?,?); ";
				//, `photo`, `birthday`, `school`, `major`, `homeTown`,`isSingle`) VALUES (?,?,?,?,?,?,?,?,?);";
		List<Object> values = new ArrayList<Object>();
		
		values.add(name);
		values.add(account);
		values.add(passwd);
		//values.add(photo);
		//values.add(birthday);values.add(school);
		//values.add(major);
		//values.add(homeTown);
		//values.add(isSingle);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
	    
		return true;
	}
	
	public boolean updateUser(String account, String name) throws Exception
	{
		String sql = "update `user` set `name` = ? where account = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(name);
		values.add(account);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	public User getUser(String account)
	{
		String sql = "SELECT * FROM `user` where `account` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(account);
		
		User result = null;
		
		try{
			ResultSet res = this.transactionOperation.exec(sql, values);
			while(res.next())
			{
				result = new User();
				result.setName(res.getString("name"));
				//result.setBirthday(res.getString("birthday"));
				//result.setPhoto(res.getString("header"));
				//result.setSingle(res.getBoolean("isSingle"));
				//result.setContent(res.getString("content"));
				//result.setCreateTime(res.getString("create_time"));
			}
		}
		catch (Exception e) {
			logger.error("select user error");
		}
		
		return result;
	}
	
	public boolean deleteUser(String account) throws Exception
	{
		String sql = "DELETE FROM TABLE `user` WHERE `account` = ?;";
		List<Object> values = new ArrayList<Object>();
		values.add(account);
		
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	public boolean registerUser(String account, String passwd) throws Exception
	{
		String sql = "update `user` set `isOnline` = 1  where `account` = ? and `passwd` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(account);
		values.add(passwd);
			
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
	
	public boolean unRegisterUser(String account, String passwd) throws Exception
	{
		String sql = "update `user` set `isOnline` = 0  where `account` = ? and `passwd` = ? ;";
		List<Object> values = new ArrayList<Object>();
		values.add(account);
		values.add(passwd);
			
		this.transactionOperation.beginTransaction();
		ResultSet res = this.transactionOperation.exec(sql, values);
		this.transactionOperation.commitTransaction();
		return true;
	}
}
