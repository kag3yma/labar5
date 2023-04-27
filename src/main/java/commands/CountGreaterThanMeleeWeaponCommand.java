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
        super("count_greater_than_melee_weapon meleeWeapon", "   вывести количество элементов,\n   " +
                "значение поля meleeWeapon которых больше заданного");
        this.collectionHandler = collectionHandler;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument == null) throw new WrongAmountOfElementsException();
            MeleeWeapon MeleeWep = MeleeWeapon.valueOf(argument);
            int quantityMelee = 0;
            HashSet<SpaceMarine> marinesMelee = collectionHandler.enumerationMelee(MeleeWep);
            if (!marinesMelee.isEmpty()) {
                for(SpaceMarine marine: marinesMelee) quantityMelee += 1;
                Console.println(quantityMelee);
                return true;
            } else Console.println("Элементов подходящих под условие не найдено!");
        } catch (WrongAmountOfElementsException exception) {
            System.out.println("использование: '" + getName() + "'");
        }
        return false;
    }
}

