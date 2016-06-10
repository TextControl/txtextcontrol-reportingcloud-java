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
 * Created by thorsten on 10.06.2016.
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

    public boolean getRemoveEmptyFields() {
        return _removeEmptyFields;
    }

    public void setRemoveEmptyFields(boolean removeEmptyFields) {
        this._removeEmptyFields = removeEmptyFields;
    }

    public boolean getRemoveEmptyBlocks() {
        return _removeEmptyBlocks;
    }

    public void setRemoveEmptyBlocks(boolean removeEmptyBlocks) {
        this._removeEmptyBlocks = removeEmptyBlocks;
    }

    public boolean getRemoveEmptyImages() {
        return _removeEmptyImages;
    }

    public void setRemoveEmptyImages(boolean removeEmptyImages) {
        this._removeEmptyImages = removeEmptyImages;
    }

    public boolean getRemoveTrailingWhitespace() {
        return _removeTrailingWhitespace;
    }

    public void setRemoveTrailingWhitespace(boolean removeTrailingWhitespace) {
        this._removeTrailingWhitespace = removeTrailingWhitespace;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        this._author = author;
    }

    public String getCreatorApplication() {
        return _creatorApplication;
    }

    public void setCreatorApplication(String creatorApplication) {
        this._creatorApplication = creatorApplication;
    }

    public String getDocumentSubject() {
        return _documentSubject;
    }

    public void setDocumentSubject(String documentSubject) {
        this._documentSubject = documentSubject;
    }

    public String getdocumentTitle() {
        return _documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this._documentTitle = documentTitle;
    }

    public String getUserPassword() {
        return _userPassword;
    }

    public void setUserPassword(String userPassword) {
        this._userPassword = userPassword;
    }
}
