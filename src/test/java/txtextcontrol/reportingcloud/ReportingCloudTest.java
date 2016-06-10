package txtextcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thorsten on 09.06.2016.
 */
public class ReportingCloudTest {

    private ReportingCloud _r;
    private byte[] _testDocData;

    public ReportingCloudTest() throws IOException {
        Path currentRelPath = Paths.get("");
        String absPath = currentRelPath.toAbsolutePath().toString();
        String testDocPath = absPath + "\\src\\test\\resources\\__java_wrapper_test.tx";
        _testDocData = Files.readAllBytes(Paths.get(testDocPath));
    }

    @Before
    public void initialize() throws IOException {
        _r = new ReportingCloud("<USERNAME>", "<PASSWORD>");
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

        // ToDo: check if template list contains meaningful data
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


    // Classes needed by mergeDocument test:

    public class NestedItem {
        public int item_no;
        public String item_description;
        public double item_total;

        public NestedItem(int item_no, String item_description, double item_total) {
            this.item_no = item_no;
            this.item_description = item_description;
            this.item_total = item_total;
        }
    }

    public class MasterItem {
        public ArrayList<NestedItem> item;
        public String recipient_name;
        public String billto_name;

        public MasterItem(String recipient_name, String billto_name) {
            this.item = new ArrayList<NestedItem>();
            this.recipient_name = recipient_name;
            this.billto_name = billto_name;
        }
    }

    @Test
    public void mergeDocument() throws Exception {
        // Generate some dummy data:
        List<Object> md = new ArrayList<>();
        MasterItem row = new MasterItem("Will Ferrell", "Colin Farrell");
        row.item.add(new NestedItem(23, "An Item.", 234.56));
        row.item.add(new NestedItem(34, "Another item.", 345.67));
        md.add(row);
        row = new MasterItem("Morgan Freeman", "Martin Freeman");
        row.item.add(new NestedItem(45, "Yet another item.", 456.78));
        row.item.add(new NestedItem(45, "And another one.", 567.89));
        md.add(row);

        // Prepare merge settings and merge body objects
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

    @Test
    public void uploadTemplate() throws Exception {
        // ToDo: implement
        Assert.assertTrue("NOT IMPLEMENTED YET", false);
    }

    @Test
    public void templateExists() throws Exception {
        // ToDo: implement
        Assert.assertTrue("NOT IMPLEMENTED YET", false);
    }

    @Test
    public void downloadTemplate() throws Exception {
        // ToDo: implement
        Assert.assertTrue("NOT IMPLEMENTED YET", false);
    }

    @Test
    public void getTemplateThumbnails() throws Exception {
        // ToDo: implement
        Assert.assertTrue("NOT IMPLEMENTED YET", false);
    }

    @Test
    public void deleteTemplate() throws Exception {
        // ToDo: implement
        Assert.assertTrue("NOT IMPLEMENTED YET", false);
    }
}