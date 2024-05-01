package View.MenuItems;

import View.UI;

public class BirthdayAnimals extends MenuItem{
    public BirthdayAnimals( UI ui) {
        super( "Список именинников", ui);
    }
    @Override
    public boolean execute() {
        getUi().showBirthdayAnimals();
        return true;
    }
}
