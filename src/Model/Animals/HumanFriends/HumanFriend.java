package Model.Animals.HumanFriends;

import Model.Animals.Animal;
import Model.Animals.Interfaces.Teachable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Абстрактный класс HumanFriend, служит для описания класса животных -
 * друзей человека. Определяет поля:
 * id - идентификатор,
 * name - кличка животного,
 * birthday - день рождения,
 * commands - список команд, выполняемых животным;
 * содержит:
 * конструктор с полным списком полей;
 * метод добавления команды,
 * метод вывода списка команд
 */
public abstract class HumanFriend extends Animal implements Teachable {
    private final String name;
    private final List<String> commands;

    /**
     * Конструктор класса HumanFriend
     * @param id идентификатор
     * @param name кличка
     * @param birthday день рождения
     * @param commands список выполняемых команд
     */
    public HumanFriend(int id, String name, Date birthday, List<String> commands) {
        super(id, birthday);
        this.name = name;
        this.commands=new ArrayList<>();
        commands.forEach(n -> this.commands.add(n.trim()));
        new Object();
    }

    /**
     * Меетод добавляет команду command в список команд commands
     * @param command новая команда
     */
    @Override
    public void teachCommand(String command) {
        commands.add(command);
    }

    /**
     * Формирует строку команд, выполняемых животным
     * @return возвращает строку со списком выполняемых команд
     */
    @Override
    public String showCommands() {
        return this.commands.toString();
    }

    /**
     * Выводит информацию о животном: id, тип животного, кличка, день рождения, список команд
     * @return строка информации
     */
    @Override
    public String getInfo() {
        return  getShortInfo() +
                ", день рождения: " + df.format(this.getBirthday())  +
                ", команды: " + this.commands;
    }

    /**
     * Выводит краткую информацию о животном: id, тип животного, кличка
     * @return строка информации
     */
    @Override
    public String getShortInfo() {
        return  super.getShortInfo() +
                ", имя: " + name;
    }

}
