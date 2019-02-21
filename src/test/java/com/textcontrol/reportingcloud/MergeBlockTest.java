package com.textcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * The MergeBlock test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeBlockTest {
    @Test
    public void isConstructedCorrectly() {
        MergeBlock mb = new MergeBlock("merge_block", new ArrayList<>(), new ArrayList<>());
        Assert.assertEquals("merge_block", mb.getName());
        Assert.assertEquals(0, mb.getMergeFields().size());
        Assert.assertEquals(0, mb.getMergeBlocks().size());
    }
}