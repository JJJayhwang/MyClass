package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateOkCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub.
		request.setCharacterEncoding("UTF-8");
		MemberDto memberdto = new MemberDto();
		memberdto.setId(request.getParameter("id"));
		memberdto.setPassword(request.getParameter("password"));
		memberdto.setName(request.getParameter("name"));
		memberdto.setJumin1(request.getParameter("jumin1"));
		memberdto.setJumin2(request.getParameter("jumin2"));
		memberdto.setEmail(request.getParameter("email"));
		memberdto.setZipcode(request.getParameter("zipcode"));
		memberdto.setAddress(request.getParameter("address"));
		memberdto.setJob(request.getParameter("job"));
		memberdto.setMailing(request.getParameter("mailing"));
		memberdto.setInterest(request.getParameter("resultInterest"));
		
		
		
		int check = MemberDao.getInstance().updateinfo(memberdto);
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/view/member/updateOk.jsp";
	}

}
