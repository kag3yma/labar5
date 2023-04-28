package commands;

import data.MeleeWeapon;
import data.SpaceMarine;
import exceptions.WrongAmountOfElementsException;
import utils.CollectionHandler;
import utils.Console;

import java.util.HashSet;

public class CountGreaterThanMeleeWeaponCommand extends AbstractCommand {
    private CollectionHandler collectionHandler;

    public CountGreaterThanMeleeWeaponCommand(CollectionHandler collectionHandler) {
        super("count_greater_than_melee_weapon meleeWeapon", "   print the number of elements, " +
                "the value of the meleeWeapon field is greater than the given one");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument == null) throw new WrongAmountOfElementsException();
            MeleeWeapon MeleeWep = MeleeWeapon.valueOf(argument.toUpperCase());
            int quantityMelee = 0;
            HashSet<SpaceMarine> marinesMelee = collectionHandler.enumerationMelee(MeleeWep);
            if (!marinesMelee.isEmpty()) {
                for(SpaceMarine marine: marinesMelee) quantityMelee += 1;
                Console.println(quantityMelee);
                return true;
            } else Console.println("Items matching the condition were not found!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("usage: '" + getName() + "'");
        } catch (IllegalArgumentException exception) {
            System.out.println("This element is not in the list!");
        }
        return false;
    }
}

