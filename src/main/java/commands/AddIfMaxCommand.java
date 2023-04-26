package commands;

import data.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;
import utils.MarineAsker;

import java.time.LocalDateTime;

public class AddIfMaxCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;
    private MarineAsker marineAsker;

    public AddIfMaxCommand(CollectionHandler collectionHandler, MarineAsker marineAsker) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionHandler = collectionHandler;
        this.marineAsker = marineAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            SpaceMarine marineToAdd = new SpaceMarine(
                    collectionHandler.generateNextId(),
                    marineAsker.askName(),
                    LocalDateTime.now(),
                    marineAsker.askCoordinates(),
                    marineAsker.askHealth(),
                    marineAsker.askHeight(),
                    marineAsker.askWeaponType(),
                    marineAsker.askMeleeWeapon(),
                    marineAsker.askChapter()
            );
            if (collectionHandler.collectionSize() == 0 ||
                    marineToAdd.compareTo(collectionHandler.getById(collectionHandler.getMax()) ) > 0) {
                collectionHandler.addToCollection(marineToAdd);
                Console.println("Солдат успешно добавлен!");
                return true;
            } else Console.printerror("Значение солдата меньше, чем значение наибольшего из солдат!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }

}
