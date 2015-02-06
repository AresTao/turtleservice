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
import com.bupt.turtleservice.action.ZoneAction;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.model.User;
import com.sohu.azure.rest.BladeRequestMapping;

@BladeRequestMapping(path="/bbs/zone")
public class ZoneServlet extends HttpServlet{

	private static final long serialVersionUID = -1360916943261849593L;
	private static Logger logger = Logger.getLogger(ZoneServlet.class);
	
	/*
	 * get /bbs/zone
	 * get zone list
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {
			
			List<String> result = null;
			ZoneAction func = new ZoneAction();
			result = func.getZoneList();
			
			JSONObject jsonData = convertZoneList2JSON(result);
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

	private JSONObject convertZoneList2JSON(List<String> result) {
		JSONObject res = new JSONObject();
		JSONArray detail = new JSONArray();
		JSONObject item;
		
		
		for (String zone : result)
		{
			item = new JSONObject();
			item.accumulate("name", zone);
			
			detail.add(item);
		}
		
		res.accumulate("zoneList", detail);
		res.put(ServletConstants.HAS_ERROR, false);
		
		return res;
	}
}
