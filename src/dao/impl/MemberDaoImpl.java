package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import model.Member;
import util.DbConnection;


public class MemberDaoImpl implements MemberDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	private static Connection conn=DbConnection.getDb();
	private PreparedStatement preparedStatement;	
	
	
	@Override
	public void add(Member member) {
		String SQ="insert"
				+ " into member(name,username,password,phone,address,email)"
				+"values(?,?,?,?,?,?)";
		try {
			PreparedStatement preparedstatement =conn.prepareStatement(SQ);
			preparedstatement.setString(1,member.getName());
			preparedstatement.setString(2,member.getUsername());
			preparedstatement.setString(3,member.getPassword());
			preparedstatement.setString(4,member.getPhone());
			preparedstatement.setString(5,member.getAddress());
			preparedstatement.setString(6,member.getEmail());
			
			preparedstatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Member> selectUsernameAndPossword(String username, String password) {
		String sql="select * from member where username=? and password=?";
		List<Member> l=new ArrayList();
		
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next())
			{
				Member m=new Member();
				m.setId(resultset.getInt("id"));
				m.setName(resultset.getString("name") );
				m.setUsername(resultset.getString("username"));
				m.setPassword(resultset.getString("password"));
				m.setAddress(resultset.getString("address"));
				m.setPhone(resultset.getString("phone"));
				m.setEmail(resultset.getString("email"));
				
				l.add(m);
				
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		
		return l;
	}

	@Override
	public void update(Member member) {
		String sql="update member set name=?,password=?,phone=?,mobile=?,where id=?";
		try {
			PreparedStatement preparedstatement =conn.prepareStatement(sql);
			preparedstatement.setString(1,member.getName());
			preparedstatement.setString(2, member.getPassword());
			preparedstatement.setString(3, member.getAddress());
			preparedstatement.setString(4, member.getPhone());
			preparedstatement.setString(5, member.getEmail());
			preparedstatement.setInt(6, member.getId());
			
			preparedstatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id ) {
		String sql="delete from member where id=?";
		try {
			PreparedStatement preparstatement =conn.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}}

	public List<Member> selectByUsername(String username) {
		String sql="select * from member where username=?";
		List<Member> l=new ArrayList();
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, username);			
			
			ResultSet resultset=preparedstatement.executeQuery();
			
			if(resultset.next())
			{
				Member m=new Member();
				m.setId(resultset.getInt("id"));
				m.setName(resultset.getString("name"));
				m.setUsername(resultset.getString("username"));
				m.setPassword(resultset.getString("password"));
				m.setAddress(resultset.getString("address"));
				m.setPhone(resultset.getString("phone"));
				m.setEmail(resultset.getString("email"));
				
				l.add(m);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}}

	

