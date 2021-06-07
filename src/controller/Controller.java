package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAO;
import model.Racunar;

public class Controller {
	
	private static Controller kontroler;
	private static ArrayList<Racunar> lista;
	
	private Controller() {}
	
	public static Controller getInstance() {
		if(kontroler==null) kontroler = new Controller();
		return kontroler;
	}

	public static void unosRacunara(Racunar r) {
		try {
			DAO.getInstance().unosRacunara(r);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Racunar> vratiRacunareSortiraneOpadajuce() {
		try {
			lista = DAO.getInstance().vratiRacunareSortiraneOpadajuce();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static ArrayList<Racunar> vratiRacunareSortiraneRastuce() {
		try {
			lista = DAO.getInstance().vratiRacunareSortiraneRastuce();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static void updateRacunara(Racunar r) {
		try {
			DAO.getInstance().updateRacunara(r);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Racunar> prikaziSkupljeOd(double min) {
		try {
			lista = DAO.getInstance().prikaziSkupljeOd(min);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static void obrisiSelektovani(int id) {
		try {
			DAO.getInstance().obrisiSelektovani(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Racunar> prikazNovPolovan(boolean jeNov) {
		try {
			lista = DAO.getInstance().prikazNovPolovan(jeNov);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
