package Model.Animals.Interfaces;

import java.io.Serializable;
import java.util.Date;

public interface Infotable extends Serializable {
    int getId();
    String getInfo();
    String getShortInfo();

    Date getBirthday();
}
