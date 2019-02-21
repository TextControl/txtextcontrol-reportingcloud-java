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
 * This enum lists all possible document divider options used by method
 * {@link com.textcontrol.reportingcloud.ReportingCloud#appendDocuments appendDocuments}.
 *
 * @author Thorsten Kummerow
 */
public enum DocumentDivider {

    /**
     * Joint Photographic Experts Group
     */
    None,

    /**
     * Portable Network Graphics
     */
    NewParagraph,

    /**
     * Windows Bitmap
     */
    NewSection;

    /**
     * Returns the value expected by the ReportingCloud API.
     * @return The value expected by the ReportingCloud API.
     */
    public int toInt() {
        switch (this) {
            case None: return 1;
            case NewParagraph: return 2;
            case NewSection: return 3;
        }
        return 0;
    }
}