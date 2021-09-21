package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vo.Good;
import com.vo.Purchase;

public class PurchaseDao {
	public static final String URL="jdbc:mysql://localhost:3306/onlineshop?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	public static final String USER="root";
	public static final String PWD="root";
	
	public ArrayList<Purchase> read(int GNo) throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Purchase> pl=new ArrayList<Purchase>();
		try {
			//1.
			Class.forName("com.mysql.jdbc.Driver");
			//2.创建链接java.sql 不要用com.mysql。jdbc
			conn=DriverManager.getConnection(URL, USER, PWD);
			//3.sql语句
			String sql="select * from purchase where GNo=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, GNo);
			rs=ps.executeQuery();
			while(rs.next()) {
				Purchase p=new Purchase(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4));
				pl.add(p);
			}
			return pl;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}finally {
			if(null!=rs) 
				rs.close();
			if(null!=ps) 
				ps.close();
			if(null!=conn) 
				conn.close();		
		}
		return null;
	}
}

