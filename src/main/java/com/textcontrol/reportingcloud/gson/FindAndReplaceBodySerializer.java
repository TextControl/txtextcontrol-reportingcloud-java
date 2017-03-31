package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.FindAndReplaceBody;
import com.textcontrol.reportingcloud.MergeSettings;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Needed by GSON to serialize {@link com.textcontrol.reportingcloud.FindAndReplaceBody} objects to
 * JSON.
 *
 * @author Thorsten Kummerow
 */
public class FindAndReplaceBodySerializer implements JsonSerializer<FindAndReplaceBody> {
    @Override
    public JsonElement serialize(FindAndReplaceBody src, Type typeOfSrc, JsonSerializationContext context) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeSettings.class, new MergeSettingsSerializer());
        gb.serializeNulls();
        Gson gson = gb.create();

        // Prepare find and replace data array and base64 template data string
        String[][] findAndReplaceData = src.getFindAndReplaceData().stream().map(p -> new String[] { p.getKey(), p.getValue() }).toArray(String[][]::new);
        String templateDataB64 = (src.getTemplateData() == null) ? null : Base64.getEncoder().encodeToString(src.getTemplateData());

        JsonObject result = new JsonObject();
        result.add("findAndReplaceData", gson.toJsonTree(findAndReplaceData));
        result.add("mergeSettings", gson.toJsonTree(src.getMergeSettings()));
        result.addProperty("template", templateDataB64);
        return result;
    }
}
