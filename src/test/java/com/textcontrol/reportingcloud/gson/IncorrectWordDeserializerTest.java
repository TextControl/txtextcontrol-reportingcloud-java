package com.textcontrol.reportingcloud.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.textcontrol.reportingcloud.IncorrectWord;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * AccountSettingsDeserializer test class.
 *
 * @author Thorsten Kummerow
 */
public class IncorrectWordDeserializerTest {

    @Test
    public void deserialize() throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("length", 5);
        hashMap.put("start", 234);
        hashMap.put("text", "Tesst");
        hashMap.put("isDuplicate", true);
        hashMap.put("language", "en_US.dic");

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(IncorrectWord.class, new IncorrectWordDeserializer());
        gb.serializeNulls();
        Gson g = gb.create();
        String json = g.toJson(hashMap);
        Assert.assertTrue(json.length() > 0);
        IncorrectWord iw = g.fromJson(json, IncorrectWord.class);

        Assert.assertEquals(5, iw.getLength());
        Assert.assertEquals(234, iw.getStart());
        Assert.assertEquals("Tesst", iw.getText());
        Assert.assertTrue(iw.isDuplicate());
        Assert.assertEquals("en_US.dic", iw.getLanguage());
    }

}