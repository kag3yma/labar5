package commands;

import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;

public class SaveCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public SaveCommand(CollectionHandler collectionHandler) {
        super("save", "save collection to file");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionHandler.saveCollection();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        }
        return false;
    }
}