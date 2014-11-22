<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<portlet:defineObjects />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#noLink").click(function(){
			$.ajax({
				url: "<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/do")%>",
				data: {
					name: "the name"
				},
				format: "text",
				success: function(result){
					var table = '';
					$(result).find('product').each(function(){
						table += "<div>";
						//var name = $(this).find('name').text();
						table += $(this).find('name').text();
						table += "</div>"
					});
					$("#f_name").html(result);
				}
			});
		});
	});
			
</script>
<script type="text/javascript">

</script>
</head>
<body> 
<button type="button">Click me</button>
<a href="<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/do")%>">test</a>     
<h1>Hello</h1>
<c:forEach var = "j" begin = "0" end = "9">

<div style="float: left" id = "noLink">${j} </div>
</c:forEach>
<div id = "f_name"></div>
</body>
</html>