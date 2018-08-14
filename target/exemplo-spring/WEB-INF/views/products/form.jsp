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
		<form:form method="post" action="/exemplo-spring/produtos"
			modelAttribute="product"
			enctype="multipart/form-data"
			>
			<!--action= ${spring:mvcUrl('saveProduct').build()} -->
			<div>
				<label for="title">Titulo-></label>
				<form:input path="title" />
				<form:errors path="title" />
			</div>

			<div>
				<label for="description">Descrição</label>
				<form:textarea rows="10" cols="20" path="description"
					id="description"></form:textarea>
				<form:errors path="description" />
			</div>
			<div>
				<label for="pages">Número de paginas</label> 
				<form:input  path="pages" name="pages" id="pages" />
				<form:errors path="pages" />
			</div>

			<div>
				<label for="releaseDate">Data de Lançamento -  formato por anotação </label> 
				<form:input path="releaseDate" id="releaseDate"/>
				<form:errors path="releaseDate" />
			</div>
			
			<div>
				<label for="dateTemp">Data - formato registro bean</label> 
				<form:input path="dateTemp" id="dateTemp"/>
				<form:errors path="dateTemp" />
			</div>

			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label> <input
						type="text" name="prices[${status.index}].value"
						id="price_${bookType}" /> <input type="hidden"
						name="prices[${status.index}].bookType" value="${bookType}" />
				</div>
			</c:forEach>
			
			<br />
			<div>
				<label for="summary">Sumário do Livro</label>
				<input type="file" name="summary">
				<form:errors path="summaryPath" />
			</div>
			

			<div>
				<input type="submit" value="Enviar">
			</div>
		</form:form>
	</div>

</body>
</html>