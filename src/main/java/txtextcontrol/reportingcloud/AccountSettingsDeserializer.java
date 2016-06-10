package txtextcontrol.reportingcloud;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by thorsten on 10.06.2016.
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
