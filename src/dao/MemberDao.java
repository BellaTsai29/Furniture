package dao;

import java.util.List;

import model.Member;

public interface MemberDao {
	
	//create
	void add(Member member);
	
	//read
	List<Member>selectUsernameAndPossword(String user ,String password);
	
	//update
	void update(Member member);
	
	//delete
	void delete (int id);

}
