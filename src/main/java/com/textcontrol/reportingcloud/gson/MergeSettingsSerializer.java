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
 * Copyright: © 2016 Text Control GmbH
 */
package com.textcontrol.reportingcloud.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.textcontrol.reportingcloud.MergeSettings;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Needed by GSON to serialize {@link com.textcontrol.reportingcloud.MergeSettings} objects to
 * JSON.
 *
 * @author Thorsten Kummerow
 */
public class MergeSettingsSerializer implements JsonSerializer<MergeSettings> {
    @Override
    public JsonElement serialize(MergeSettings src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("removeEmptyFields", src.getRemoveEmptyFields());
        result.addProperty("removeEmptyBlocks", src.getRemoveEmptyBlocks());
        result.addProperty("removeEmptyImages", src.getRemoveEmptyImages());
        result.addProperty("removeTrailingWhitespace", src.getRemoveTrailingWhitespace());
        result.addProperty("author", src.getAuthor());
        result.addProperty("creatorApplication", src.getCreatorApplication());
        result.addProperty("documentSubject", src.getDocumentSubject());
        result.addProperty("documentTitle", src.getdocumentTitle());
        result.addProperty("userPassword", src.getUserPassword());
        ZonedDateTime lastModDate = src.getLastModificationDate();
        String strLastModDate = lastModDate == null ? null : lastModDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        result.addProperty("lastModificationDate", strLastModDate);
        ZonedDateTime creationDate = src.getCreationDate();
        String strCreationDate = creationDate == null ? null : creationDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        result.addProperty("creationDate", strCreationDate);
        return result;
    }
}
