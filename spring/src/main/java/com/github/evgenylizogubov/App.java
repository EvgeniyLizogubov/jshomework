package com.github.evgenylizogubov;

import com.github.evgenylizogubov.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.github.evgenylizogubov");
        Cart cart = context.getBean(Cart.class);
        ProductRepository repository = context.getBean(ProductRepository.class);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nДоступные продукты:");
            repository.getAll().forEach(System.out::println);
            
            System.out.println("\nПродукты в корзине:");
            cart.getAll().forEach(System.out::println);
            
            System.out.println("\nДля добавления продукта в корзину введите: add id_товара");
            System.out.println("Для удаления продукта в корзину введите: remove id_товара");
            
            String operation = scanner.nextLine();
            
            if (operation.startsWith("add ")) {
                cart.addProduct(Integer.parseInt(operation.split("\s")[1]));
            }
            
            if (operation.startsWith("remove ")) {
                cart.removeProduct(Integer.parseInt(operation.split("\s")[1]));
            }
        }
    }
}
