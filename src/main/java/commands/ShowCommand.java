package commands;

import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;

public class ShowCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public ShowCommand(CollectionHandler collectionHandler) {
        super("show", "вывести все элементы коллекции");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            System.out.println(collectionHandler);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
