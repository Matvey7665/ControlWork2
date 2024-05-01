package View.MenuItems;

import View.UI;

public class Show extends MenuItem {
    public Show(UI ui) {
        super("Вывести список", ui);
    }

    @Override
    public boolean execute() {
        getUi().showAnimalList();
        return true;
    }
}
