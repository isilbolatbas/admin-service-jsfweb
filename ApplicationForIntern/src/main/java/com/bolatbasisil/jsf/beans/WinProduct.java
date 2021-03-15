package com.bolatbasisil.jsf.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bolatbasisil.jsf.util.DataConnect;

@ManagedBean(name="winproduct")
@SessionScoped
public class WinProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 146436L;
	
	private String win32Product;

	public String getWin32Product() {
		return win32Product;
	}

	public void setWin32Product(String win32Product) {
		this.win32Product = win32Product;
	}
	public List<WinProduct> getWinproduct() throws ClassNotFoundException, SQLException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = DataConnect.getConnection();
			ps = con.prepareStatement("select * from win32product");

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		List<WinProduct> winproduct = new ArrayList<WinProduct>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			WinProduct win = new WinProduct();

		
			win.setWin32Product(rs.getString("win32product"));
			

			winproduct.add(win);

		}
		con.close();
		ps.close();
		rs.close();

		return winproduct;

	}
}
