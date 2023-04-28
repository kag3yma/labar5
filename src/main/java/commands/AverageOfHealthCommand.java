package commands;


import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;


public class AverageOfHealthCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public AverageOfHealthCommand(CollectionHandler collectionHandler) {
        super("average_of_health", "display the average value of the health field for all elements of the collection");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionHandler.averageHealth() > 0) {
                System.out.println(collectionHandler.averageHealth());
                return true;
            } else System.out.println("Collection is empty!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("usage: '" + getName() + "'");
        }
        return false;
    }
}
