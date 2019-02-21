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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.textcontrol.reportingcloud.DocumentSettings;

/**
 * Needed by GSON to serialize {@link com.textcontrol.reportingcloud.DocumentSettings} objects to
 * JSON.
 *
 * @author Thorsten Kummerow
 */
public class DocumentSettingsSerializer implements JsonSerializer<DocumentSettings> {
    @Override
    public JsonElement serialize(DocumentSettings src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
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
