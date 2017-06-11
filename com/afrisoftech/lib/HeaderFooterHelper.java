/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrisoftech.lib;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Charles Waweru <cwaweru@systempartners.biz>
 */
public class HeaderFooterHelper extends PdfPageEventHelper {

    Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);

    static public Phrase header = null;
    static public Phrase footer = null;

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
       // Phrase header = new Phrase("this is a header", ffont);
        // Phrase footer = new Phrase("this is a footer", ffont);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                new Phrase(footer.getContent() +" "+String.valueOf(writer.getPageNumber())),
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);
    }
}
