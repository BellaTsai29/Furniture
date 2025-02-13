package service.impl;

import java.util.List;

import dao.impl.PorderDaoImpl;
import model.Porder;
import service.PorderService;

public class PorderServiceImpl implements PorderService {

	public static void main(String[] args) {
		System.out.println(new PorderServiceImpl().findAllPorder());

	}
	private static PorderDaoImpl porderdaoimpl=new PorderDaoImpl();
	
	@Override
	public void addPorder(Porder porder) {
		if(porder.getChair()>=0 && porder.getBed()>=0 && porder.getShoe()>=0 && porder.getTable()>=0)
		{
			porderdaoimpl.add(porder);
		}
		
	}

	@Override
	public String AllPorder() {
		List<Porder> l=porderdaoimpl.selectAll();
		String show="";
		for(Porder p:l)
		{
			int sum=p.getChair()*4999+p.getBed()*1280+p.getShoe()*799+p.getTable()*500;
			
			show=show+"訂單編號:"+p.getId()+
					"\nChair:"+p.getChair()+
					"\nBed:"+p.getBed()+
					"\nShoe"+p.getShoe()+
					"\nTable"+p.getTable()+
					"\n金額:"+sum+"元\n";
		}
		return show;
	}

	@Override
	public List<Porder> findAllPorder() {
		return porderdaoimpl.selectAll();
	}

	@Override
	public Porder findById(int id) {
		/*
		 * 1.id>=0
		 * 2.Porder無訂單--->null
		 */
		Porder porder=null;
		if(id>=0)
		{
			List<Porder> l=porderdaoimpl.selectById(id);
			if(l.size()>=0)
			{
				porder=l.get(0);
			}
			
		}	
		
		
		
		return porder;
	}

}
