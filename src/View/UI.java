package View;

import Model.Exceptions.IdNotFoundException;
import Model.Services.HumanFriendsList;
import Model.Services.Serialization;
import Presenter.Presenter;
import View.Commands.AddAnimalCommand;
import View.Commands.ServiceCommands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UI {
    private final Presenter presenter;
    private final ServiceCommands serviceCommands;
    AddAnimalCommand addAnimalCommand;
    public static final Scanner scanner = new Scanner(System.in);
    public static final DateFormat df;

    static {
        df = new SimpleDateFormat("dd.MM.yyyy");
    }

    public UI() throws IdNotFoundException {
        this.presenter = new Presenter(this);
        serviceCommands = new ServiceCommands(presenter);
        addAnimalCommand = new AddAnimalCommand();
        serviceCommands.init();
        System.out.println("\nРеестр домашних животных\n    \"Human friends\"");
        new Menu(this, scanner);
    }

    public void addAnimal(){
        addAnimalCommand.addAnimal(presenter);
    }

    public void showAnimalList() {
        System.out.println("\nСписок животных:\n" + presenter.showAnimalsList());
    }

    public void addCommand() {
        System.out.print("\nДобавление команды\nВведите id животного: ");
        serviceCommands.choosingAnAnimal();
        while (true) {
            String animal = presenter.getFoundItem();
            if(animal.isEmpty()) {
                break;
            }
            showMessage(animal);
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();
            if (!presenter.addCommand(command.trim())){
                break;
            };
        }
    }


    public void showCommands() throws IdNotFoundException {
        System.out.print("\nВывод команд\nВведите id животного: ");
        serviceCommands.choosingAnAnimal();
        System.out.print("\nСписок команд: \n");
        System.out.println(presenter.showCommands());
    }


    public void showBirthdayAnimals() {
        System.out.println("\nВывод именинников");
        try {
            Date birthday = ServiceCommands.inputDate("Введите дату: ");
            System.out.println("\nСписок именинников:");
            showMessage(presenter.showBirthdayAnimals(birthday));
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public static void showMessage(String msg) {
        System.out.println(msg);
    }

    public void exit() {
        Presenter.writeData(); // Сохраняем данные в файл
        System.out.println("\nДо встречи!");
    }

}