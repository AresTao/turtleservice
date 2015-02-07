package com.bupt.turtleservice.web;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.constants.MailConstants;
import com.bupt.turtleservice.constants.ServletConstants;
import com.bupt.turtleservice.task.EmailSender;
import com.bupt.turtleservice.utils.StreamUtil;
import com.sohu.azure.rest.BladeRequestMapping;
@BladeRequestMapping(path="/mail")
public class MailServlet extends HttpServlet{

	private static final long serialVersionUID = -1360916943261949593L;
	private static Logger logger = Logger.getLogger(MailServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ServletOutputStream output = res.getOutputStream();
		try {		
			JSONObject jsonData = StreamUtil.getRequestJsonObject(req);
			String email = jsonData.getString("email");
			if (! isEmail(email))
			{
				JSONObject jsonResult = new JSONObject();
				jsonResult.put(ServletConstants.HAS_ERROR, true);
				jsonResult.put(ServletConstants.ERROR_MESSAGE, "illegal email address");
				
				res.setStatus(ServletConstants.STATUS_CODE_BAD_REQUEST);
				output.println(jsonResult.toString());
			} else
			{
				
				EmailSender sender = new EmailSender();
			
				logger.info("send file to "+ email);
			
				sender.setAddress(MailConstants.MAIL_FULLACCOUNT,email,MailConstants.MAIL_TOPIC);
		     //设置要发送附件的位置和标题
				sender.setAffix(MailConstants.FILE_ROOT,MailConstants.FILE_NAME);
		     //设置smtp服务器以及邮箱的帐号和密码
				sender.send(MailConstants.MAIL_HOST,MailConstants.MAIL_ACCOUNT,MailConstants.MAIL_PASSWD);
			
				JSONObject result = new JSONObject();
				result.put(ServletConstants.HAS_ERROR, false);
				res.setStatus(ServletConstants.STATUS_CODE_OK);
				output.println(result.toString());
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
	private boolean isEmail(String email){  
        if (null==email || "".equals(email)) return false;    
//      Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
        Matcher m = p.matcher(email);  
        return m.matches();  
    }
}
