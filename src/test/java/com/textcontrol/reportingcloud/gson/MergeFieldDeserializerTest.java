package com.textcontrol.reportingcloud.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.textcontrol.reportingcloud.MergeField;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The MergeFieldDeserializer test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeFieldDeserializerTest {

    private static final String MergeFieldJson = "{"
        + "\"dateTimeFormat\": \"\","
        + "\"name\": \"UnitPrice\","
        + "\"numericFormat\": \"$#,###.00\","
        + "\"preserveFormatting\": true,"
        + "\"text\": \"«UnitPrice»\","
        + "\"textAfter\": \"\","
        + "\"textBefore\": \"\""
        + "}";

    @Test
    public void deserialize() throws Exception {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeField.class, new MergeFieldDeserializer());
        Gson g = gb.create();
        MergeField mf = g.fromJson(MergeFieldJson, MergeField.class);
        Assert.assertEquals("UnitPrice", mf.getName());
        Assert.assertEquals("$#,###.00", mf.getNumericFormat());
        Assert.assertEquals("", mf.getTextAfter());
        Assert.assertEquals(true, mf.getPreserveFormatting());
        Assert.assertEquals("«UnitPrice»", mf.getText());
    }
}