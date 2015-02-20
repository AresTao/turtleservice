package com.bupt.turtleservice.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.action.AdminAction;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.utils.StreamUtil;

public class AdminServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8198634484025166101L;
	private static Logger logger = Logger.getLogger(AdminServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			
			AdminAction action = new AdminAction();
			boolean status = action.registerAdmin(jsonData);
			logger.info("register admin");
			
			if (status)
			{
				JSONObject result = new JSONObject();
				result.put(ServletConstants.HAS_ERROR, false);
				res.setStatus(ServletConstants.STATUS_CODE_OK);
				output.println(result.toString());
			} else {
				JSONObject jsonResult = new JSONObject();
				jsonResult.put(ServletConstants.HAS_ERROR, true);
				jsonResult.put(ServletConstants.ERROR_MESSAGE, "Wrong name or passwd");
				
				res.setStatus(ServletConstants.STATUS_CODE_WRONG_PASSWD);
				output.println(jsonResult.toString());
			}
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
