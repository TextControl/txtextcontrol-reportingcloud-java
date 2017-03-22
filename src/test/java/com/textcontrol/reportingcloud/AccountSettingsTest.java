package com.textcontrol.reportingcloud;

import org.junit.Assert;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * The AccountSettings test class.
 *
 * @author Thorsten Kummerow
 */
public class AccountSettingsTest {
    @Test
    public void isConstructedCorrectly() {
        AccountSettings s = new AccountSettings("1234567890123", 2, 3, 100000, 500, "2016-05-24T15:24:57+00:00");

        Assert.assertEquals("1234567890123", s.getSerialNumber());
        Assert.assertEquals(2, s.getCreatedDocuments());
        Assert.assertEquals(3, s.getUploadedTemplates());
        Assert.assertEquals(100000, s.getMaxDocuments());
        Assert.assertEquals(500, s.getMaxTemplates());
        Assert.assertEquals(
                ZonedDateTime.parse("2016-05-24T15:24:57+00:00",
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME), s.getValidUntil());
    }
}