package commands;

import data.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;
import utils.MarineAsker;

import java.time.LocalDateTime;

public class AddCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;
    private MarineAsker marineAsker;

    public AddCommand(CollectionHandler collectionHandler, MarineAsker marineAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionHandler = collectionHandler;
        this.marineAsker = marineAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionHandler.addToCollection(new SpaceMarine(
                    collectionHandler.generateNextId(),
                    marineAsker.askName(),
                    marineAsker.askCoordinates(),
                    LocalDateTime.now(),
                    marineAsker.askHealth(),
                    marineAsker.askHeight(),
                    marineAsker.askWeaponType(),
                    marineAsker.askMeleeWeapon(),
                    marineAsker.askChapter()
            ));
            Console.println("Солдат успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}