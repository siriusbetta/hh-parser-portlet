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
			var cbDate = document.getElementById('cbDate');
			
			var cbDateV = null;
			
			if(cbDate.checked){
				cbDateV = cbDate.value;
			}else{
				cbDateV = "";
			}
			var cbMoney = document.getElementById('cbMoney');
			var cbMoneyV = null;
			if(cbMoney.checked){
				cbMoneyV = cbMoney.value;
			}else{
				cbMoneyV = "";
			}
			document.getElementById('buffer').value = id;
			
			document.getElementById(id).style.color= "red";
		 $.ajax({
				url: "<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/do")%>",
				data: {
					page: id,
					date: cbDateV,
					money: cbMoneyV
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
			
</script>
<script type="text/javascript">
        function OnChangeCheckbox (checkbox) {
        	var buf = document.getElementById('buffer').value;
        	if (checkbox.checked) {
                doGet(buf);
            }
            else {
            	doGet(buf);
            }
        }
    </script>
    
<script type="text/javascript">
function pageStart(){
	document.getElementById('buffer').value = 1;
	document.getElementById(id).style.color= "red";
	doGet(1);
}
</script>
<style type="text/css">
.poin{
	cursor: pointer;
}
</style>
</head>
<body> 

<div id = "f_name"></div>
<input type = "hidden" id = "buffer">
<p>Сортировка</p>
<div style = "float: left"> <input type = "checkbox" onclick="OnChangeCheckbox (this)" id = "cbMoney" value = "By money">По зарплате</div><div style="padding: 10px;float: left"></div>
<div> <input type = "checkbox" onclick="OnChangeCheckbox (this)" id = "cbDate" value = "By date">По дате</div>
<table cellspacing = "5">
	<tr>
		<c:forEach var = "j" begin = "1" end = "10">
			<td id = "${j}" width="25" onclick = "doGet(${j})" class = "poin"> ${j} </td>	
		</c:forEach>	
	</tr>

</table>
</body>
</html>