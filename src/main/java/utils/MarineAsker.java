package utils;

import data.Chapter;
import data.Coordinates;
import data.MeleeWeapon;
import data.Weapon;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import run.App;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MarineAsker {
    private final Float MIN_X = Float.valueOf(-647);
    private final Float MIN_HEALTH = Float.valueOf(1);
    private final int MIN_MARINES = 1;
    private final int MAX_MARINES = 1000;

    private Scanner userScanner;
    private boolean fileMode;

    public MarineAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public Scanner getUserScanner() {
        return userScanner;
    }

    public void setFileMode() {
        fileMode = true;
    }

    public void setUserMode() {
        fileMode = false;
    }

    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public float askHeight() throws IncorrectInputInScriptException {
        String height;
        float h;
        while (true) {
            try {
                Console.println("Введите рост:");
                Console.print(App.PS2);
                height = userScanner.nextLine().trim();
                if (fileMode) Console.println(height);
                h = Float.parseFloat(height);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Рост не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Рост должен быть представлен числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return h;
    }

    public Float askX() throws IncorrectInputInScriptException {
        String strX;
        Float x;
        while (true) {
            try {
                Console.println("Введите координату X > " + (MIN_X) + ":");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Float.parseFloat(strX);
                if (x < MIN_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Координата X не может быть меньше " + MIN_X + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    public float askY() throws IncorrectInputInScriptException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("Введите координату Y:");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        Float x;
        float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    public Float askHealth() throws IncorrectInputInScriptException {
        String strHealth;
        Float health;
        while (true) {
            try {
                Console.println("Введите здоровье:");
                Console.print(App.PS2);
                strHealth = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHealth);
                health = Float.parseFloat(strHealth);
                if (health < MIN_HEALTH) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Здоровье не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Здоровье должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Здоровье должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return health;
    }

    public Weapon askWeaponType() throws IncorrectInputInScriptException {
        String strWeaponType;
        Weapon weaponType;
        while (true) {
            try {
                Console.println("Список оружия дальнего боя - " + Weapon.nameList());
                Console.println("Введите оружие дальнего боя:");
                Console.print(App.PS2);
                strWeaponType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strWeaponType);
                weaponType = Weapon.valueOf(strWeaponType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Оружие не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Оружия нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weaponType;
    }

    public MeleeWeapon askMeleeWeapon() throws IncorrectInputInScriptException {
        String strMeleeWeapon;
        MeleeWeapon meleeWeapon;
        while (true) {
            try {
                Console.println("Список оружия ближнего боя - " + MeleeWeapon.nameList());
                Console.println("Введите оружие ближнего боя:");
                Console.print(App.PS2);
                strMeleeWeapon = userScanner.nextLine().trim();
                if (fileMode) Console.println(strMeleeWeapon);
                meleeWeapon = MeleeWeapon.valueOf(strMeleeWeapon.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Оружие не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Оружия нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return meleeWeapon;
    }

    public String askChapterName() throws IncorrectInputInScriptException {
        String chapterName;
        while (true) {
            try {
                Console.println("Введите имя ордена:");
                Console.print(App.PS2);
                chapterName = userScanner.nextLine().trim();
                if (fileMode) Console.println(chapterName);
                if (chapterName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя ордена не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя ордена не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return chapterName;
    }
    public String askParentLegion() throws IncorrectInputInScriptException {
        String parentLegion;
        while (true) {
            try {
                Console.println("Введите родительский орден:");
                Console.print(App.PS2);
                parentLegion = userScanner.nextLine().trim();
                if (fileMode) Console.println(parentLegion);
                break;
            }   catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return parentLegion;
    }

    public int askChapterMarinesCount() throws IncorrectInputInScriptException {
        String strMarinesCount;
        int marinesCount;
        while (true) {
            try {
                Console.println("Введите количество солдат в ордене меньше " + (MAX_MARINES + 1) + " и больще " + MIN_MARINES + ":");
                Console.print(App.PS2);
                strMarinesCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strMarinesCount);
                marinesCount = Integer.parseInt(strMarinesCount);
                if (marinesCount < MIN_MARINES || marinesCount > MAX_MARINES) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Количество солдат в ордене не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Количество солдат в ордене должно быть положительным и не превышать " + MAX_MARINES + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Количество солдат в ордене должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return marinesCount;
    }
    public String askWorld() throws IncorrectInputInScriptException {
        String world;
        while (true) {
            try {
                Console.println("Введите название мира:");
                Console.print(App.PS2);
                world = userScanner.nextLine().trim();
                if (fileMode) Console.println(world);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя ордена не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }  catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return world;
    }

    public Chapter askChapter() throws IncorrectInputInScriptException {
        String name;
        String parentLegion;
        int marinesCount;
        String world;
        name = askChapterName();
        parentLegion = askParentLegion();
        marinesCount = askChapterMarinesCount();
        world = askWorld();
        return new Chapter(name, parentLegion, marinesCount, world);
    }

    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }
}
