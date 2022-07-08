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
 * Servlet implementation class LoginSErvlet
 */
@WebServlet("/login.do")
public class LoginSErvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String lev = request.getParameter("lev");
		String url = null;
		EmployeesDAO empDAO = EmployeesDAO.getInstance();
		
		int result = empDAO.userCheck(id, pwd, lev);
		if(result ==2 || result ==3) {
			EmployeesVO emp = new EmployeesVO();
			emp = empDAO.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", emp);
			session.setAttribute("result", result);
			url = "main.jsp";
		}else {
			url = "login.jsp";
			if(result == 1) {
				request.setAttribute("message", "레벨이 불일치 합니다. 확인해 주세요");
			}else if(result ==0) {
				request.setAttribute("message", "비밀번호가 불일치 합니다. 확인 해 주세요");
			}else {
				request.setAttribute("message", "존재하지 않는 회원 입니다. 확인 해 주세요");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
