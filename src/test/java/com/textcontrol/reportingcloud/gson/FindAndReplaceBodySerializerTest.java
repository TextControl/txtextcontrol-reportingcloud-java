package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.FindAndReplaceBody;
import com.textcontrol.reportingcloud.MergeSettings;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FindAndReplaceBodySerializer test class.
 *
 * @author Thorsten Kummerow
 */
public class FindAndReplaceBodySerializerTest {
    @Test
    public void serialize() throws Exception {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("Foo", "Bar"));
        data.add(new Pair<>("Baz", "Qux"));
        String html = "<b>Test</b>";
        byte[] htmlUTF8 = html.getBytes("UTF-8");
        FindAndReplaceBody frb = new FindAndReplaceBody(data, htmlUTF8, new MergeSettings());

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(FindAndReplaceBody.class, new FindAndReplaceBodySerializer());
        gb.serializeNulls();
        Gson g = gb.create();

        String json = g.toJson(frb);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.length() > 0);
        JsonParser jp = new JsonParser();
        JsonObject obj = jp.parse(json).getAsJsonObject();

        Assert.assertTrue(obj.has("template"));
        Assert.assertTrue(obj.has("findAndReplaceData"));
        Assert.assertTrue(obj.has("mergeSettings"));
        String htmlB64 = obj.get("template").getAsString();
        Assert.assertFalse(htmlB64.isEmpty());
        htmlUTF8 = Base64.getDecoder().decode(htmlB64);
        Assert.assertFalse(htmlUTF8.length == 0);
        String htmlDecoded = new String(htmlUTF8, "UTF-8");
        Assert.assertEquals(html, htmlDecoded);
        JsonArray dataJsonArray = obj.get("findAndReplaceData").getAsJsonArray();
        List<Pair<String, String>> dataRecovered = new ArrayList<>();
        dataJsonArray.forEach(elem -> {
            JsonArray pair = elem.getAsJsonArray();
            dataRecovered.add(new Pair<>(pair.get(0).getAsString(), pair.get(1).getAsString()));
        });
        Assert.assertEquals(2, dataRecovered.size());
        Assert.assertEquals("Foo", dataRecovered.get(0).getKey());
        Assert.assertEquals("Bar", dataRecovered.get(0).getValue());
        Assert.assertEquals("Baz", dataRecovered.get(1).getKey());
        Assert.assertEquals("Qux", dataRecovered.get(1).getValue());
        JsonObject objMergeSettings = obj.get("mergeSettings").getAsJsonObject();
        Assert.assertTrue(objMergeSettings.has("removeEmptyFields"));
        Assert.assertTrue(objMergeSettings.has("removeEmptyBlocks"));
        Assert.assertTrue(objMergeSettings.has("removeEmptyImages"));
        Assert.assertTrue(objMergeSettings.has("removeTrailingWhitespace"));
        Assert.assertTrue(objMergeSettings.has("mergeHtml"));
    }
}