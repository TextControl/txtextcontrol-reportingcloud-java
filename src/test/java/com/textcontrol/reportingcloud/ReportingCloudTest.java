package com.textcontrol.reportingcloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
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
    private static String Username;
    private static String Password;

    private static final String TestTemplateName = "__java_wrapper_test.tx";
    private static final String CredentialsFileName = "rc_credentials.json";
    public static String ResourcesDir;

    static {
        Path currentRelPath = Paths.get("");
        String absPath = currentRelPath.toAbsolutePath().toString();
        ResourcesDir = absPath + "\\src\\test\\resources\\";

        // ToDo: To run the ReportingCloud API tests, provide the file
        //  "src/test/resources/rc_credentials.json" containing an object with
        //  the properties "username" and "password".

        String credentialsPath = ResourcesDir + CredentialsFileName;
        try {
            byte[] jsonUTF8 = Files.readAllBytes(Paths.get(credentialsPath));
            String json = new String(jsonUTF8, StandardCharsets.UTF_8);
            Gson gson = (new GsonBuilder()).create();
            Credentials cred = gson.fromJson(json, Credentials.class);
            Username = cred.username;
            Password = cred.password;
        }
        catch (Exception exc) {
            throw new RuntimeException("Something went wrong reading the credentials file. To run the tests put a file \"" + CredentialsFileName + "\" containing an object with the properties \"username\" and \"password\" into the folder \"src/test/resources\". Exception message: \"" + exc.getMessage() + "\"");
        }
    }

    public ReportingCloudTest() throws IOException {
        String testDocPath = ResourcesDir + TestTemplateName;
        _testDocData = Files.readAllBytes(Paths.get(testDocPath));
    }

    @Before
    public void initialize() throws Exception {
        _r = new ReportingCloud(Username, Password);
    }

    @Test
    public void getTemplateCount() throws Exception {
        // ToDo: Adapt the following to your own template storage.
        int nCount = _r.getTemplateCount();
        Assert.assertEquals(5, nCount);
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
        Assert.assertEquals(5, templates.size());

        // ToDo: Adapt the following to your own template storage.
        Assert.assertEquals("sample_invoice.tx", templates.get(4).getTemplateName());
        Assert.assertEquals(34845, templates.get(4).getSize());
        Assert.assertEquals(ZonedDateTime.parse("2016-04-15T19:05:18+00:00"), templates.get(4).getModified());
    }

    @Test
    public void convertDocument() throws Exception {
        byte[] converted = _r.convertDocument(_testDocData, ReturnFormat.PDF, true);

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
        Assert.assertEquals(ZonedDateTime.parse("2026-12-08T08:00+00:00"), as.getValidUntil());
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
    public void mergeDocument_usingIterable() throws Exception {
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
        List<byte[]> mergeResult = _r.mergeDocument(mb, "sample_invoice.tx", ReturnFormat.PDF, false, true);

        Assert.assertEquals(2, mergeResult.size());

        // Check for PDF magic number
        Assert.assertEquals(0x25, mergeResult.get(0)[0]);
        Assert.assertEquals(0x50, mergeResult.get(0)[1]);
        Assert.assertEquals(0x44, mergeResult.get(0)[2]);
        Assert.assertEquals(0x46, mergeResult.get(0)[3]);
    }

    @Test
    public void mergeDocument_usingArray() throws Exception {
        // Generate some dummy data:
        MasterItem[] md = {
                new MasterItem("Will Ferrell", "Colin Farrell"),
                new MasterItem("Morgan Freeman", "Martin Freeman")
        };
        md[0].item.add(new NestedItem(23, "An Item.", 234.56));
        md[0].item.add(new NestedItem(34, "Another item.", 345.67));
        md[1].item.add(new NestedItem(45, "Yet another item.", 456.78));
        md[1].item.add(new NestedItem(45, "And another one.", 567.89));

        // Prepare merge settings and merge body objects
        MergeSettings ms = new MergeSettings();
        ms.setAuthor("John Doe");
        ms.setDocumentTitle("A document merged by Text Control ReportingCloud.");
        MergeBody mb = new MergeBody(md, ms);

        // Merge the document
        List<byte[]> mergeResult = _r.mergeDocument(mb, "sample_invoice.tx", ReturnFormat.PDF, false, true);

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

    @Test
    public void getTemplateInfo() throws Exception {
        String templateName = "invoice.tx";
        TemplateInfo info = _r.getTemplateInfo(templateName);
        Assert.assertEquals(templateName, info.getTemplateName());
    }

    @Test
    public void findAndReplace() throws Exception {
        // ToDo: Adapt the following to your own template storage.
        String templateName = "invoice.tx";
        List<Template> templates = _r.listTemplates();
        Assert.assertTrue(templates.stream().anyMatch(t -> t.getTemplateName().equals(templateName)));
        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair<>("Quick Facts", "Awesome Facts"));
        data.add(new Pair<>("Total Due", "IOU"));
        FindAndReplaceBody frb = new FindAndReplaceBody(data);
        byte[] result = _r.findAndReplace(frb, templateName, ReturnFormat.HTML, true);
        String html = new String(result, "UTF-8");
        Assert.assertTrue(html.contains("Awesome Facts"));
        Assert.assertTrue(html.contains("IOU"));
        Assert.assertFalse(html.contains("Quick Facts"));
        Assert.assertFalse(html.contains("Total Due"));
    }

    @Test
    public void listFonts() throws Exception {
        List<String> fontNames = _r.listFonts();
        Assert.assertTrue(fontNames.size() > 0);
        Assert.assertTrue(fontNames.stream().anyMatch(name -> name.equals("Arial")));
    }

    @Test
    public void checkText() throws Exception {
        List<IncorrectWord> incorrectWords = _r.checkText("Thiss is a testt", "en_US.dic");
        Assert.assertEquals(2, incorrectWords.size());
        IncorrectWord iw = incorrectWords.get(0);
        Assert.assertEquals(5, iw.getLength());
        Assert.assertEquals(0, iw.getStart());
        Assert.assertEquals("Thiss", iw.getText());
        Assert.assertFalse(iw.isDuplicate());
        Assert.assertEquals("en_US.dic", iw.getLanguage());
        iw = incorrectWords.get(1);
        Assert.assertEquals(5, iw.getLength());
        Assert.assertEquals(11, iw.getStart());
        Assert.assertEquals("testt", iw.getText());
        Assert.assertFalse(iw.isDuplicate());
        Assert.assertEquals("en_US.dic", iw.getLanguage());
    }

    @Test
    public void getAvailableDictionaries() throws Exception {
        List<String> dicts = _r.getAvailableDictionaries();
        Assert.assertFalse(dicts.isEmpty());
        Assert.assertTrue(dicts.stream().anyMatch(dict -> dict.equals("en_US.dic")));
        Assert.assertTrue(dicts.stream().anyMatch(dict -> dict.equals("fr.dic")));
        Assert.assertTrue(dicts.stream().anyMatch(dict -> dict.equals("en_GB.dic")));
    }

    @Test
    public void getSuggestions() throws Exception {
        List<String> suggestions = _r.getSuggestions("Thiss", "en_US.dic", 10);
        Assert.assertEquals(10, suggestions.size());
        Assert.assertEquals("This", suggestions.get(0));
        Assert.assertEquals("Hiss", suggestions.get(1));
        Assert.assertEquals("Thesis", suggestions.get(2));
    }

    @Test
    public void getAPIKeys() throws Exception {
        // ToDo: Adapt the following to your own account settings.
        List<APIKey> keys = _r.getAPIKeys();
        Assert.assertEquals(keys.size(), 2);
        for (APIKey key : keys) {
            Assert.assertTrue(isAPIKey(key.getKey()));
        }
    }

    @Test
    public void createAndDeleteAPIKey() throws Exception {
        // ToDo: Adapt the following to your own account settings.
        String key = _r.createAPIKey();
        Assert.assertTrue(isAPIKey(key));
        List<APIKey> keys = _r.getAPIKeys();
        Assert.assertEquals(keys.size(), 3);
        Assert.assertTrue(keys.stream().anyMatch(k -> k.getKey().equals(key)));
        _r.deleteAPIKey(key);
        keys = _r.getAPIKeys();
        Assert.assertEquals(keys.size(), 2);
        Assert.assertFalse(keys.stream().anyMatch(k -> k.getKey().equals(key)));
    }

    private boolean isAPIKey(String text) {
        return text.matches("[a-zA-Z0-9]*");
    }
}