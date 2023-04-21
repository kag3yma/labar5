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
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
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
            Console.println("Солдат успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        }
        return false;
    }
}
