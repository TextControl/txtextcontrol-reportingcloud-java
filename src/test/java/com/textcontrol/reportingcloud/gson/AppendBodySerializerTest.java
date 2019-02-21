package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * AppendBodySerializer test class.
 *
 * @author Thorsten Kummerow
 */
public class AppendBodySerializerTest {

    @Test
    public void serialize() {
        List<AppendDocument> docs = new ArrayList<>();
        docs.add(new AppendDocument(new byte[] { 1, 2, 3, 4, 5, 6 }));
        docs.add(new AppendDocument(new byte[] { 1, 2, 3, 4, 5, 6 }, DocumentDivider.NewSection));
        AppendBody ab = new AppendBody(docs, new DocumentSettings());

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(AppendBody.class, new AppendBodySerializer());
        gb.serializeNulls();
        Gson g = gb.create();

        String json = g.toJson(ab);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.length() > 0);
        JsonParser jp = new JsonParser();
        JsonObject obj = jp.parse(json).getAsJsonObject();

        Assert.assertTrue(obj.has("documents"));
        Assert.assertTrue(obj.has("documentSettings"));

        // ToDo: finish implementation

    }
}
