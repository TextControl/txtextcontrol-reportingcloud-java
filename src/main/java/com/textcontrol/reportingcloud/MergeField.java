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

/**
 * Represents a merge field in a document template.
 *
 * @author Thorsten Kummerow
 */
public class MergeField {
    private final String _dateTimeFormat;
    private final String _name;
    private final String _numericFormat;
    private final boolean _bPreserveFormatting;
    private final String _text;
    private final String _textAfter;
    private final String _textBefore;


    public MergeField(String dateTimeFormat, String name, String numericFormat, boolean preserveFormatting, String text, String textAfter, String textBefore) {
        this._dateTimeFormat = dateTimeFormat;
        this._name = name;
        this._numericFormat = numericFormat;
        this._bPreserveFormatting = preserveFormatting;
        this._text = text;
        this._textAfter = textAfter;
        this._textBefore = textBefore;
    }

    /**
     * Returns the format which is applied to date / time values.
     *
     * @return The format which is applied to date / time values.
     */
    public String getDateTimeFormat() {
        return _dateTimeFormat;
    }

    /**
     * Returns the name of the field.
     *
     * @return The name of the field.
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns the format which is applied to numeric values.
     *
     * @return The format which is applied to numeric values.
     */
    public String getNumericFormat() {
        return _numericFormat;
    }

    /**
     * Returns whether formatting is preserved.
     *
     * @return Returns whether formatting is preserved.
     */
    public boolean getPreserveFormatting() {
        return _bPreserveFormatting;
    }

    /**
     * Returns the field text.
     *
     * @return The field text.
     */
    public String getText() {
        return _text;
    }

    /**
     * Returns the text after the field.
     *
     * @return The text after the field.
     */
    public String getTextAfter() {
        return _textAfter;
    }

    /**
     * Returns the text before the field.
     *
     * @return The text before the field.
     */
    public String getTextBefore() {
        return _textBefore;
    }

}
