package bll;

import java.util.List;

import bo.Contact;
import dal.ContactDAO;

public class ContactBLL {
	private ContactDAO dao;
	
	public ContactBLL() {
		dao = new ContactDAO();
	}
	
	public List<Contact> select() {
		return dao.select();
	}
	
	public Contact select(int id) {
		return dao.select(id);
	}
	
	public void insert(Contact contact) {
		dao.insert(contact);
	}
	
	public void update(Contact contact) {
		dao.update(contact);
	}
	
	public void delete(int id) {
		dao.delete(id);
	}

	public List<Contact> selectByNomOrPrenom(String filtre) {
		return dao.selectByNomOrPrenom(filtre);
	}
}
