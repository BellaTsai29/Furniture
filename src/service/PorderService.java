package service;

import java.util.List;

import model.Porder;

public interface PorderService {
	
	//create
	void addPorder(Porder porder);
	
	//read-->列印報表
	String AllPorder();
	List<Porder> findAllPorder();
	Porder findById(int id);
	
	//update
	
	//delete
	

}
