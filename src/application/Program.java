package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.UserDao;
import model.entities.User;
import util.Validator;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		UserDao userDao = DaoFactory.createUserDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		
		
		System.out.print("Digite o seu nome: ");
		String name = sc.nextLine();
		
		if(Validator.checkStringEmpty(name)) {
			System.out.println("Nome inválido!");
			return;
		}
		
		System.out.print("Digite seu email: ");
		String email = sc.nextLine();
		
		if(Validator.checkStringEmpty(email)) {
			System.out.println("E-mail inválido!");
			return;
		}
		
		System.out.print("Digite seu endereço: ");
		String address = sc.nextLine();
		
		if(Validator.checkStringEmpty(address)) {
			System.out.println("Endereço inválido!");
			return;
		}
		
		User user = new User(null, name, email, address, false);
		userDao.insert(user);
		
		System.out.println();
		System.out.println(user);
		
		
		
		
		sc.close();
	}

}
