package utils;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import data.SpaceMarine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class FileManager {
    private Gson gson = new Gson();
    private String nameFile;

    public FileManager(String nameFile) {
        this.nameFile = nameFile;
    }

    public void writeFile(Collection<?> collection) {
        if ((System.getenv()).get(nameFile) != null) {
            try (FileWriter collectionFileWriter = new FileWriter(new File(System.getenv().get(nameFile)))) {
                collectionFileWriter.write(gson.toJson(collection));
                Console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                Console.printerror("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
    }
    public HashSet<SpaceMarine> readFromFile() {
        if (System.getenv().get(nameFile) != null) {
            try (Scanner collectionFileScanner = new Scanner(new File(System.getenv().get(nameFile)))) {
                HashSet<SpaceMarine> collection;
                Type collectionType = new TypeToken<HashSet<SpaceMarine>>() {}.getType();
                collection = gson.fromJson(collectionFileScanner.nextLine().trim(), collectionType);
                Console.println("Коллекция успешна загружена!");
                return collection;
            } catch (FileNotFoundException exception) {
                Console.printerror("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                Console.printerror("Загрузочный файл пуст!");
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerror("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
        return new HashSet<SpaceMarine>();
    }
}
