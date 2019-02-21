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
import com.textcontrol.reportingcloud.MergeBlock;
import com.textcontrol.reportingcloud.MergeField;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Needed by GSON to create {@link com.textcontrol.reportingcloud.MergeBlock} objects from JSON data.
 *
 * @author Thorsten Kummerow
 */
public class MergeBlockDeserializer implements JsonDeserializer<MergeBlock> {
    @Override
    public MergeBlock deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<MergeBlock> mergeBlocks = new ArrayList<>();
        List<MergeField> mergeFields = new ArrayList<>();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeBlock.class, new MergeBlockDeserializer());
        gb.registerTypeAdapter(MergeField.class, new MergeFieldDeserializer());
        Gson gson = gb.create();

        JsonObject jsonObj = json.getAsJsonObject();
        for (JsonElement elem : jsonObj.get("mergeBlocks").getAsJsonArray()) {
            mergeBlocks.add(gson.fromJson(elem, MergeBlock.class));
        }
        for (JsonElement elem : jsonObj.get("mergeFields").getAsJsonArray()) {
            mergeFields.add(gson.fromJson(elem, MergeField.class));
        }
        return new MergeBlock(jsonObj.get("name").getAsString(), mergeBlocks, mergeFields);
    }
}
