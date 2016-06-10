package txtextcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by thorsten on 09.06.2016.
 */
public class ReportingCloudTest {

    private ReportingCloud _r;
    private byte[] _testDocData;

    @Before
    public void initialize() throws IOException {
        _r = new ReportingCloud("<USERNAME>", "<PASSWORD>");
        Path currentRelPath = Paths.get("");
        String absPath = currentRelPath.toAbsolutePath().toString();
        String testDocPath = absPath + "\\src\\test\\resources\\__java_wrapper_test.tx";
        _testDocData = Files.readAllBytes(Paths.get(testDocPath));
    }

    @Test
    public void getTemplateCount() throws Exception {
        int nCount = _r.getTemplateCount();
        Assert.assertEquals(nCount, 2);
    }

    @Test
    public void getTemplatePageCount() throws Exception {
        int nPages = _r.getTemplatePageCount("new_template.docx");
        Assert.assertEquals(nPages, 2);
    }

    @Test
    public void listTemplates() throws Exception {
        List<Template> templates = _r.listTemplates();
        Assert.assertEquals(templates.size(), 2);

        // ToDo: check if templates contain meaningful data
    }

    @Test
    public void convertDocument() throws Exception {
        byte[] converted = _r.convertDocument(_testDocData, ReturnFormat.PDF);

        // Check for PDF magic number
        Assert.assertEquals(converted[0], 0x25);
        Assert.assertEquals(converted[1], 0x50);
        Assert.assertEquals(converted[2], 0x44);
        Assert.assertEquals(converted[3], 0x46);
    }

    @Test
    public void getAccountSettings() throws Exception {
        AccountSettings as = _r.getAccountSettings();
        Assert.assertEquals(as.getSerialNumber().length(), 13);

        // ToDo: Assert more stuff
    }

    @Test
    public void mergeDocument() throws Exception {
        List<Object> md = new ArrayList<>();

        // Create some dummy data
        HashMap<String, Object> item = new HashMap<>();
        List<Object> nestedList = new ArrayList<>();
        HashMap<String, Object> nestedItem = new HashMap<>();

        nestedItem.put("item_no", 23);
        nestedItem.put("item_description", "An Item.");
        nestedItem.put("item_total", 234.56);
        nestedList.add(nestedItem);

        nestedItem = new HashMap<>();
        nestedItem.put("item_no", 34);
        nestedItem.put("item_description", "Another Item.");
        nestedItem.put("item_total", 345.67);
        nestedList.add(nestedItem);

        item.put("billto_name", "Will Ferell");
        item.put("recipient_name", "Colin Farrell");
        item.put("item", nestedList);
        md.add(item);

        item = new HashMap<>();
        nestedList = new ArrayList<>();
        nestedItem = new HashMap<>();

        nestedItem.put("item_no", 45);
        nestedItem.put("item_description", "Yet another item.");
        nestedItem.put("item_total", 567.89);
        nestedList.add(nestedItem);

        nestedItem = new HashMap<>();
        nestedItem.put("item_no", 56);
        nestedItem.put("item_description", "And another one.");
        nestedItem.put("item_total", 678.90);
        nestedList.add(nestedItem);

        item.put("billto_name", "Morgan Freeman");
        item.put("recipient_name", "Martin Freeman");
        item.put("item", nestedList);
        md.add(item);

        MergeSettings ms = new MergeSettings();
        ms.setAuthor("John Doe");
        ms.setDocumentTitle("A document merged by Text Control ReportingCloud.");
        MergeBody mb = new MergeBody(md, ms);

        // Merge the document
        List<byte[]> mergeResult = _r.mergeDocument(mb, "sample_invoice.tx");

        Assert.assertEquals(mergeResult.size(), 2);

        // Check for PDF magic number
        Assert.assertEquals(mergeResult.get(0)[0], 0x25);
        Assert.assertEquals(mergeResult.get(0)[1], 0x50);
        Assert.assertEquals(mergeResult.get(0)[2], 0x44);
        Assert.assertEquals(mergeResult.get(0)[3], 0x46);
    }
}