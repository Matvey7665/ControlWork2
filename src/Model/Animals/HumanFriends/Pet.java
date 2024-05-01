package Model.Animals.HumanFriends;

import java.util.Date;
import java.util.List;

/**
 * Класс описывает домашних питомцев.
 * Добавленные поля:
 * animalHouse - место размещения животного,
 * статическое поле countId - счетчик.
 * Содержит:
 * конструктор Pet(String name, Date birthday, List<String> commands, String animalHouse),
 * статический метод generateId() - генерирует id при вызове конструктора
 */
public abstract class Pet extends HumanFriend {
    private static int countId;
    private final String animalHouse;

    /**
     * Конструктор класса Pet
     * @param name имя животного
     * @param birthday дата рождения
     * @param commands список команд
     * @param animalHouse место размещения
     */
    public Pet(String name, Date birthday, List<String> commands, String animalHouse){
        super(generateId(), name, birthday, commands);
        this.animalHouse = animalHouse;
    }

    /**
     * Генерирует уникальный id
     * @return int
     */
    private static int generateId(){
        return ++countId;
    }

    /**
     * Выводит информацию о животном: id, тип животного, кличка, день рождения, список команд, место размещения
     * @return строка информации
     */
    @Override
    public String getInfo() {
        return super.getInfo()
                + ", место размещения: "
                + this.animalHouse;
    }
}
