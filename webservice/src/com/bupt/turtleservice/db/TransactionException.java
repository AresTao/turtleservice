package com.bupt.turtleservice.db;

/**
 * Fatal level exception. If occur, stop current job.
 * 
 * @author ztwu
 *
 */
public class TransactionException extends Exception {
	private static final long serialVersionUID = 1L;
	public TransactionException(String strMessage, Exception e)
	{
		super(strMessage, e);
	}
	public TransactionException(String strMessage)
	{
		super(strMessage);
	}
	public TransactionException()
	{
		
	}
}
