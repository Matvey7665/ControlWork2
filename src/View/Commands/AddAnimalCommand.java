package View.Commands;

import Presenter.Presenter;
import View.UI;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static View.UI.scanner;

public class AddAnimalCommand {
    public void addAnimal(Presenter presenter){
        System.out.println("\nДобавление животного:");
        String name, commands, otherData;
        Date birthday = null;
        boolean isPets, isPackAnimals;
        String pets = "Dog, Cat, Hamster";
        String packAnimals = "Camel, Horse, Donkey";

        List<String> petsList = Arrays.asList(pets.split(", "));
        List<String> packAnimalsList = Arrays.asList(packAnimals.split(", "));


        System.out.print("\nДоступно: " +pets+", " + packAnimals + "\nВведите вид: ");
        String type = scanner.nextLine().trim();
        type = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();

        isPets = petsList.contains(type);
        isPackAnimals = packAnimalsList.contains(type);

        if (isPets || isPackAnimals) {
            System.out.print("\nИмя: ");
            name = scanner.nextLine();
            try {
                birthday = ServiceCommands.inputDate("\nДата рождения: ");
            } catch (Exception e) {
                UI.showMessage(e.getMessage());
                return;
            }


            System.out.print("\nПример команд: Sit, Stay, Fetch, Roll, Hide, Paw, Bark..."
                    + "\nВведите команды через запятую: ");
            commands = scanner.nextLine();
            System.out.println();
            if(isPets) {
                System.out.print("Пример места содержания: booth, aviary, aquarium, terrarium, box, shed, garage\nМесто содержания: ");
            } else {
                System.out.print("Грузоподъемность (кг): ");
            }
            otherData = scanner.nextLine();
            presenter.addAnimal(name, type, birthday, commands, otherData);
            UI.showMessage("\nДобавлено!");
        } else {
            UI.showMessage("Неизвестное животное");
        }
    }
}
