package com.nx;

import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ief.appimpl.nxBoot.nx.com") 
public interface IServiceApp {
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	public String getXmlFormat(@WebParam(name ="templateId")String templateId);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] signPDF(byte[] pdf, String keyLable, String password, byte[] img, float llx, float lly, float urx, float ury,
			int pageNum);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] signPDFs(String templateId, String xmlData, String keyLable, String password, float llx, float lly, float urx,
			float ury, int pageNum);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	String readPdfAndValidate(byte[] file) throws Exception;
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] mergePdfByArrayByte(String PdfList, String outLines, String xmlDate);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] mergePdfByTemplateId(String templateId, String outLines, String xmlDate);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	String readData(String mapJson);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] writePDF(String templateId, String xmlData);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] writeXmlSecToPDF(String templateId, String xmlData,String XmlSecCode);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	String getPDFTemId(byte[] pdf);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	String readPdfInsertDataPage(byte[] pdf);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	Map<String, Object> writePDFAndOutPage(String templateId, String xmlData);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] writeSecPDF(String templateId, String xmlData, String SecCode);
	
	String getName();
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] readSecRolePdf(byte[] pdfFile, String roleCode);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] readSecPdf(byte[] pdfFile, String secCode);
	
	@WebResult(name = "return", targetNamespace = "http://ief.appimpl.nxBoot.nx.com")
	byte[] readXmlSecToPdf(byte[] pdfFile, String XmlSecCode);
}
