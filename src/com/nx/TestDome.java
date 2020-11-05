package com.nx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class TestDome {

	public static void main(String[] args) throws Exception {
		
		TestDome t = new TestDome();
		
		t.testWritePDF("GS-QYDJ-NZQY-LLYXX");
		
		t.testWriteXmlSecToPDF("GS-QYDJ-NZQY-LLYXX", "ABC");
		
		t.testReadData();
		
		t.testReadXmlSecToPdf();
		
		t.testMergePdfByTemplateId();
		
		t.testMergePdfByArrayByte();
	}
	
	//初始化客户端
	public IServiceApp initJaxWsBean(){
		
		// 接口地址
		String address = "http://www.";

		// 代理工厂
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		// 设置代理地址
		jaxWsProxyFactoryBean.setAddress(address);
		// 设置接口类型
		jaxWsProxyFactoryBean.setServiceClass(IServiceApp.class);
		// 创建一个代理接口实现
		IServiceApp cs = (IServiceApp) jaxWsProxyFactoryBean.create();
		
		return cs;
		
	}
	
	//解析xml字符串数据
	public String getXmlData(){
		
		try {
		
			SAXReader reader = new SAXReader();
	
			FileInputStream in;
			
				in = new FileInputStream(new File("D://nzyxgssl-v1.3.xml"));
			
			Document document = reader.read(in);
	
			ByteArrayOutputStream out = new ByteArrayOutputStream();
	
			OutputFormat format = OutputFormat.createCompactFormat(); // 漂亮格式：有空格换行
			
			format.setEncoding("UTF-8");
			
			XMLWriter writer = new XMLWriter(out, format);
			
			writer.write(document);
			
			writer.close();
	
			return out.toString();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
	//测试PDF写入接口，这里使用模板ID（GS-QYDJ-NZQY-LLYXX）为例，即联络员信息PDF，如需要可改其他
	public void testWritePDF(String temId){
		
		try {
			IServiceApp cs = initJaxWsBean();
	
			String xmlDate = getXmlData();
			
			byte[] by = cs.writePDF(temId, xmlDate);
			
			OutputStream outPut = new FileOutputStream(new File("D://" + temId + ".pdf"));
			outPut.write(by);
			outPut.flush();
			outPut.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//测试可根据xml密级标识过滤数据PDF写入接口，这里使用模板ID（GS-QYDJ-NZQY-LLYXX）为例，即联络员信息PDF，如需要可改其他
	public void testWriteXmlSecToPDF(String temId, String xmlSecCode){
		
		try {
			IServiceApp cs = initJaxWsBean();
	
			String xmlDate = getXmlData();
			
			byte[] by = cs.writeXmlSecToPDF(temId, xmlDate, xmlSecCode);
			
			OutputStream outPut = new FileOutputStream(new File("D://" +temId+"_"+xmlSecCode+".pdf"));
			
			outPut.write(by);
			outPut.flush();
			outPut.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//测试读取pdf数据,可读多个PDF数据
	public void testReadData(){
		
		try {
			IServiceApp cs = initJaxWsBean();
	
			FileInputStream fileInput = new FileInputStream("D://GS-QYDJ-NZQY-LLYXX.pdf"); 
			byte[] fileBy = new byte[fileInput.available()]; 
			fileInput.read(fileBy);
		 	fileInput.close(); 
		 	
			Map<String, byte[]> map = new HashMap<String, byte[]>();
			map.put("GS-QYDJ-NZQY-LLYXX", fileBy);
		 	
		 	String res = cs.readData(FastJsonUtil.toJson(map));
		 	
		 	System.out.println("读取PDF的xml数据为:" + res);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//测试根据xml密级标识过滤PDF显示字段信息
	public void testReadXmlSecToPdf(){
		
		try {
			IServiceApp cs = initJaxWsBean();
	
			FileInputStream fileInput = new FileInputStream("D://GS-QYDJ-NZQY-LLYXX.pdf"); 
			byte[] fileBy = new byte[fileInput.available()]; 
			fileInput.read(fileBy);
		 	fileInput.close(); 
		 	
		 	byte[] res = cs.readXmlSecToPdf(fileBy, "ABC");
		 	
		 	OutputStream outPut = new FileOutputStream(new File("D://GS-QYDJ-NZQY-LLYXX_ABC.pdf"));
			outPut.write(res);
			outPut.flush();
			outPut.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试根据模板ID多个pdf合并用例,并写入数据,这里以GS-QYDJ-NZQY-LLYXX,GS-QYDJ-NZQY-KZSMORWTS这两个模板进行测试,书签自定义
	public void testMergePdfByTemplateId(){
		try {
		
			IServiceApp cs = initJaxWsBean();
			
			//可不带数据合并，这样只是纯模板pdf合并
			String xmlDate = getXmlData();
			
			List<OutLine> outLines = new ArrayList<>();
			OutLine o = new OutLine();
			o.setTitle("1111");
			o.setOpen("true");
			o.setPage("1");
			outLines.add(o);
	
			o = new OutLine();
			o.setTitle("222");
			o.setOpen("true");
			o.setPage("2");
			outLines.add(o);
	
			byte[] results = cs.mergePdfByTemplateId("GS-QYDJ-NZQY-LLYXX,GS-QYDJ-NZQY-KZSMORWTS",FastJsonUtil.toJson(outLines), xmlDate);
			
			OutputStream output = new FileOutputStream("D://模板ID合并pdf.pdf");
			output.write(results);
			output.flush();
			output.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//测试根据模板ID多个pdf合并用例,可带写入数据,如果之前PDF存在数据，将被覆盖
	public void testMergePdfByArrayByte(){
		try {
		
			IServiceApp cs = initJaxWsBean();
			
			//可不带数据合并，这样只是纯pdf合并
			String xmlDate = getXmlData();
			
			FileInputStream fileInput = new FileInputStream("D://GS-QYDJ-NZQY-LLYXX.pdf"); 
			byte[] file1 = new byte[fileInput.available()]; 
			fileInput.read(file1);
		 	fileInput.close(); 
		 	
		 	fileInput = new FileInputStream("D://GS-QYDJ-NZQY-LLYXX_ABC.pdf"); 
			byte[] file2 = new byte[fileInput.available()]; 
			fileInput.read(file2);
		 	fileInput.close(); 
			
			List<OutLine> outLines = new ArrayList<>();
			OutLine o = new OutLine();
			o.setTitle("1111");
			o.setOpen("true");
			o.setPage("1");
			outLines.add(o);
	
			o = new OutLine();
			o.setTitle("222");
			o.setOpen("true");
			o.setPage("2");
			outLines.add(o);
			
			List<Entry> PdfList = new ArrayList<>();
			Entry e = new Entry();
			e.setKey("one");
			e.setValue(file1);
			PdfList.add(e);
			e = new Entry();
			e.setKey("two");
			e.setValue(file2);
			PdfList.add(e);
			
			byte[] results = cs.mergePdfByArrayByte(FastJsonUtil.toJson(PdfList), FastJsonUtil.toJson(outLines), xmlDate);
			
			OutputStream output = new FileOutputStream("D://合并pdf.pdf");
			output.write(results);
			output.flush();
			output.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
