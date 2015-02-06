package com.bupt.turtleservice.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.bupt.turtleservice.db.TransactionException;
import com.bupt.turtleservice.db.TransactionOperation;
import com.bupt.turtleservice.db.ZoneDBFunction;

public class ZoneAction {

	private TransactionOperation transactionOperation = null;
	private Logger logger = Logger.getLogger(ZoneAction.class);
	
	public ZoneAction() throws TransactionException {
		this.transactionOperation = new TransactionOperation();
	}
	
	public List<String> getZoneList()
	{
		ZoneDBFunction func = new ZoneDBFunction(this.transactionOperation);
		List<String> res = func.getZoneList();
		return res;
	}
}
