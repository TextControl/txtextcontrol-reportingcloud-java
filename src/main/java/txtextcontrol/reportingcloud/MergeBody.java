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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thorsten on 10.06.2016.
 */
public class MergeBody {

    private byte[] _template;
    private MergeSettings _mergeSettings;
    private List<Object> _mergeData;

    public MergeBody(List<Object> mergeData) throws InvalidParameterException {
        this(mergeData, null);
    }

    public MergeBody(List<Object> mergeData, MergeSettings mergeSettings) throws InvalidParameterException {
        this(mergeData, mergeSettings, null);
    }

    public MergeBody(List<Object> mergeData, MergeSettings mergeSettings, byte[] template) throws InvalidParameterException {
        if (mergeData == null) {
            throw new InvalidParameterException("mergeData must not be null.");
        }

        this._template = template;
        this._mergeSettings = mergeSettings;
        this._mergeData = mergeData;
    }

    public byte[] getTemplate() {
        return _template;
    }

    public void setTemplate(byte[] template) {
        this._template = template;
    }

    public MergeSettings getMergeSettings() {
        return _mergeSettings;
    }

    public void setMergeSettings(MergeSettings mergeSettings) {
        this._mergeSettings = mergeSettings;
    }

    public List<Object> getMergeData() {
        return _mergeData;
    }

    public void setMergeData(List<Object> mergeData) {
        this._mergeData = mergeData;
    }
}
