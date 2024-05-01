package Model.Animals.Types;

import Model.Animals.HumanFriends.Pet;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class Hamster extends Pet {
    public Hamster(String name, Date birthday, List<String> commands, String animalHouse) throws ParseException {
        super(name, birthday, commands, animalHouse);
    }
}
