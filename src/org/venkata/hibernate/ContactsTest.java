package org.venkata.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class ContactsTest {

	public static void main(String[] args) {
		Contacts c1 = new Contacts();
		c1.setFirst_name("John");
		c1.setLast_name("Doe");
		c1.setEmail("John.Doe@test.com");
		c1.setPhoneNumbers("1234567890");
		c1.setDateAdded(new Date());
		
		Contacts c2 = new Contacts();
		c2.setFirst_name("Venky");
		c2.setLast_name("Yern");
		c2.setEmail("Venky.Yern@test.com");
		c2.setPhoneNumbers("0987654321");
		c2.setDateAdded(new Date());
		
		PhoneNumber ph = new PhoneNumber();
		ph.setHomePhone("1234567890");
		
		Address addr1 = new Address();
		addr1.setCity("XYZ City");
		addr1.setCountry("SomeCountry");
		addr1.setState("State");
		addr1.setStreetName("ABC Street");
		
		c2.setAddress(addr1);

		Configuration cfg = new AnnotationConfiguration().configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(c1);
		session.save(c2);
		session.getTransaction().commit();
		session.close();
		
		c2 = null;
		
		session = factory.openSession();
		session.beginTransaction();
		c2 = (Contacts) session.get(Contacts.class, 2);
		System.out.println("First Name is: " + c2.getFirst_name() + " and Address is:" + c2.getAddress());
	
	}
	
}
