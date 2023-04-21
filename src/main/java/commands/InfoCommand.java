package commands;

import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;

import java.time.LocalDateTime;

public class InfoCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public InfoCommand(CollectionHandler collectionHandler) {
        super("info", "вывести информацию о коллекции");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            LocalDateTime lastInitTime = collectionHandler.getInitDateTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = collectionHandler.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            System.out.println("Сведения о коллекции:");
            System.out.println(" Тип: " + collectionHandler.collectionType());
            System.out.println(" Количество элементов: " + collectionHandler.collectionSize());
            System.out.println(" Дата последнего сохранения: " + lastSaveTimeString);
            System.out.println(" Дата последней инициализации: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}