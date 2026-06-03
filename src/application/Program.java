package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ItemDao;
import model.dao.OrderDao;
import model.dao.OrderItemDao;
import model.dao.UserDao;
import model.entities.Item;
import model.entities.Order;
import model.entities.OrderItem;
import model.entities.User;
import util.Validator;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UserDao userDao = DaoFactory.createUserDao();
		ItemDao itemDao = DaoFactory.createItemDao();
		OrderDao orderDao = DaoFactory.createOrderDao();
		OrderItemDao orderItemDao = DaoFactory.createOrderItemDao();

		User user = new User();

		/*
		 * System.out.print("Digite o seu nome: "); String name = sc.nextLine();
		 * 
		 * if(Validator.checkStringEmpty(name)) { System.out.println("Nome inválido!");
		 * return; }
		 * 
		 * System.out.print("Digite seu email: "); String email = sc.nextLine();
		 * 
		 * if(Validator.checkStringEmpty(email)) {
		 * System.out.println("E-mail inválido!"); return; }
		 * 
		 * System.out.print("Digite seu endereço: "); String address = sc.nextLine();
		 * 
		 * if(Validator.checkStringEmpty(address)) {
		 * System.out.println("Endereço inválido!"); return; }
		 * 
		 * 
		 * userDao.insert(user = new User(null, name, email, address, false));
		 * 
		 * 
		 * String path =
		 * "C:\\projetos_eclipse\\order-management-system\\lista-itens.txt";
		 * 
		 * try (BufferedReader br = new BufferedReader(new FileReader(path))){
		 * List<Item> listItens = new ArrayList<>();
		 * 
		 * String line = br.readLine();
		 * 
		 * while(line != null) { String[] fields = line.split(",");
		 * 
		 * String nameItem = fields[0]; Double price = Double.parseDouble(fields[1]);
		 * 
		 * listItens.add(new Item(null, nameItem, price)); line = br.readLine(); }
		 * 
		 * for(Item item: listItens) { itemDao.insert(item); }
		 * 
		 * System.out.println("Itens cadastrados com sucesso!"); } catch (IOException e)
		 * { System.out.println("Erro: " + e.getMessage()); }
		 */

		user = userDao.findById(3);
		
		System.out.println("=== ITEMS ===");
		System.out.println();
		List<Item> listItens = itemDao.findAll();
		for (Item item : listItens) {
			System.out.println(item);
		}
		System.out.println();
		System.out.println("============");

		System.out.println();

		List<OrderItem> carrinho = new ArrayList<>();

		char choice = 's';
		double total = 0.0;
		
		while (choice == 's') {

			System.out.print("Digite o ID do item: ");
			int itemId = sc.nextInt();
			
			Item item = itemDao.findById(itemId);
			
			if(item == null) {
				System.out.println("Item não encontrado!");
		        continue;
			}

			System.out.print("Quantidade: ");
			int quantity = sc.nextInt();
			
			if(quantity <= 0) {
			    System.out.println("Quantidade deve ser maior que zero!");
			    continue;
			}
			
			OrderItem orderItem = new OrderItem(null, null, item.getId(), quantity);
			carrinho.add(orderItem);
			
			total += item.getPrice() * quantity;

			
			System.out.println("Adicionar outro item? (s/n) ");
			choice = Character.toLowerCase(sc.next().charAt(0));
		}
		
		Order order = new Order(null, user.getId(), null, total);
		orderDao.insert(order);
		
		for(OrderItem oi : carrinho) {
			OrderItem orderItem = new OrderItem(null, order.getId(),  oi.getItemId(), oi.getQuantity());
			orderItemDao.insert(orderItem);
		}
		
		System.out.println("========================");
		System.out.println("ORDER SUMMARY");
		System.out.println("========================");	      
		System.out.println();
		
		System.out.println("User: " + user.getName());
		System.out.println("Address: " + user.getAddress());
		System.out.println();
		
		System.out.println("Itens:");	
		System.out.println("-------------------------------------");
		for(OrderItem oi: carrinho) {
			Item item = itemDao.findById(oi.getItemId());
			
			double subtotal = item.getPrice() * oi.getQuantity();
			
			System.out.printf("%s x%d  |  R$ %.2f%n", item.getName(), oi.getQuantity(), subtotal);
		}
		System.out.println("-------------------------------------");	
		System.out.println();
		
		System.out.printf("TOTAL: R$ %.2f%n", order.getTotalAmount());
		System.out.println();
		
		System.out.println(
			    "Date: " +
			    java.time.LocalDateTime.now()
			        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

		System.out.println();
		System.out.println("Order saved successfully!");

			
		
		
		sc.close();
	}

}
