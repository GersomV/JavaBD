package org.example.mocking.greeter;

public class Start {

    public static void main(String[] args) {
        GreeterService greeterService = new GreeterService(); // dependency
        String greet = greeterService.greet();
        System.out.println(greet);
    }
}
