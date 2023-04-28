package commands;

import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;

import java.time.LocalDateTime;

public class InfoCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public InfoCommand(CollectionHandler collectionHandler) {
        super("info", "display information about the collection");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            LocalDateTime lastInitTime = collectionHandler.getInitDateTime();
            String lastInitTimeString = (lastInitTime == null) ? "initialization has not yet taken place in this session" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionHandler.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "this session has not yet been saved" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            System.out.println("Collection details:");
            System.out.println(" Type: " + collectionHandler.collectionType());
            System.out.println(" Amount of elements: " + collectionHandler.collectionSize());
            System.out.println(" Last save date: " + lastSaveTimeString);
            System.out.println(" Date of last initialization: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("usage: '" + getName() + "'");
        }
        return false;
    }
}