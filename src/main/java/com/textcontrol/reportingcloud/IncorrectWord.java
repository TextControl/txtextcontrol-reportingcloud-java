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

/**
 * Represents an incorrect word in a spell checked text.
 *
 * @author Thorsten Kummerow
 */
public class IncorrectWord {

    private int _length;
    private int _start;
    private String _text;
    private boolean _isDuplicate;
    private String _language;

    public IncorrectWord(int length, int start, String text, boolean isDuplicate, String language) {
        this._length = length;
        this._start = start;
        this._text = text;
        this._isDuplicate = isDuplicate;
        this._language = language;
    }

    /**
     * Returns the length of the spelled word.
     *
     * @return  The length of the spelled word.
     */
    public int getLength() {
        return _length;
    }

    /**
     * Returns the starting position of a spelled word.
     *
     * @return  The starting position of a spelled word.
     */
    public int getStart() {
        return _start;
    }

    /**
     * Returns the text of the spelled word.
     *
     * @return  The text of the spelled word.
     */
    public String getText() {
        return _text;
    }

    /**
     * Returns a value indicating whether the spelled word is declared as incorrect,
     * because the previous word has the same text.
     *
     * @return  a value indicating whether the spelled word is declared as incorrect
     *          because the previous word has the same text.
     */
    public boolean isDuplicate() {
        return _isDuplicate;
    }

    /**
     * Returns a value indicating the language the incorrect word was spelled.
     *
     * @return   A value indicating the language the incorrect word was spelled.
     */
    public String getLanguage() {
        return _language;
    }
}
