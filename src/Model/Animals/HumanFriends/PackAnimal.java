package Model.Animals.HumanFriends;

import java.util.Date;
import java.util.List;

/**
 * Класс описывает вьючных животных.
 * Добавлены поля:
 * loadCapacity - грузоподъемность,
 * статическое поле countId - счетчик.
 * Содержит:
 * конструктор PackAnimals(String name, Date birthday, List<String> commands, String animalHouse),
 * статический метод generateId() - генерирует id при вызове конструктора
 */
public abstract class PackAnimal extends HumanFriend {
    private static int countId;
    private final int loadCapacity;


    /** Конструктор класса PackAnimal
     * @param name имя животного
     * @param birthday дата рождения
     * @param commands список команд
     * @param loadCapacity грузоподъемность
     */
    public PackAnimal(String name, Date birthday, List<String> commands, String loadCapacity){
        super(generateId(), name, birthday, commands);
        try{
            this.loadCapacity = Integer.parseInt(loadCapacity);
        } catch (Exception e) {
            throw new RuntimeException("Неверно задана грузоподъемность: требуется ввести число(кг)");
        }
    }
    /**
     * Генерирует уникальный id
     * @return int
     */
    private static int generateId(){
        return ++countId;
    }

    /**
     * Выводит информацию о животном: id, тип животного, кличка, день рождения, список команд, грузоподъемность
     * @return строка информации
     */
    @Override
    public String getInfo() {
        return super.getInfo()
                + ", грузоподъемность (кг): "
                + this.loadCapacity;
    }
}
