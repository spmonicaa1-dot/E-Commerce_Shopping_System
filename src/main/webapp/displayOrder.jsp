<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.wipro.eshop.bean.EShopBean" %> 
      <%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Order</title>
</head>
<body>
<h2>Order Details</h2>
<%

EShopBean order = (EShopBean)request.getAttribute("order");

if(order == null)
{
%>
     <h3>No matching records exists! Please try again!</h3>
<%
}
else
{
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
%>

Order ID : <%=order.getOrderId()%><br>
Customer Name : <%=order.getCustomerName()%><br>
Product Name : <%=order.getProductName()%><br>
Order Date : <%=f.format(order.getOrderDate())%><br>
Quantity : <%=order.getQuantity()%><br>
Price : <%=order.getPrice()%><br>
Remarks : <%=order.getRemarks()%><br>

<%
}
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
