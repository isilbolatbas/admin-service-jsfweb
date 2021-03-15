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

@ManagedBean(name="uygulamalar")
@SessionScoped
public class Uygulamalar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14363463L;
	private String dosyaIsmi;
	private String ipAddress;
	private String makineIsmi;
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDosyaIsmi() {
		return dosyaIsmi;
	}

	public void setDosyaIsmi(String dosyaIsmi) {
		this.dosyaIsmi = dosyaIsmi;
	}
	public String getMakineIsmi() {
		return makineIsmi;
	}

	public void setMakineIsmi(String makineIsmi) {
		this.makineIsmi = makineIsmi;
	}


	public List<Uygulamalar> getUygulamalar() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select * from dosya");

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		List<Uygulamalar> uygulamalar = new ArrayList<Uygulamalar>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			Uygulamalar uygulama = new Uygulamalar();
			uygulama.setDosyaIsmi(rs.getString("dosya_name"));
			uygulama.setIpAddress(rs.getString("ip_address"));
			uygulama.setMakineIsmi(rs.getString("machine_name"));
			uygulamalar.add(uygulama);
		}
		con.close();
		ps.close();
		rs.close();
		return uygulamalar;
	}
	public List<Uygulamalar> getUygulamalaIsimleri() throws ClassNotFoundException, SQLException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = DataConnect.getConnection();
			ps = con.prepareStatement("select * from dosya" );

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		List<Uygulamalar> uygulamalar = new ArrayList<Uygulamalar>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			Uygulamalar uygulama = new Uygulamalar();
		
			uygulama.setMakineIsmi(rs.getString("machine_name"));

			uygulamalar.add(uygulama);

		}
		con.close();
		ps.close();
		rs.close();

		return uygulamalar;

	}




}
