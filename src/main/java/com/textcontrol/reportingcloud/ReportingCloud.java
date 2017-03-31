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
 * Copyright: © 2017 Text Control GmbH
 */
package com.textcontrol.reportingcloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.textcontrol.reportingcloud.gson.*;

/**
 * The ReportingCloud API wrapper class.
 *
 * @author Thorsten Kummerow
 */
public class ReportingCloud {

    private static final String DEFAULT_BASE_URL = "https://api.reporting.cloud";
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
        gb.registerTypeAdapter(MergeBody.class, new MergeBodySerializer());
        gb.registerTypeAdapter(TemplateInfo.class, new TemplateInfoDeserializer());
        gb.registerTypeAdapter(FindAndReplaceBody.class, new FindAndReplaceBodySerializer());
        gb.serializeNulls();
        _gson = gb.create();
    }

    /**
     * Creates a new ReportingCloud wrapper instance with the API base URL
     * set to <tt>"https://api.reporting.cloud"</tt>.
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
     * @throws IOException If an I/O error occurs.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     */
    public int getTemplateCount() throws IllegalArgumentException, IOException {
        String res = request(ReqType.GET, "/templates/count");
        return Integer.parseInt(res);
    }

    /**
     * Gets the current user's account settings.
     *
     * @return The account settings.
     * @throws IOException If an I/O error occurs.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     */
    public AccountSettings getAccountSettings() throws IllegalArgumentException, IOException {
        String res = request(ReqType.GET, "/account/settings");
        return _gson.fromJson(res, AccountSettings.class);
    }

    /**
     * Lists all templates from the template storage.
     *
     * @return A list of Template objects.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
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
     * @throws IOException If an I/O error occurs.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     */
    public int getTemplatePageCount(String templateName) throws IllegalArgumentException, IOException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);
        String res = request(ReqType.GET, "/templates/pagecount", params);
        return Integer.parseInt(res);
    }

    /**
     * Returns a list of thumbnails of a specific template.
     *
     * @param templateName  The filename of the template in the template storage.
     * @param zoomFactor An Integer value between <tt>1</tt> and <tt>400</tt> to set the percentage
     *                   zoom factor of the created thumbnail images.
     * @return An array of binary image data.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> getTemplateThumbnails(String templateName, int zoomFactor) throws IllegalArgumentException, IOException {
        return getTemplateThumbnails(templateName, zoomFactor, 1);
    }

    /**
     * Returns a list of thumbnails of a specific template.
     *
     * @param templateName  The filename of the template in the template storage.
     * @param zoomFactor An Integer value between <tt>1</tt> and <tt>400</tt> to set the percentage
     *                   zoom factor of the created thumbnail images.
     * @param fromPage  An Integer value that specifies the first page.
     * @return An array of binary image data.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> getTemplateThumbnails(String templateName, int zoomFactor, int fromPage) throws IllegalArgumentException, IOException {
        return getTemplateThumbnails(templateName, zoomFactor, fromPage, 0);
    }

    /**
     * Returns a list of thumbnails of a specific template.
     *
     * @param templateName  The filename of the template in the template storage.
     * @param zoomFactor An Integer value between <tt>1</tt> and <tt>400</tt> to set the percentage
     *                   zoom factor of the created thumbnail images.
     * @param fromPage  An Integer value that specifies the first page.
     * @param toPage An Integer value that specifies the last page.
     * @return An array of binary image data.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> getTemplateThumbnails(
            String templateName, int zoomFactor, int fromPage, int toPage) throws IllegalArgumentException, IOException {
        return getTemplateThumbnails(templateName, zoomFactor, fromPage, toPage, ImageFormat.PNG);
    }

    /**
     * Returns a list of thumbnails of a specific template.
     *
     * @param templateName  The filename of the template in the template storage.
     * @param zoomFactor An Integer value between <tt>1</tt> and <tt>400</tt> to set the percentage
     *                   zoom factor of the created thumbnail images.
     * @param fromPage  An Integer value that specifies the first page.
     * @param toPage An Integer value that specifies the last page.
     * @param imageFormat Defines the image format of the returned thumbnails.
     * @return An array of binary image data.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> getTemplateThumbnails(
            String templateName, int zoomFactor, int fromPage,
            int toPage, ImageFormat imageFormat) throws IllegalArgumentException, IOException {

        TemplateNameValidator.validate(templateName);

        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);
        params.put("zoomFactor", zoomFactor);
        params.put("fromPage", fromPage);
        params.put("toPage", toPage);
        if (imageFormat != ImageFormat.PNG) {
            params.put("imageFormat", imageFormat.name());
        }

        // Send request
        String res = request(ReqType.GET, "/templates/thumbnails", params);

        // Parse response
        List<String> imagesB64 = _gson.fromJson(res, new TypeToken<List<String>>(){}.getType());
        return imagesB64.stream().map(img -> Base64.getDecoder().decode(img)).collect(Collectors.toList());
    }

    /**
     * Stores an uploaded template in the template storage (<tt>.doc</tt>, <tt>.docx</tt>,
     * <tt>.rtf</tt> and <tt>.tx</tt>).
     *
     * @param templateName The filename of the template in the template storage. Existing
     *                     files with the same filename will be overwritten.
     * @param templateData Binary document data. The supported formats are <tt>.doc</tt>,
     *                     <tt>.docx</tt>, <tt>.rtf</tt> and <tt>.tx</tt>.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public void uploadTemplate(String templateName, byte[] templateData) throws IllegalArgumentException, IOException {
        TemplateNameValidator.validate(templateName);
        TemplateDataValidator.validate(templateData);

        // Base64-encode binary template data
        String templateDataB64 = Base64.getEncoder().encodeToString(templateData);

        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);

        // Send request
        request(ReqType.POST, "/templates/upload", params, "\"" + templateDataB64 + "\"");
    }

    /**
     * Checks whether a template exists in the template storage.
     *
     * @param templateName The filename of the template to be checked for
     *                     availability in the template storage.
     * @return Returns if the template with the given name exists in the template
     * storage.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public boolean templateExists(String templateName) throws IllegalArgumentException, IOException {
        TemplateNameValidator.validate(templateName);

        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);

        // Send request
        String res = request(ReqType.GET, "/templates/exists", params);
        switch (res) {
            case "true": return true;
            case "false": return false;
            default: throw new IllegalArgumentException("Unknown response value received.");
        }
    }

    /**
     * Returns the selected template from the storage.
     *
     * @param templateName The filename of the template in the template storage.
     * @return The template document data.
     * @throws IOException If an I/O error occurs.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     */
    public byte[] downloadTemplate(String templateName) throws IllegalArgumentException, IOException {
        TemplateNameValidator.validate(templateName);

        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);

        // Send request
        String res = request(ReqType.GET, "/templates/download", params);

        // Parse and return response
        res = res.substring(1, res.length() - 1);
        return Base64.getDecoder().decode(res);
    }

    /**
     * Deletes a template from the template storage.
     *
     * @param templateName The filename of the template to be deleted
     *                     from the template storage.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public void deleteTemplate(String templateName) throws IllegalArgumentException, IOException {
        TemplateNameValidator.validate(templateName);

        // Prepare query parameter
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);

        // Send request
        request(ReqType.DELETE, "/templates/delete", params);
    }

    /**
     * Converts a document to another format.
     *
     * @param templateData The source document encoded as a Base64 string.
     *                     The supported document formats are <tt>.rtf</tt>, <tt>.doc</tt>,
     *                     <tt>.docx</tt>, <tt>.html</tt>, <tt>.pdf</tt> and <tt>.tx</tt>.
     * @param returnFormat The format of the created document.
     * @return The created document encoded as a Base64 string.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] convertDocument(byte[] templateData, ReturnFormat returnFormat)
            throws IllegalArgumentException, IOException {
        return convertDocument(templateData, returnFormat, false);
    }

    /**
     * Converts a document to another format.
     *
     * @param templateData The source document encoded as a Base64 string.
     *                     The supported document formats are <tt>.rtf</tt>, <tt>.doc</tt>,
     *                     <tt>.docx</tt>, <tt>.html</tt>, <tt>.pdf</tt> and <tt>.tx</tt>.
     * @param returnFormat The format of the created document.
     * @param test Specifies whether it is a test run or not. A test run is not counted
     *             against the quota and created documents contain a watermark.
     * @return The created document encoded as a Base64 string.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] convertDocument(byte[] templateData, ReturnFormat returnFormat, boolean test)
            throws IllegalArgumentException, IOException {
        TemplateDataValidator.validate(templateData);

        // Convert template to base64
        String dataB64 = Base64.getEncoder().encodeToString(templateData);

        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("returnFormat", returnFormat.name());
        params.put("test", test);

        // Send request
        String res = request(ReqType.POST, "/document/convert", params, "\"" + dataB64 + "\"");

        // Parse and return response
        res = res.substring(1, res.length() - 1);
        return Base64.getDecoder().decode(res);
    }

    /**
     * Merges and returns a template from the template storage or an uploaded template with JSON data.
     *
     * @param mergeBody The MergeBody object contains the data source
     *                  as a JSON data object and optionally, a template encoded as a Base64 string.
     * @return The response body contains an array of the created documents.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> mergeDocument(MergeBody mergeBody) throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, null);
    }

    /**
     * Merges and returns a template from the template storage or an uploaded template with JSON data.
     *
     * @param mergeBody The MergeBody object contains the data source
     *                  as a JSON data object and optionally, a template encoded as a Base64 string.
     * @param templateName The name of the template in the template storage. If no template
     *                     name is specified, the template must be uploaded in the MergeBody
     *                     object of this request.
     * @return The response body contains an array of the created documents.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> mergeDocument(MergeBody mergeBody, String templateName)
            throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, templateName, ReturnFormat.PDF);
    }

    /**
     * Merges and returns a template from the template storage or an uploaded template with JSON data.
     *
     * @param mergeBody The MergeBody object contains the data source
     *                  as a JSON data object and optionally, a template encoded as a Base64 string.
     * @param templateName The name of the template in the template storage. If no template
     *                     name is specified, the template must be uploaded in the MergeBody
     *                     object of this request.
     * @param returnFormat The format of the created document.
     * @return The response body contains an array of the created documents.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> mergeDocument(MergeBody mergeBody, String templateName, ReturnFormat returnFormat)
            throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, templateName, returnFormat, false);
    }

    /**
     * Merges and returns a template from the template storage or an uploaded template with JSON data.
     *
     * @param mergeBody The MergeBody object contains the data source
     *                  as a JSON data object and optionally, a template encoded as a Base64 string.
     * @param templateName The name of the template in the template storage. If no template
     *                     name is specified, the template must be uploaded in the MergeBody
     *                     object of this request.
     * @param returnFormat The format of the created document.
     * @param append Specifies whether the documents should be appended to one resulting
     *               document when more than 1 data row is passed.
     * @return The response body contains an array of the created documents.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> mergeDocument(
            MergeBody mergeBody,
            String templateName,
            ReturnFormat returnFormat,
            boolean append)
            throws IllegalArgumentException, IOException {
        return mergeDocument(mergeBody, templateName, returnFormat, append, false);
    }

    /**
     * Merges and returns a template from the template storage or an uploaded template with JSON data.
     *
     * @param mergeBody The MergeBody object contains the data source
     *                  as a JSON data object and optionally, a template encoded as a Base64 string.
     * @param templateName The name of the template in the template storage. If no template
     *                     name is specified, the template must be uploaded in the MergeBody
     *                     object of this request.
     * @param returnFormat The format of the created document.
     * @param append Specifies whether the documents should be appended to one resulting
     *               document when more than 1 data row is passed.
     * @param test Specifies whether it is a test run or not. A test run is not counted
     *             against the quota and created documents contain a watermark.
     * @return The response body contains an array of the created documents.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public List<byte[]> mergeDocument(
            MergeBody mergeBody,
            String templateName,
            ReturnFormat returnFormat,
            boolean append,
            boolean test)
            throws IllegalArgumentException, IOException {

        // Parameter validation
        if ((mergeBody.getTemplate() != null) && (templateName != null)) {
            throw new InvalidParameterException("Template name and template data must not be present at the same time.");
        }
        else if ((mergeBody.getTemplate() == null) && (templateName == null)) {
            throw new InvalidParameterException("Either a template name or template data must be present.");
        }
        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("returnFormat", returnFormat.name());
        params.put("append", append);
        params.put("test", test);
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
     * Returns information about a template including merge fields and merge blocks.
     *
     * @param templateName The filename of the template in the template storage to retrieve the information for.
     * @return The template information.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public TemplateInfo getTemplateInfo(String templateName) throws IllegalArgumentException, IOException {
        TemplateNameValidator.validate(templateName);
        HashMap<String, Object> params = new HashMap<>();
        params.put("templateName", templateName);
        String res = request(ReqType.GET, "/templates/info", params);
        return _gson.fromJson(res, TemplateInfo.class);
    }

    /**
     * Executes a find and replace on a template.
     *
     * @param findAndReplaceBody The request body.
     * @return The created document.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] findAndReplace(FindAndReplaceBody findAndReplaceBody) throws IllegalArgumentException, IOException {
        return findAndReplace(findAndReplaceBody, null);
    }

    /**
     * Executes a find and replace on a template.
     *
     * @param findAndReplaceBody The request body.
     * @param templateName The name of the template in the template storage. If no template
     *                     is specified, the template must be included in the
     *                     FindAndReplaceBody object of this request.
     * @return The created document.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] findAndReplace(FindAndReplaceBody findAndReplaceBody, String templateName) throws IllegalArgumentException, IOException {
        return findAndReplace(findAndReplaceBody, templateName, ReturnFormat.PDF);
    }

    /**
     * Executes a find and replace on a template.
     *
     * @param findAndReplaceBody The request body.
     * @param templateName The name of the template in the template storage. If no template
     *                     is specified, the template must be included in the
     *                     FindAndReplaceBody object of this request.
     * @param returnFormat The format of the created document.
     * @return The created document.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] findAndReplace(FindAndReplaceBody findAndReplaceBody, String templateName, ReturnFormat returnFormat)
            throws IllegalArgumentException, IOException {
        return findAndReplace(findAndReplaceBody, templateName, returnFormat, false);
    }

    /**
     * Executes a find and replace on a template.
     *
     * @param findAndReplaceBody The request body.
     * @param templateName The name of the template in the template storage. If no template
     *                     is specified, the template must be included in the
     *                     FindAndReplaceBody object of this request.
     * @param returnFormat The format of the created document.
     * @param test Specifies whether it is a test run or not. A test run is not counted
     *             against the quota and created documents contain a watermark.
     * @return The created document.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] findAndReplace(
            FindAndReplaceBody findAndReplaceBody,
            String templateName,
            ReturnFormat returnFormat,
            boolean test) throws IllegalArgumentException, IOException {

        // Parameter validation
        if (findAndReplaceBody == null) throw new NullPointerException("Parameter \"findAndReplaceBody\" must not be null.");
        if ((templateName != null) && (templateName.isEmpty())) templateName = null;

        // Prepare query parameters
        HashMap<String, Object> params = new HashMap<>();
        params.put("returnFormat", returnFormat.name());
        params.put("templateName", templateName);
        params.put("test", test);

        // Send request
        String findAndReplaceBodyJson = _gson.toJson(findAndReplaceBody);
        String res = request(ReqType.POST, "/document/findandreplace", params, findAndReplaceBodyJson);

        // Parse and return response
        res = res.substring(1, res.length() - 1);
        return Base64.getDecoder().decode(res);
    }

    /**
     * Possible HTTP request types
     */
    private enum ReqType {
        GET, POST, DELETE
    }

    /**
     * Performs a HTTP request of a given type.
     *
     * @param endpoint The endpoint (e. g. <tt>"/templates/list"</tt>)
     * @return The HTTP response body.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    private String request(ReqType reqType, String endpoint) throws IllegalArgumentException, IOException {
        return request(reqType, endpoint, null);
    }

    /**
     * Performs a HTTP request of a given type.
     *
     * @param endpoint The endpoint (e. g. <tt>"/templates/list"</tt>)
     * @param params The query parameters.
     * @return The HTTP response body.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    private String request(ReqType reqType, String endpoint, HashMap<String, Object> params)
            throws IllegalArgumentException, IOException {
        return request(reqType, endpoint, params, (String) null);
    }

    /**
     * Performs a HTTP request of a given type.
     *
     * @param endpoint The endpoint (e. g. <tt>"/templates/list"</tt>)
     * @param params The query parameters.
     * @param strBodyJson The request body as a JSON string.
     * @return The HTTP response body.
     * @throws IllegalArgumentException If something went wrong concerning the HTTP request.
     * @throws IOException If an I/O error occurs.
     */
    private String request(ReqType reqType, String endpoint, HashMap<String, Object> params, String strBodyJson)
            throws IllegalArgumentException, IOException {
        String queryString = queryStringFromHashMap(params);

        // Create connection
        String strUrl = _baseUrl + "/" + _version + endpoint + "/" + queryString;
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
        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_ACCEPTED:
            case HttpURLConnection.HTTP_CREATED:
            case HttpURLConnection.HTTP_NO_CONTENT:
                return result.toString();
            default: throw new IllegalArgumentException(result.toString());
        }
    }

    /**
     * Generates a query string from a hash.
     */
    private static String queryStringFromHashMap(HashMap<String, Object> hashMap) {
        if (hashMap == null) return "";
        return "?" + hashMap.keySet().stream().map(k -> k + "=" + hashMap.get(k).toString()).collect(Collectors.joining("&"));
    }
}
