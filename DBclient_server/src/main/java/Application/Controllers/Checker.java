package Application.Controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечающий за проверку введенного текста.
 * Описание его методов:
 *
 * @checkString(String str) - проверяет строку, которую подали на вход на наличие "плохих" символов.
 * returns true если таких символов не обнаружено
 * @checkString(String str, List<String> bad) - проверяет строку, которую подавали на вход на наличие
 * "плохих" символов заданных по умолчанию в конструкторе, и тех, которые подали на вход в List
 * returns true если таких символов не обнаружено
 */

public class Checker {
    private static volatile Checker instance;
    private static ArrayList<String> badValues;

    private Checker() {
    }

    public static Checker getInstance() {
        if (instance == null) {
            synchronized (Checker.class) {
                if (instance == null)
                    instance = new Checker();
                badValues = new ArrayList<>();
                badValues.add("?");
                badValues.add("!");
                badValues.add(":");
                badValues.add("%");
            }
        }
        return instance;
    }

    public boolean checkString(String str) {
        for (String sub : badValues) {
            if (str.contains(sub))
                return false;
        }
        return true;
    }

    public boolean checkString(String str, List<String> bad) {
        if (checkString(str)) {
            for (String sub : bad) {
                if (str.contains(sub))
                    return false;
            }
            return true;
        }
        return false;
    }

}
