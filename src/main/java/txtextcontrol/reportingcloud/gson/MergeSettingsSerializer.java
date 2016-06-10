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
package txtextcontrol.reportingcloud.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import txtextcontrol.reportingcloud.MergeSettings;

import java.lang.reflect.Type;

/**
 * Created by thorsten on 10.06.2016.
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
        return result;
    }
}
