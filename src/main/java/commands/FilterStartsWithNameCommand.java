package commands;

import data.SpaceMarine;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;

import java.util.HashSet;


public class FilterStartsWithNameCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public FilterStartsWithNameCommand(CollectionHandler collectionHandler) {
        super("filter_starts_with_name name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            HashSet<SpaceMarine> marinesNames = collectionHandler.namestart(argument);
            if (!marinesNames.isEmpty()) {
                for(SpaceMarine marine: marinesNames) Console.println(marine.getName());
                return true;
            } else Console.println("Совпадений не найдено!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("использование: '" + getName() + "'");
        }
        return false;
    }
}
