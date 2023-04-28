package commands;

import data.SpaceMarine;
import exceptions.CollectionIsEmptyException;
import exceptions.MarineNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;

public class RemoveByIdCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public RemoveByIdCommand(CollectionHandler collectionHandler) {
        super("remove_by_id <ID>", "remove item from collection by ID");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionHandler.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(argument);
            SpaceMarine marineToRemove = collectionHandler.getById(id);
            if (marineToRemove == null) throw new MarineNotFoundException();
            collectionHandler.removeFromCollection(marineToRemove);
            Console.println("Soldier successfully removed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID must be represented by a number!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("There is no soldier with this ID in the collection!");
        }
        return false;
    }
}
