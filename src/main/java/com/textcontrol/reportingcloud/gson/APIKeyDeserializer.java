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
import com.textcontrol.reportingcloud.APIKey;
import java.lang.reflect.Type;

public class APIKeyDeserializer implements JsonDeserializer<APIKey> {

    @Override
    public APIKey deserialize(JsonElement json, Type typeOfT,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        String key = jsonObj.get("key").getAsString();
        boolean isActive = jsonObj.get("active").getAsBoolean();
        return new APIKey(key, isActive);
    }
}
