package org.example.boundary;

import org.example.domain.Product;
import org.example.domain.User;

import java.util.Scanner;

import static java.lang.Long.parseLong;
import static org.example.App.*;

public class RegistrerenGebruikers implements Boundary {
    private static final Scanner scan = new Scanner(System.in);

    private String zoeknaam = null;

    private static RegistrerenGebruikers self;

    private RegistrerenGebruikers() {
    }

    public static synchronized RegistrerenGebruikers registrerenGebruikers() {
        if (self == null) self = new RegistrerenGebruikers();
        return self;
    }


    public void start() {
        User userWithCookie = null;
        while (true) {
            System.out.println("Wat wilt u doen?");
            System.out.println("(1) [aanmelden]");
            System.out.println("(2) [inloggen]");
            System.out.println("(3) [uw advertenties bekijken]");
            System.out.println("(4) [een advertentie plaatsen]");
            System.out.println("(5) [een product in uw winkelmandje plaatsen]");
            System.out.println("(6)) [winkelwagen inzien];");
            System.out.println("(7)) [bekijk alle advertenties];");


            System.out.println("(x) [Terug]");

            switch (readLine()) {

                case "1":
                    add();
                    break;
                case "2":
                    System.out.println("Login");
                    System.out.println("Gebruikersnaam: ");
                    String name = scan.nextLine();
                    userWithCookie = usrDao.currentUser(name);
                    System.out.println("Wachtwoord: ");
                    String password = scan.nextLine();
                    if (!password.equals(userWithCookie.getPassWord())) {
                        System.out.println("Ongeldige combinatie van username en password");
                    }
                    else {
                        System.out.println("Ingelogd als " + usrDao.currentUser(name).getUserName());
                        User cookiedUser = usrDao.currentUser(name);
                    }
                    break;
                case "3":
                    if (userWithCookie != null){
                        usrDao.findAllAds(userWithCookie.getId());
                    }
                    else{
                        System.out.println("Nog niet ingelogd");

                    };
                    break;
                case "4":
                    System.out.println("Voeg een advertentie toe. ");
                    System.out.println("Titel van de advertentie: ");
                    String title = scan.nextLine();
                    System.out.println("Een bondige advertentietekst: ");
                    String body = scan.nextLine();
                    System.out.println("Prijs: ");
                    int price = scan.nextInt();
                    Product newAd = new Product(title, body, price);
                    usrDao.placeANewAd(userWithCookie, newAd);
                    newAd.setUser(userWithCookie);
                    System.out.println("new product added " + newAd.getTitle());
                    break;
                case "5":
                    System.out.println("kies uw product om toe te voegen:");

                    break;
                case "6":
                    usrDao.listShoppingCard(userWithCookie.getId());
                    break;
                case "7":
                    prodDao.findAllProducts();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Ongeldige keuze; probeer opnieuw.");
            }
        }
    }

    private void add() {
        System.out.println("Aanmelden nieuwe gebruiker");
        usrDao.save(new User(prompt("Naam: "), prompt("\nWachtwoord: ")));
        System.out.println("Gebruiker is nu geregistreerd");
    }
}
