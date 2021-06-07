package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Racunar;

public class DAO {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private static DAO dao;
	
	private DAO() {};
	
	public static DAO getInstance() {
		if(dao == null) dao = new DAO();
		return dao;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/racunari", "root", "");
	}

	public ArrayList<Racunar> vratiRacunareSortiraneOpadajuce() throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		String query = "SELECT * From racunari ORDER BY cena DESC";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			int id = resultSet.getInt("id");
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		close();
		return lista;
	}

	public ArrayList<Racunar> vratiRacunareSortiraneRastuce() throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		String query = "SELECT * From racunari ORDER BY cena ASC";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			int id = resultSet.getInt("id");
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		close();
		return lista;
	}

	public void obrisiSelektovani(int id) throws ClassNotFoundException, SQLException {
		connect();
		String query = "DELETE FROM racunari WHERE id = ?";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		close();
	}

	public void unosRacunara(Racunar r) throws ClassNotFoundException, SQLException {
		connect();
		String query = "INSERT INTO racunari(`vrsta`, `cena`, `nov`) VALUES (?, ?, ?)";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, r.getVrsta());
		preparedStatement.setDouble(2, r.getCena());
		preparedStatement.setBoolean(3, r.isNov());
		preparedStatement.execute();
		close();
	}

	public void updateRacunara(Racunar r) throws ClassNotFoundException, SQLException {
		connect();
		String query = "UPDATE racunari SET `vrsta`=?, `cena`=? WHERE `id`=?";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, r.getVrsta());
		preparedStatement.setDouble(2, r.getCena());
		preparedStatement.setInt(3, r.getId());
		preparedStatement.execute();
		close();
	}
	
	public ArrayList<Racunar> prikaziSkupljeOd(double min) throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		String query = "SELECT * From racunari WHERE cena > ?";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setDouble(1, min);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();

		while (resultSet.next()) {
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			int id = resultSet.getInt("id");
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		close();
		return lista;
	}
	
	public ArrayList<Racunar> prikazNovPolovan(boolean jeNov) throws ClassNotFoundException, SQLException {
		ArrayList<Racunar> lista = new ArrayList<Racunar>();
		connect();
		String query = "SELECT * FROM `racunari` WHERE nov = ?";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setBoolean(1, jeNov);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while (resultSet.next()) {
			String vrsta = resultSet.getString("vrsta");
			double cena = resultSet.getDouble("cena");
			boolean nov = resultSet.getBoolean("nov");
			int id = resultSet.getInt("id");
			Racunar r = new Racunar(id, vrsta, cena, nov);
			lista.add(r);
		}
		close();
		return lista;
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
