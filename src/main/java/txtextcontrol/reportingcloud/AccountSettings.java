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
 * Represents ReportingCloud account settings.
 *
 * @author Thorsten Kummerow
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

    /**
     * Gets the serial number that is attached to the
     * account. Possible values are <tt>"FREE"</tt>, <tt>"TRIAL"</tt> and a 13 character long serial number.
     *
     * @return The serial number.
     */
    public String getSerialNumber() {
        return _serialNumber;
    }

    /**
     * Gets the number of created documents in the current month.
     *
     * @return The number of created documents.
     */
    public int getCreatedDocuments() {
        return _createdDocuments;
    }

    /**
     * Gets the number of uploaded templates to the template storage.
     *
     * @return The number of uploaded templates.
     */
    public int getUploadedTemplates() {
        return _uploadedTemplates;
    }

    /**
     * Gets the maximum number of documents that can be created per month.
     *
     * @return The maximum number of documents.
     */
    public int getMaxDocuments() {
        return _maxDocuments;
    }

    /**
     * Gets the maximum number of templates that can be uploaded to the template storage.
     *
     * @return The maximum number of templates.
     */
    public int getMaxTemplates() {
        return _maxTemplates;
    }

    /**
     * Gets the date until the current subscription is valid. Can be nil.
     *
     * @return The date until the current subscription is valid.
     */
    public LocalDateTime getValidUntil() {
        return _validUntil;
    }
}
