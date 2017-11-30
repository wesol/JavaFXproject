package app.Model;

public class Edycja {
	
	private String edycja;
	private Integer procent;
	public String getEdycja() {
		return edycja;
	}
	public void setEdycja(String edycja) {
		this.edycja = edycja;
	}
	public Integer getProcent() {
		return procent;
	}
	public void setProcent(Integer procent) {
		this.procent = procent;
	}
	public Edycja(String edycja, Integer procent) {
		super();
		this.edycja = edycja;
		this.procent = procent;
	}
	public Edycja(String edycja) {
		super();
		this.edycja = edycja;
	}
	


}
