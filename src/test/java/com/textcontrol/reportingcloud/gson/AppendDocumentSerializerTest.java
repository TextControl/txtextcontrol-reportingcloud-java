package com.textcontrol.reportingcloud.gson;

import com.google.gson.*;
import com.textcontrol.reportingcloud.AppendDocument;
import com.textcontrol.reportingcloud.DocumentDivider;
import org.junit.Assert;
import org.junit.Test;

/**
 * AppendDocumentSerializer test class.
 *
 * @author Thorsten Kummerow
 */
public class AppendDocumentSerializerTest {

    @Test
    public void serialize() {
        AppendDocument ad = new AppendDocument(new byte[] { 1, 2, 3, 4, 5, 6 }, DocumentDivider.NewParagraph);

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

        // ToDo: finish implementation

    }
}
