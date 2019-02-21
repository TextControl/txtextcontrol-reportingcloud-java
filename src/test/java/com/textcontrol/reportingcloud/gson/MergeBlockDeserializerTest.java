package com.textcontrol.reportingcloud.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.textcontrol.reportingcloud.MergeBlock;
import com.textcontrol.reportingcloud.ReportingCloudTest;
import com.textcontrol.reportingcloud.TemplateInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * The MergeBockDeserializer test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeBlockDeserializerTest {
    private String _testFixtureFileContent;
    private static final String TestFixtureFileName = "merge_block.json";

    public MergeBlockDeserializerTest() throws IOException {
        String testFixtureFilePath = ReportingCloudTest.ResourcesDir + TestFixtureFileName;
        _testFixtureFileContent = new String(Files.readAllBytes(Paths.get(testFixtureFilePath)), "UTF-8");
    }

    @Test
    public void deserialize() throws Exception {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(MergeBlock.class, new MergeBlockDeserializer());
        Gson g = gb.create();
        MergeBlock mb = g.fromJson(_testFixtureFileContent, MergeBlock.class);
        Assert.assertEquals("Sales_SalesOrderDetail", mb.getName());
        Assert.assertEquals(0, mb.getMergeBlocks().size());
        Assert.assertEquals(5, mb.getMergeFields().size());
        Assert.assertEquals("UnitPrice", mb.getMergeFields().get(3).getName());
        Assert.assertEquals("$#,###.00", mb.getMergeFields().get(3).getNumericFormat());
    }
}