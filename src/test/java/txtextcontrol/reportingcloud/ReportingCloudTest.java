package txtextcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReportingCloud test class.
 *
 * @author Thorsten Kummerow
 */
public class ReportingCloudTest {

    private ReportingCloud _r;
    private byte[] _testDocData;

    private static final String TestTemplateName = "__java_wrapper_test.tx";

    public ReportingCloudTest() throws IOException {
        Path currentRelPath = Paths.get("");
        String absPath = currentRelPath.toAbsolutePath().toString();
        String testDocPath = absPath + "\\src\\test\\resources\\" + TestTemplateName;
        _testDocData = Files.readAllBytes(Paths.get(testDocPath));
    }

    @Before
    public void initialize() throws IOException {
        // ToDo: Fill in your ReportingCloud credentials here.
        _r = new ReportingCloud("<USERNAME>", "<PASSWORD>");
    }

    @Test
    public void getTemplateCount() throws Exception {
        // ToDo: Adapt the following to your own template storage.
        int nCount = _r.getTemplateCount();
        Assert.assertEquals(2, nCount);
    }

    @Test
    public void getTemplatePageCount() throws Exception {
        // ToDo: Adapt the following to your own template storage.
        int nPages = _r.getTemplatePageCount("new_template.docx");
        Assert.assertEquals(2, nPages);
    }

    @Test
    public void listTemplates() throws Exception {
        List<Template> templates = _r.listTemplates();
        Assert.assertEquals(2, templates.size());

        // ToDo: Adapt the following to your own template storage.
        Assert.assertEquals("sample_invoice.tx", templates.get(1).getTemplateName());
        Assert.assertEquals(34845, templates.get(1).getSize());
        Assert.assertEquals(LocalDateTime.parse("2016-05-24T15:24:57"), templates.get(1).getModified());
    }

    @Test
    public void convertDocument() throws Exception {
        byte[] converted = _r.convertDocument(_testDocData, ReturnFormat.PDF);

        // Check for PDF magic number
        Assert.assertEquals(0x25, converted[0]);
        Assert.assertEquals(0x50, converted[1]);
        Assert.assertEquals(0x44, converted[2]);
        Assert.assertEquals(0x46, converted[3]);
    }

    @Test
    public void getAccountSettings() throws Exception {
        // ToDo: Adapt the following to your own account details.
        AccountSettings as = _r.getAccountSettings();
        Assert.assertEquals(13, as.getSerialNumber().length());
        Assert.assertEquals(LocalDateTime.parse("2020-01-01T00:00"), as.getValidUntil());
        Assert.assertEquals(100000, as.getMaxDocuments());
        Assert.assertEquals(500, as.getMaxTemplates());
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

        Assert.assertEquals(2, mergeResult.size());

        // Check for PDF magic number
        Assert.assertEquals(0x25, mergeResult.get(0)[0]);
        Assert.assertEquals(0x50, mergeResult.get(0)[1]);
        Assert.assertEquals(0x44, mergeResult.get(0)[2]);
        Assert.assertEquals(0x46, mergeResult.get(0)[3]);
    }

    @Test
    public void uploadTemplate() throws Exception {
        _r.uploadTemplate(TestTemplateName, _testDocData);
        Assert.assertTrue(_r.templateExists(TestTemplateName));
        _r.deleteTemplate(TestTemplateName);
        Assert.assertFalse(_r.templateExists(TestTemplateName));
    }

    @Test
    public void templateExists() throws Exception {
        _r.uploadTemplate(TestTemplateName, _testDocData);
        Assert.assertTrue(_r.templateExists(TestTemplateName));
        _r.deleteTemplate(TestTemplateName);
        Assert.assertFalse(_r.templateExists(TestTemplateName));
    }

    @Test
    public void downloadTemplate() throws Exception {
        _r.uploadTemplate(TestTemplateName, _testDocData);
        Assert.assertTrue(_r.templateExists(TestTemplateName));
        byte[] downloaded = _r.downloadTemplate(TestTemplateName);
        Assert.assertArrayEquals(_testDocData, downloaded);
    }

    @Test
    public void getTemplateThumbnails() throws Exception {
        _r.uploadTemplate(TestTemplateName, _testDocData);
        Assert.assertTrue(_r.templateExists(TestTemplateName));

        // Get PNGs
        List<byte[]> thumbnails = _r.getTemplateThumbnails(TestTemplateName, 25);
        Assert.assertEquals(3, thumbnails.size());

        // Check for PNG magic number
        Assert.assertEquals(0x89, (int) thumbnails.get(0)[0] & 0xFF);
        Assert.assertEquals(0x50, thumbnails.get(0)[1]);
        Assert.assertEquals(0x4E, thumbnails.get(0)[2]);
        Assert.assertEquals(0x47, thumbnails.get(0)[3]);
        Assert.assertEquals(0x0D, thumbnails.get(0)[4]);

        // Get JPGs
        thumbnails = _r.getTemplateThumbnails(TestTemplateName, 25, 1, 0, ImageFormat.JPG);

        // Check for JPG magic number
        Assert.assertEquals(0xFF, (int) thumbnails.get(0)[0] & 0xFF);
        Assert.assertEquals(0xD8, (int) thumbnails.get(0)[1] & 0xFF);

        _r.deleteTemplate(TestTemplateName);
        Assert.assertFalse(_r.templateExists(TestTemplateName));
    }

    @Test
    public void deleteTemplate() throws Exception {
        _r.uploadTemplate(TestTemplateName, _testDocData);
        Assert.assertTrue(_r.templateExists(TestTemplateName));
        _r.deleteTemplate(TestTemplateName);
        Assert.assertFalse(_r.templateExists(TestTemplateName));
    }
}