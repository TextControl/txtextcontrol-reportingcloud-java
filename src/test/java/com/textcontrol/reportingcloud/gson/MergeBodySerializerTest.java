package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.MergeBody;
import com.textcontrol.reportingcloud.MergeSettings;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * MergeBodySerializer test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeBodySerializerTest {

    @Test
    public void serialize() {
        List<Object> mergeData = new ArrayList<>();
        HashMap<String, Object> dataRow = new HashMap<>();
        dataRow.put("column00", "Data in column 0.");
        dataRow.put("column01", "Data in column 1.");
        mergeData.add(dataRow);
        MergeSettings ms = new MergeSettings();
        ms.setAuthor("Author");
        ms.setCreationDate("2016-05-30T12:07:45+00:00");
        MergeBody mb = new MergeBody(mergeData, ms);

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeBody.class, new MergeBodySerializer());
        gb.serializeNulls();
        Gson g = gb.create();

        String json = g.toJson(mb);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.length() > 0);
        JsonParser jp = new JsonParser();
        JsonObject obj = jp.parse(json).getAsJsonObject();

        // Check merge settings content
        Assert.assertTrue(obj.has("mergeSettings"));
        JsonObject objSettings = obj.get("mergeSettings").getAsJsonObject();
        Assert.assertEquals("Author", objSettings.get("author").getAsString());
        Assert.assertEquals("2016-05-30T12:07:45Z", objSettings.get("creationDate").getAsString());

        // Check merge data
        Assert.assertTrue(obj.has("mergeData"));
        JsonArray arrData = obj.get("mergeData").getAsJsonArray();
        Assert.assertEquals(1, arrData.size());
        JsonObject objRow = arrData.get(0).getAsJsonObject();
        Assert.assertEquals("Data in column 0.", objRow.get("column00").getAsString());
        Assert.assertEquals("Data in column 1.", objRow.get("column01").getAsString());
    }
}