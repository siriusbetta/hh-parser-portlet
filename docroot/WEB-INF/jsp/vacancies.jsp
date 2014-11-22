<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<script type="text/javascript">

</script>
</head>
      
<h1>Hello</h1>
<c:forEach var = "j" begin = "0" end = "9">
${j}
</c:forEach>
</html>