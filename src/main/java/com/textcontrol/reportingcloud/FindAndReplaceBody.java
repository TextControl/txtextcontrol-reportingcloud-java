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

import javafx.util.Pair;
import java.util.List;

/**
 * Passes data to the find and replace method.
 *
 * @author Thorsten Kummerow
 */
public class FindAndReplaceBody {

    private MergeSettings _mergeSettings;
    private byte[] _templateData;
    private List<Pair<String, String>> _findAndReplaceData;

    /**
     * @param findAndReplaceData The find and replace pair values.
     * @param templateData The source document. The supported document formats
     *                     are <tt>.rtf</tt>, <tt>.doc</tt>, <tt>.docx</tt>, and <tt>.tx</tt>.
     * @param mergeSettings Merge settings to specify merge properties and document
     *                      properties such as title and author.
     */
    public FindAndReplaceBody(List<Pair<String, String>> findAndReplaceData, byte[] templateData, MergeSettings mergeSettings) {
        setFindAndReplaceData(findAndReplaceData);
        _mergeSettings = mergeSettings;
        setTemplateData(templateData);
    }

    /**
     * @param findAndReplaceData The find and replace pair values.
     * @param templateData The source document. The supported document formats
     *                     are <tt>.rtf</tt>, <tt>.doc</tt>, <tt>.docx</tt>, and <tt>.tx</tt>.
     */
    public FindAndReplaceBody(List<Pair<String, String>> findAndReplaceData, byte[] templateData) {
        this(findAndReplaceData, templateData, null);
    }

    /**
     * @param findAndReplaceData The find and replace pair values.
     */
    public FindAndReplaceBody(List<Pair<String, String>> findAndReplaceData) {
        this(findAndReplaceData, null);
    }

    /**
     * Returns the merge settings object.
     *
     * @return The merge settings object.
     */
    public MergeSettings getMergeSettings() {
        return _mergeSettings;
    }

    /**
     * Sets the merge settings.
     *
     * @param mergeSettings The merge settings.
     */
    public void setMergeSettings(MergeSettings mergeSettings) {
        this._mergeSettings = mergeSettings;
    }

    /**
     * Returns the template document data.
     *
     * @return The template document data.
     */
    public byte[] getTemplateData() {
        return _templateData;
    }

    /**
     * Sets the template document data.
     *
     * @param templateData The template document data.
     */
    public void setTemplateData(byte[] templateData) {
        if ((templateData != null) && (templateData.length == 0)) templateData = null;
        this._templateData = templateData;
    }

    /**
     * Gets the find and replace data.
     *
     * @return The find and replace data.
     */
    public List<Pair<String, String>> getFindAndReplaceData() {
        return _findAndReplaceData;
    }

    /**
     * Sets find and replace data.
     *
     * @param findAndReplaceData Find and replace data.
     */
    public void setFindAndReplaceData(List<Pair<String, String>> findAndReplaceData) {
        if (findAndReplaceData == null) throw new NullPointerException("Parameter \"findAndReplaceData\" must not be null.");
        if (findAndReplaceData.isEmpty()) throw new IllegalArgumentException("findAndReplaceData must not be empty.");
        this._findAndReplaceData = findAndReplaceData;
    }
}
