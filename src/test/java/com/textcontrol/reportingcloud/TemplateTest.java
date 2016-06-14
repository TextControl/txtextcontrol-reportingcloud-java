package com.textcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * The Template test class.
 *
 * @author Thorsten Kummerow
 */
public class TemplateTest {

    @Test
    public void isConstructedCorrectly() {
        Template t = new Template("name", "2016-05-30T12:07:45+00:00", 23456);
        Assert.assertEquals(23456, t.getSize());
        Assert.assertEquals("name", t.getTemplateName());
        Assert.assertEquals(ZonedDateTime.parse("2016-05-30T12:07:45+00:00"), t.getModified());
    }
}