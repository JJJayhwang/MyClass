package com.java.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;

public class MemberDao {
	//Data Access Object(DAO)
	//Singleton pattern : 단 한개의 객체만을 가지고 구현
	//어플리케이션에서 어떠한 클래스가 단 한번만 메모리를 할당해 그 메모리 내에서
	//객체를 만들어 사용하는 방법
	private static MemberDao instance = new MemberDao();
					//A		   a 	  = new    A	 ()
	public static MemberDao getInstance() {
		return instance;
	}
	
	public int insert(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt  = null;
		int value=0;
		
		try {
			String sql = "insert into member values(member_num_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			conn=ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getId());	
			pstmt.setString(2, memberDto.getPassword());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getJumin1());
			pstmt.setString(5, memberDto.getJumin2());
			pstmt.setString(6, memberDto.getEmail());
			pstmt.setString(7, memberDto.getZipcode());
			pstmt.setString(8, memberDto.getAddress());
			pstmt.setString(9, memberDto.getJob());
			pstmt.setString(10, memberDto.getMailing());
			
			pstmt.setString(11, memberDto.getInterest());
			pstmt.setString(12, memberDto.getMemberLevel());
			
			value=pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return 1;
	}

	public int idCheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int value=0;
		
		try {
			String sql = "select id from member where id=?";
			conn= ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			//물음표에 들어갈 id를 넣어줌
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				value=1;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;
	}
	
	public String loginCheck(String id, String password) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		String value = null;
		ResultSet rs = null;
		try {
			String sql = "select member_level from member where id=? and password=?" ;
					
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value=rs.getString("member_level");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;
		
		
		
	}

	public int delete(String id, String password) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int value = 0;
		ResultSet rs = null;
		
		try {
			String sql = "delete from member where id= ? and password = ?";
			conn = ConnectionProvider.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				value=1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return value;
	}

	public MemberDto load(String id) {
		MemberDto memberdto = new MemberDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs=null;
		
		try {
			String sql = "select * from member where id=?";
			conn = ConnectionProvider.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
			memberdto.setId(rs.getString("id"));
			memberdto.setPassword(rs.getString("password"));
			memberdto.setName(rs.getString("name"));
			memberdto.setJumin1(rs.getString("jumin1"));
			memberdto.setJumin2(rs.getString("jumin2"));
			memberdto.setEmail(rs.getString("email"));
			memberdto.setZipcode(rs.getString("zipcode"));
			memberdto.setId(rs.getString("id"));
			memberdto.setAddress(rs.getString("address"));
			memberdto.setJob(rs.getString("job"));
			memberdto.setMailing(rs.getString("mailing"));
			memberdto.setInterest(rs.getString("interest"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(conn);
		}
		
		return memberdto;
	}

	public int updateinfo(MemberDto memberdto) {
	
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int value=0;
		try {
			String sql =  " update member set"
					+ " password=?,"
					+ " email=?,"
					+ " zipcode=?,"
					+ " address=?,"
					+ " job=?,"
					+ " mailing=?,"
					+ " interest=? "
					+ " where id= ?";
			
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, memberdto.getPassword());
			
			
			pstmt.setString(2, memberdto.getEmail());
			pstmt.setString(3, memberdto.getZipcode());
			pstmt.setString(4, memberdto.getAddress());
			pstmt.setString(5, memberdto.getJob());
			pstmt.setString(6, memberdto.getMailing());
			pstmt.setString(7, memberdto.getInterest());
			pstmt.setString(8, memberdto.getId());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				value=1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		
		return value;
	}
	
	
	
	
	
	
}
