package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Asset;
import com.model.Employee;
import com.util.HibernateUtil;

public class App {

	public static void main(String[] args) {

		insert();
		read();
		update();
		delete();
		HibernateUtil.closeSessionFactory();
		System.out.println("Thank you");

	}

	private static void update() {
		SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
		Session session = sessionfactory.openSession();
		Transaction t = session.beginTransaction();
		Employee employee = session.get(Employee.class, 21);
		employee.setPassword("emp123");
		session.saveOrUpdate(employee);
		session.getTransaction().commit();
		session.close();
		System.out.println("Update operation completed successfully");
	}

	private static void delete() {
		SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
		Session session = sessionfactory.openSession();
		Transaction t = session.beginTransaction();
		Employee employee = session.get(Employee.class, 21);
		session.remove(employee);
		session.getTransaction().commit();
		session.close();
		System.out.println("Delete operation completed successfully");
	}

	private static void insert() {
		SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
		Session session = sessionfactory.openSession();
		Transaction t = session.beginTransaction();
		Asset asset = new Asset(101, 55555, 89098, "PC", 67899, "PCMC");
		Employee employee = new Employee("Dhiraj", 21, "abc12345", asset);
		session.save(asset);
		session.save(employee);
		session.getTransaction().commit();
		session.close();
		System.out.println("Insert operation completed successfully");
	}

	private static void read() {
		SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
		Session session = sessionfactory.openSession();
		Asset a1 = session.get(Asset.class, 101);
		System.out.println(a1);
		session.close();
		System.out.println("Read operation completed successfully");

	}
}
