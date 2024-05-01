package Model.Services;

import Model.Animals.Interfaces.Teachable;

import java.io.*;
import java.util.List;

public class Serialization<T> {
    public void writeData(T data) throws IOException {
        //создаем 2 потока для сериализации объекта и сохранения его в файл
        FileOutputStream outputStream = new FileOutputStream("data.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // сохраняем данные в файл
        objectOutputStream.writeObject(data);

        //закрываем поток и освобождаем ресурсы
        objectOutputStream.close();
    }

    public T readData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("data.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return (T) (objectInputStream.readObject());
    }
}
