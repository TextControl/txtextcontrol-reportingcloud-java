package com.textcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The MergeSettings test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeSettingsTest {

    @Test(expected = DateTimeParseException.class)
    public void setCreationDate_throwsOnInvalidFormat() {
        MergeSettings s = new MergeSettings();
        s.setCreationDate("sfsdf");
    }

    @Test
    public void setCreationDate_acceptsValidString() {
        MergeSettings s = new MergeSettings();
        s.setCreationDate("2016-05-30T12:07:45+00:00");
        Assert.assertEquals(
                ZonedDateTime.parse("2016-05-30T12:07:45+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                s.getCreationDate());
    }

    @Test
    public void setCreationDate_acceptsNull() {
        MergeSettings s = new MergeSettings();
        s.setCreationDate((ZonedDateTime) null);
        Assert.assertEquals(null, s.getCreationDate());
    }

    @Test(expected = DateTimeParseException.class)
    public void setLastModificationDate_throwsOnInvalidFormat() {
        MergeSettings s = new MergeSettings();
        s.setLastModificationDate("sfsdf");
    }

    @Test
    public void setLastModificationDate_acceptsValidString() {
        MergeSettings s = new MergeSettings();
        s.setLastModificationDate("2016-05-30T12:07:45+00:00");
        Assert.assertEquals(
                ZonedDateTime.parse("2016-05-30T12:07:45+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                s.getLastModificationDate());
    }

    @Test
    public void setLastModificationDate_acceptsNull() {
        MergeSettings s = new MergeSettings();
        s.setLastModificationDate((ZonedDateTime) null);
        Assert.assertEquals(null, s.getLastModificationDate());
    }
}