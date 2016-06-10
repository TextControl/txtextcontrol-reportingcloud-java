package txtextcontrol.reportingcloud;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by thorsten on 10.06.2016.
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
