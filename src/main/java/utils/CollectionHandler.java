package utils;

import data.SpaceMarine;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionHandler {
    HashSet<SpaceMarine> marinesCollection =  new HashSet<>();
    private LocalDateTime initDateTime;
    private LocalDateTime saveDateTime;
    private FileManager fileManager;


    public CollectionHandler(FileManager fileManager) {
        this.initDateTime = null;
        this.saveDateTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }
    public void addToCollection(SpaceMarine marine) {
        marinesCollection.add(marine);
    }

    public String collectionType() {
        return marinesCollection.getClass().getName();
    }
    public int collectionSize() {
        return marinesCollection.size();
    }

    public void removeFromCollection(SpaceMarine marine) {
        marinesCollection.remove(marine);
    }
    public SpaceMarine getById(Long id) {
        for (SpaceMarine marine : marinesCollection) {
            if (marine.getId().equals(id)) return marine;
        }
        return null;
    }

    public LocalDateTime getInitDateTime(){return initDateTime;}
    public LocalDateTime getLastSaveTime() {
        return saveDateTime;
    }
    public HashSet<SpaceMarine> getCollection(){return marinesCollection;}

    public void clearCollection(){
        marinesCollection.clear();
    }

    public Long generateNextId(){
        Long nextId = Long.valueOf(0);
        for(SpaceMarine spaceMarine : marinesCollection){
            if (spaceMarine.getId() >= nextId){
                nextId = spaceMarine.getId();
            }
        }
        return nextId+1;
    }

    public SpaceMarine getFirst() {
        if (marinesCollection.isEmpty()) return null;
        Iterator<SpaceMarine> i = marinesCollection.iterator();
        return i.next();
    }
    public void saveCollection() {
        fileManager.writeFile(marinesCollection);
        saveDateTime = LocalDateTime.now();
    }

    public void loadCollection(){
        marinesCollection = fileManager.readFromFile();
        initDateTime = LocalDateTime.now();
    }
    @Override
    public String toString() {
        if (marinesCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        Iterator iterator = marinesCollection.iterator();
        while (iterator.hasNext()){
            info += iterator.next();
        }

        return info;
    }
}
