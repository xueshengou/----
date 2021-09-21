package com.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.GoodDao;
import com.mysql.fabric.Response;
import com.vo.Good;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig(location = "C:/Users/13553/eclipse-workspace/Online_Shop/WebContent/img")
public class UploadServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 为避免中文乱码
		String ima_add=""; 
		Good good=new Good();
		request.setCharacterEncoding("utf-8");
		String basePath = "C:/Users/13553/eclipse-workspace/Online_Shop/WebContent/img";
		// 获取所有上传文件信息遍历写入服务器
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			if (part.getSize() > 0) {
				String fname = part.getSubmittedFileName();
				// 随机产生一个uuid作为文件名称
				String uuid = UUID.randomUUID().toString();
				// 获取文件后缀
				if(fname!=null) {
					String suffix = fname.substring(fname.lastIndexOf("."));
					// 组合uuid和文件后缀成为新的文件名称
					fname = uuid + suffix;
					part.write(basePath + "/"+ fname);
					ima_add+="/img/" + fname+";";
				}else
					continue;
			}
		}
		try {
			good.setG_name(request.getParameter("sname"));
			good.setG_des(request.getParameter("sintruction"));
			good.setG_img(ima_add);
			good.setG_price(Double.parseDouble(request.getParameter("sprice")));
			GoodDao gd=new GoodDao();
			gd.release(good);
		} catch (Exception e) {
			request.getRequestDispatcher("Release.jsp").forward(request, response);
			e.printStackTrace();
		}
		response.sendRedirect("Show.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
