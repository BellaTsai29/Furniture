package model;

public class Porder {

	private Integer id;
	private Integer Chair;
	private Integer Bed;
	private Integer Shoe;
	private Integer Table;
	
	public Porder(Integer Chair ,Integer Bed,Integer Shoe, Integer Table) {
		super();
		this.Chair = Chair;
		this.Bed = Bed;
		this.Shoe = Shoe;
		this.Table=Table;
	}
	
	public Porder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChair() {
		return Chair;
	}

	public  void setChair(Integer Chair) {
		this.Chair = Chair;
	}

	public Integer getBed() {
		return Bed;
	}

	public  void setBed(Integer Bed) {
		this.Bed = Bed;
	}

	public Integer getShoe() {
		return Shoe;
	}

	public void setShoe(Integer Shoe) {
		this.Shoe = Shoe;
	}

	public Integer getTable() {
		return Table;
	}

	public void setTable(Integer Table) {
		this.Table = Table;
	}
	
	
	
	}


