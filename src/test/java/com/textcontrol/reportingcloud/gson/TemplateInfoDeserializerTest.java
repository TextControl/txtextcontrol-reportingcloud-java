package com.textcontrol.reportingcloud.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.textcontrol.reportingcloud.AccountSettings;
import com.textcontrol.reportingcloud.TemplateInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * The TemplateInfoDeserializer test class.
 *
 * @author Thorsten Kummerow
 */
public class TemplateInfoDeserializerTest {

    private String _testFixtureFileContent;
    private static final String TestFixtureFileName = "get_template_info.json";

    public TemplateInfoDeserializerTest() throws IOException {
        Path currentRelPath = Paths.get("");
        String absPath = currentRelPath.toAbsolutePath().toString();
        String testFixtureFilePath = absPath + "\\src\\test\\resources\\" + TestFixtureFileName;
        _testFixtureFileContent = new String(Files.readAllBytes(Paths.get(testFixtureFilePath)), "UTF-8");
    }

    @Test
    public void deserialize() throws Exception {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(TemplateInfo.class, new TemplateInfoDeserializer());
        Gson g = gb.create();
        TemplateInfo ti = g.fromJson(_testFixtureFileContent, TemplateInfo.class);
        Assert.assertEquals("invoice.tx", ti.getTemplateName());
        Assert.assertEquals(1, ti.getMergeBlocks().size());
        Assert.assertEquals("Sales_SalesOrderDetail", ti.getMergeBlocks().get(0).getName());
        Assert.assertEquals(5, ti.getMergeBlocks().get(0).getMergeFields().size());
        Assert.assertEquals(15, ti.getMergeFields().size());
        Assert.assertEquals("Customer_Customer.Sales_CustomerAddress.Person_Address.City", ti.getMergeFields().get(5).getName());
        Assert.assertEquals(0, ti.getMergeBlocks().get(0).getMergeBlocks().size());
    }
}