package Model.Services;

import Model.Animals.Interfaces.Infotable;
import Model.Exceptions.IdNotFoundException;
import Model.Animals.Interfaces.Teachable;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Класс ServiceHumanFriends осуществляет размещение реестра Животных - друзей человека,
 * а также сервисные функции управления, выборки и просмотра записей этого реестра
 */
public class ServiceHumanFriends {
    private static HumanFriendsList humanFriendsList = HumanFriendsList.getHumanFriendsList();
    private static List<Teachable> animals = humanFriendsList.getAnimals();
    private static ServiceHumanFriends serviceHumanFriends; // ссылка на самого себя

    private final Builder builder = new Builder();
    private List<Infotable> foundAnimalList; //Список животных формируется при выборе по id

    private ServiceHumanFriends() {
        serviceHumanFriends = this;
    }

    /**
     * Возвращаеет ссылку на единственный экземпляр класса
     * @return экземпляр ServiceHumanFriends
     */
    public static ServiceHumanFriends getServiceHumanFriends() {
        if(serviceHumanFriends == null) new ServiceHumanFriends();
        return serviceHumanFriends;
    }

    /**
     * Возвращает ссылку на список животных
     * @return список животных
     */
    public List<Teachable> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Teachable an: animals) {
            sb.append("\n").append(an.getInfo());
        }
        return  sb.toString();
    }

    /**
     * Добавляет команду в список команд
     * @param command команда
     * @return true, в случае успешного добавления,
     * false - в противном случае
     */
    public boolean addCommand(String command) {
        if(!foundAnimalList.isEmpty()) {
            Teachable animal = (Teachable) foundAnimalList.removeFirst();
            if (!command.isEmpty()) {
                animal.teachCommand(command);
            }
            return true;
        }
        return false;
    }

    /**
     * Поиск животного по id
     * @param id идентификатор животного
     * @throws IdNotFoundException исключение, выбрасываемое при отсустствии животного в списке с заданным id
     */
    public void findAnimalById(int id) throws IdNotFoundException {
        foundAnimalList = new ArrayList<>();

        for(Teachable animal : animals) {
            if(animal.getId() == id) {
                foundAnimalList.add(animal);
            }
        }
        if (foundAnimalList.isEmpty()) {
            throw new IdNotFoundException("id не найден");
        }
    }

    /**
     * Отображение списка команд
     * @return строка, содержащая список команд
     * @throws IdNotFoundException исключение, выбрасываемое при отсустствии животного в списке с заданным id
     */
    public String showCommands() throws IdNotFoundException {
        StringBuilder sb = new StringBuilder();
        foundAnimalList.forEach(n ->
             sb.append(((Teachable)n).getShortInfo())
                    .append(", команды: ")
                    .append(((Teachable)n).showCommands())
                    .append("\n")
        );
        if(sb.isEmpty()){
            throw new IdNotFoundException("id не найден");
        }
        return sb.toString();
    }

    /**
     * Добавление животного в реестр
     * @param name кличка
     * @param type тип
     * @param birthday день рождения
     * @param commands список команд
     * @param otherData другие данные
     * @throws ParseException исключение преобразования данных
     */
    public void addAnimal(String name, String type, Date birthday, String commands, String otherData) throws ParseException {
        Teachable animal = builder.build(name, type, birthday, commands, otherData);
        addAnimal(animal);
    }

    /**
     * Добавление животное в реестр
     * @param animal экземпляр класса, реализующего интерфейс Teachable
     */
    public void addAnimal(Teachable animal) {
        animals.add(animal);
    }

    /**
     * Вывод именинников по дате
     * @param birthday дата
     * @return все животные, день и месяц рождения которых приходятся на заданную дату
     */
    public String showBirthdayAnimals(Date birthday) {
        StringBuilder sb = new StringBuilder();

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(birthday);
        int d = calendar1.get(Calendar.DAY_OF_MONTH);
        int m = calendar1.get(Calendar.MONTH);

        for (Teachable an : animals) {
            calendar2.setTime(an.getBirthday());
            if(d == calendar2.get(Calendar.DAY_OF_MONTH) && m == calendar2.get(Calendar.MONTH)) {
                sb.append(an.getShortInfo());
                sb.append("\n");
           }
        }
        return sb.toString();
    }

    /**
     * Возвращает краткую информацию об извлеченном первом животном из списке,
     * сформированного при поиске по id с использованием метода findAnimalById(int id)
     * @return краткая информация об извлеченном животном
     */
    public String getFoundItem() {
        if(foundAnimalList.isEmpty()){
            return "";
        } else {
            return foundAnimalList.getFirst().getShortInfo();
        }
    }


    public static void writeData() throws IOException {
        new Serialization<HumanFriendsList>().writeData(humanFriendsList);
    }

    public static void readData() throws IOException, ClassNotFoundException {
        humanFriendsList = new Serialization<HumanFriendsList>().readData();
        animals = humanFriendsList.getAnimals();
    }
}
