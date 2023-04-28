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
                Console.println("Collection successfully saved to file!");
            } catch (IOException exception) {
                Console.printerror("The download file is a directory/cannot be opened!");
            }
        } else Console.printerror("Boot file system variable not found!");
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
                    Console.println("Collection uploaded successfully!");
                    return collection;
                } catch (FileNotFoundException e) {
                    Console.printerror("Download file not found!");
                    throw new RuntimeException(e);
                } catch (NoSuchElementException exception) {
                    Console.printerror("Boot file is empty!");
                    throw new RuntimeException(exception);
                } catch (NullPointerException exception) {
                    Console.printerror("Required collection not found in upload file!");
                    throw new RuntimeException(exception);
                }
            }
            } else Console.printerror("Boot file system variable not found!");
            return new HashSet<SpaceMarine>();
        }
    }
