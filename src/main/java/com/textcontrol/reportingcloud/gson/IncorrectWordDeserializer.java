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
import com.textcontrol.reportingcloud.IncorrectWord;
import java.lang.reflect.Type;

/**
 * Needed by GSON to create {@link com.textcontrol.reportingcloud.IncorrectWord} objects from JSON data.
 *
 * @author Thorsten Kummerow
 */
public class IncorrectWordDeserializer implements JsonDeserializer<IncorrectWord> {

    @Override
    public IncorrectWord deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        JsonElement elemLanguage = jsonObj.get("language");
        String language = elemLanguage.isJsonNull() ? "" : elemLanguage.getAsString();
        return new IncorrectWord(
            jsonObj.get("length").getAsInt(),
            jsonObj.get("start").getAsInt(),
            jsonObj.get("text").getAsString(),
            jsonObj.get("isDuplicate").getAsBoolean(),
            language
        );
    }
}
