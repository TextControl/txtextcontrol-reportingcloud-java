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
 * Copyright: © 2017 Text Control GmbH
 */
package com.textcontrol.reportingcloud;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Used to pass data to the merge method.
 *
 * @author Thorsten Kummerow
 */
public class MergeBody {

    private byte[] _template;
    private MergeSettings _mergeSettings;
    private Iterable<Object> _mergeData;

    /**
     * @param mergeData The merge data. Can be an iterable type of {@link java.util.HashMap} objects
     *                  or an iterable type of objects which can be serialized to JSON by GSON.
     */
    public MergeBody(Iterable<Object> mergeData) throws InvalidParameterException {
        this(mergeData, null);
    }

    /**
     * @param mergeData The merge data. Can be an array of {@link java.util.HashMap} objects
     *                  or an array of objects which can be serialized to JSON by GSON.
     */
    public MergeBody(Object[] mergeData) throws InvalidParameterException {
        this(mergeData, null);
    }

    /**
     * @param mergeData The merge data. Can be an iterable type of {@link java.util.HashMap} objects
     *                  or an iterable type of objects which can be serialized to JSON by GSON.
     * @param mergeSettings Merge settings to specify merge properties and document properties
     *                      such as title and author.
     */
    public MergeBody(Iterable<Object> mergeData, MergeSettings mergeSettings) throws InvalidParameterException {
        this(mergeData, mergeSettings, null);
    }

    /**
     * @param mergeData The merge data. Can be an array of {@link java.util.HashMap} objects
     *                  or an array of objects which can be serialized to JSON by GSON.
     * @param mergeSettings Merge settings to specify merge properties and document properties
     *                      such as title and author.
     */
    public MergeBody(Object[] mergeData, MergeSettings mergeSettings) throws InvalidParameterException {
        this(mergeData, mergeSettings, null);
    }

    /**
     * @param mergeData The merge data. Can be an iterable type of {@link java.util.HashMap} objects
     *                  or an iterable type of objects which can be serialized to JSON by GSON.
     * @param mergeSettings Merge settings to specify merge properties and document properties
     *                      such as title and author.
     * @param template The binary template document data. Supported formats are <tt>.rtf</tt>,
     *                 <tt>.doc</tt>, <tt>.docx</tt> and <tt>.tx</tt>.
     */
    public MergeBody(Iterable<Object> mergeData, MergeSettings mergeSettings, byte[] template) throws InvalidParameterException {
        this._template = template;
        this._mergeSettings = mergeSettings;
        this.setMergeData(mergeData);
    }

    /**
     * @param mergeData The merge data. Can be an array of {@link java.util.HashMap} objects
     *                  or an array of objects which can be serialized to JSON by GSON.
     * @param mergeSettings Merge settings to specify merge properties and document properties
     *                      such as title and author.
     * @param template The binary template document data. Supported formats are <tt>.rtf</tt>,
     *                 <tt>.doc</tt>, <tt>.docx</tt> and <tt>.tx</tt>.
     */
    public MergeBody(Object[] mergeData, MergeSettings mergeSettings, byte[] template) throws InvalidParameterException {
        this(Arrays.asList(mergeData), mergeSettings, template);
    }

    /**
     * Gets the binary template document data. Supported formats are <tt>.rtf</tt>, <tt>.doc</tt>,
     * <tt>.docx</tt> and <tt>.tx</tt>.
     *
     * @return The binary template document data.
     */
    public byte[] getTemplate() {
        return _template;
    }

    /**
     * Sets the binary template document data. Supported formats are <tt>.rtf</tt>, <tt>.doc</tt>,
     * <tt>.docx</tt> and <tt>.tx</tt>.
     *
     * @param template The binary template document data
     */
    public void setTemplate(byte[] template) {
        this._template = template;
    }

    /**
     * Gets the merge settings to specify merge properties and document properties such
     * as title and author.
     *
     * @return The merge settings.
     */
    public MergeSettings getMergeSettings() {
        return _mergeSettings;
    }

    /**
     * Sets the merge settings to specify merge properties and document properties such
     * as title and author.
     *
     * @param mergeSettings The merge settings.
     */
    public void setMergeSettings(MergeSettings mergeSettings) {
        this._mergeSettings = mergeSettings;
    }

    /**
     * Gets the merge data.
     *
     * @return The merge data.
     */
    public Iterable<Object> getMergeData() {
        return _mergeData;
    }

    /**
     * Sets the merge data. Can be an iterable type of {@link java.util.HashMap} objects or an
     * iterable type of objects which can be serialized to JSON by GSON.
     *
     * @param mergeData The merge data.
     */
    public void setMergeData(Iterable<Object> mergeData) throws InvalidParameterException {
        if (mergeData == null) {
            throw new InvalidParameterException("mergeData must not be null.");
        }
        this._mergeData = mergeData;
    }

    /**
     * Sets the merge data. Can be an array of {@link java.util.HashMap} objects or an array of objects
     * which can be serialized to JSON by GSON.
     *
     * @param mergeData The merge data.
     */
    public void setMergeData(Object[] mergeData) throws InvalidParameterException {
        if (mergeData == null) {
            throw new InvalidParameterException("mergeData must not be null.");
        }
        if (mergeData.length == 0) {
            throw new InvalidParameterException("mergeData must not be empty.");
        }
        this._mergeData = Arrays.asList(mergeData);
    }
}
