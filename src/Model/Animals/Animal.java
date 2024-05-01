package Model.Animals;

import Model.Animals.Interfaces.Infotable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Базовый класс Animal является родителем классов всех животных.
 * Определяет поля:
 * id идентификатор животного,
 * birthday день рождения животного;
 * статическое поле DateFormat -  формат вывода дат;
 * конструктор класса Animal(int id, Date birthday);
 * методы getId(), getBirthday(), getShortInfo()
 */
public abstract class Animal implements Infotable {
    private final int id;
    private final Date birthday;
    public static DateFormat df;

    static {
        df = new SimpleDateFormat("dd.MM.yyyy");
    }

    /**
     * Конструктор класса Animal
     * @param id идентификатор животного
     * @param birthday день рождения животного
     */
    public Animal(int id, Date birthday) {
        this.id = id;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    /**
     * Возвращает дату рождения животного
     * @return значение поля birthday типа Date
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Выводит информацию о животном: id, тип животного, кличка, день рождения, список команд
     * @return строка информации
     */
    @Override
    public String getShortInfo() {
        return  "id: " + id +
                ", животное: " + this.getClass().getSimpleName();
    }

}
