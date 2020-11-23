
package application;


import java.util.List;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class Program {
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("TEST 2: seller findbyDepartment ->>");
		Department  department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj:list) {
			System.out.println(obj);
		}
		
		// findall
		System.out.println("TEST 3: seller findall ->>");		
	    list = sellerDao.findAll();
		for(Seller obj:list) {
			System.out.println(obj);
		}
		/*
		System.out.println("TEST 4: seller insert ->>");
		Seller newSeller = new Seller(null, "Gina", "gina@gmail.com", new Date(), 5000.00, department);
		sellerDao.insert(newSeller);
		System.out.println("Insered with new id:" + newSeller.getIdInteger());
		*/
		/*
		System.out.println("TEST 5: seller update ->>");
		
		seller = sellerDao.findById(1);
		seller.setNameString("Marta Vine");
		sellerDao.update(seller);
		System.out.println("Update completed");
		*/
		Scanner scanner = new Scanner(System.in);
		System.out.println("TEST 6: deleteById ->>");
		System.out.print("Enter with id to delete: ");
		int id = scanner.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		scanner.close();
		
		
	}
}
