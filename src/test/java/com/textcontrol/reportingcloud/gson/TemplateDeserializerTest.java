package com.textcontrol.reportingcloud.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.textcontrol.reportingcloud.Template;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.HashMap;

/**
 * TemplateDeserializer test class.
 *
 * @author Thorsten Kummerow
 */
public class TemplateDeserializerTest {

    @Test
    public void deserialize() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("templateName", "name");
        hashMap.put("modified", "2016-05-30T12:07:45+00:00");
        hashMap.put("size", 12345);

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Template.class, new TemplateDeserializer());
        Gson g = gb.create();
        String json = g.toJson(hashMap);
        Assert.assertTrue(json.length() > 0);
        Template t = g.fromJson(json, Template.class);

        Assert.assertEquals("name", t.getTemplateName());
        Assert.assertEquals(ZonedDateTime.parse("2016-05-30T12:07:45+00:00"), t.getModified());
        Assert.assertEquals(12345, t.getSize());
    }
}