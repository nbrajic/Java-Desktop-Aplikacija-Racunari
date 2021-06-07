package model;

public class Racunar {
	private int id;
	private String vrsta;
	private double cena;
	private boolean nov;

	public Racunar(int id, String vrsta, double cena, boolean nov) {
		super();
		this.id = id;
		this.vrsta = vrsta;
		this.cena = cena;
		this.nov = nov;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isNov() {
		return nov;
	}

	public void setNov(boolean nov) {
		this.nov = nov;
	}

	public String status(boolean nov) {
		if (nov)
			return "Racunar je nov.";
		else
			return "Racunar je polovan.";
	}

	@Override
	public String toString() {
		return "\nRacunar: " + vrsta + "\nCena: " + cena + "\n" + status(nov) + "\n--------------------";
	}

}
