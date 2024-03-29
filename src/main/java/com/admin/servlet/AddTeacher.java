package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TeacherDao;
import com.db.DBConnect;
import com.entity.teacher;

@WebServlet("/addTeacher")
public class AddTeacher extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String fullName = req.getParameter("fullname");
			

			String course = req.getParameter("course");
			String email = req.getParameter("email");
			String pass= req.getParameter("pw");
			

			teacher d = new teacher(fullName,email,course,pass);

			TeacherDao dao = new TeacherDao(DBConnect.getConn());
			
			HttpSession session = req.getSession();

			if (dao.registerTeacher(d)) {
				session.setAttribute("succMsg", "Teacher Added Sucessfully..");
				resp.sendRedirect("admin/teacher_added.jsp");
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("admin/teacher.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
