package com.textcontrol.reportingcloud;

import org.junit.Test;

import java.security.InvalidParameterException;

/**
 * The TemplateNameValidator test class.
 *
 * @author Thorsten Kummerow
 */
public class TemplateNameValidatorTest {

    @Test(expected = InvalidParameterException.class)
    public void validate_throwsOnEmptyData() throws Exception {
        TemplateNameValidator.validate("");
    }

    @Test(expected = InvalidParameterException.class)
    public void validate_throwsOnNull() throws Exception {
        TemplateNameValidator.validate(null);
    }

    @Test
    public void validate_validatesNonEmptyData() {
        TemplateNameValidator.validate("random string");
    }
}