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

import com.google.gson.*;
import com.textcontrol.reportingcloud.MergeBody;
import com.textcontrol.reportingcloud.MergeSettings;

import java.lang.reflect.Type;
import java.util.Base64;

/**
 * Needed by GSON to serialize {@link com.textcontrol.reportingcloud.MergeBody} objects to
 * JSON.
 *
 * @author Thorsten Kummerow
 */
public class MergeBodySerializer implements JsonSerializer<MergeBody> {
    @Override
    public JsonElement serialize(MergeBody src, Type typeOfSrc, JsonSerializationContext context) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeSettings.class, new MergeSettingsSerializer());
        gb.serializeNulls();
        Gson gson = gb.create();

        JsonObject result = new JsonObject();
        byte[] templateData = src.getTemplate();
        String templateDataB64 = null;
        if (templateData != null) {
            templateDataB64 = Base64.getEncoder().encodeToString(templateData);
        }

        result.addProperty("template", templateDataB64);
        result.add("mergeSettings", gson.toJsonTree(src.getMergeSettings()));
        result.add("mergeData", gson.toJsonTree(src.getMergeData()));
        return result;
    }
}
