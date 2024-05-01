package View.MenuItems;
import Model.Exceptions.IdNotFoundException;
import View.UI;

public abstract class MenuItem {
    private String name;
    private UI ui;

    protected MenuItem(String name, UI ui) {
        this.name = name;
        this.ui = ui;
    }

    public abstract boolean execute() throws IdNotFoundException;

    public String getName() {
        return name;
    }

    public UI getUi() {
        return ui;
    }
}
