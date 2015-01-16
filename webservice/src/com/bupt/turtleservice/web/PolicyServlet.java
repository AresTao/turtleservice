package com.bupt.turtleservice.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sohu.azure.rest.BladeRequestMapping;
@BladeRequestMapping(path="/policy")
public class PolicyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2473524355645218009L;
	private static Logger logger = Logger.getLogger(PolicyServlet.class);
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			logger.info("put method");		
			
			output.println("test");
		} catch(Exception e) {
			
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
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		
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
