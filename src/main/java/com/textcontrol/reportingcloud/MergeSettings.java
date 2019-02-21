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
 * Copyright: © 2019 Text Control GmbH
 */
package com.textcontrol.reportingcloud;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Holds the merge settings needed by the {@link com.textcontrol.reportingcloud.ReportingCloud#mergeDocument mergeDocument}
 * method.
 *
 * @author Thorsten Kummerow
 */
public class MergeSettings {
    private boolean _removeEmptyFields;
    private boolean _removeEmptyBlocks;
    private boolean _removeEmptyImages;
    private boolean _removeTrailingWhitespace;
    private boolean _mergeHtml;
    private String _author;
    private String _creatorApplication;
    private String _documentSubject;
    private String _documentTitle;
    private String _userPassword;
    private ZonedDateTime _lastModificationDate;
    private ZonedDateTime _creationDate;

    public MergeSettings() {
        this._removeEmptyFields = true;
        this._removeEmptyBlocks = true;
        this._removeEmptyImages = true;
        this._removeTrailingWhitespace = true;
        this._mergeHtml = false;
        this._author = null;
        this._creatorApplication = null;
        this._documentSubject = null;
        this._documentTitle = null;
        this._userPassword = null;
        this._lastModificationDate = null;
        this._creationDate = null;
    }

    /**
     * Gets whether empty fields should be removed from the template or not.
     *
     * @return Empty fields should be removed.
     */
    public boolean getRemoveEmptyFields() {
        return _removeEmptyFields;
    }

    /**
     * Sets whether empty fields should be removed from the template or not.
     *
     * @param removeEmptyFields Empty fields should be removed or not.
     */
    public void setRemoveEmptyFields(boolean removeEmptyFields) {
        this._removeEmptyFields = removeEmptyFields;
    }

    /**
     * Gets whether the content of empty merge blocks should be removed from the template
     * or not.
     *
     * @return The content of empty merge blocks should be removed.
     */
    public boolean getRemoveEmptyBlocks() {
        return _removeEmptyBlocks;
    }

    /**
     * Sets whether the content of empty merge blocks should be removed from the template
     * or not.
     *
     * @param removeEmptyBlocks The content of empty merge blocks should be removed
     *                          from the template or not.
     */
    public void setRemoveEmptyBlocks(boolean removeEmptyBlocks) {
        this._removeEmptyBlocks = removeEmptyBlocks;
    }

    /**
     * Gets whether images which don't have merge data should be removed from
     * the template or not.
     *
     * @return The images which don't have merge data should be removed.
     */
    public boolean getRemoveEmptyImages() {
        return _removeEmptyImages;
    }

    /**
     * Sets whether images which don't have merge data should be removed from
     * the template or not.
     *
     * @param removeEmptyImages Images which don't have merge data should be
     *                          removed from the template or not.
     */
    public void setRemoveEmptyImages(boolean removeEmptyImages) {
        this._removeEmptyImages = removeEmptyImages;
    }

    /**
     * Gets whether trailing whitespace should be removed before saving a document.
     *
     * @return Trailing whitespace should be removed.
     */
    public boolean getRemoveTrailingWhitespace() {
        return _removeTrailingWhitespace;
    }

    /**
     * Sets whether trailing whitespace should be removed before saving a document.
     *
     * @param removeTrailingWhitespace Trailing whitespace should be removed before
     *                                 saving a document.
     */
    public void setRemoveTrailingWhitespace(boolean removeTrailingWhitespace) {
        this._removeTrailingWhitespace = removeTrailingWhitespace;
    }

    /**
     * Returns whether field data can contain formatted Html content or not.
     *
     * @return Field data can contain formatted Html content or not.
     */
    public boolean getMergeHtml() {
        return this._mergeHtml;
    }

    /**
     * Sets whether field data can contain formatted Html content or not.
     *
     * @param mergeHtml Field data can contain formatted Html content or not.
     */
    public void setMergeHtml(boolean mergeHtml) {
        this._mergeHtml = mergeHtml;
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
