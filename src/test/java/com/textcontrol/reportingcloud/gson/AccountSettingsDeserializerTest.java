package com.textcontrol.reportingcloud.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.textcontrol.reportingcloud.AccountSettings;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * AccountSettingsDeserializer test class.
 *
 * @author Thorsten Kummerow
 */
public class AccountSettingsDeserializerTest {

    @Test
    public void deserialize() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("serialNumber", "1234567890123");
        hashMap.put("uploadedTemplates", 2);
        hashMap.put("createdDocuments", 3);
        hashMap.put("maxDocuments", 100000);
        hashMap.put("maxTemplates", 500);
        hashMap.put("validUntil", "2016-05-30T12:07:45+00:00");

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(AccountSettings.class, new AccountSettingsDeserializer());
        gb.serializeNulls();
        Gson g = gb.create();
        String json = g.toJson(hashMap);
        Assert.assertTrue(json.length() > 0);
        AccountSettings s = g.fromJson(json, AccountSettings.class);

        Assert.assertEquals("1234567890123", s.getSerialNumber());
        Assert.assertEquals(2, s.getUploadedTemplates());
        Assert.assertEquals(3, s.getCreatedDocuments());
        Assert.assertEquals(100000, s.getMaxDocuments());
        Assert.assertEquals(500, s.getMaxTemplates());
        Assert.assertEquals(ZonedDateTime.parse("2016-05-30T12:07:45+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME), s.getValidUntil());
    }
}