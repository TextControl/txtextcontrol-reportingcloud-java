package com.textcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The IncorrectWord test class.
 *
 * @author Thorsten Kummerow
 */
public class IncorrectWordTest {
    @Test
    public void isConstructedCorrectly() {
        IncorrectWord iw = new IncorrectWord(5, 123, "Tesst", true, "en_US.dic");

        Assert.assertEquals(5, iw.getLength());
        Assert.assertEquals(123, iw.getStart());
        Assert.assertEquals("Tesst", iw.getText());
        Assert.assertTrue(iw.isDuplicate());
        Assert.assertEquals("en_US.dic", iw.getLanguage());
    }
}