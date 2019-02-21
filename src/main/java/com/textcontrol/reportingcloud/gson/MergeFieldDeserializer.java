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
import com.textcontrol.reportingcloud.MergeField;

import java.lang.reflect.Type;

/**
 * Needed by GSON to create {@link com.textcontrol.reportingcloud.MergeField} objects from JSON data.
 *
 * @author Thorsten Kummerow
 */
public class MergeFieldDeserializer implements JsonDeserializer<MergeField> {
    @Override
    public MergeField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        return new MergeField(
                jsonObj.get("dateTimeFormat").getAsString(),
                jsonObj.get("name").getAsString(),
                jsonObj.get("numericFormat").getAsString(),
                jsonObj.get("preserveFormatting").getAsBoolean(),
                jsonObj.get("text").getAsString(),
                jsonObj.get("textAfter").getAsString(),
                jsonObj.get("textBefore").getAsString()
        );
    }
}
