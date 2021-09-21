package com.vo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;



public class Purchase {
	private int Gno;
	private Date PDate;
	private String Username;
	private String Phone;
	public int getGno() {
		return Gno;
	}
	public void setGno(int gno) {
		Gno = gno;
	}
	public String getPDate() {
		String value = null;
		//将Date类型的时间转换成指定格式的字符串
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		value = dateFormat.format(PDate);
		return value;
	}
	public void setPDate(String pDate) {
		//将字符串类型的日期转换成Date类型的指定格式的日期
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);//从第一个字符开始解析
			try {
				this.PDate = (Date) f.parse(pDate,pos);/*对参数msg_create_date（String类型）从第一个字符开始解析（由pos），转换成java.util.Date类型，
			而这个Date的格式为"yyyy-MM-dd"（因为SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");）*/
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public Purchase(int gno, Date pDate, String username, String phone) {
		super();
		Gno = gno;
		PDate = pDate;
		Username = username;
		Phone = phone;
	}
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
