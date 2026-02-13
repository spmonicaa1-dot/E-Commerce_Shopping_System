package com.wipro.eshop.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.eshop.bean.EShopBean;
import com.wipro.eshop.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Administrator admin = new Administrator();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String operation = request.getParameter("operation");
		if(operation .equals("newRecord"))
		{
			String result =addRecord(request);
			if(result.equals("FAIL") || result == null || result.contains("INVALID"))
			{
				response.sendRedirect("error.html");
			}
			else
			{
				response.sendRedirect("success.html");
			}
		}
		else if(operation.equals("viewRecord"))
		{
			EShopBean bean = viewRecord(request);
			 request.setAttribute("order", bean);
				RequestDispatcher rd =request.getRequestDispatcher("displayOrder.jsp");
				rd.forward(request, response);
		}
		
		else if(operation.equals("viewAllRecords"))
		{
			List<EShopBean> list = viewAllRecords(request);
			request.setAttribute("orderList",list);
				RequestDispatcher rd =request.getRequestDispatcher("displayAllOrders.jsp");
				rd.forward(request, response);
			
		}
		
	}
	public String addRecord(HttpServletRequest request)
	{
		try
		{
		String customerName =request.getParameter("customerName");
		String productName =request.getParameter("productName");
		SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd");
		Date orderDate =f.parse(request.getParameter("orderDate"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Double price = Double.parseDouble(request.getParameter("price"));
		String remarks =request.getParameter("remarks");
		
		EShopBean eb = new EShopBean();
	
		eb.setCustomerName(customerName);
		eb.setProductName(productName);
		
		eb.setOrderDate(new java.sql.Date(orderDate.getTime()));

		eb.setQuantity(quantity);
		eb.setPrice(price);
		eb.setRemarks(remarks);
		
		
		String result = admin.addRecord(eb);
		return result;
		}
		catch(Exception e)
		{
			return "FAIL";
		}
		
	}
	
	public EShopBean viewRecord(HttpServletRequest request)
	{
		try
		{

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date  orderDate = f.parse(request.getParameter("orderDate"));
		String customerName =request.getParameter("customerName");
		return admin.viewRecord(customerName, orderDate);

		
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public List<EShopBean> viewAllRecords(HttpServletRequest request)
	{
		return admin.viewAllRecords();	}
	
	

}
