package bo;

public class User {
	private int id;
	private String login;
	private byte[] password;
	private byte[] salt;
	private String token;
	
	public User(int id, String login, byte[] password, byte[] salt, String token) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.salt = salt;
		this.token = token;
	}
	
	public User(String login, byte[] password, byte[] salt, String token) {
		this.login = login;
		this.password = password;
		this.salt = salt;
		this.token = token;
	}
	
	public User() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
