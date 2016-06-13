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

/**
 * Holds the merge settings needed by the merge method.
 *
 * @author Thorsten Kummerow
 */
public class MergeSettings {
    private boolean _removeEmptyFields;
    private boolean _removeEmptyBlocks;
    private boolean _removeEmptyImages;
    private boolean _removeTrailingWhitespace;
    private String _author;
    private String _creatorApplication;
    private String _documentSubject;
    private String _documentTitle;
    private String _userPassword;

    public MergeSettings() {
        this._removeEmptyFields = true;
        this._removeEmptyBlocks = true;
        this._removeEmptyImages = true;
        this._removeTrailingWhitespace = true;
        this._author = null;
        this._creatorApplication = null;
        this._documentSubject = null;
        this._documentTitle = null;
        this._userPassword = null;
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
     * @param removeEmptyImages images which don't have merge data should be
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
     * @param removeTrailingWhitespace trailing whitespace should be removed before
     *                                 saving a document.
     */
    public void setRemoveTrailingWhitespace(boolean removeTrailingWhitespace) {
        this._removeTrailingWhitespace = removeTrailingWhitespace;
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
}
