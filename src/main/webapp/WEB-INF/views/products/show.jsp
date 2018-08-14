<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>

		<form:form servletRelativeAction="/shopping">

			<input type="hidden" value="${product.id}" name="productId" />

			<ul id="variants">
				<c:forEach items="${product.prices}" var="price">
					<li>
						<input type="radio" name="bookType"
							id="${product.id}-${price.bookType}" value="${price.bookType}"
							${price.bookType.name() == 'COMBO' ? 'checked' : ''}> 
							
							<label for="${product.id}-${price.bookType}"> ${price.bookType} </label>
							${price.value}
					</li>
				</c:forEach>
			</ul>
			<input type="submit" alt="Compre agora"
				title="Compre agora ' ${product.title}'!" value="comprar" />

		</form:form>

		<div>

			<section>
				<h2>${product.title}</h2>
				<span>
					<p>${product.description}</p>
				</span>
			</section>

			<section>
				<h2>Dados do livro:</h2>
				<p>
					Número de paginas: <span>${product.pages}</span>
				</p>

				<p></p>
			</section>
		</div>


	</section>

</body>
</html>