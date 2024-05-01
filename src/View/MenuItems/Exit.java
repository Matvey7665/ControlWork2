package View.MenuItems;

import View.UI;

public class Exit extends MenuItem {
    public Exit(UI ui) {
        super("Выход", ui);
    }

    @Override
    public boolean execute() {
        getUi().exit();
        return false;
    }
}
