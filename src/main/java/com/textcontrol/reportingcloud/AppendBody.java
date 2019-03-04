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
import java.util.List;

/**
 * Passes data to the {@link com.textcontrol.reportingcloud.ReportingCloud#appendDocuments appendDocuments}
 * method.
 *
 * @author Thorsten Kummerow
 */
public class AppendBody {

    private List<AppendDocument> _documents;
    private DocumentSettings _documentSettings;

    /**
     * Combines documents by appending them divided by a new section, paragraph or nothing.
     * @param documents The documents that are appended including divider options.
     */
    public AppendBody(List<AppendDocument> documents) throws InvalidParameterException {
        this(documents, null);
    }

    /**
     * Combines documents by appending them divided by a new section, paragraph or nothing.
     * @param documents The documents that are appended including divider options.
     * @param documentSettings Document settings to specify document properties such as title and
     *                         author.
     */
    public AppendBody(List<AppendDocument> documents, DocumentSettings documentSettings) throws InvalidParameterException {
        if (documents.size() == 0) throw new InvalidParameterException("No documents provided.");
        _documents = documents;
        _documentSettings = documentSettings;
    }

    /**
     * Returns the documents.
     * @return The documents.
     */
    public List<AppendDocument> getDocuments() { return _documents; }

    /**
     * Sets the list of documents.
     * @param documents The list of documents.
     */
    public void setDocuments(List<AppendDocument> documents) { _documents = documents; }

    /**
     * Returns the document settings.
     * @return The document settings.
     */
    public DocumentSettings getDocumentSettings() { return _documentSettings; }

    /**
     * Sets the document settings.
     * @param documentSettings The document settings.
     */
    public void setDocumentSettings(DocumentSettings documentSettings) { _documentSettings = documentSettings; }
}
