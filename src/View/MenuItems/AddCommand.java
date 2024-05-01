package View.MenuItems;

import Model.Exceptions.IdNotFoundException;
import View.UI;

public class AddCommand extends MenuItem{
    public AddCommand( UI ui) {
        super( "Добавить команду", ui);
    }
    @Override
    public boolean execute() throws IdNotFoundException {
        getUi().addCommand();
        return true;
    }
}
