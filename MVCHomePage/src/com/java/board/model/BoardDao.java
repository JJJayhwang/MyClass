package com.java.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;

public class BoardDao {

	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}

	public int insert(BoardDto boardDto) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		int value = 0;
		
		writeNumber(boardDto, conn);
		
		try {
			String sql = "insert into board (board_number, writer, subject, email, content, password, write_date, read_count,"
					+ " group_number, sequence_number, sequence_level)"
					+ " values(board_number_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			
			conn = ConnectionProvider.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getWriter());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getEmail());
			pstmt.setString(4, boardDto.getContent().replace("\r\n", "<br>"));
			pstmt.setString(5, boardDto.getPassword());
			pstmt.setTimestamp(6, new Timestamp(boardDto.getWriteDate().getTime()));
			pstmt.setInt(7, boardDto.getReadCount());
			pstmt.setInt(8, boardDto.getGroupNumber());
			pstmt.setInt(9, boardDto.getSequenceNumber());
			pstmt.setInt(10, boardDto.getSequenceLevel());
			
			value = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return value;
	}

	private void writeNumber(BoardDto boardDto, Connection conn) {

		//그룹번호, 글순서(자식), 글레벨(자식) 처리 --- 답변의 경우
		
		int boardNumber = boardDto.getBoardNumber();//0이 들어간상태
		int groupNumber = boardDto.getGroupNumber();//0이 들어간상태
		int sequenceNumber = boardDto.getSequenceNumber();//0이 들어간상태
		int sequenceLevel = boardDto.getSequenceLevel();//0이 들어간상태
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		
		try {
			if(boardNumber==0) {	//부모글의 그룹번호
				sql="select max(group_number) from board";
				conn=ConnectionProvider.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					int max = rs.getInt(1);
					boardDto.setGroupNumber(max+1);
				}
			} else {
				//답글 : 글 순서, 글레벨 세팅
				sql="update board set sequence_number = sequence_number+1"
						+ "where group_number=? and sequence_number>? ";
				
				conn=ConnectionProvider.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, groupNumber);
				pstmt.setInt(2, sequenceNumber);
				pstmt.executeUpdate();
				
				sequenceNumber=sequenceNumber+1;
				sequenceLevel = sequenceLevel+1;
				
				boardDto.setSequenceNumber(sequenceNumber);
				boardDto.setSequenceLevel(sequenceLevel);
				
			
			}
		} catch (Exception e) {

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		
	}
}
