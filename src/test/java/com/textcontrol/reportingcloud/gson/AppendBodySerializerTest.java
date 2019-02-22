package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Base64;
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
        byte[] testDocument = new byte[] { 1, 2, 3, 4, 5, 6 };
        docs.add(new AppendDocument(testDocument));
        docs.add(new AppendDocument(testDocument, DocumentDivider.NewSection));
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

        JsonArray objDocs = (JsonArray) obj.get("documents");
        Assert.assertEquals(2, objDocs.size());

        JsonObject objDoc = (JsonObject) objDocs.get(0);
        Assert.assertTrue(objDoc.has("document"));
        Assert.assertTrue(objDoc.has("documentDivider"));
        String docB64 = objDoc.get("document").getAsString();
        Assert.assertTrue(docB64.length() > 0);
        byte[] docData = Base64.getDecoder().decode(docB64);
        Assert.assertArrayEquals(testDocument, docData);

        int divider = objDoc.get("documentDivider").getAsInt();
        Assert.assertEquals(1, divider);
    }
}
