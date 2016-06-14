package com.textcontrol.reportingcloud;

import org.junit.Test;

import java.security.InvalidParameterException;

/**
 * The TemplateDataValidator test class.
 *
 * @author Thorsten Kummerow
 */
public class TemplateDataValidatorTest {

    @Test(expected = InvalidParameterException.class)
    public void validate_throwsOnEmptyData() throws Exception {
        TemplateDataValidator.validate(new byte[0]);
    }

    @Test(expected = InvalidParameterException.class)
    public void validate_throwsOnNull() throws Exception {
        TemplateDataValidator.validate(null);
    }

    @Test
    public void validate_validatesNonEmptyData() {
        TemplateDataValidator.validate(new byte[] { 23, 45, 23, 56, 89 });  // Random data
    }
}