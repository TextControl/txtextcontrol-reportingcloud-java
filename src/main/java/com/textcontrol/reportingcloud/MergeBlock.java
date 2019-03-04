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

import java.util.List;

/**
 * Represents a merge block in a document template.
 *
 * @author Thorsten Kummerow
 */
public class MergeBlock {
    private final String _name;
    private final List<MergeBlock> _mergeBlocks;
    private final List<MergeField> _mergeFields;

    public MergeBlock(String name, List<MergeBlock> mergeBlocks, List<MergeField> mergeFields) {
        this._name = name;
        this._mergeBlocks = mergeBlocks;
        this._mergeFields = mergeFields;
    }

    /**
     * Returns the merge block's name.
     *
     * @return The merge block's name.
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns the merge blocks inside the merge block.
     *
     * @return The merge blocks inside the merge block.
     */
    public List<MergeBlock> getMergeBlocks() {
        return _mergeBlocks;
    }

    /**
     * Returns the merge fields inside the merge block.
     *
     * @return The merge fields inside the merge block.
     */
    public List<MergeField> getMergeFields() {
        return _mergeFields;
    }
}
