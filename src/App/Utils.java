package App;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    public static Company initData() {
        Company company = new Company("Super Firma");
        company.getEmployees().add(new Employee(new Person("Jan", "Testowy"), Departament.SALES));
        company.getEmployees().add(new Employee(new Person("Kazimierz", "Przykładowy"), Departament.ADMINISTRATION));
        company.getEmployees().add(new Employee(new Person("Maria", "Demo"), Departament.FINANCE));
        return company;
    }

    public static void printBanner(String text) {
        String border = "+" + "-".repeat(text.length() + 2) + "+";
        System.out.println(border);
        System.out.println("| " + text + " |");
        System.out.println(border);
    }

    public static void printAnswer(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static int inputInt(String message) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            printAnswer("Podano błędną wartość, spróbuj jeszcze raz.");
            return inputInt(message);
        }
    }

    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }
}
