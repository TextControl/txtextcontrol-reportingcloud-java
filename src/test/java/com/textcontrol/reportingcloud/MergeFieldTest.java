package com.textcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Test;

/**
 * The MergeField test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeFieldTest {
    @Test
    public void isConstructedCorrectly() {
        MergeField mf = new MergeField(null, "Name", "", true, "«Name»", "", null);

        Assert.assertEquals(null, mf.getDateTimeFormat());
        Assert.assertEquals("Name", mf.getName());
        Assert.assertEquals("", mf.getNumericFormat());
        Assert.assertEquals(true, mf.getPreserveFormatting());
        Assert.assertEquals("«Name»", mf.getText());
        Assert.assertEquals("", mf.getTextAfter());
        Assert.assertEquals(null, mf.getTextBefore());
    }
}