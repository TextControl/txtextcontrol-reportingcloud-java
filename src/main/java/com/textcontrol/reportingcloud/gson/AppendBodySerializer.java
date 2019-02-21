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
 * Copyright: © 2019 Text Control GmbH
 */
package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.AppendBody;
import com.textcontrol.reportingcloud.AppendDocument;
import com.textcontrol.reportingcloud.DocumentSettings;

import java.lang.reflect.Type;

/**
 * Needed by GSON to serialize {@link com.textcontrol.reportingcloud.AppendBody} objects to
 * JSON.
 *
 * @author Thorsten Kummerow
 */
public class AppendBodySerializer implements JsonSerializer<AppendBody> {
    @Override
    public JsonElement serialize(AppendBody src, Type typeOfSrc, JsonSerializationContext context) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(AppendDocument.class, new AppendDocumentSerializer());
        gb.registerTypeAdapter(DocumentSettings.class, new DocumentSettingsSerializer());
        gb.serializeNulls();
        Gson gson = gb.create();

        JsonObject result = new JsonObject();
        result.add("documents", gson.toJsonTree(src.getDocuments()));
        if (src.getDocumentSettings() != null) {
            result.add("documentSettings", gson.toJsonTree(src.getDocumentSettings()));
        }
        return result;
    }
}
