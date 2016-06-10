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

import java.util.HashMap;

/**
 * Created by thorsten on 10.06.2016.
 */
public class MergeBody {

    private byte[] _template;
    private MergeSettings _mergeSettings;
    private HashMap<String, Object> _mergeData;

    public MergeBody(HashMap<String, Object> mergeData) {
        this(mergeData, null);
    }

    public MergeBody(HashMap<String, Object> mergeData, MergeSettings mergeSettings) {
        this(mergeData, mergeSettings, null);
    }

    public MergeBody(HashMap<String, Object> mergeData, MergeSettings mergeSettings, byte[] template) {
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

    public HashMap<String, Object> getMergeData() {
        return _mergeData;
    }

    public void setMergeData(HashMap<String, Object> mergeData) {
        this._mergeData = mergeData;
    }
}
