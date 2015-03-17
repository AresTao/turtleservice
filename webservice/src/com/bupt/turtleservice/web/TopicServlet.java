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
import com.bupt.turtleservice.utils.StringUtil;
import com.sohu.azure.rest.BladeRequestMapping;
@BladeRequestMapping(path="/bbs/topic")
public class TopicServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1664605870231208581L;
	private static Logger logger = Logger.getLogger(TopicServlet.class);
	
	/*
	 * get /topic?key=XXXXXXX&classId=XXXX
	 * get topic list
	 * 
	 * get /topic?topicId=XXXXX
	 * get topic
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			String classId = req.getParameter("classId");
			logger.info("get test");
			String key = req.getParameter("key");
			TopicAction func = new TopicAction();
			JSONObject jsonData;
			if (! StringUtil.isBlank(key) && ! StringUtil.isBlank(classId))
			{
				List<Topic> result = null;
				
				result = func.getTopicList(key);
			
				jsonData = convertTopicList2JSON(result);
			} else
			{
				int topicId = Integer.parseInt(req.getParameter("topicId"));
				Topic result = func.getTopic(topicId);
				jsonData = convertTopic2JSON(result);
			}
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			int classId = Integer.parseInt(req.getParameter("classId"));
			
			String title = req.getParameter("title");
			int userId = Integer.parseInt(req.getParameter("userId"));
			String description = req.getParameter("description");
			String method = req.getParameter("method");
			
			TopicAction action = new TopicAction();
			if (StringUtil.isBlank(method))
			{
				action.createTopic(classId, title, userId, description);
				logger.info("create topic "+title);
			} else {
				int topicId = Integer.parseInt(req.getParameter("topicId"));
				if (StringUtil.isBlank(req.getParameter("topicId")))
				{
					JSONObject jsonResult = new JSONObject();
					jsonResult.put(ServletConstants.HAS_ERROR, true);
					jsonResult.put(ServletConstants.ERROR_MESSAGE, "no topicId param");
					
					res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
					output.println(jsonResult.toString());
				}
				if (method.equals("reply"))
				{
					String message = req.getParameter("message");
					String respTo = req.getParameter("respTo");
					if (StringUtil.isBlank(respTo))
					{
						respTo = "";
					}
					int respUserId;
					if (! StringUtil.isBlank(req.getParameter("respUserId")))
					{
						respUserId = Integer.parseInt(req.getParameter("respUserId"));
					} else
					{
						respUserId = -1;
					}
					
					if (StringUtil.isBlank(message))
					{
						JSONObject jsonResult = new JSONObject();
						jsonResult.put(ServletConstants.HAS_ERROR, true);
						jsonResult.put(ServletConstants.ERROR_MESSAGE, "no message param");
						
						res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
						output.println(jsonResult.toString());
					}
					action.replyTopic(userId, topicId, message, respTo, respUserId);
					logger.info("reply topic ");
				}
				else if (method.equals("update"))
				{
					action.updateTopic(title, description, topicId);
					logger.info("update topic");
				} else if (method.equals("delete"))
				{
					action.deleteTopic(topicId);
					logger.info("delete topic");
				} else
				{
					JSONObject jsonResult = new JSONObject();
					jsonResult.put(ServletConstants.HAS_ERROR, true);
					jsonResult.put(ServletConstants.ERROR_MESSAGE, "unsupport method param");
						
					res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
					output.println(jsonResult.toString());
					logger.error("unsupport method");
				}
			}
			
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
	
	private JSONObject convertTopic2JSON(Topic result) {
		JSONObject res = new JSONObject();
		JSONObject item = new JSONObject();
		JSONArray replyList = new JSONArray();
		JSONObject reply;
		
		item.accumulate("title", result.getTitle());
		item.accumulate("description", result.getDescription());
		item.accumulate("createTime", result.getCreateTime());
			
		for (Reply replyItem : result.getReplyList())
		{
			reply = new JSONObject();
				
			reply.accumulate("userId", replyItem.getUserId());
			reply.accumulate("replyTime", replyItem.getReplyTime());
			reply.accumulate("message", replyItem.getMessage());
				
			replyList.add(reply);
		}
		item.accumulate("replyList", replyList);
		
		res.accumulate("topic", item);
		res.put(ServletConstants.HAS_ERROR, false);
		
		return res;
	}

	private JSONObject convertTopicList2JSON(List<Topic> result) {
		JSONObject res = new JSONObject();
		JSONArray detail = new JSONArray();
		JSONObject item;
		
		for (Topic data : result)
		{
			item = new JSONObject();
			item.accumulate("title", data.getTitle());
			item.accumulate("description", data.getDescription());
			item.accumulate("createTime", data.getCreateTime());
			
			detail.add(item);
		}
		
		res.accumulate("topicList", detail);
		res.put(ServletConstants.HAS_ERROR, false);
		
		return res;
	}
}
