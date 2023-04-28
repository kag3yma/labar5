package commands;

import exceptions.WrongAmountOfElementsException;
import utils.Console;

public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "execute script from specified file");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Executing a script '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("usage: '" + getName() + "'");
        }
        return false;
    }
}