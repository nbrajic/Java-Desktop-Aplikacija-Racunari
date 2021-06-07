package jtable;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Racunar;

public class JTableRacunari extends AbstractTableModel {

	private ArrayList<Racunar> lista;

	public JTableRacunari(ArrayList<Racunar> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Racunar pom = lista.get(r);
		switch (c) {
		case 0:
			return pom.getVrsta();
		case 1:
			return pom.getCena();
		case 2:
			return pom.isNov() ? "Da" : "Ne";
		default:
			return "Greska";
		}
	}

	@Override
	public void setValueAt(Object v, int r, int c) {
		try{ 
			Racunar pom = lista.get(r);
			String regex = "-?\\d+(\\.\\d+)?";
		switch (c) {
		case 0:
			if(v.toString().equalsIgnoreCase("Desktop") || v.toString().equalsIgnoreCase("Laptop")) {
				pom.setVrsta((String) v);
				Controller.updateRacunara(pom);
			}
			else 
				JOptionPane.showMessageDialog(null, "Vrsta moze biti samo u formatu 'Laptop' ili 'Desktop'!");
			break;
		case 1:
			if(v.toString().matches(regex)) {
				pom.setCena(Double.parseDouble((String) v));
				Controller.updateRacunara(pom);
			}
			else 
				JOptionPane.showMessageDialog(null, "Cena mora biti broj!");
			break;
		}}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Greska " + e.getMessage());
		}
		fireTableRowsUpdated(r, c);
	}

	@Override
	public String getColumnName(int c) {
		switch (c) {
		case 0:
			return "Vrsta";
		case 1:
			return "Cena";
		case 2:
			return "Nov";
		default:
			return "Greska";
		}
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		switch (c) {
		case 0:
		case 1:
			return true;
		default:
			return false;
		}
	}

	public void resetTabele() {
		lista.clear();
		fireTableDataChanged();
	}

	public void obrisiSelektovani(int r) {
		lista.remove(r);
		fireTableDataChanged();
	}

	public ArrayList<Racunar> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Racunar> lista) {
		this.lista = lista;
	}

}
