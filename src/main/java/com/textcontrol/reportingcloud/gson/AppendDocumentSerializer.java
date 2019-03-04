/**
 * ReportingCloud Java SDK
 *
 * Official Java SDK for the ReportingCloud Web API. Authored, maintained and fully supported
 * by Text Control GmbH. (http://www.textcontrol.com).
 *
 * Go to http://www.reporting.cloud to learn more about ReportingCloud
 * Go to https://github.com/TextControl/txtextcontrol-reportingcloud-java for the
 * canonical source repository.
 *
 * License: https://raw.githubusercontent.com/TextControl/txtextcontrol-reportingcloud-java/master/LICENSE.md
 *
 * Copyright: © 2019 Text Control GmbH
 */
package com.textcontrol.reportingcloud.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.textcontrol.reportingcloud.AppendDocument;

import java.lang.reflect.Type;
import java.util.Base64;

/**
 * Needed by GSON to serialize {@link com.textcontrol.reportingcloud.AppendDocument} objects to
 * JSON.
 *
 * @author Thorsten Kummerow
 */
public class AppendDocumentSerializer implements JsonSerializer<AppendDocument> {
    @Override
    public JsonElement serialize(AppendDocument src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        String documentData = Base64.getEncoder().encodeToString(src.getDocument());
        result.addProperty("document", documentData);
        result.addProperty("documentDivider", src.getDivider().toInt());
        return result;
    }
}
