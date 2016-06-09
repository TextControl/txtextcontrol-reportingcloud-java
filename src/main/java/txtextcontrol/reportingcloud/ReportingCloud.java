package txtextcontrol.reportingcloud;

import java.util.HashMap;
import com.google.gson.Gson;

/**
 * Created by thorsten on 09.06.2016.
 */
public class ReportingCloud {

    private static final String DEFAULT_BASE_URI = "http://api.reporting.cloud";
    private static final String DEFAULT_VERSION = "v1";
    private static final int DEFAULT_TIMEOUT = 10;
    private static final String USER_AGENT = "Mozilla/5.0";

    private String _username;
    private String _password;
    private String _baseUri;
    private String _version;

    /**
     *
     * @param username
     * @param password
     * @param baseUri
     */
    public ReportingCloud(String username, String password, String baseUri) {
        _username = username;
        _password = password;
        _baseUri = baseUri;
        _version = DEFAULT_VERSION;
    }

    /**
     *
     * @param username
     * @param password
     */
    public  ReportingCloud(String username, String password) {
        this(username, password, DEFAULT_BASE_URI);
    }

    public int getTemplateCount(String templateName) {
        // ToDo: implement
        return 0;
    }

    private static String get(String endpoint, HashMap<String, Object> params) {
        // ToDo: implement
        return "";
    }

    private static String queryStringFromHashMap(HashMap<String, Object> hasMap) {
        // ToDo: implement
        Gson g = new Gson();

        return "";
    }
}
