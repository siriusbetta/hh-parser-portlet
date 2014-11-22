<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<portlet:defineObjects />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	function doGet(id){
		 document.getElementById('buffer').value =id;
			//alert("asdf");
			document.getElementById(id).style.color= "red";
		 $.ajax({
				url: "<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/do")%>",
				data: {
					name: id
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
	
	}
	$(document).ready(function(){
		$("tod").click(function(){
			$.ajax({
				url: "<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/do")%>",
				data: {
					name: "#buffer".text
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

<style type="text/css">
.poin{
	cursor: pointer;
}
</style>
</head>
<body> 

<div id = "f_name"></div>
<input type = "text" id = "buffer">
<table cellspacing = "5">
	<tr>
		<c:forEach var = "j" begin = "0" end = "9">
			<td id = "${j}" width="25" onclick = "doGet(${j})" class = "poin"> ${j} </td>	
		</c:forEach>	
	</tr>

</table>
</body>
</html>