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

/**
 * Holds the merge settings needed by the {@link com.textcontrol.reportingcloud.ReportingCloud#mergeDocument mergeDocument}
 * method.
 *
 * @author Thorsten Kummerow
 */
public class MergeSettings extends DocumentSettings {
    private boolean _removeEmptyFields;
    private boolean _removeEmptyBlocks;
    private boolean _removeEmptyImages;
    private boolean _removeTrailingWhitespace;
    private boolean _mergeHtml;

    public MergeSettings() {
        this._removeEmptyFields = true;
        this._removeEmptyBlocks = true;
        this._removeEmptyImages = true;
        this._removeTrailingWhitespace = true;
        this._mergeHtml = false;
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
}
