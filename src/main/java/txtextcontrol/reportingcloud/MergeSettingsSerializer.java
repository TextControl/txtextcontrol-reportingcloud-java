package txtextcontrol.reportingcloud;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by thorsten on 10.06.2016.
 */
public class MergeSettingsSerializer implements JsonSerializer<MergeSettings> {
    @Override
    public JsonElement serialize(MergeSettings src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        // ToDo: implement
        // result.addProperty("removeEmptyFields", src.getRemoveEmptyFields());
        return result;
    }
}
