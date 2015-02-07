package com.bupt.turtleservice.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.action.UserAction;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.utils.StreamUtil;
import com.sohu.azure.rest.BladeRequestMapping;

@BladeRequestMapping(path="/registeruser")
public class RegisterUserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8098634484025166101L;
	private static Logger logger = Logger.getLogger(RegisterUserServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			String method = jsonData.getString("method");
			UserAction action = new UserAction();
			if (method.equals("register"))
			{
				action.registerUser(jsonData);
				logger.info("register user");
			} else
			{
				action.unRegisterUser(jsonData);
				logger.info("unRegister user");
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
}
