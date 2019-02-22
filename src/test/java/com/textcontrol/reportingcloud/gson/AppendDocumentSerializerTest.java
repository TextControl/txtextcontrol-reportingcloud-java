package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.AppendDocument;
import com.textcontrol.reportingcloud.DocumentDivider;
import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

/**
 * AppendDocumentSerializer test class.
 *
 * @author Thorsten Kummerow
 */
public class AppendDocumentSerializerTest {

    @Test
    public void serialize() {
        byte[] testDocument = new byte[] { 1, 2, 3, 4, 5, 6 };
        AppendDocument ad = new AppendDocument(testDocument, DocumentDivider.NewParagraph);

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(AppendDocument.class, new AppendDocumentSerializer());
        gb.serializeNulls();
        Gson g = gb.create();

        String json = g.toJson(ad);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.length() > 0);
        JsonParser jp = new JsonParser();
        JsonObject obj = jp.parse(json).getAsJsonObject();

        Assert.assertTrue(obj.has("document"));
        Assert.assertTrue(obj.has("documentDivider"));

        String docB64 = obj.get("document").getAsString();
        Assert.assertTrue(docB64.length() > 0);
        byte[] docData = Base64.getDecoder().decode(docB64);
        Assert.assertArrayEquals(testDocument, docData);

        int divider = obj.get("documentDivider").getAsInt();
        Assert.assertEquals(2, divider);
    }
}
