package txtextcontrol.reportingcloud;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

/**
 * Created by thorsten on 09.06.2016.
 */
public class ReportingCloud {

    private static final String DEFAULT_BASE_URI = "http://api.reporting.cloud";
    private static final String DEFAULT_VERSION = "v1";
    private static final int DEFAULT_TIMEOUT = 10;
    private static final String USER_AGENT = "Mozilla/5.0";
    private Gson _gson;

    private String _username;
    private String _password;
    private URI _baseUri;
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
        _baseUri = URI.create(baseUri);
        _version = DEFAULT_VERSION;
        _gson = new Gson();
    }

    /**
     *
     * @param username
     * @param password
     */
    public  ReportingCloud(String username, String password) {
        this(username, password, DEFAULT_BASE_URI);
    }

    public int getTemplateCount(String templateName) throws IOException {
        // ToDo: implement
        String res = get("/templates/count");
        return 0;
    }

    private String get(String endpoint) throws IOException {
        return get(endpoint, null);
    }

    private String get(String endpoint, HashMap<String, Object> params) throws IOException {
        String queryString = queryStringFromHashMap(params);

        return "";
    }

    private static String queryStringFromHashMap(HashMap<String, Object> hashMap) {
        if (hashMap == null) return "";
        List<String> params = new ArrayList<String>();
        for (String key : hashMap.keySet()) {
            params.add(key + "=" + hashMap.get(key).toString());
        }
        return String.join("&", params);
    }
}
