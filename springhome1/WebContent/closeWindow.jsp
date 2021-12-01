<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- closeWindow.jsp -->
<%
		String msg = (String)request.getAttribute("msg");
%>
<script type="text/javascript">
	alert("<%=msg%>")
	self.close()
</script>