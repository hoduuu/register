package com.magic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.magic.dto.EmployeesVO;

public class EmployeesDAO {
	private EmployeesDAO() {
		
	}
	private static EmployeesDAO instance = new EmployeesDAO();
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		return conn;
	}
	
	public int userCheck(String id, String pwd, String lev) {

		int result = -1;
		Connection conn = null;
		String sql = "select * from employees where id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 비밀 번호까지 일치할때
				if(pwd.equals(rs.getString("PASS"))) {
					// 회원 등급까지 일치할때
					if(lev.equals(rs.getString("LEV"))) {
						result = 2; //관리자로 로그인 성공
						if(lev.equals("B")) {
							result = 3; // 일반회원으로 로그인
						}
					}else {  // 레벨 불일치
						result =1;
					}
				}else {  // 비밀 번호 불일치 로그인 실패
					result=0;
				}
			}else {  // 아이디가 불일치
				result= -1;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
			if( rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return result;
	
	}
	
	public EmployeesVO getMember(String id) {
		EmployeesVO member = null;
		Connection conn = null;
		String sql = "select * from employees where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new EmployeesVO();
				member.setId(rs.getString("ID"));
				member.setPass(rs.getString("PASS"));
				member.setName(rs.getString("NAME"));
				member.setLev(rs.getString("LEV"));
				
				member.setPhone(rs.getString("PHONE"));
				member.setEnter(rs.getDate("ENTER"));
				member.setGender(rs.getInt("GENDER"));
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (Exception e){
				System.out.println(e);
			}
		}
		return member;
	}
	
	
	public int updateMember(EmployeesVO eVo) {
		int result = -1;
		String sql = "update employees set gender=?, pass=?,"
				+ " name=?, lev=?, phone=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eVo.getGender());
			pstmt.setString(2, eVo.getPass());
			pstmt.setString(3, eVo.getName());
			pstmt.setString(4, eVo.getLev());
			pstmt.setString(5, eVo.getPhone());
			pstmt.setString(6, eVo.getId());
			result = pstmt.executeUpdate();
			System.out.println("하이");
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return result;
		
	}
	
	public void insertMember(EmployeesVO member) {
		String sql = "insert into employees values(?,?,?,?,SYSDATE,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			System.out.println(member.getId());
			System.out.println(member.getPass());
			System.out.println(member.getName());
			System.out.println(member.getLev());
			System.out.println(member.getGender());
			System.out.println(member.getPhone());
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getLev());
			pstmt.setInt(5, member.getGender());
			pstmt.setString(6, member.getPhone());
			pstmt.executeUpdate();
		
		}catch(Exception e){
			System.out.println(e);
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
		
		
		
		
	}
	
}
