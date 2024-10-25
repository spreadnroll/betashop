<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<%@ include file="common/head.jspf"%>
 
  <body>
  
<%@ include file="common/navbar.jspf" %>

 
 <h1 class="title"> Benvenuto in BetaShop</h1>
 <h3 class="subtitle"> Saluti ${username}, clicca <a href="/betashop/articoli">qui</a> per vedere gli articoli disponibili.</h3>   
 
  <%@ include file="common/foot.jspf"%> 
  </body>
</html>