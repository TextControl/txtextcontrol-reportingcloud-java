/**
 * ReportingCloud Java Wrapper
 *
 * Official wrapper (authored by Text Control GmbH, publisher of ReportingCloud) to access 
 * ReportingCloud in Java.
 *
 * Go to http://www.reporting.cloud to learn more about ReportingCloud
 * Go to https://github.com/TextControl/txtextcontrol-reportingcloud-java for the
 * canonical source repository.
 *
 * License: https://raw.githubusercontent.com/TextControl/txtextcontrol-reportingcloud-java/master/LICENSE.md
 *
 * Copyright: © 2016 Text Control GmbH 
 */
package txtextcontrol.reportingcloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by thorsten on 09.06.2016.
 * The main Wrapper class
 */
public class ReportingCloud {

    private static final String DEFAULT_BASE_URL = "http://api.reporting.cloud";
    private static final String DEFAULT_VERSION = "v1";
    private static final int DEFAULT_TIMEOUT = 10;
    private static final String USER_AGENT = "Mozilla/5.0";
    private Gson _gson;

    private String _username;
    private String _password;
    private String _baseUrl;
    private String _version;

    /**
     * Creates a new ReportingCloud wrapper instance.
     *
     * @param username The username.
     * @param password The password.
     * @param baseUrl The API base URL.
     */
    public ReportingCloud(String username, String password, String baseUrl) {
        _username = username;
        _password = password;
        _baseUrl = baseUrl;
        // Remove possible slash at the end of base url
        if (_baseUrl.endsWith("/")) _baseUrl = _baseUrl.substring(0, _baseUrl.length() - 2);
        _version = DEFAULT_VERSION;

        // Register custom JSON deserializers and create json parser instance
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Template.class, new TemplateDeserializer());
        gb.registerTypeAdapter(AccountSettings.class, new AccountSettingsDeserializer());
        gb.registerTypeAdapter(MergeSettings.class, new MergeSettingsSerializer());
        _gson = gb.create();
    }

    /**
     * Creates a new ReportingCloud wrapper instance with the API base URL
     * set to "http://api.reporting.cloud".
     *
     * @param username The username.
     * @param password The password.
     */
    public ReportingCloud(String username, String password) {
        this(username, password, DEFAULT_BASE_URL);
    }

    /**
     * Returns the number of templates in the template storage.
     *
     * @return The number of templates in the template storage.
     * @throws IOException
     */
    public int getTemplateCount() throws IllegalArgumentException, IOException {
        String res = request(ReqType.GET, "/templates/count");
        return Integer.parseInt(res);
    }

    /**
     * Gets the current user's account settings.
     *
     * @return The account settings.
     */
    public AccountSettings getAccountSettings() throws IllegalArgumentException, IOException {
        String res = request(ReqType.GET, "/account/settings");
        return _gson.fromJson(res, new TypeToken<AccountSettings>(){}.getType());
    }

    /**
     * Lists all templates from the template storage.
     *
     * @return A list of Template objects.
     * @throws Exception
     */
    public List<Template> listTemplates() throws IllegalArgumentException, IOException {
        String res = request(ReqType.GET, "/templates/list");
        return _gson.fromJson(res, new TypeToken<List<Template>>(){}.getType());
    }

    /**
     * Returns the number of pages of a template in the template storage.
     *
     * @param templateName The filename of the template in the template
     *                     storage to retrieve the number of pages for.
     * @return The number of pages in the template.
     */
    public int getTemplatePageCount(String templateName) throws IllegalArgumentException, IOException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);
        String res = request(ReqType.GET, "/templates/pagecount", params);
        return Integer.parseInt(res);
    }

    /**
     * Converts a document to another format.
     *
     * @param templateData The source document encoded as a Base64 string.
     *                     The supported document formats are .rtf, .doc, .docx,
     *                     .html, .pdf and .tx.
     * @param returnFormat The format of the created document.
     * @return The created document encoded as a Base64 string.
     * @throws Exception
     */
    public byte[] convertDocument(byte[] templateData, ReturnFormat returnFormat) throws IllegalArgumentException, IOException {
        String dataB64 = Base64.getEncoder().encodeToString(templateData);

        HashMap<String, Object> params = new HashMap<>();
        params.put("returnFormat", returnFormat.name());
        String res = request(ReqType.POST, "/document/convert", params, "\"" + dataB64 + "\"");
        res = res.substring(1, res.length() - 2);
        return Base64.getDecoder().decode(res);
    }

    public List<byte[]> mergeDocument(MergeBody mergeBody) throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, null);
    }

    public List<byte[]> mergeDocument(MergeBody mergeBody, String templateName) throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, templateName, ReturnFormat.PDF);
    }

    public List<byte[]> mergeDocument(MergeBody mergeBody, String templateName, ReturnFormat returnFormat) throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, templateName, returnFormat);
    }

    public List<byte[]> mergeDocument(MergeBody mergeBody, String templateName, ReturnFormat returnFormat, boolean append) throws IllegalArgumentException, IOException {
        // Parameter validation
        if ((mergeBody.getTemplate() != null) && (templateName != null)) {
            throw new IllegalArgumentException("Template name and template data must not be present at the same time.");
        }
        else if ((mergeBody.getTemplate() == null) && (templateName == null)) {
            throw new IllegalArgumentException("Either a template name or template data must be present.");
        }
        // Create query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("returnFormat", returnFormat.name());
        params.put("append", append);
        if ((templateName != null) && (templateName.length() > 0)) {
            params.put("templateName", templateName);
        }

        // Send request
        String mergeBodyJson = _gson.toJson(mergeBody);
        String res = request(ReqType.POST, "/document/merge", params, mergeBodyJson);

        // Parse result
        List<String> mergeResult = _gson.fromJson(res, new TypeToken<List<String>>(){}.getType());
        return mergeResult.stream().map(d -> Base64.getDecoder().decode(d)).collect(Collectors.toList());
    }

    /**
     * Possible HTTP request types
     */
    private enum ReqType {
        GET, POST, DELETE
    }

    /**
     * Performs a HTTP request of a given type.
     * @param endpoint The endpoint (e. g. "/templates/list")
     * @return The HTTP response body.
     * @throws IOException
     */
    private String request(ReqType reqType, String endpoint) throws IllegalArgumentException, IOException {
        return request(reqType, endpoint, null);
    }

    /**
     * Performs a HTTP request of a given type.
     * @param endpoint The endpoint (e. g. "/templates/list")
     * @param params The query parameters.
     * @return The HTTP response body.
     * @throws IOException
     */
    private String request(ReqType reqType, String endpoint, HashMap<String, Object> params) throws IllegalArgumentException, IOException {
        return request(reqType, endpoint, params, (String) null);
    }

    /**
     * Performs a HTTP request of a given type.
     * @param endpoint The endpoint (e. g. "/templates/list")
     * @param params The query parameters.
     * @param strBodyJson The request body as a JSON string.
     * @return The HTTP response body.
     * @throws IOException
     */
    private String request(ReqType reqType, String endpoint, HashMap<String, Object> params, String strBodyJson) throws IllegalArgumentException, IOException {
        String queryString = queryStringFromHashMap(params);

        // DEBUG OUTPUT (ToDo: remove)
        System.out.println("Query string: " + queryString);

        // Create connection
        String strUrl = _baseUrl + "/" + _version + endpoint + "/" + queryString;

        // DEBUG OUTPUT (ToDo: remove)
        System.out.println("URL: " + strUrl);

        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod(reqType.name());
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setConnectTimeout(DEFAULT_TIMEOUT * 1000);

        // Basic Auth
        byte[] authUTF8 = (_username + ":" + _password).getBytes("UTF-8");
        String authEncoded = Base64.getEncoder().encodeToString(authUTF8);
        con.setRequestProperty("Authorization", "Basic " + authEncoded);

        // Add body content if necessary
        if ((strBodyJson != null) && (strBodyJson.length() > 0)) {

            // DEBUG OUTPUT (ToDo: remove)
            System.out.println("Body: " + strBodyJson);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            byte[] bodyUTF8 = strBodyJson.getBytes("UTF-8");
            try (OutputStream os = con.getOutputStream()) {
                os.write(bodyUTF8);
            }
        }

        // Send request
        StringBuilder result = new StringBuilder();
        try {
            try (InputStreamReader reader = new InputStreamReader(con.getInputStream())) {
                try (BufferedReader in = new BufferedReader(reader)) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        result.append(line);
                    }
                }
            }
        }
        catch (IOException exc) {
            // Read error message and throw it
            StringBuilder sb = new StringBuilder();
            try (InputStreamReader reader = new InputStreamReader(con.getErrorStream())) {
                try (BufferedReader in = new BufferedReader(reader)) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                }
            }
            throw new IllegalArgumentException(sb.toString());
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) return result.toString();
        else throw new IllegalArgumentException(result.toString());
    }

    /**
     * Generates a query string from a hash
     */
    private static String queryStringFromHashMap(HashMap<String, Object> hashMap) {
        if (hashMap == null) return "";
        return "?" + hashMap.keySet().stream().map(k -> k + "=" + hashMap.get(k).toString()).collect(Collectors.joining("&"));
    }
}
