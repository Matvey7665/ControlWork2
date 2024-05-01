package Model.Animals.Types;

import Model.Animals.HumanFriends.PackAnimal;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Horse extends PackAnimal {
    public Horse(String name, Date birthday, List<String> commands, String loadCapacity) throws ParseException {
        super(name, birthday, commands, loadCapacity);
    }
}
