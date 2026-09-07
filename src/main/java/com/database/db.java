package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db {
	
	String url = "jdbc:postgresql://localhost:5445/invetsech_mar_16";
	String user = "root";
	String password = "7lVUBVfapy;)oYpP";
	
	
	public String getOtp() throws SQLException {
		
		String Latest_otp = null;
		
		Connection conn = DriverManager.getConnection(url, user, password);
		PreparedStatement stmt = conn.prepareStatement("SELECT otp_value FROM public.otp ORDER BY otp_id desc limit 1");
		ResultSet rs = stmt.executeQuery();
		
		
		if(rs.next()) {
			Latest_otp = rs.getString("otp_value");
		}
		
		rs.close();
		stmt.close();
		conn.close();

		
		
		return Latest_otp;
	}

}
