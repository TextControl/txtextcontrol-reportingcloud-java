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
 * Represents a ReportingCloud API key object.
 *
 * @author Thorsten Kummerow
 */
public class APIKey {

    private String _key;
    private boolean _isActive;

    /**
     * @param key The actual API Key that belongs to the account
     * @param isActive Specifies whether the API Key is active or not (not used yet).
     */
    public APIKey(String key, boolean isActive) {
        _key = key;
        _isActive = isActive;
    }

    /**
     * Returns the API key.
     * @return The API key.
     */
    public String getKey() { return _key; }

    /**
     * Returns whether the API key is active or not (not used yet).
     * @return The state of the API key (active or inactive).
     */
    public boolean getIsActive() { return _isActive; }
}
