package View;

import View.MenuItems.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Меню программы
 */
public class Menu {
    Scanner scanner;
    List<MenuItem> menu = new ArrayList<>();

    public Menu(UI ui, Scanner scanner){
        this.scanner = scanner;
        menu.add(new Create(ui));
        menu.add(new Show(ui));
        menu.add(new AddCommand(ui));
        menu.add(new ShowCommands(ui));
        menu.add(new BirthdayAnimals(ui));
        menu.add(new Exit(ui));
        start();
    }

    private void start() {
        MenuItem menuItem;
        boolean access = true;
        do {
            showItemsMenu();
            try {
                menuItem = menu.get(scanner.nextInt() - 1);
                scanner.nextLine();
                access = (menuItem.execute());
            } catch (Exception e) {
                scanner.nextLine();
                UI.showMessage("Неверный ввод. Введите число от 1 до " + menu.size());
            }
        } while(access);
    }

    private void showItemsMenu() {
        System.out.println("\n        Меню:");
        System.out.println("-".repeat(20));
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getName());
        }
        System.out.println("-".repeat(20));
        System.out.print("Ваш выбор: ");
    }
}
