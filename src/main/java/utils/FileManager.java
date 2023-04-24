package utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import data.SpaceMarine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FileManager {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).registerTypeAdapter(
            LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    private String nameFile;

    public FileManager(String nameFile) {
        this.nameFile = nameFile;
    }

    public void writeFile(Collection<?> collection) {
        if (nameFile != null) {
            try (FileWriter collectionFileWriter = new FileWriter(new File(nameFile))) {
                collectionFileWriter.write(gson.toJson(collection));
                Console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                Console.printerror("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
    }

    public HashSet<SpaceMarine> readFromFile() {
        if (nameFile != null) {
            File file = new File(nameFile);
            if (file.canRead()) {
                try (Scanner collectionFileScanner = new Scanner(file)) {
                    HashSet<SpaceMarine> collection;
                    Type collectionType = new TypeToken<HashSet<SpaceMarine>>() {
                    }.getType();
                    String coll = null;
                    while (collectionFileScanner.hasNext()) {
                        String col = collectionFileScanner.nextLine().trim();
                        coll = coll + col;
                    }
                    String a = coll.substring(4);
                    collection = gson.fromJson(a, collectionType);
                    HashSet<SpaceMarine> marines = collection;
                    for (SpaceMarine marine : marines) CollectionHandler.getArrayForId().add(marine.getId());
                    Console.println("Коллекция загружена успешно!");
                    return collection;
                } catch (FileNotFoundException e) {
                    Console.printerror("Файл для загрузки не найден!");
                    throw new RuntimeException(e);
                } catch (NoSuchElementException exception) {
                    Console.printerror("Загрузочный файл пуст!");
                    throw new RuntimeException(exception);
                } catch (NullPointerException exception) {
                    Console.printerror("В загрузочном файле не обнаружена необходимая коллекция!");
                    throw new RuntimeException(exception);
                }
            }
            } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
            return new HashSet<SpaceMarine>();
        }
    }
