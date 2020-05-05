package Appli.Controllers;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private static volatile Checker instance;
    private static ArrayList<String> badValues;

    private Checker(){
    }

    public static Checker getInstance(){
        if (instance == null){
            synchronized (Checker.class){
                if(instance == null)
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

    public boolean checkString(String str){
        for(String sub : badValues){
            if(str.contains(sub))
                return false;
        }
        return true;
    }

    public boolean checkString(String str, List<String> bad){
        if(checkString(str)){
            for(String sub : bad){
                if(str.contains(sub))
                    return false;
            }
            return true;
        }
        return false;
    }

}
