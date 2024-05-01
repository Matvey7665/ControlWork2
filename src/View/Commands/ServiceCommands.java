package View.Commands;

import Model.Exceptions.DateParseException;
import Model.Exceptions.IdNotFoundException;
import Presenter.Presenter;
import View.UI;

import java.text.ParseException;
import java.util.Date;

public class ServiceCommands {
    Presenter presenter;

    public ServiceCommands(Presenter presenter) {
        this.presenter = presenter;
    }

    public static Date inputDate(String msg) throws DateParseException {
        System.out.print(msg);
        String input = UI.scanner.nextLine();
        try {
            return UI.df.parse(input);
        } catch (ParseException e) {
            throw new DateParseException("Ожидается значение в формате дд.мм.ггг", 0);
        }
    }
    public void choosingAnAnimal() {
        try {
            int id = UI.scanner.nextInt();
            UI.scanner.nextLine();
            findAnimalById(id);
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            UI.showMessage("Неверный ввод: ожидается число");
        }
    }

    public void findAnimalById(int id) throws IdNotFoundException {
        presenter.findAnimalById(id);
    }

    public void init() {
        try {
            presenter.readData();
//            presenter.addAnimal("Rex", "Dog", UI.df.parse("01.01.2020"), "Sit, Stay, Fetch", "Cage");
//            presenter.addAnimal("Whiskers", "Cat", UI.df.parse("15.05.2019"), "Sit, Pounce", "Box");
//            presenter.addAnimal("Hammy", "Hamster", UI.df.parse("10.03.2021"), "Roll, Hide", "Terrarium");
//            presenter.addAnimal("Thunder", "Horse", UI.df.parse("21.07.2021"), "Trot, Canter, Gallop", "300");
//            presenter.addAnimal("Sandy", "Camel", UI.df.parse("03.11.2016"), "Sit, Stay, Roll", "600");
//            presenter.addAnimal("Eeyore", "Donkey", UI.df.parse("01.01.2019"), "Walk, Carry Load, Bray", "250");
        } catch(Exception e) {
            UI.showMessage(e.getMessage());
        }
    }

}
