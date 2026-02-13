<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
<%@ page import="com.wipro.eshop.bean.EShopBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Orders</title>
</head>
<body>

<h2>All Orders</h2>


<%
List<EShopBean> list = (List<EShopBean>)request.getAttribute("orderList");

if(list == null || list.isEmpty())
{
%>
    <h3>No records available!</h3>
<%
}
else
{
%>

<table border="1">

<tr>
<th>Order ID</th>
<th>Customer Name</th>
<th>Product Name</th>
<th>Quantity</th>
<th>Price</th>
<th>Remarks</th>
<th>Order Date</th>


<%
for(EShopBean order : list)
{
%>

<tr>
<td><%=order.getOrderId()%></td>
<td><%=order.getCustomerName()%></td>
<td><%=order.getProductName()%></td>
<td><%=order.getQuantity()%></td>
<td><%=order.getPrice()%></td>
<td><%=order.getRemarks()%></td>
<td><%=order.getOrderDate()%></td>
</tr>

<%
}
%>

</table>

<%
}
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
