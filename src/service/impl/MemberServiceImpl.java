package service.impl;

import java.util.List;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService {

	public static void main(String[] args) {
		System.out.println(new MemberServiceImpl().Login("amy", "908"));

	}
	
	private static MemberDaoImpl memberdaoimpl=new MemberDaoImpl();
	
	@Override
	public void addMember(Member member) {
		memberdaoimpl.add(member);
		
	}

	@Override
	public Member Login(String username, String password) {
		/*
		 * 1.判斷member
		 * true-->Member物件
		 * false-->null
		 */
		Member member=null;
		List<Member> l=memberdaoimpl.selectUsernameAndPossword(username, password);
		if(l.size ()!=0)
		{
			member=l.get(0);
		}
		return member;
		
		
	}

	@Override
	public boolean isUsernameBeenUse(String username) {
		// TODO Auto-generated method stub
		
		return !memberdaoimpl.selectByUsername(username).isEmpty();
	}

}
