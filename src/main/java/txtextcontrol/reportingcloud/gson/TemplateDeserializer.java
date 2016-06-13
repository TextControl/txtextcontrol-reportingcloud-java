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

import com.google.gson.*;
import txtextcontrol.reportingcloud.Template;

import java.lang.reflect.Type;

/**
 * Needed by GSON to create {@link txtextcontrol.reportingcloud.Template} objects from JSON data.
 *
 * @author Thorsten Kummerow
 */
public class TemplateDeserializer implements JsonDeserializer<Template> {
    @Override
    public Template deserialize(JsonElement json,Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObj = json.getAsJsonObject();
        return new Template(
                jsonObj.get("templateName").getAsString(),
                jsonObj.get("modified").getAsString(),
                jsonObj.get("size").getAsInt());
    }
}
