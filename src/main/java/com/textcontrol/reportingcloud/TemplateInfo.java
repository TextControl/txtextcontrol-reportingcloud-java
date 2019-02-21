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

import java.util.List;

/**
 * Holds information about the merge blocks and merge fields in a template.
 *
 * @author Thorsten Kummerow
 */
public class TemplateInfo {
    private final String _templateName;
    private final List<MergeBlock> _mergeBlocks;
    private final List<MergeField> _mergeFields;

    /**
     * @param templateName The template file name.
     * @param mergeBlocks The top level merge blocks in the template.
     * @param mergeFields The top level merge fields in the template.
     */
    public TemplateInfo(String templateName, List<MergeBlock> mergeBlocks, List<MergeField> mergeFields) {
        this._templateName = templateName;
        this._mergeBlocks = mergeBlocks;
        this._mergeFields = mergeFields;
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
     * Gets all top level merge blocks in the template.
     *
     * @return All top level merge blocks in the template.
     */
    public List<MergeBlock> getMergeBlocks() {
        return _mergeBlocks;
    }

    /**
     * Gets all top level merge fields in the template.
     *
     * @return All top level merge fields in the template.
     */
    public List<MergeField> getMergeFields() {
        return _mergeFields;
    }
}
