package com.textcontrol.reportingcloud;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The MergeBody test class.
 *
 * @author Thorsten Kummerow
 */
public class MergeBodyTest {

    @Test(expected = InvalidParameterException.class)
    public void setMergeData_throwsInCaseOfNull() {
        MergeBody b = new MergeBody(new Object[] { new Object() });
        b.setMergeData((ArrayList<Object>) null);
    }

    @Test(expected = InvalidParameterException.class)
    public void setMergeData_throwsInCaseOfEmptyArray() {
        MergeBody b = new MergeBody(new Object[] { new Object() });
        b.setMergeData(new Object[0]);
    }
}