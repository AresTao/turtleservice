package com.bupt.turtleservice.constants;

public class ServletConstants {
	public static final String URL_VIEW = "view";
	public static final String URL_ACCOUNT_ID = "accountId";
	public static final String URL_INTERNAL_ID = "internalId";
	public static final String URL_START = "start";
	public static final String URL_END = "end";
	public static final String URL_VIEW_BRIEF = "brief";
	public static final String URL_VIEW_DETAIL = "detail";
	public static final String URL_VIEW_ORIGIN = "origin";
	public static final String URL_VIEW_BUCKET = "bucket";
	public static final String URL_VIEW_TRAFFIC = "traffic";
	public static final String URL_VIEW_HIT = "hit";
	
	// Json data related
	public static final String HAS_ERROR = "hasError";
	public static final String ERROR_MESSAGE = "errorMsg";
	public static final String OPERATION = "operation";
	public static final String OPERATION_ADD_CNAME = "addCName";
	public static final String OPERATION_ENABLE_DISTRIBUTION = "enableDistribution";
	public static final String OPERATION_DISABLE_DISTRIBUTION = "disableDistribution";
	public static final String OPERATION_UPDATE_LOG_CONFIG = "updateLogConfig";
	public static final String OPERATION_CHECK_CNAME = "checkCName";
	public static final String OPERATION_ADD_CACHE_BEHAVIOR = "addCacheBehaviors";
	public static final String OPERATION_DELETE_CACHE_BEHAVIOR = "deleteCacheBehaviors";
	public static final String OPERATION_OVERWRITE_CACHE_BEHAVIOR = "overwriteCacheBehaviors";
	public static final String OPERATION_ADD_ACL_BEHAVIOR = "addAclBehaviors";
	public static final String OPERATION_MODIFY_ACL_BEHAVIOR = "modifyAclBehaviors";
	public static final String OPERATION_DELETE_ACL_BEHAVIOR = "deleteAclBehaviors";
	public static final String OPERATION_DELETE_DISTRIBUTION = "deleteDistribution";
	public static final String DATA = "data";
	public static final String VALIDATE = "validate";
	public static final String DETAIL = "detail";
	public static final String OPERATION_GET_ISP = "getFlowISPDistribute";
	public static final String OPERATION_GET_PROVINCE = "getFlowAndHitProvincesDistribute";
	public static final String OPERATION_GET_TOPVISITOR = "getTopVistorStat";
	public static final String OPERATION_GET_POPULARFILE = "getPopularFilesStat";
	public static final String OPERATION_GET_STATUSCODE = "getStatusCodeStat";
	public static final String OPERATION_GET_THRESHOLDALARM = "getThresholdSetting";
	public static final String OPERATION_CHANGE_THRESHOLDALARM = "changeThresholdSetting";
	public static final String FIELD_GET_ISP = "flowISPDistribute";
	public static final String FIELD_GET_PROVINCE_FLOW = "flowProvincesDistribute";
	public static final String FIELD_GET_PROVINCE_HIT = "hitProvincesDistribute";
	public static final String FIELD_GET_TOPVISITOR = "topVisitorStat";
	public static final String FIELD_GET_POPULARFILE = "popularFilesStat";
	public static final String FIELD_GET_STATUSCODE = "statusCodeStat";
	
	public static final String ACCOUNT_ID = "account_id";
	public static final String INTERNAL_ID = "internal_id";
	public static final String DOMAIN_NAME = "domain_name";
	public static final String CNAME = "cname";
	public static final String APPLICATION_TYPE = "application_type";
	public static final String SERVICE_TYPE = "service_type";
	public static final String ORIGIN = "origin";
	public static final String ORIGIN_HOST = "origin_host";
	public static final String STATUS = "status";
	public static final String ENABLED = "enabled";
	public static final String CREATE_TIME = "create_time";
	public static final String LAST_MODIFY = "last_modify";
	public static final String COMMENT = "comment";
	
	public static final String CACHE_BEHAVIORS = "cache_behaviors";
	public static final String DEFAULT_CACHE_BEHAVIOR = "cache_behavior";
	public static final String PATH_PATTERN = "path_pattern";
	public static final String PATH_PATTERN_DEFAULT = "/*";
	public static final String IGNORE_CACHE_CONTROL = "ignore_cache_control";
	public static final String CACHE_TIME = "cache_ttl";
	public static final String IGNORE_QUERY_STRING = "ignore_query_string";
	
	public static final String ACL_BEHAVIORS = "acl_behaviors";
	public static final String VALID_REFERERS = "valid_referers";
	public static final String INVALID_REFERERS = "invalid_referers";
	public static final String FORBIDDEN_IPS = "forbidden_ips";
	
	public static final String IS_LOGGED = "is_logged";
	public static final String LOG_CONFIG = "log_config";
	public static final String LOG_DEST_BUCKET = "log_dest_bucket";
	public static final String LOG_PREFIX = "log_prefix";
	public static final String SUPPLIER_LIST = "supplier_list";
	
	
	public static final String SCS_BUCKET_LIST = "scs";
	public static final String IMG_BUCKET_LIST = "img";
	public static final String FILES = "files";
	public static final String DIRS = "dirs";
	
	public static final String BANDWIDTH_REPORT = "bandwidth_report";
	public static final String MAX_BANDWIDTH = "max_bandwidth";
	public static final String BANDWIDTH_DATA = "bandwidth_data";
	public static final String BANDWIDTH = "bandwidth";
	public static final String HIT_REPORT = "hit_report";
	public static final String HIT_SUMMARY = "hit_summary";
	public static final String HIT_DATA = "hit_data";
	public static final String HIT = "hit";
	public static final String TIMESTAMP = "timestamp";
	
	// Return data related
	public static final int STATUS_CODE_BAD_REQUEST = 400;
	public static final int STATUS_CODE_WRONG_PASSWD = 403;
	public static final int STATUS_CODE_OK = 200;
}
