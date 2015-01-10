package com.bupt.turtleservice.web;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.utils.StreamUtil;
import com.sohu.azure.rest.BladeRequestMapping;

@BladeRequestMapping(path="/test")
public class CreateTopicServlet extends HttpServlet{
	private static final long serialVersionUID = -7508995043213585214L;
	private static Logger logger = Logger.getLogger(CreateTopicServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
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
	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			PurgeEngine engine = new PurgeEngine();
			String internalId = req.getParameter(ServletConstants.INTERNAL_ID);
			String range = req.getParameter("query_range");
			
			String[] twoNum = null;
			List<PurgeData> result = null;
			if (!StringUtil.isBlank(range))
			{
				twoNum = range.split(":");
				int start = Integer.valueOf(twoNum[0]);
				int end = Integer.valueOf(twoNum[1]);
				result = engine.queryPurge(internalId, start, end);
			} else
			{
				result = engine.queryPurge(internalId);
			}
			
			JSONObject retData = convertPurgeData2JSON(result);
			output.println(retData.toString());
			
		} catch(Exception e) {
			JSONObject jsonResult = new JSONObject();
			jsonResult.put(ServletConstants.HAS_ERROR, true);
			jsonResult.put(ServletConstants.ERROR_MESSAGE, e.getMessage());
			
			res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
			output.println(jsonResult.toString());
		} finally {
			output.close();
		}
	}*/
	/*private JSONObject convertPurgeData2JSON(List<PurgeData> result) {
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
