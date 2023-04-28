package commands;

import exceptions.WrongAmountOfElementsException;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "display help on available commands");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("usage: '" + getName() + "'");
        }
        return false;
    }
}