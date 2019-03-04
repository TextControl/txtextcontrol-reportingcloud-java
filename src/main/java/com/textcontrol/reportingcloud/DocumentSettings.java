/**
 * ReportingCloud Java SDK
 *
 * Official Java SDK for the ReportingCloud Web API. Authored, maintained and fully supported
 * by Text Control GmbH. (http://www.textcontrol.com).
 *
 * Go to http://www.reporting.cloud to learn more about ReportingCloud
 * Go to https://github.com/TextControl/txtextcontrol-reportingcloud-java for the
 * canonical source repository.
 *
 * License: https://raw.githubusercontent.com/TextControl/txtextcontrol-reportingcloud-java/master/LICENSE.md
 *
 * Copyright: © 2019 Text Control GmbH
 */
package com.textcontrol.reportingcloud;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Holds the merge settings needed by the {@link com.textcontrol.reportingcloud.ReportingCloud#appendDocuments appendDocuments}
 * method.
 *
 * @author Thorsten Kummerow
 */
public class DocumentSettings {
    private String _author;
    private String _creatorApplication;
    private String _documentSubject;
    private String _documentTitle;
    private String _userPassword;
    private ZonedDateTime _lastModificationDate;
    private ZonedDateTime _creationDate;

    public DocumentSettings() {
        this._author = null;
        this._creatorApplication = null;
        this._documentSubject = null;
        this._documentTitle = null;
        this._userPassword = null;
        this._lastModificationDate = null;
        this._creationDate = null;
    }

    /**
     * Gets the document's author.
     *
     * @return The document's author.
     */
    public String getAuthor() {
        return _author;
    }

    /**
     * Sets the document's author.
     *
     * @param author The document's author.
     */
    public void setAuthor(String author) {
        this._author = author;
    }

    /**
     * Gets the application which created the document.
     *
     * @return The application which created the document.
     */
    public String getCreatorApplication() {
        return _creatorApplication;
    }

    /**
     * Sets the application which created the document.
     *
     * @param creatorApplication The application which created the document.
     */
    public void setCreatorApplication(String creatorApplication) {
        this._creatorApplication = creatorApplication;
    }

    /**
     * Gets the document's subject.
     *
     * @return The document's subject.
     */
    public String getDocumentSubject() {
        return _documentSubject;
    }

    /**
     * Sets the document's subject.
     *
     * @param documentSubject The document's subject.
     */
    public void setDocumentSubject(String documentSubject) {
        this._documentSubject = documentSubject;
    }

    /**
     * Gets the document's title.
     *
     * @return The document's title.
     */
    public String getdocumentTitle() {
        return _documentTitle;
    }

    /**
     * Sets the document's title.
     *
     * @param documentTitle The document's title.
     */
    public void setDocumentTitle(String documentTitle) {
        this._documentTitle = documentTitle;
    }

    /**
     * Gets the password needed to open the document.
     *
     * @return The password needed to open the document.
     */
    public String getUserPassword() {
        return _userPassword;
    }

    /**
     * Sets the password needed to open the document.
     *
     * @param userPassword The password needed to open the document.
     */
    public void setUserPassword(String userPassword) {
        this._userPassword = userPassword;
    }

    /**
     * Gets the creation date.
     *
     * @return The creation date.
     */
    public ZonedDateTime getCreationDate() {
        return _creationDate;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate The creation date (ISO-8601 extended offset date-time format).
     *                     Can be null.
     */
    public void setCreationDate(String creationDate) throws DateTimeParseException {
        if (creationDate == null) _creationDate = null;
        else _creationDate = ZonedDateTime.parse(creationDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate The creation date. Can be null.
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        _creationDate = creationDate;
    }

    /**
     * Gets the last modification date.
     *
     * @return The last modification date.
     */
    public ZonedDateTime getLastModificationDate() {
        return _lastModificationDate;
    }

    /**
     * Sets the last modification date.
     *
     * @param lastModificationDate The last modification date (ISO-8601 extended offset date-time format).
     *                             Can be null.
     */
    public void setLastModificationDate(String lastModificationDate) throws DateTimeParseException {
        if (lastModificationDate == null) _lastModificationDate = null;
        else _lastModificationDate = ZonedDateTime.parse(lastModificationDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /**
     * Sets the last modification date.
     *
     * @param lastModificationDate The last modification date. Can be null.
     */
    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        _lastModificationDate = lastModificationDate;
    }
}
