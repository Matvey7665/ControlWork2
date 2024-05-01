package Presenter;

import Model.Animals.Interfaces.Teachable;
import Model.Exceptions.IdNotFoundException;
import Model.Services.Serialization;
import Model.Services.ServiceHumanFriends;
import View.UI;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Класс обеспечивает взаимосвязь пользовательского интерфейса и сервисной части модели.
 * методы дублируют команды меню UI
 */
public class Presenter {
    private final ServiceHumanFriends serviceHumanFriends;
    UI ui;

    public Presenter(UI ui) {
        this.serviceHumanFriends = ServiceHumanFriends.getServiceHumanFriends();
        this.ui = ui;
    }

    public String showAnimalsList () {
        return serviceHumanFriends.toString();
    }

    public void addAnimal(String name, String type, Date birthday, String commands, String otherData) {
        try{
            serviceHumanFriends.addAnimal(name, type, birthday,commands,otherData);
        } catch (Exception e) {
            UI.showMessage(e.getMessage());
        }
    }

    public boolean addCommand(String command) {
        if(command.isEmpty()) {
            UI.showMessage("Пропущено\n");
            return serviceHumanFriends.addCommand(command);
        } else if (serviceHumanFriends.addCommand(command)) {
            UI.showMessage("Команда добавлена\n");
            return true;
        }
        return false;
    }

    public void findAnimalById(int id) throws IdNotFoundException {
        serviceHumanFriends.findAnimalById(id);
    }

    public String showCommands() throws IdNotFoundException {
        return serviceHumanFriends.showCommands();
    }

    public String showBirthdayAnimals(Date birthday) {
        return  serviceHumanFriends.showBirthdayAnimals(birthday);
    }

    public String getFoundItem() {
        return serviceHumanFriends.getFoundItem();
    }

    public static void readData() {
        try{
            ServiceHumanFriends.readData();
            UI.showMessage("Данные считаны");
        } catch (Exception e) {
            UI.showMessage("Ошибка чтения из файла");
        }
    }

    public static void writeData() {
        try{
            ServiceHumanFriends.writeData();
            UI.showMessage("Данные записаны");
        } catch (Exception e) {
            UI.showMessage("Ошибка записи в файл");
        }
    }
}
