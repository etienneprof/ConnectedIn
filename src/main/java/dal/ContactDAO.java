package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Contact;

public class ContactDAO {
	private static final String SELECT = "SELECT * FROM contacts";
	private static final String SELECT_BY_ID = "SELECT * FROM contacts WHERE id = ?";
	private static final String SELECT_BY_NOM_OR_PRENOM = "SELECT * FROM contacts WHERE nom LIKE ? OR prenom LIKE ?";
	private static final String INSERT = "INSERT INTO contacts (nom, prenom, date_naissance, telephone, url, poste, specialite) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE contacts SET nom = ?, prenom = ?, date_naissance = ?, telephone = ?, url = ?, poste = ?, specialite = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM contacts WHERE id = ?";

	public List<Contact> select() {
		List<Contact> contacts = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				contacts.add(convertResultSetToContact(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	public Contact select(int id) {
		Contact result = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result = convertResultSetToContact(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void insert(Contact contact) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, contact.getNom());
			ps.setString(2, contact.getPrenom());
			ps.setDate(3, Date.valueOf(contact.getDateNaissance()));
			ps.setString(4, contact.getTelephone());
			ps.setString(5, contact.getUrl());
			ps.setString(6, contact.getPoste());
			ps.setString(7, contact.getSpecialite());			
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				contact.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Contact contact) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, contact.getNom());
			ps.setString(2, contact.getPrenom());
			ps.setDate(3, Date.valueOf(contact.getDateNaissance()));
			ps.setString(4, contact.getTelephone());
			ps.setString(5, contact.getUrl());
			ps.setString(6, contact.getPoste());
			ps.setString(7, contact.getSpecialite());	
			ps.setInt(8, contact.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean delete(int id) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Contact convertResultSetToContact(ResultSet rs) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getInt("id"));
		contact.setNom(rs.getString("nom"));
		contact.setPrenom(rs.getString("prenom"));
		contact.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
		contact.setTelephone(rs.getString("telephone"));
		contact.setUrl(rs.getString("url"));
		contact.setPoste(rs.getString("poste"));
		contact.setSpecialite(rs.getString("specialite"));
		return contact;
	}

	public List<Contact> selectByNomOrPrenom(String filtre) {
		List<Contact> contacts = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_NOM_OR_PRENOM);
			ps.setString(1, "%" + filtre + "%");
			ps.setString(2, "%" + filtre + "%");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				contacts.add(convertResultSetToContact(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}
}
