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
        super("add_if_min {element}", "add a new element if its value is less than that of the smallest");
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
                    marineToAdd.healthCompareTo(collectionHandler.getById(collectionHandler.getMin()) ) < 0) {
                collectionHandler.addToCollection(marineToAdd);
                Console.println("Soldier successfully added!");
                return true;
            } else Console.printerror("The value of the soldier is greater than the value of the smallest of the soldiers!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }

}
