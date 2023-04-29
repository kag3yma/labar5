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
        super("add {element}", "add a new element to the collection");
        this.collectionHandler = collectionHandler;
        this.marineAsker = marineAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            boolean solution = marineAsker.askWantChapter();
            if (solution){
            collectionHandler.addToCollection(new SpaceMarine(
                    collectionHandler.generateNextId(),
                    marineAsker.askName(),
                    LocalDateTime.now(),
                    marineAsker.askCoordinates(),
                    marineAsker.askHealth(),
                    marineAsker.askHeight(),
                    marineAsker.askWeaponType(),
                    marineAsker.askMeleeWeapon(),
                    marineAsker.askChapter()
            ));
            Console.println("Soldier successfully added!");
            return true;}
            if (!solution){
                collectionHandler.addToCollection(new SpaceMarine(
                        collectionHandler.generateNextId(),
                        marineAsker.askName(),
                        LocalDateTime.now(),
                        marineAsker.askCoordinates(),
                        marineAsker.askHealth(),
                        marineAsker.askHeight(),
                        marineAsker.askWeaponType(),
                        marineAsker.askMeleeWeapon()
                ));
                Console.println("Soldier successfully added!");
                return true;}
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}