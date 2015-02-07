package com.bupt.turtleservice.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.action.UserAction;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.model.Policy;
import com.bupt.turtleservice.model.User;
import com.bupt.turtleservice.utils.StreamUtil;
import com.sohu.azure.rest.BladeRequestMapping;

@BladeRequestMapping(path="/user")
public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360916943261949593L;
	private static Logger logger = Logger.getLogger(RegisterUserServlet.class);
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			UserAction action = new UserAction();
			action.createUser(jsonData);
			
			logger.info("create user");
			
			JSONObject result = new JSONObject();
			result.put(ServletConstants.HAS_ERROR, false);
			res.setStatus(ServletConstants.STATUS_CODE_OK);
			output.println(result.toString());
			
		} catch(Exception e) {
			JSONObject jsonResult = new JSONObject();
			jsonResult.put(ServletConstants.HAS_ERROR, true);
			jsonResult.put(ServletConstants.ERROR_MESSAGE, e.getMessage());
			
			res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
			output.println(jsonResult.toString());
		} finally {
			output.close();
		}
	}
	
	/*
	 * get /user?account=XXXXXXX
	 * get user
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			String key = req.getParameter("account");
			User result = null;
			UserAction func = new UserAction();
			result = func.getUser(key);
			
			JSONObject jsonData = convertUser2JSON(result);
			res.setStatus(ServletConstants.STATUS_CODE_OK);
			output.println(jsonData.toString());
		} catch(Exception e) {
			JSONObject jsonResult = new JSONObject();
			jsonResult.put(ServletConstants.HAS_ERROR, true);
			jsonResult.put(ServletConstants.ERROR_MESSAGE, e.getMessage());
			
			res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
			output.println(jsonResult.toString());
		} finally {
			output.close();
		}
	}
	
	private JSONObject convertUser2JSON(User data) {
		JSONObject res = new JSONObject();
		JSONArray detail = new JSONArray();
		JSONObject item;
		
		item = new JSONObject();
			
		item.accumulate("name", data.getName());		
		
		res.accumulate("user", item);
		res.put(ServletConstants.HAS_ERROR, false);
		
		return res;
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			UserAction action = new UserAction();
			action.deleteUser(jsonData);
			
			logger.info("delete user");
			
			JSONObject result = new JSONObject();
			result.put(ServletConstants.HAS_ERROR, false);
			res.setStatus(ServletConstants.STATUS_CODE_OK);
			output.println(result.toString());
			
		} catch(Exception e) {
			JSONObject jsonResult = new JSONObject();
			jsonResult.put(ServletConstants.HAS_ERROR, true);
			jsonResult.put(ServletConstants.ERROR_MESSAGE, e.getMessage());
			
			res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
			output.println(jsonResult.toString());
		} finally {
			output.close();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			UserAction action = new UserAction();
			action.updateUser(jsonData);
			
			logger.info("update userinfo");
			
			JSONObject result = new JSONObject();
			result.put(ServletConstants.HAS_ERROR, false);
			res.setStatus(ServletConstants.STATUS_CODE_OK);
			output.println(result.toString());
			
		} catch(Exception e) {
			JSONObject jsonResult = new JSONObject();
			jsonResult.put(ServletConstants.HAS_ERROR, true);
			jsonResult.put(ServletConstants.ERROR_MESSAGE, e.getMessage());
			
			res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
			output.println(jsonResult.toString());
		} finally {
			output.close();
		}
	}
}
