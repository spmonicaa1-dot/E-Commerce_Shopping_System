package com.wipro.eshop.service;

import java.util.Date;
import java.util.List;

import com.wipro.eshop.bean.EShopBean;
import com.wipro.eshop.dao.EShopDAO;
import com.wipro.eshop.util.InvalidInputException;

public class Administrator {
	EShopDAO d =new EShopDAO();
public String addRecord(EShopBean bean) throws InvalidInputException
{
	
	if(bean == null ||bean.getCustomerName() == null|| bean.getOrderDate() == null)
	{
		throw new InvalidInputException();
	}
	if(bean.getCustomerName().length()<2)
	{
		return "INVALID CUSTOMER NAME";
	}
	if(bean.getQuantity()<1 || bean.getPrice() <=0)
	{
		return "INVALID ORDER DETAILS";
	}
	
	if(d.recordExists(bean.getCustomerName(),bean.getOrderDate()))
	{
		return "ALREADY EXISTS";
	}
	return d.createRecord(bean);
	
	
	
}
public EShopBean viewRecord(String customerName,Date orderDate)
{
	return d.fetchRecord(customerName, orderDate);
}
public List<EShopBean> viewAllRecords()
{
	return d.fetchAllRecords();
}
}
