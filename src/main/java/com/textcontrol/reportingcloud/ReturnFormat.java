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
package com.textcontrol.reportingcloud;

/**
 * This enum lists all possible return formats for the Merge method.
 *
 * @author Thorsten Kummerow
 */
public enum ReturnFormat {
    /**
     * Adobe PDF
     */
    PDF,

    /**
     * Adobe PDF/A
     */
    PDFA,

    /**
     * Microsoft Word 97-2003
     */
    DOC,

    /**
     * Microsoft Office Open XML
     */
    DOCX,

    /**
     * Rich Text Format
     */
    RTF,

    /**
     * TX Text Control Internal Format
     */
    TX,

    /**
     * Hypertext Markup Language
     */
    HMTL
}