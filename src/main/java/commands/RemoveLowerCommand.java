package commands;

import data.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;
import utils.MarineAsker;

import java.time.LocalDateTime;

public class RemoveLowerCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;
    private MarineAsker marineAsker;

    public RemoveLowerCommand(CollectionHandler collectionHandler, MarineAsker marineAsker) {
        super("remove_lower {element}", "remove from the collection all elements smaller than the given one");
        this.collectionHandler = collectionHandler;
        this.marineAsker = marineAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            SpaceMarine marineToCompare = new SpaceMarine(
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
            if (collectionHandler.enumeration(marineToCompare.getHealth()) == 1) {
                Console.println("Soldiers successfully removed!");
                return true;
            } else if (collectionHandler.enumeration(marineToCompare.getHealth()) == 2) {
                Console.printerror("The value of the soldier is less than all the soldiers in the collection!");
            } else Console.printerror("Collection is empty!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}

