<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<portlet:defineObjects />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript">
	function onStart(){
		getSize();
		doGet(1);
	}
</script>

<script type="text/javascript">
	function doGet(id) {
			var sortOb = document.getElementById('sort').value;
			var deskDown = document.getElementById('desc');
			var cBox = "";
			document.getElementById('buffer').value = id;
			if(deskDown.checked){
				cBox = deskDown.value;
			}
		 $.ajax({
				url: "<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/do") %>",
				data: {
					page: id,
					sort: sortOb,
					desk: cBox
				},
				format: "text",
				success: function(result) {
					var table = '';
					$(result).find('vacancy').each(function() {
						table += '<div>';
						//var name = $(this).find('name').text();
						table += '<div><font size="1"><b>' + $(this).find('name').text() + '</b></font></div>';
						table += '<font size="1">' + $(this).find('employer').text() +'</font><div>';
						table += 'От: ' + $(this).find('from').text()+' До: ' + $(this).find('from').text()+'</div>';
						table += '<font size="1">' + 'Дата создания: ' + $(this).find('date').text() + '</font>';
						table += '<br>';
						table += '--------------------------------';
						table += "</div>"
					});
					
					$("#f_name").html(table);
					paginator(id);
				//Dont touch
				
			}
	});
}
</script>



<script type="text/javascript">
	function getSize() {
		 $.ajax({
				url: "<%= renderResponse.encodeURL(renderRequest.getContextPath()+"/links") %>",
				format: "text",
				success: function(result) {
					document.getElementById('size').value = result;
					}

				//Dont touch
	});
}
</script>
<script type="text/javascript">
	function paginator(id){
		var size = document.getElementById('size').value;
		var links = '';
		var middle = 0;
		if(id < 5){
			middle = 0;
		}
		else{
			middle = id - 5;
		}
		for(var i = 1; i <= 10; i++){
			middle++;
			if(middle != id){
			links += '<div id ="' + i + '" class = "divs"' + '>' + '<p '+ 'class = "pages"' + 'onclick = doGet(' +  middle +
')>' + middle + '</p></div>';
			}else{
				links += '<div id ="' + i + '" class = "divs"' + '>' + '<p '+ 'class = "pages_under"' + 'onclick = doGet(' +  middle +
				')>' + middle + '</p></div>';
			}
		}
		document.getElementById('links').innerHTML = links;
	}
</script>
<script type="text/javascript">
function sortEvents(){
	var id = document.getElementById('buffer').value;
	doGet(id);
}
</script>

<style type="text/css">
.divs{
	width: 15pt;
	float: left;
	text-align: center;
}
.pages_under{
	text-decoration: underline;
	cursor: pointer;
}
.pages{
	
	cursor: pointer;
}
.poin{
	cursor: pointer;
}
</style>
</head>

<body onload = "onStart()">

<div>
<div id = "f_name"></div>
<input type = "hidden" id = "size">
<input type = "hidden" id = "buffer">
<div style = "float: bottom">
<input type = "submit" id = "but" onclick = "paginator(3)">
</div>
 <select id = "sort" style = "float: left" onchange = "sortEvents()">
 	<option disabled ">Сортировка</option>
    <option selected value="datacreation">По дате</option>
    <option value="money">По зарплате</option>
   </select>
   
   <input type = "checkbox" style = "float: left; margin: 5%;" id = "desc" value = "desc"><p>По убывающей</p>

<div id ="links">

<table><tr><td id = "1"><td id = "2"><td id = "3"> 
<td id = "4"><td id = "5"><td id = "6">
<td id = "7"><td id = "8"><td id = "9"><td id = "10">
</tr></table>
</div>
</div>
</body>
</html>