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

import java.security.InvalidParameterException;

/**
 * Internal method parameter validator class.
 *
 * @author Thorsten Kummerow (@thomerow)
 */
public class TemplateDataValidator {
    public static void validate(byte[] templateData) {
        if ((templateData == null) || (templateData.length == 0)) {
            throw new InvalidParameterException("No template data provided.");
        }
    }
}
