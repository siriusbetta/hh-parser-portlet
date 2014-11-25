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
		var size = parseInt(document.getElementById('size').value);
		if(size < 10){
			document.getElementById('up').value = size;
		}else{
			document.getElementById('up').value = 10;
		}
		document.getElementById('down').value = 1;
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
function limUpInc(val){
	val++;
	var prev = document.getElementById('down').value;
	prev++;
	document.getElementById('down').value = prev;
	document.getElementById('up').value = val;
	doGet(val);
}
function limDownDec(val){
	val--;
	var next = document.getElementById('up').value;
	next--;
	document.getElementById('up').value = next;
	document.getElementById('down').value = val;
	doGet(val);
}
</script>
<script type="text/javascript">
	function paginator(id){
		var size = parseInt(document.getElementById('size').value);
		var limUp = 0;
		if(size < 10){
			limUp = size;
		}else{
			limUp = parseInt(document.getElementById('up').value);
		}
			//parseInt(document.getElementById('up').value);
		//alert(limUp);
		var limDown = parseInt(document.getElementById('down').value);
		var links = '';
		//alert(limDown + limUp);
		var limUpHtml = '';
		if(limUp != size){
			limUpHtml = '<div class = "limDivs">' + '<p '+ 'class = "limUp"' + 'onclick = limUpInc(' +  limUp +
			')>' + 'Next' + '</p></div>';
		}else{
			limUpHtml = '<div class = "limDivs">' + '<p>' + 'Next' + '</p></div>';
		}
		
		var limDownHtml = '';
		if(limDown != 1){
			limDownHtml = '<div class = "limDivs">' + '<p '+ 'class = "limDown"' + 'onclick = limDownDec(' +  limDown +
			')>' + 'Prev' + '</p></div>';
		}else{
			limDownHtml = '<div class = "limDivs">' + '<p>' + 'Prev' + '</p></div>';
		}
		for(var i = limDown; i <= limUp; i++){
			if(i != id){
			links += '<div id ="' + i + '" class = "divs"' + '>' + '<p '+ 'class = "pages"' + 'onclick = doGet(' +  i +
')>' + i + '</p></div>';
			}else{
				links += '<div id ="' + i + '" class = "divs"' + '>' + '<p '+ 'class = "pages_under"' + 'onclick = doGet(' +  i +
				')>' + i + '</p></div>';
			}
		}
		limDownHtml += links;
		limDownHtml += limUpHtml;
		document.getElementById('links').innerHTML = limDownHtml;
	}
</script>
<script type="text/javascript">
function sortEvents(){
	var id = document.getElementById('buffer').value;
	doGet(id);
}
</script>

<style type="text/css">
.limDivs{
	width:25pt;
}
.limDown{
	cursor: pointer;
}
.limUp{
	cursor: pointer;
}
.divs{
	width: 20pt;
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
body{
	margin: 0;	
}
.layout{
	 margin:auto;
}
.wrap{
	padding: 20px;
}
</style>
</head>

<body onload = "onStart()">
	<div class = "layout">
		<div class = "wrap">
			<div id = "f_name"></div>
			<input type = "hidden" id = "size">
			<input type = "hidden" id = "buffer">
			<input type = "hidden" id = "up">
			<input type = "hidden" id = "down">
			<div>
			 	<select id = "sort" onchange = "sortEvents()">
				 	<option disabled ">Сортировка</option>
				    <option selected value="datacreation">По дате</option>
				    <option value="money">По зарплате</option>
				    <option value="money&date">По зарплате и дате</option>
			  	 </select><br>
			   	<input type = "checkbox" id = "desc" value = "desc" onclick = "sortEvents()" style = "float: left">
			   <p>По убывающей</p>
			</div>

			<div id ="links" style = "clear:left; width: 300px;"></div>


		</div>
	</div>
	</body>
</html>