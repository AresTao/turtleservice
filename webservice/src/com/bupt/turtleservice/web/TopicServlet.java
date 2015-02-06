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

import com.bupt.turtleservice.action.PolicyAction;
import com.bupt.turtleservice.action.TopicAction;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.model.Policy;
import com.bupt.turtleservice.model.Reply;
import com.bupt.turtleservice.model.Topic;
import com.bupt.turtleservice.utils.StreamUtil;
import com.sohu.azure.rest.BladeRequestMapping;
@BladeRequestMapping(path="/bbs/topic")
public class TopicServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1664605870231208581L;
	private static Logger logger = Logger.getLogger(RegisterUserServlet.class);
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			TopicAction action = new TopicAction();
			action.createTopic(jsonData);
			
			logger.info("create topic");
			
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
	 * get /topic?classId=XXXXXXX
	 * get topic list
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			String classId = req.getParameter("classId");
			List<Topic> result = null;
			TopicAction func = new TopicAction();
			result = func.getTopic(classId);
			
			JSONObject jsonData = convertTopicList2JSON(result);
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

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			TopicAction action = new TopicAction();
			action.deleteTopic(jsonData);
			
			logger.info("delete topic");
			
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
			TopicAction action = new TopicAction();
			action.updateTopic(jsonData);
			
			logger.info("update topic");
			
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
	
	private JSONObject convertTopicList2JSON(List<Topic> result) {
		JSONObject res = new JSONObject();
		JSONArray detail = new JSONArray();
		JSONObject item;
		JSONArray replyList;
		JSONObject reply;
		for (Topic data : result)
		{
			item = new JSONObject();
			replyList = new JSONArray();
			item.accumulate("title", data.getTitle());
			item.accumulate("description", data.getDescription());
			item.accumulate("createTime", data.getCreateTime());
			
			for (Reply replyItem : data.getReplyList())
			{
				reply = new JSONObject();
				
				reply.accumulate("userId", replyItem.getUserId());
				reply.accumulate("replyTime", replyItem.getReplyTime());
				reply.accumulate("message", replyItem.getMessage());
				
				replyList.add(reply);
			}
			item.accumulate("replyList", replyList);
			
			detail.add(item);
		}
		
		res.accumulate("topicList", detail);
		res.put(ServletConstants.HAS_ERROR, false);
		
		return res;
	}
}
