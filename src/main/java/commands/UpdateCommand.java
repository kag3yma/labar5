package commands;

import data.*;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.MarineNotFoundException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;
import utils.MarineAsker;

import java.time.LocalDateTime;

public class UpdateCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;
    private MarineAsker marineAsker;

    public UpdateCommand(CollectionHandler collectionHandler, MarineAsker marineAsker) {
        super("update <ID> {element}", "update collection element value by ID");
        this.collectionHandler = collectionHandler;
        this.marineAsker = marineAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionHandler.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            SpaceMarine oldMarine = collectionHandler.getById(id);
            if (oldMarine == null) throw new MarineNotFoundException();

            String name = oldMarine.getName();
            Coordinates coordinates = oldMarine.getCoordinates();
            LocalDateTime creationDate = oldMarine.getCreationDate();
            Float health = oldMarine.getHealth();
            float height = oldMarine.getHeight();
            Weapon weaponType = oldMarine.getWeaponType();
            MeleeWeapon meleeWeapon = oldMarine.getMeleeWeapon();
            Chapter chapter = oldMarine.getChapter();

            collectionHandler.removeFromCollection(oldMarine);

            if (marineAsker.askQuestion("Want to change the soldier's name?")) name = marineAsker.askName();
            if (marineAsker.askQuestion("Want to change the coordinates of a soldier?")) coordinates = marineAsker.askCoordinates();
            if (marineAsker.askQuestion("Want to change a soldier's health?")) health = marineAsker.askHealth();
            if (marineAsker.askQuestion("Want to change the height of a soldier?")) height = marineAsker.askHeight();
            if (marineAsker.askQuestion("Want to change a soldier's ranged weapon?")) weaponType = marineAsker.askWeaponType();
            if (marineAsker.askQuestion("Want to change a soldier's melee weapon?")) meleeWeapon = marineAsker.askMeleeWeapon();
            if (marineAsker.askQuestion("Want to change the order of a soldier?")) chapter = marineAsker.askChapter();

            collectionHandler.addToCollection(new SpaceMarine(
                    id,
                    name,
                    creationDate,
                    coordinates,
                    health,
                    height,
                    weaponType,
                    meleeWeapon,
                    chapter
            ));
            Console.println("Soldier successfully changed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID must be represented by a number!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("There is no soldier with this ID in the collection!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
