package app.Model;

public class Stat {
	private String name;
	private String last;
	private String group;
	private int ilosc;
	private double poprawne;

	public Stat(String name, String last, String group, int ilosc, double poprawne) {
		super();
		this.name = name;
		this.last = last;
		this.group = group;
		this.ilosc = ilosc;
		this.poprawne = poprawne;
	}
	
	public Stat(String name, String last, double poprawne) {
		super();
		this.name = name;
		this.poprawne = poprawne;
	}

	public Stat(String name, double poprawne) {
		super();
		this.name = name;
		this.poprawne = poprawne;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getIlosc() {
		return ilosc;
	}

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public double getPoprawne() {
		return poprawne;
	}

	public void setPoprawne(double poprawne) {
		this.poprawne = poprawne;
	}

}
