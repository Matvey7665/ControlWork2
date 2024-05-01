package Model.Services;

import Model.Animals.Interfaces.Teachable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HumanFriendsList implements Serializable {
    private static HumanFriendsList humanFriendsList;
    private List<Teachable> animals = new ArrayList<>();

    private HumanFriendsList() {
        humanFriendsList = this;
    }

    public static HumanFriendsList getHumanFriendsList() {
        if (humanFriendsList == null) new HumanFriendsList();
        return humanFriendsList;
    }

     List<Teachable> getAnimals() {
        return animals;
    }

}
