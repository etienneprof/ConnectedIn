package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.User;

public class UserDAO {
	private static final String SELECT_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
	private static final String SELECT_BY_TOKEN = "SELECT * FROM users WHERE token = ?";
	private static final String INSERT = "INSERT INTO users (login, password, salt) VALUES (?,?,?)";
	private static final String UPDATE_TOKEN = "UPDATE users SET token = ? WHERE id = ?"; 

	public User selectByLogin(String login) {
		User result = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_LOGIN);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result = convertResultSetToUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User selectByToken(String token) {
		User result = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_TOKEN);
			ps.setString(1, token);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result = convertResultSetToUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void insert(User user) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getLogin());
			ps.setBytes(2, user.getPassword());
			ps.setBytes(3, user.getSalt());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private User convertResultSetToUser(ResultSet rs) throws SQLException {
		User user = new User(
				rs.getInt("id"),
				rs.getString("login"),
				rs.getBytes("password"),
				rs.getBytes("salt"),
				rs.getString("token")
				);
		return user;
	}

	public void updateToken(User user) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement ps = cnx.prepareStatement(UPDATE_TOKEN);
			ps.setString(1, user.getToken());
			ps.setInt(2, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
