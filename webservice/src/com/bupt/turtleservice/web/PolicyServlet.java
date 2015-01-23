package com.bupt.turtleservice.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.action.PolicyAction;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.utils.StreamUtil;
import com.sohu.azure.rest.BladeRequestMapping;
@BladeRequestMapping(path="/policy")
public class PolicyServlet extends HttpServlet{

	private static final long serialVersionUID = -2473524355645218009L;
	private static Logger logger = Logger.getLogger(PolicyServlet.class);
	
	/*
	 * put /policy
	 * 
	 * create policy
	 * */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			PolicyAction action = new PolicyAction();
			action.createPolicy(jsonData);
			
			logger.info("create policy");
			
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
	 * get /purge?account_id=XXXXXXX&internal_id=XXXXXXX
	 * get purge list
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			
		} catch(Exception e) {
			
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
			PolicyAction action = new PolicyAction();
			action.deletePolicy(jsonData);
			
			logger.info("delete policy");
			
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
			PolicyAction action = new PolicyAction();
			action.createPolicy(jsonData);
			
			logger.info("update policy");
			
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
	private JSONObject convertPurgeData2JSON(List<PurgeData> result) {
		JSONObject res = new JSONObject();
		JSONArray detail = new JSONArray();
		JSONObject item;
		
		for (PurgeData data : result)
		{
			item = new JSONObject();
			
			item.accumulate("request_id", data.getPurgeId());
			item.accumulate("path", data.getPath());
			item.accumulate("status", data.getStatus());
			item.accumulate("time", DateUtil.getDatetime(data.getDate()));
			
			detail.add(item);
		}
		
		res.accumulate("purge_items", detail);
		return res;
	}*/
}
