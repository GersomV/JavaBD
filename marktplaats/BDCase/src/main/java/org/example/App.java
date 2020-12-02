package org.example;

import org.example.boundary.MainMenu;
import org.example.dao.ProductDao;
import org.example.dao.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App {

    public static final EntityManager em = Persistence.createEntityManagerFactory("MySQL-jpademo").createEntityManager();
    public static final ProductDao prodDao = new ProductDao(em);
    public static final UserDao usrDao = new UserDao(em);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { new MainMenu().start(); }

    public static String readLine() { return scanner.nextLine(); }

    public static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

}
