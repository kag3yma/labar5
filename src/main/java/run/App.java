package run;

import java.util.Scanner;
import commands.*;
import utils.*;

public class App {


        public static final String PS1 = "$ ";
        public static final String PS2 = "> ";
        public static void main(String[] args) {
            try (Scanner userScanner = new Scanner(System.in)) {
                final String nameFile = args[0];
                MarineAsker marineAsker = new MarineAsker(userScanner);
                FileManager fileManager = new FileManager(nameFile);
                CollectionHandler collectionHandler = new CollectionHandler(fileManager);
                CommandManager commandManager = new CommandManager(
                        new HelpCommand(),
                        new InfoCommand(collectionHandler),
                        new ShowCommand(collectionHandler),
                        new AddCommand(collectionHandler, marineAsker),
                        new UpdateCommand(collectionHandler, marineAsker),
                        new RemoveByIdCommand(collectionHandler),
                        new ClearCommand(collectionHandler),
                        new SaveCommand(collectionHandler),
                        new ExitCommand(),
                        new ExecuteScriptCommand(),
                        new AddIfMinCommand(collectionHandler, marineAsker),
                        new AddIfMaxCommand(collectionHandler, marineAsker),
                        new RemoveLowerCommand(collectionHandler, marineAsker),
                        new AverageOfHealthCommand(collectionHandler)
                );
                Console console = new Console(commandManager, userScanner, marineAsker);

                console.interactiveMode();
            }
        }
    }