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
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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

            if (marineAsker.askQuestion("Хотите изменить имя солдата?")) name = marineAsker.askName();
            if (marineAsker.askQuestion("Хотите изменить координаты солдата?")) coordinates = marineAsker.askCoordinates();
            if (marineAsker.askQuestion("Хотите изменить здоровье солдата?")) health = marineAsker.askHealth();
            if (marineAsker.askQuestion("Хотите изменить рост солдата?")) height = marineAsker.askHeight();
            if (marineAsker.askQuestion("Хотите изменить оружие дальнего боя солдата?")) weaponType = marineAsker.askWeaponType();
            if (marineAsker.askQuestion("Хотите изменить оружие ближнего боя солдата?")) meleeWeapon = marineAsker.askMeleeWeapon();
            if (marineAsker.askQuestion("Хотите изменить орден солдата?")) chapter = marineAsker.askChapter();

            collectionHandler.addToCollection(new SpaceMarine(
                    id,
                    name,
                    coordinates,
                    LocalDateTime.now(),
                    health,
                    height,
                    weaponType,
                    meleeWeapon,
                    chapter
            ));
            Console.println("Солдат успешно изменен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (MarineNotFoundException exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
