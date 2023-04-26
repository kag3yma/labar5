package utils;

import data.SpaceMarine;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionHandler {
    HashSet<SpaceMarine> marinesCollection =  new HashSet<>();
    private static HashSet<Long> setForId = new HashSet<>();
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
    public static HashSet<Long> getArrayForId() {
        return setForId;
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
    public Float averageHealth() {
        if (marinesCollection.isEmpty()) return 0F;
        Float avghealth;
        Float sumhealth = 0F;
        Float quantity = 0F;
        for(SpaceMarine Marine: marinesCollection) {
            sumhealth += Marine.getHealth();
            quantity += 1F;
        }
        avghealth = sumhealth / quantity;
        return avghealth;
    }
    public int enumeration(Float health) {
        int executeStatus = 0;
        if (marinesCollection.isEmpty()) return 0;
        for(SpaceMarine Marine: marinesCollection)
            if(Marine.getHealth() < health) {
                marinesCollection.remove(Marine);
                executeStatus = 1;
            }
        if (executeStatus == 0) return 2;
        return 1;
    }
    public Long getFirst() {
        if (marinesCollection.isEmpty()) return 999999999L;
        Float minhealth = 999999.0F;
        Long minId = 999999999L;
        for(SpaceMarine firstMarine: marinesCollection)
            if(firstMarine.getHealth() < minhealth) {
                minhealth = firstMarine.getHeight();
                minId = firstMarine.getId();
            }
        return minId;
    }
    public Long getLast() {
        if (marinesCollection.isEmpty()) return 0L;
        Float maxhealth = 0F;
        Long maxId = 0L;
        for(SpaceMarine firstMarine: marinesCollection)
            if(firstMarine.getHealth() > maxhealth) {
                maxhealth = firstMarine.getHeight();
                maxId = firstMarine.getId();
            }
        return maxId;
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
