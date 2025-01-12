package App;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Company company = Utils.initData();
        Utils.printBanner("Panel administracyjny firmy " + company.getName());

        boolean done = false;
        while (!done) {

            System.out.println("Dostępne operacje:");
            System.out.println("1 - Wyświetl pracowników");
            System.out.println("2 - Dodaj pracownika");
            System.out.println("3 - Edytuj pracownika");
            System.out.println("4 - Usuń pracownika");
            System.out.println("0 - Zakończ program");
            System.out.println();

            int choice = Utils.inputInt("Wybierz operację: ");

            switch (choice) {
                case 1:
                    listEmployees(company);
                    break;
                case 2:
                    addEmployee(company);
                    break;
                case 3:
                    updateEmployee(company);
                    break;
                case 4:
                    deleteEmployee(company);
                    break;
                case 0:
                    Utils.printAnswer("Do zobaczenia");
                    done = true;
                    break;
                default:
                    Utils.printAnswer("Podano błędną wartość, spróbuj jeszcze raz");
            }
        }
    }

    private static void listEmployees(Company company) {
        List<Employee> employees = company.getEmployees();
        if (employees.isEmpty()) {
            Utils.printAnswer("Brak pracowników w firmie.");
        } else {
            Utils.printAnswer("Lista pracowników:");
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                System.out.printf("%d. %s %s, Dział: %s, Adres: %s %s\n",
                        i + 1,
                        employee.getName(),
                        employee.getSurname(),
                        employee.getDepartament().getName(),
                        employee.getAddress() != null ? employee.getAddress().getCity() : "Brak",
                        employee.getAddress() != null ? employee.getAddress().getStreet() : "Brak");
            }
        }
    }

    private static void addEmployee(Company company) {
        String name = Utils.inputString("Podaj imię: ");
        String surname = Utils.inputString("Podaj nazwisko: ");
        String city = Utils.inputString("Podaj miasto: ");
        String street = Utils.inputString("Podaj ulicę: ");

        System.out.println("Dostępne działy:");
        for (Departament dept : Departament.values()) {
            System.out.println(dept.ordinal() + 1 + " - " + dept.getName());
        }
        int deptChoice = Utils.inputInt("Wybierz dział: ") - 1;

        if (deptChoice < 0 || deptChoice >= Departament.values().length) {
            Utils.printAnswer("Niepoprawny wybór działu.");
            return;
        }

        Departament departament = Departament.values()[deptChoice];
        Person person = new Person(name, surname);
        person.setAddress(city, street);
        Employee employee = new Employee(person, departament);

        company.getEmployees().add(employee);
        Utils.printAnswer("Dodano pomyślnie pracownika: " + name + " " + surname);
    }

    private static void updateEmployee(Company company) {
        listEmployees(company);
        int index = Utils.inputInt("Podaj numer pracownika do edycji: ") - 1;

        if (index < 0 || index >= company.getEmployees().size()) {
            Utils.printAnswer("Niepoprawny numer pracownika.");
            return;
        }

        Employee employee = company.getEmployees().get(index);

        String newName = Utils.inputString("Podaj nowe imię (obecne: " + employee.getName() + "): ");
        String newSurname = Utils.inputString("Podaj nowe nazwisko (obecne: " + employee.getSurname() + "): ");
        String newCity = Utils.inputString("Podaj nowe miasto (obecne: " + (employee.getAddress() != null ? employee.getAddress().getCity() : "Brak") + "): ");
        String newStreet = Utils.inputString("Podaj nową ulicę (obecna: " + (employee.getAddress() != null ? employee.getAddress().getStreet() : "Brak") + "): ");

        System.out.println("Dostępne działy:");
        for (Departament dept : Departament.values()) {
            System.out.println(dept.ordinal() + 1 + " - " + dept.getName());
        }
        int deptChoice = Utils.inputInt("Wybierz nowy dział (obecny: " + employee.getDepartament().getName() + "): ") - 1;

        if (deptChoice >= 0 && deptChoice < Departament.values().length) {
            employee.setDepartament(Departament.values()[deptChoice]);
        }

        employee.setName(newName.isEmpty() ? employee.getName() : newName);
        employee.setSurname(newSurname.isEmpty() ? employee.getSurname() : newSurname);
        employee.setAddress(newCity.isEmpty() ? employee.getAddress().getCity() : newCity,
                newStreet.isEmpty() ? employee.getAddress().getStreet() : newStreet);

        Utils.printAnswer("Zaktualizowano dane pracownika.");
    }

    private static void deleteEmployee(Company company) {
        listEmployees(company);
        int index = Utils.inputInt("Podaj numer pracownika do usunięcia: ") - 1;

        if (index < 0 || index >= company.getEmployees().size()) {
            Utils.printAnswer("Niepoprawny numer pracownika.");
            return;
        }

        Employee removedEmployee = company.getEmployees().remove(index);
        Utils.printAnswer("Usunięto pracownika: " + removedEmployee.getName() + " " + removedEmployee.getSurname());
    }
}
