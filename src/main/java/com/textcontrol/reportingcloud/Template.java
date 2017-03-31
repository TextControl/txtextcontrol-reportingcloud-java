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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Holds information about a template in the template storage.
 *
 * @author Thorsten Kummerow
 */
public class Template {

    private String _templateName;
    private ZonedDateTime _modified;
    private int _size;

    /**
     * Creates a new Template instance.
     * @param templateName The template file name.
     * @param modified The date and time the template file was last modified (ISO 8601 date and
     *                 time string).
     * @param size The size of the template file in bytes.
     */
    public Template(String templateName, String modified, int size) {
        this._templateName = templateName;
        this._modified = ZonedDateTime.parse(modified, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this._size = size;
    }

    /**
     * Gets the template file name.
     *
     * @return The template file name.
     */
    public String getTemplateName() {
        return _templateName;
    }

    /**
     * Gets the date and time the template file was last modified
     *
     * @return The modification date.
     */
    public ZonedDateTime getModified() {
        return _modified;
    }

    /**
     * Gets the size of the template file in bytes.
     *
     * @return The template size.
     */
    public int getSize() {
        return _size;
    }
}
