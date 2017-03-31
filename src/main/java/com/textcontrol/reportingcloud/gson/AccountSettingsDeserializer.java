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
package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.AccountSettings;

import java.lang.reflect.Type;

/**
 * Needed by GSON to create {@link com.textcontrol.reportingcloud.AccountSettings} objects from JSON data.
 *
 * @author Thorsten Kummerow
 */
public class AccountSettingsDeserializer implements JsonDeserializer<AccountSettings> {
    @Override
    public AccountSettings deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObj = json.getAsJsonObject();
        return new AccountSettings(
            jsonObj.get("serialNumber").getAsString(),
            jsonObj.get("createdDocuments").getAsInt(),
            jsonObj.get("uploadedTemplates").getAsInt(),
            jsonObj.get("maxDocuments").getAsInt(),
            jsonObj.get("maxTemplates").getAsInt(),
            jsonObj.get("validUntil").getAsString()
        );
    }
}
