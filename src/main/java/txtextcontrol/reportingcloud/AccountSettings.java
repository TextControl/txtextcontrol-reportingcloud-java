/**
 * ReportingCloud Java Wrapper
 *
 * Official wrapper (authored by Text Control GmbH, publisher of ReportingCloud) to access
 * ReportingCloud in Java.
 *
 * Go to http://www.reporting.cloud to learn more about ReportingCloud
 * Go to https://github.com/TextControl/txtextcontrol-reportingcloud-java for the
 * canonical source repository.
 *
 * License: https://raw.githubusercontent.com/TextControl/txtextcontrol-reportingcloud-java/master/LICENSE.md
 *
 * Copyright: © 2016 Text Control GmbH
 */
package txtextcontrol.reportingcloud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by thorsten on 10.06.2016.
 */
public class AccountSettings {

    private String _serialNumber;
    private int _createdDocuments;
    private int _uploadedTemplates;
    private int _maxDocuments;
    private int _maxTemplates;
    private LocalDateTime _validUntil;

    public AccountSettings(String serialNumber, int createdDocuments, int uploadedTemplates, int maxDocuments, int maxTemplates, String validUntil) {
        this._serialNumber = serialNumber;
        this._createdDocuments = createdDocuments;
        this._uploadedTemplates = uploadedTemplates;
        this._maxDocuments = maxDocuments;
        this._maxTemplates = maxTemplates;
        this._validUntil = LocalDateTime.parse(validUntil, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String getSerialNumber() {
        return _serialNumber;
    }

    public int getCreatedDocuments() {
        return _createdDocuments;
    }

    public int getUploadedTemplates() {
        return _uploadedTemplates;
    }

    public int getMaxDocuments() {
        return _maxDocuments;
    }

    public int getMaxTemplates() {
        return _maxTemplates;
    }

    public LocalDateTime getValidUntil() {
        return _validUntil;
    }
}
