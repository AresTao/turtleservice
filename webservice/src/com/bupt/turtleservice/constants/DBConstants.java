package com.bupt.turtleservice.constants;

public class DBConstants {
	//public static final String DB_HOST = "112.126.82.238";
	public static final String DB_HOST = "127.0.0.1";
	//public static final String DB_HOST = "10.109.247.80";
	public static final String DB_PORT = "3306";
	public static final String DB_USER = "ztwu";
	public static final String DB_PASS = "123";
	public static final String DB_NAME = "turtleservice";
	public static final int RETRY_TIMES = 3;
	
	//filter related
	public static final int FORMAT_FILTER_CONDITION_INDEX = 0;
	public static final int FORMAT_FILTER_ARGS_INDEX = 1;
	public static final int RAW_FILTER_FIELD_INDEX = 0;
	public static final int RAW_FILTER_OPERATOR_INDEX = 1;
	public static final int RAW_FILTER_ARGS_START_INDEX = 2;
	
	//condition operator related
	public static final String CONDITION_OPERATOR_EQUAL = "=";
	public static final String CONDITION_OPERATOR_UNEQUAL = "<>";
	public static final String CONDITION_OPERATOR_LIKE = "LIKE";
	public static final String CONDITION_OPERATOR_IN = "IN";
	public static final String CONDITION_OPERATOR_NLT = ">=";
	public static final String CONDITION_OPERATOR_LT = "<";
	public static final String CONDITION_OPERATOR_NGT = "<=";
	
	public static final String[] ARRAY_SINGLE_CONDITION_OPERATOR = {CONDITION_OPERATOR_EQUAL, CONDITION_OPERATOR_LT,
		CONDITION_OPERATOR_UNEQUAL, CONDITION_OPERATOR_LIKE, CONDITION_OPERATOR_NLT, CONDITION_OPERATOR_NGT};
	public static final String[] ARRAY_MULTI_CONDITION_OPERATOR = {CONDITION_OPERATOR_IN};
	
	public static final String CONDITION_OPERATOR_TYPE_SINGLE = "single";
	public static final String CONDITION_OPERATOR_TYPE_MULTI = "multi";
	
	//field value related
	public static final String SERVICE_TYPE = "web";
	public static final String ACL_DEFAULT = "default";
	public static final String CACHE_DEFAULT = "default";
	public static final String LOGGING_FORMAT = "Original";
	public static final long SPLIT_TIME_HOUR = 6;
	
	private DBConstants () {}
}
