package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import jtable.JTableRacunari;
import model.Racunar;

import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Button;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class frmRacunari extends JFrame {

	private JPanel contentPane;
	private JTextField txtCena;
	private ButtonGroup grupa;
	private JTextField txtPrikaziSkuplje;
	private JTable tableRacunari;
	private ArrayList lista;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRacunari frame = new frmRacunari();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmRacunari() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Racunari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 23, 202, 147);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox cmbVrsta = new JComboBox();
		cmbVrsta.setModel(new DefaultComboBoxModel(new String[] { "Desktop", "Laptop" }));
		cmbVrsta.setBounds(86, 21, 106, 22);
		panel.add(cmbVrsta);

		JLabel lblNewLabel = new JLabel("Vrsta:");
		lblNewLabel.setBounds(10, 25, 46, 14);
		panel.add(lblNewLabel);

		txtCena = new JTextField();
		txtCena.setBounds(86, 54, 106, 20);
		panel.add(txtCena);
		txtCena.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cena:");
		lblNewLabel_1.setBounds(10, 57, 46, 14);
		panel.add(lblNewLabel_1);

		JRadioButton rdbtnNov = new JRadioButton("Nov");
		rdbtnNov.setBounds(10, 81, 109, 23);
		panel.add(rdbtnNov);

		JRadioButton rdbtnPolovan = new JRadioButton("Polovan");
		rdbtnPolovan.setBounds(10, 107, 109, 23);
		panel.add(rdbtnPolovan);

		grupa = new ButtonGroup();
		grupa.add(rdbtnNov);
		grupa.add(rdbtnPolovan);

		Button btnUnos = new Button("Unos");
		btnUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regex = "-?\\d+(\\.\\d+)?";
				if(txtCena.getText().matches(regex)) {
					String vrsta = (String) cmbVrsta.getSelectedItem();
					double cena = Double.parseDouble(txtCena.getText());
					boolean nov = rdbtnNov.isSelected();
					Controller.unosRacunara(new Racunar(0, vrsta, cena, nov)); 
				}
				else {
					JOptionPane.showMessageDialog(null, "Cena mora biti broj!");
					clearFields();
				}
			}
		});
		btnUnos.setBounds(122, 115, 70, 22);
		panel.add(btnUnos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 28, 335, 433);
		contentPane.add(scrollPane);

		tableRacunari = new JTable();
		tableRacunari.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Vrsta", "Cena", "Nov" }));
		scrollPane.setViewportView(tableRacunari);

		JComboBox cmbSortiranje = new JComboBox();
		cmbSortiranje.setModel(new DefaultComboBoxModel(new String[] { "Opadajuce", "Rastuce" }));
		cmbSortiranje.setBounds(127, 186, 103, 22);
		contentPane.add(cmbSortiranje);

		Button btnSort = new Button("Prikazi:");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbSortiranje.getSelectedItem().equals("Opadajuce")) 
					lista = Controller.vratiRacunareSortiraneOpadajuce();
				else {
					lista = Controller.vratiRacunareSortiraneRastuce();
				}
				tableRacunari.setModel((TableModel) new JTableRacunari(lista));
			}
		});
		btnSort.setBounds(28, 186, 83, 22);
		contentPane.add(btnSort);

		txtPrikaziSkuplje = new JTextField();
		txtPrikaziSkuplje.setColumns(10);
		txtPrikaziSkuplje.setBounds(151, 228, 79, 22);
		contentPane.add(txtPrikaziSkuplje);

		Button btnPrikaziSkuplje = new Button("Prikazi skuplje od:");
		btnPrikaziSkuplje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regex = "-?\\d+(\\.\\d+)?";
				if(txtPrikaziSkuplje.getText().matches(regex)) {
					Double min = Double.parseDouble(txtPrikaziSkuplje.getText());
					lista = Controller.prikaziSkupljeOd(min);
					tableRacunari.setModel((TableModel) new JTableRacunari(lista));
				}
				else {
					JOptionPane.showMessageDialog(null, "Cena mora biti broj!");
					clearFields();
				}
			}
		});
		btnPrikaziSkuplje.setBounds(28, 228, 108, 22);
		contentPane.add(btnPrikaziSkuplje);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Polovan", "Nov"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(30, 274, 97, 37);
		contentPane.add(list);

		Button btnPrikazi = new Button("Prikazi");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean nov = list.getSelectedValue().equals("Nov") ? true : false;
				lista = Controller.prikazNovPolovan(nov);
				tableRacunari.setModel((TableModel) new JTableRacunari(lista));
			}
		});
		btnPrikazi.setBounds(147, 279, 83, 22);
		contentPane.add(btnPrikazi);

		Button btnObrisi = new Button("Obrisi selektovani");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTableRacunari model = (JTableRacunari) tableRacunari.getModel();
				int selectedRow = tableRacunari.getSelectedRow();
				if(selectedRow != -1) {
					int id = model.getLista().get(selectedRow).getId();
					Controller.obrisiSelektovani(id);
					model.obrisiSelektovani(selectedRow);
				}
				else {
					JOptionPane.showMessageDialog(getContentPane(), "Nije selektovan racunar za brisanje.");
				}
			}
		});
		btnObrisi.setBounds(53, 348, 136, 22);
		contentPane.add(btnObrisi);

		Button btnReset = new Button("Resetuj");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableRacunari.getRowCount() != 0) {
					JTableRacunari model = (JTableRacunari) tableRacunari.getModel();
					model.resetTabele();
				}
				else {
					JOptionPane.showMessageDialog(null, "Greska!");
				}
			}
		});
		btnReset.setBounds(53, 396, 136, 22);
		contentPane.add(btnReset);
	}

	private void clearFields() {
		txtCena.setText("");
		txtPrikaziSkuplje.setText("");
		grupa.clearSelection();
	}
}
