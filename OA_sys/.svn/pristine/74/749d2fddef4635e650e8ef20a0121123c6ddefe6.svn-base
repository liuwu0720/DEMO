<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="java.io.*,
	com.lowagie.text.*,
	java.util.List,
	com.lowagie.text.pdf.*,
	java.net.URL"
 %>
<!DOCTYPE HTML >
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <base href="<%=basePath%>">
    
    <title>My JSP 'PDA.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%
  
		Document doc = new Document(PageSize.A4);
  BaseFont bfchina = BaseFont.createFont("C:/windows/fonts/simsun.ttc,1",BaseFont.IDENTITY_H,BaseFont.EMBEDDED); 
		//BaseFont bfchina = BaseFont.createFont("STSong-Light",
					//"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		PdfWriter.getInstance(doc, response.getOutputStream());
		doc.open();			
		doc.add((Element)new Paragraph("hello sb~"));
		
		
		PdfPTable t = new PdfPTable(3);
		t.setHeaderRows(1);
		Paragraph title = new Paragraph("序号",new Font(bfchina));
		//Font font = new Font(bfchina);
		//title.setFont(font);
		//title.add("序号");
		title.setAlignment(Paragraph.ALIGN_CENTER);
		
		PdfPCell cel = new PdfPCell(title);
		cel.setColspan(3);
		cel.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.addCell(cel);
		t.addCell("姓名");
		t.addCell("年龄");
		
		PdfPCell cell = new PdfPCell(new Paragraph("傻逼 ",new Font(bfchina)));
		cell.setHorizontalAlignment(1);
		cell.setVerticalAlignment(1);
		t.addCell(cell);
		
		t.addCell("xx");
		t.addCell("12");
		t.addCell("2");
		t.addCell("yy");
		t.addCell("13");
		
	
		doc.add(t);
		
		doc.close(); 
		
		
		response.setContentType("application/pdf");
		out.clear();
		out=pageContext.pushBody();
   %>
  <body> 
    <br><br>This is my JSP page. <br>
  </body>
</html>
