<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Add Order</h2>
<form action="MainServlet" method ="post">
<input type="hidden" name="operation" value="newRecord">
Customer Name:
<input type="text" name="customerName" required><br><br>
Product Name:
<input type="text" name="productName" required><br><br>
Order Date:
<input type="date" name="orderDate" required><br><br>
Quantity:
<input type="number" name="quantity" required><br><br>
Price:
<input type="number" name="price" required><br><br>
Remarks:
<input type="text" name="remarks" required><br><br>
<input type="submit" value="Add Order">
</form>
</body>
</html>