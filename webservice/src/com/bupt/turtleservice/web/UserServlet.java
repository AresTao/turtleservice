package com.bupt.turtleservice.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
}
