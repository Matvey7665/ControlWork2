import Model.Exceptions.IdNotFoundException;
import View.UI;

/**
 * Приложение, реализованное в виде класса) реализующее ведение реестра домашних животных
 */
public class Main {
    public static void main(String[] args) throws IdNotFoundException {
        new UI(); // Запуск пользовательского интерфейса
    }
}