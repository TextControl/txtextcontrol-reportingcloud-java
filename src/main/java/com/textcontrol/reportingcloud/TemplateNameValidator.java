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

import java.security.InvalidParameterException;

/**
 * Internal method parameter validator class.
 *
 * @author Thorsten Kummerow
 */
class TemplateNameValidator {
    /**
     * Checks if a template name contains something. Throws an exception if not.
     *
     * @param templateName The Template name.
     */
    public static void validate(String templateName) throws InvalidParameterException {
        if ((templateName == null) || templateName.length() == 0) {
            throw new InvalidParameterException("No template name given.");
        }
    }
}
