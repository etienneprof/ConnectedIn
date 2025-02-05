package bll;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import bo.User;
import dal.UserDAO;

public class UserBLL {
	private UserDAO dao;
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
	
	public UserBLL() {
		dao = new UserDAO();
	}
	
	public User selectByLoginAndPassword(String login, String password) {
		User user = dao.selectByLogin(login);
		if (user != null) {
			byte[] hashedPassword = hashPassword(password, user.getSalt());
			if (Arrays.equals(hashedPassword, user.getPassword())) {
				return user;
			}
		}
		return null;
	}
	
	public User selectByToken(String token) {
		return dao.selectByToken(token);
	}
	
	public User insert(String login, String password) {
		User user = new User();
		user.setLogin(login);
		generateSalt(user);
		user.setPassword(hashPassword(password, user.getSalt()));
		
		dao.insert(user);
		return user;
	}
	
	private void generateSalt(User user) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		user.setSalt(salt);
	}
	
	private byte[] hashPassword(String password, byte[] salt) {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			for (byte b : hash) {
				System.out.print(b + " ");
			}
			System.out.println();
			return hash;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String generateToken(User user) {
		byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    String token = base64Encoder.encodeToString(randomBytes);
	    user.setToken(token);
	    dao.updateToken(user);
		return token;
	}
}
