package txtextcontrol.reportingcloud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

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
}