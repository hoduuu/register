package com.magicl.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.dao.EmployeesDAO;
import com.magic.dto.EmployeesVO;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmployeesVO emp =(EmployeesVO)session.getAttribute("loginUser");
		if(emp != null) {
			String url = "mypage.jsp";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}else {
			response.sendRedirect("login.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		EmployeesVO member = new EmployeesVO();
		member.setId(request.getParameter("id"));
		member.setPass(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setLev(request.getParameter("lev"));
		if(request.getParameter("gender")!= null)
			member.setGender(Integer.parseInt(request.getParameter("gender").trim()));
		member.setPhone(request.getParameter("phone"));
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		eDao.updateMember(member);
		EmployeesVO emp = eDao.getMember(member.getId());
		request.setAttribute("member", emp);
		request.setAttribute("message", "회원 정보가 수정되었습니다");
		
		int result = eDao.userCheck(member.getId(), member.getPass(), member.getLev());
		session.setAttribute("loginUser", emp);
		session.setAttribute("result", result);
		String url = "joinsuccess.jsp";
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
