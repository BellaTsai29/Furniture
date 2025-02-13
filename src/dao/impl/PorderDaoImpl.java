package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PorderDao;
import model.Porder;
import util.DbConnection;

public class PorderDaoImpl implements PorderDao {

	public static void main(String[] args) {
		new PorderDaoImpl().delete(4);
	}
	private static Connection conn=DbConnection.getDb();
	
	
	
	@Override
	public void add(Porder porder) {
		String Sql = "insert into porder(Chair, Bed, Shoe, `Table`) values(?,?,?,?)";
		try {

			PreparedStatement preparedstatement =conn.prepareStatement(Sql);
			preparedstatement.setInt(1, porder.getChair());
			preparedstatement.setInt(2, porder.getBed());
			preparedstatement.setInt(3, porder.getShoe());
			preparedstatement.setInt(4, porder.getTable());
			
			preparedstatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	

	@Override
	public List<Porder> selectAll() {
	    String sql = "select * from porder";
	    List<Porder> list = new ArrayList<>();
	    
	    try {
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);    
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while(resultSet.next()){
	            Porder porder = new Porder();
	            // 假設 Porder 有 setId 方法，且資料庫欄位名稱為 "ID"
	            porder.setId(resultSet.getInt("id")); 
	            porder.setChair(resultSet.getInt("Chair"));
	            porder.setBed(resultSet.getInt("Bed"));
	            porder.setShoe(resultSet.getInt("Shoe"));
	            porder.setTable(resultSet.getInt("Table"));
	            list.add(porder);    
	        }
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public List<Porder> selectById(int id) {
		String Sql="select * from porder where id=?";
		List<Porder> l=new ArrayList();
		
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(Sql);
			preparedstatement.setInt(1, id);
			ResultSet resultset=preparedstatement.executeQuery();
			if(resultset.next())
			{
				Porder porder=new Porder();
				porder.setId(resultset.getInt("id")); 
				porder.setChair(resultset.getInt("Chair"));
				porder.setBed(resultset.getInt("Bed"));
				porder.setShoe(resultset.getInt("Shoe"));
				porder.setTable(resultset.getInt("Table"));
				l.add(porder);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public void update(Porder porder) {
		// TODO Auto-generated method stub
		String sql="update porder set Chair=?,Bed=?,Shoe=?,`Table`=? where id=?";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, porder.getChair());
			preparedstatement.setInt(2, porder.getBed());
			preparedstatement.setInt(3, porder.getShoe());
			preparedstatement.setInt(4, porder.getTable());
			preparedstatement.setInt(5, porder.getId());
			
			preparedstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void delete(int id) {
		String sql="delete from porder where id=?";
		try {
			PreparedStatement preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
