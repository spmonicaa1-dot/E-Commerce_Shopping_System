package com.wipro.eshop.dao;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wipro.eshop.bean.EShopBean;
import com.wipro.eshop.util.DBUtil;

public class EShopDAO {
	public String createRecord(EShopBean bean)
	{
		String query ="INSERT INTO ESHOP_TB VALUES(?,?,?,?,?,?,?)";
		try
		{
			String orderId =generateOrderID(bean.getCustomerName(), bean.getOrderDate());
	         bean.setOrderId(orderId);
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps =con.prepareStatement(query);
			ps.setString(1,bean.getOrderId());
			ps.setString(2, bean.getCustomerName());
			ps.setString(3, bean.getProductName());
			ps.setDate(4,bean.getOrderDate());
			ps.setInt(5,bean.getQuantity());
			ps.setDouble(6, bean.getPrice());
			ps.setString(7, bean.getRemarks());
			int  rs =ps.executeUpdate();
			if(rs>0)
			{
				return bean.getOrderId();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAIL";
	}
	public EShopBean fetchRecord(String customerName,Date orderDate)
	{
		String query ="SELECT * FROM ESHOP_TB WHERE CUSTOMERNAME =? AND ORDER_DATE=?";
		try
		{
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,customerName);
			ps.setDate(2, new java.sql.Date(orderDate.getTime()));
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				EShopBean es = new EShopBean();
				es.setOrderId(rs.getString(1));
				es.setCustomerName(rs.getString(2));
				es.setProductName(rs.getString(3));
				es.setOrderDate(rs.getDate(4));
				es.setQuantity(rs.getInt(5));
				es.setPrice(rs.getDouble(6));
				es.setRemarks(rs.getString(7));
				return es;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
		public String generateOrderID(String customerName, Date orderDate) {

	        String orderId = "";

	        try {
	            DateFormat f = new SimpleDateFormat("yyyyMMdd");
	          
	            String datePart = f.format(new java.util.Date(orderDate.getTime()));

	            String namePart = customerName.substring(0, 2).toUpperCase();

	            Connection con = DBUtil.getDBConnection();
	            String query = "SELECT COUNT(*) FROM ESHOP_TB WHERE ORDER_DATE=?";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setDate(1, new java.sql.Date(orderDate.getTime()));

	            ResultSet rs = ps.executeQuery();

	            int seq = 1;
	            if (rs.next()) {
	                seq = rs.getInt(1) + 1;
	            }

	            orderId = datePart + namePart + seq;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return orderId;
	    }
	
	public boolean recordExists(String customerName,Date orderDate)
	{
		EShopBean bean = fetchRecord(customerName,orderDate);
		return bean != null;
	}
	
	public List<EShopBean> fetchAllRecords()
	{
		String query = "SELECT * FROM ESHOP_TB";
		List<EShopBean> ans = new ArrayList<>();
		try
		{
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				EShopBean es = new EShopBean();
				es.setOrderId(rs.getString(1));
				es.setCustomerName(rs.getString(2));
				es.setProductName(rs.getString(3));
				es.setOrderDate(rs.getDate(4));
				es.setQuantity(rs.getInt(5));
				es.setPrice(rs.getDouble(6));
				es.setRemarks(rs.getString(7));
				ans.add(es);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ans;
	}

}
