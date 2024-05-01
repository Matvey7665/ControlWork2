package View.MenuItems;

import Model.Exceptions.IdNotFoundException;
import View.UI;

public class ShowCommands extends MenuItem{
    public ShowCommands(UI ui) {
        super("Вывод команд", ui);
    }

    @Override
    public boolean execute() throws IdNotFoundException {
        getUi().showCommands();
        return true;
    }
}
