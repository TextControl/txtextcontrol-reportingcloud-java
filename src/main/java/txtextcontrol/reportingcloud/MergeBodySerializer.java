package txtextcontrol.reportingcloud;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Base64;

/**
 * Created by thorsten on 10.06.2016.
 */
public class MergeBodySerializer implements JsonSerializer<MergeBody> {
    @Override
    public JsonElement serialize(MergeBody src, Type typeOfSrc, JsonSerializationContext context) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeSettings.class, new MergeSettingsSerializer());
        Gson gson = gb.create();

        JsonObject result = new JsonObject();
        byte[] templateData = src.getTemplate();
        String templateDataB64 = null;
        if (templateData != null) {
            templateDataB64 = Base64.getEncoder().encodeToString(templateData);
        }

        result.addProperty("template", templateDataB64);
        result.add("mergeSettings", gson.toJsonTree(src.getMergeSettings()));
        result.add("mergeData", gson.toJsonTree(src.getMergeData()));
        return result;
    }
}
