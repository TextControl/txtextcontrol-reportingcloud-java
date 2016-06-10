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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Thorsten Kummerow (@thomerow)
 */
public class Template {

    private String _templateName;
    private LocalDateTime _modified;
    private int _size;

    public Template(String templateName, String modified, int size) {
        this._templateName = templateName;
        this._modified = LocalDateTime.parse(modified, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this._size = size;
    }



    public String getTemplateName() {
        return _templateName;
    }

    public int getSize() {
        return _size;
    }

    public LocalDateTime getModified() {
        return _modified;
    }
}
