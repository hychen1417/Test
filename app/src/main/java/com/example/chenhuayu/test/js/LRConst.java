//package com.example.chenhuayu.test.js;
//
///**
// * Created by chenhuayu on 2017/8/22.
// */
//
//public class LRConst {
//    public static final int SDK_VERSION;
//    public static final String SDK_VERSION_LOG = "3.2.0.4-SNAPSHOT";
//    public static final String V2_LOG_REPORT_URL = "https://api.neixin.cn/events/api/sdk/v1/xm";
//    public static final String TEST_V2_LOG_REPORT_URL = "http://192.168.22.141:8020/events/api/sdk/v1/xm";
//    public static final String V2_MSG_PARSE_ERROR_REPORT_URL = "https://api.neixin.cn/events/api/error/v1/packet";
//
//    public LRConst() {
//    }
//
//    static {
//        SDK_VERSION = BuildConfig.DX_IM_SDK_VERSION_CODE.intValue();
//    }
//
//    public static class RhinoUploadType {
//        public static final int TYPE_IMAGE = 1;
//        public static final int TYPE_VIDEO = 2;
//        public static final int TYPE_AUDIO = 3;
//        public static final int TYPE_FILE = 4;
//        public static final int TYPE_UNKNOWN = 100;
//
//        public RhinoUploadType() {
//        }
//    }
//
//    public class RescodeForLog {
//        public static final int BACKGROUND = 10000;
//        public static final int NET_CHANGE = 10001;
//        public static final int NO_IP = 10002;
//        public static final int EXCHANGE_TIME_OUT = 20000;
//        public static final int LOGIN_TIME_OUT = 20001;
//        public static final int EXCHANGE_ERROR = 20002;
//        public static final int SOCKET_TIMEOUT = 20010;
//        public static final int SOCKET_DISCONNECT = 20011;
//
//        public RescodeForLog() {
//        }
//    }
//
//    public class ReportError {
//        public static final int UNNOWN_ERROR = 0;
//        public static final int FILE_CACHE_NOT_READY = 1;
//        public static final int LOG_FILE_ERROR = 2;
//        public static final int INIT_ERROR = 3;
//
//        public ReportError() {
//        }
//    }
//
//    public class ReportAttributeConst {
//        public static final String NET = "net";
//        public static final String APN = "apn";
//        public static final String HOST = "host";
//        public static final String PORT = "port";
//        public static final String PASSPORT = "passpt";
//        public static final String UID = "uid";
//        public static final String CID = "cid";
//        public static final String COUNT = "count";
//        public static final String OFFSET = "offset";
//        public static final String LIMIT = "limit";
//        public static final String NEXT = "next";
//        public static final String MESSAGE_ID = "mid";
//        public static final String CLIENT_MESSAGE_ID = "cmid";
//        public static final String SESSION_ID = "sessionId";
//        public static final String PACKET = "packet";
//        public static final String REASON = "reason";
//        public static final String TIME = "time";
//        public static final String MSG_NUM = "msg_num";
//        public static final String SEARCH_COUNT = "search_count";
//        public static final String RETRIES = "retries";
//        public static final String CHAT = "chat";
//        public static final String IDS = "ids";
//        public static final String SEQID = "seqId";
//        public static final String CHAT_ID = "chatid";
//        public static final String FUNC = "func";
//        public static final String MSG = "msg";
//        public static final String TYPE = "type";
//        public static final String APP_STATE = "appstate";
//        public static final String NAME = "name";
//        public static final String MD5_CALCULATE_TIME = "calcMd5";
//        public static final String RHINO_CHECK_EXIST_TIME = "checkExist";
//        public static final String RHINO_START_SIZE = "startSize";
//        public static final String RHINO_DIVIDE_BLOCK = "divideBlock";
//        public static final String RHINO_GET_URL_TIME = "getUrl";
//        public static final String RHINO_REGISTER_BLOCK_TIME = "registerBlock";
//        public static final String RHINO_CREATE_PATH_TIME = "createPath";
//        public static final String RHINO_BIZ_RES = "result";
//        public static final String RHINO_HTTP_CODE = "code";
//        public static final String RHINO_COPY_URL = "url";
//        public static final String RHINO_COPY_FORWARD_TYPE = "forwardType";
//        public static final String RHINO_FILE_SIZE = "size";
//        public static final String ACTIVE_INIT = "init";
//        public static final String ACTIVE_CONNECT = "connect";
//        public static final String ACTIVE_MESSAGE = "mesage";
//        public static final String RESULT = "result";
//        public static final String MESSAGE = "message";
//        public static final String DETECT = "detect";
//        public static final String VALUE1 = "value1";
//        public static final String VALUE2 = "value2";
//        public static final String VALUE3 = "value3";
//        public static final String VALUE4 = "value4";
//        public static final String VALUE5 = "value5";
//        public static final String VALUE6 = "value6";
//        public static final String VALUE7 = "value7";
//        public static final String VALUE8 = "value8";
//        public static final String VALUE9 = "value9";
//        public static final String VALUE10 = "value10";
//        public static final String VALUE11 = "value11";
//        public static final String VALUE12 = "value12";
//        public static final String VALUE13 = "value13";
//        public static final String VALUE14 = "value14";
//        public static final String VALUE15 = "value15";
//        public static final String VALUE16 = "value16";
//        public static final String VALUE17 = "value17";
//        public static final String VALUE18 = "value18";
//        public static final String VALUE19 = "value19";
//        public static final String VALUE20 = "value20";
//
//        public ReportAttributeConst() {
//        }
//    }
//
//    public class ReportInConst {
//        public static final String NET_ENABLE = "ne";
//        public static final String NET_SWITCH = "ns";
//        public static final String NET_DISABLE = "nd";
//        public static final String LVS_START = "lst";
//        public static final String LVS_SUCCESS = "lss";
//        public static final String LVS_ERROR = "ler";
//        public static final String SOCKET_START = "sst";
//        public static final String SOCKET_SUCCESS = "sss";
//        public static final String SOCKET_ERROR = "ser";
//        public static final String LOGIN_START = "lgst";
//        public static final String LOGIN_SUCCESS = "lgss";
//        public static final String LOGIN_ERROR = "lger";
//        public static final String DNS_PARSE_SUCCESS = "dnspss";
//        public static final String DNS_PARSE_ERROR = "dnsper";
//        public static final String LOGIN_SESSION = "lgsess";
//        public static final String LOGIN_EXCHANGE = "lgexchange";
//        public static final String CHAT_START = "chatst";
//        public static final String CHAT_SUCCESS = "chatss";
//        public static final String CHAT_ERROR = "chater";
//        public static final String IM_OFFLINE_START = "imofst";
//        public static final String IM_OFFLINE_SUCCESS = "imofss";
//        public static final String IM_OFFLINE_ERROR = "imofer";
//        public static final String GROUP_OFFLINE_START = "gpofst";
//        public static final String GROUP_OFFLINE_SUCCESS = "gpofss";
//        public static final String GROUP_OFFLINE_ERROR = "gpofer";
//        public static final String GROUP_HISTORY_START = "ghhsst";
//        public static final String GROUP_HISTORY_SUCCESS = "gphsss";
//        public static final String GROUP_HISTORY_ERROR = "gphser";
//        public static final String IM_HISTORY_START = "imhsst";
//        public static final String IM_HISTORY_SUCCESS = "imhsss";
//        public static final String IM_HISTORY_ERROR = "imhser";
//        public static final String IM_SYNC_START = "imscst";
//        public static final String IM_SYNC_SUCCESS = "imscss";
//        public static final String IM_SYNC_ERROR = "imscer";
//        public static final String IM_JOIN_CHAT = "ijc";
//        public static final String IM_LOCAL_MSG_GET = "ilmg";
//        public static final String IM_DB_DATA_READY = "iddr";
//        public static final String IM_LOCAL_SEARCH_MSG = "ilsm";
//        public static final String IM_LOCAL_MSG_COUNT = "ilmc";
//        public static final String IM_LOCAL_GROUP_MSG_COUNT = "ilgc";
//        public static final String SEQID_MISSING = "seqmis";
//        public static final String SEQID_DUPLICATE = "seqdup";
//        public static final String PUB_OFFLINE_START = "pbofst";
//        public static final String PUB_OFFLINE_SUCCESS = "pbofss";
//        public static final String PUB_OFFLINE_ERROR = "pbofer";
//        public static final String PUB_HISTORY_START = "pbhsst";
//        public static final String PUB_HISTORY_SUCCESS = "pbhsss";
//        public static final String PUB_HISTORY_ERROR = "pbhser";
//        public static final String PUB_SYNC_START = "pbscst";
//        public static final String PUB_SYNC_SUCCESS = "pbscss";
//        public static final String PUB_SYNC_ERROR = "pbscer";
//        public static final String PUB_JOIN_CHAT = "pjc";
//        public static final String PUB_LOCAL_MSG_GET = "plmg";
//        public static final String PUB_DB_DATA_READY = "pddr";
//        public static final String PUB_LOCAL_SEARCH_MSG = "plsm";
//        public static final String PUB_LOCAL_MSG_COUNT = "plmc";
//        public static final String MSG_PARSE_ERROR = "msgerr";
//        public static final String NO_NICK_ERROR = "nonickerr";
//        public static final String DB_ERROR = "dberr";
//        public static final String SEND_MSG = "sendmsg";
//        public static final String APP_LOGIN = "alh";
//        public static final String APP_START_UP = "asu";
//        public static final String SEND_ERRER = "sender";
//        public static final String UPLOAD_ERROR = "uplder";
//        public static final String UPLOAD_SUCESS = "upldsuc";
//        public static final String RHINO_COPY_ERROR = "rhcperr";
//        public static final String ELEPHANT_UPLOAD = "imupload";
//        public static final String ELEPHANT_DOWNLOAD = "imdownload";
//        public static final String MBOX_UPLOAD = "mboxupload";
//        public static final String MBOX_DOWNLOAD = "mboxdownload";
//        public static final String MBOX_CHANGE_PERSONAL_TYPE = "ui_pfilechange";
//        public static final String MBOX_CHANGE_CHAT_FILE_TYPE = "ui_cfilechange";
//        public static final String MBOX_OPEN_CHAT_FILE = "ui_cfileopen";
//        public static final String VALUE1 = "value1";
//        public static final String VALUE2 = "value2";
//        public static final String VALUE3 = "value3";
//        public static final String VALUE4 = "value4";
//        public static final String VALUE5 = "value5";
//        public static final String VALUE6 = "value6";
//        public static final String CID = "cid";
//        public static final String SDK_ACTIVE = "active";
//        public static final String SDK_LVS = "lvs";
//        public static final String SDK_CONN_IPs = "connect_all_ips";
//        public static final String SDK_CONN_IP = "connect_ip";
//        public static final String SDK_HEARTBEAT = "heartbeat";
//        public static final String LVS_DNS = "lvs_dns";
//        public static final String SDK_NET_CHANGE = "net_change";
//        public static final String SDK_NET_LOST = "net_lost";
//        public static final String IP_SELECTOR = "ip_selector";
//        public static final String NET_TRAFFIC = "net_traffic";
//
//        public ReportInConst() {
//        }
//    }
//
//    public class ReportOutConst {
//        public static final String DEVICE_ID = "did";
//        public static final String DEVICE_TOKEN = "dtk";
//        public static final String SDK_VERSION = "sv";
//        public static final String APP_VERSION = "av";
//        public static final String APP_NAME = "an";
//        public static final String PLATFORM_VERSION = "pv";
//        public static final String DEVICE_MODEL = "dm";
//        public static final String MANUFACTURER = "manuf";
//        public static final String PLATFORM_TYPE = "pt";
//        public static final String APPID_ID = "ai";
//        public static final String EVENTS = "events";
//
//        public ReportOutConst() {
//        }
//    }
//}
