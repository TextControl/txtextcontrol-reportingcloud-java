package com.textcontrol.reportingcloud;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * The FindAndReplaceBody test class.
 *
 * @author Thorsten Kummerow
 */
public class FindAndReplaceBodyTest {
    @Test
    public void isConstructedCorrectly() {
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("Foo", "Bar"));
        data.add(new Pair<>("Baz", "Qux"));
        data.add(new Pair<>("Quux", "Quuz"));
        FindAndReplaceBody frb = new FindAndReplaceBody(data);
        Assert.assertEquals(3, frb.getFindAndReplaceData().size());
        Assert.assertEquals(null, frb.getTemplateData());
        Assert.assertEquals(null, frb.getMergeSettings());
    }
}