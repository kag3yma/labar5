package commands;

import data.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;

import java.time.LocalDateTime;

public class AverageOfHealthCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public AverageOfHealthCommand(CollectionHandler collectionHandler) {
        super("average_of_health", "вывести среднее значение поля health для всех элементов коллекции");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionHandler.averageHealth() > 0) {
                System.out.println(collectionHandler.averageHealth());
                return true;
            } else System.out.println("Коллекция пуста!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("использование: '" + getName() + "'");
        }
        return false;
    }
}
