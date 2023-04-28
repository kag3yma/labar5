package commands;

import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;

public class ClearCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public ClearCommand(CollectionHandler collectionHandler) {
        super("clear", "clear the collection");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionHandler.clearCollection();
            Console.println("Collection cleared!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        }
        return false;
    }
}
