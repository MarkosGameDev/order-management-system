package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		UserDao userDao = DaoFactory.createUserDao();
		
		/* 
		System.out.print("Digite o seu nome: ");
		String name = sc.nextLine();
		
		System.out.print("Digite seu email: ");
		String email = sc.nextLine();
		
		System.out.print("Digite seu endereço: ");
		String address = sc.nextLine();
		
		User user = new User(null, name, email, address, false);
		userDao.insert(user);
		
		System.out.println();
		System.out.println(user);
		*/
		
		List<User> listUser = userDao.findAll();
		for(User obj: listUser) {
			System.out.println(obj);
		}
		
		
		sc.close();
	}

}
