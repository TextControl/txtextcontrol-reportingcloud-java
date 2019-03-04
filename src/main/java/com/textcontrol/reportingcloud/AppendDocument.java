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

/**
 * A document that is appended by the {@link com.textcontrol.reportingcloud.ReportingCloud#appendDocuments appendDocuments}
 * method.
 *
 * @author Thorsten Kummerow
 */
public class AppendDocument {

    private byte[] _document;
    private DocumentDivider _divider;

    /**
     * @param document The document. Supported formats are <tt>.rtf</tt>, <tt>.doc</tt>, <tt>.docx</tt> and
     *                 <tt>.tx</tt>.
     */
    public AppendDocument(byte[] document) throws IllegalArgumentException {
        this(document, DocumentDivider.None);
    }

    /**
     * @param document The document. Supported formats are <tt>.rtf</tt>, <tt>.doc</tt>, <tt>.docx</tt> and
     *                 <tt>.tx</tt>.
     * @param divider  The document divider option.
     */
    public AppendDocument(byte[] document, DocumentDivider divider) throws IllegalArgumentException {
        if (document == null || document.length == 0) {
            throw new IllegalArgumentException("Document must not be null or empty.");
        }
        _document = document;
        _divider = divider;
    }

    /**
     * Returns the document.
     * @return The document.
     */
    public byte[] getDocument() { return _document; }

    /**
     * Sets the document.
     * @param document The document. Supported formats are <tt>.rtf</tt>, <tt>.doc</tt>, <tt>.docx</tt> and
     *                 <tt>.tx</tt>.
     */
    public void setDocument(byte[] document) { _document = document; }

    /**
     * Returns the document divider option.
     * @return The document divider option.
     */
    public DocumentDivider getDivider() { return _divider; }

    /**
     * Sets the document divider option.
     * @param divider The document divider option.
     */
    public void setDivider(DocumentDivider divider) { _divider = divider; }
}
