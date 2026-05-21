package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.UserDao;
import model.entities.Item;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		UserDao userDao = DaoFactory.createUserDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		
		/*
		 
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
		
		 */
		
		String path = "C:\\projetos_eclipse\\order-management-system\\lista-itens.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Item> listItens = new ArrayList<>();
			
			String line = br.readLine();
			
			while(line != null) {
				String[] fields = line.split(",");
				
				String name = fields[0];
				Double price = Double.parseDouble(fields[1]);
				
				listItens.add(new Item(null, name, price));
				line = br.readLine();
			}
			
			for(Item item: listItens) {
				itemDao.insert(item);
			}
			
			System.out.println("Itens cadastrados com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		sc.close();
	}

}
