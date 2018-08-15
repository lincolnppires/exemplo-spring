<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<tbody>
		<c:forEach items="${shoppingCart.list}" var="item">
			<tr>
				<td>${item.product.title}  -  ${item.bookType}</td>
				
				<td>${item.price}</td>
				
				<td>
					<input type="number" min="0" max="2"
						readonly="readonly" value="${shoppingCart.getQuantity(item)}">
				</td>
				<td>${shoppingCart.getTotal(item)}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2">
				<form action="${spring:mvcUrl('checkoutPaymentController').build()}" method="post">
					<input type="submit" name="checkout"
						value="Finalizar compra " id="checkout" />
				</form>
			</td>
			<td>${shoppingCart.total}</td>
			<td></td>
		</tr>
	</tfoot>



</body>
</html>