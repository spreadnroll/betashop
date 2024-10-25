<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<%@ include file="common/head.jspf"%>

<body>

	<%@ include file="common/navbar.jspf"%>
	<%@ include file="common/jumbotron.jspf"%>

	<!-- Form Login -->
	<div class="container login-container">
		<div class="row">
			<div class="col-md-6 login-form">
				<h3>Login Form</h3>

				<form method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="username"
							id="username" placeholder="Nome Utente">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="password"
							id="password" placeholder="Password">
					</div>
					<div class="form-group">
						<input class="btnSubmit" type="submit">
					</div>
					<div class="form-group">
						<a href="#" class="ForgetPwd">Password Dimenticata?</a>
					</div>

					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>${ErrMsg}</p>
						</div>
					</c:if>

				</form>

			</div> 
		</div> 
	</div> 

	<%@ include file="common/foot.jspf"%>

</body>
</html>
