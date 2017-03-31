package com.textcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * The TemplateInfo test class.
 *
 * @author Thorsten Kummerow
 */
public class TemplateInfoTest {
    @Test
    public void isConstructedCorrectly() {
        String templateName = "invoice.tx";
        TemplateInfo ti = new TemplateInfo(templateName, new ArrayList<>(), new ArrayList<>());
        Assert.assertEquals(templateName, ti.getTemplateName());
        Assert.assertEquals(0, ti.getMergeBlocks().size());
        Assert.assertEquals(0, ti.getMergeBlocks().size());
    }
}