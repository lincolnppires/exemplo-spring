<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>

	<div>
		<spring:hasBindErrors name="product">
			<ul>
				<c:forEach var="error" items="${errors.allErrors}">
					<li><spring:message code="${error.code}"
							text="${error.defaultMessage}" /></li>
				</c:forEach>
			</ul>
		</spring:hasBindErrors>
	</div>


	<div>
		<form:form method="post" action="/exemplo-spring/produtos"
			modelAttribute="product">
			<!--action= ${spring:mvcUrl('saveProduct').build()} -->
			<div>
				<label for="title">Titulo-></label> 
				<form:input path="title"/>
				<form:errors path="title" />
			</div>

			<div>
				<label for="description">Descrição</label>
				<textarea rows="10" cols="20" name="description" id="description"></textarea>
				<form:errors path="description" />
			</div>
			<div>
				<label for="pages">Número de paginas</label> <input type="text"
					name="pages" id="pages" />
				<form:errors path="pages" />
			</div>

			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label> <input
						type="text" name="prices[${status.index}].value"
						id="price_${bookType}" /> <input type="hidden"
						name="prices[${status.index}].bookType" value="${bookType}" />
				</div>
			</c:forEach>

			<div>
				<input type="submit" value="Enviar">
			</div>
		</form:form>
	</div>

</body>
</html>