package project.dao;

import java.util.ArrayList;

public class Controller {

    public void printSelected(ArrayList<ArrayList<String>> queryOut) {
        for (int i = 0; i < queryOut.size(); i++) {
            for (int j = 0; j < queryOut.get(i).size(); j++) {
                System.out.print(queryOut.get(i).get(j));
                if (j != queryOut.get(i).size() - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public boolean isNumber(String input) {

        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }


    }
}
