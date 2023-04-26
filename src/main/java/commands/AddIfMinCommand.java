package commands;

import data.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;
import utils.MarineAsker;

import java.time.LocalDateTime;

public class AddIfMinCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;
    private MarineAsker marineAsker;

    public AddIfMinCommand(CollectionHandler collectionHandler, MarineAsker marineAsker) {
        super("add_if_min {element}", "добавить новый элемент, если его значение меньше, чем у наименьшего");
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
                    marineToAdd.compareTo(collectionHandler.getById(collectionHandler.getMin()) ) < 0) {
                collectionHandler.addToCollection(marineToAdd);
                Console.println("Солдат успешно добавлен!");
                return true;
            } else Console.printerror("Значение солдата больше, чем значение наименьшего из солдат!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }

}
