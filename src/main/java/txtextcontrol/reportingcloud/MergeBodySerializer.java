package txtextcontrol.reportingcloud;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by thorsten on 10.06.2016.
 */
public class MergeBodySerializer implements JsonSerializer<MergeBody> {
    @Override
    public JsonElement serialize(MergeBody src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        // ToDo: implement
        // result.addProperty("mergeSettings", src.getMergeSettings());
        return result;
    }
}
