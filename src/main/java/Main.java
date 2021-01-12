import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception("you must pass a file as an args");
        } else {
            try {
                File file = new File(args[0]);
                Scanner reader = new Scanner(file);
                Object[][] carte = new Object[0][];
                HashMap<String, Object> hashMap = new HashMap<>();
                Object[] carteAndHashMap = new Object[2];

                int axeH = 0;
                int axeV = 0;
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    // commentaire
                    if (data.charAt(0) != '#') {
                        String[] datas = data.split(" - ");

                        // initialize carte
                        if (datas[0].equals("C")) {
                            axeH = Integer.parseInt(datas[1]);
                            axeV = Integer.parseInt(datas[2]);
                            carte = new Object[axeV][axeH];
                            for (int i = 0; i < axeV; i++) {
                                for (int j = 0; j < axeH; j++) {
                                    carte[i][j] = ".";
                                }
                            }
                        }
                        carteAndHashMap = completeCarteAndHashMap(axeH, axeV, data, datas, carte, hashMap, carteAndHashMap);
                    }
                }
                reader.close();
                carte = (Object[][]) carteAndHashMap[0];
                hashMap = (HashMap<String, Object>) carteAndHashMap[1];
                displayCarte(axeH, axeV, carte);
                moove(axeH, axeV, carte, hashMap);
            } catch (FileNotFoundException exception) {
                throw new RuntimeException("file not found");
            }
        }
    }

    private static Object[] completeCarteAndHashMap(int axeH, int axeV, String data, String[] datas, Object[][] carte,
                                                    HashMap<String, Object> hashMap, Object[] carteAndHashMap) {

        boolean flag = false;
        if (datas[0].equals("M")) {
            for (int i = 0; i < axeV && !flag; i++) {
                for (int j = 0; j < axeH && !flag; j++) {
                    if (i == Integer.parseInt(datas[2]) && j == Integer.parseInt(datas[1])) {

                        carte[i][j] = new Mountain(Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));
                        hashMap.put(data, new Mountain(Integer.parseInt(datas[1]), Integer.parseInt(datas[2])));
                        flag = true;
                    }
                }
            }
        }
        if (datas[0].equals("T")) {
            for (int i = 0; i < axeV && !flag; i++) {
                for (int j = 0; j < axeH && !flag; j++) {
                    if (i == Integer.parseInt(datas[2]) && j == Integer.parseInt(datas[1])) {
                        carte[i][j] = new Treasure(Integer.parseInt(datas[1]), Integer.parseInt(datas[2])
                                , Integer.parseInt(datas[3]));
                        hashMap.put(data, new Treasure(Integer.parseInt(datas[1]), Integer.parseInt(datas[2])
                                , Integer.parseInt(datas[3])));
                        flag = true;
                    }
                }
            }
        }
        if (datas[0].equals("A")) {
            for (int i = 0; i < axeV && !flag; i++) {
                for (int j = 0; j < axeH && !flag; j++) {
                    if (i == Integer.parseInt(datas[3]) && j == Integer.parseInt(datas[2])) {
                        carte[i][j] = new Adventurer(datas[1], Integer.parseInt(datas[2])
                                , Integer.parseInt(datas[3]), datas[4], datas[5]);
                        hashMap.put(data, new Adventurer(datas[1], Integer.parseInt(datas[2])
                                , Integer.parseInt(datas[3]), datas[4], datas[5]));
                        flag = true;
                    }
                }
            }
        }
        carteAndHashMap[0] = carte;
        carteAndHashMap[1] = hashMap;
        return carteAndHashMap;
    }

    public static void displayCarte(int axeH, int axeV, Object[][] carte) {
        String str = "";
        for (int i = 0; i < axeV; i++) {
            for (int j = 0; j < axeH; j++) {
                if (carte[i][j].getClass() == Mountain.class) {
                    str = "M";
                } else if (carte[i][j].getClass() == Adventurer.class) {
                    str = "A";
                    Adventurer a = (Adventurer) carte[i][j];
                    str += ("(" + a.getName() + ")");
                } else if (carte[i][j].getClass() == Treasure.class) {
                    str = "T";
                    Treasure t = (Treasure) carte[i][j];
                    str += ("(" + t.getNbTresor() + ")");
                } else {
                    str = carte[i][j].toString();
                }
                if (j != axeV) {
                    // 9 espaces
                    System.out.print(str);
                    if (j != axeH - 1) {
                        for (int k = str.length(); k < 10; k++) {
                            System.out.print(" ");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    private static void moove(int axeH, int axeV, Object[][] carte, HashMap<String, Object> hashMap) {
        Adventurer a1 = null;
        Object[][] copyCarte = Arrays.stream(carte).map(Object[]::clone).toArray(Object[][]::new);

        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).getClass() == Adventurer.class) {
                a1 = (Adventurer) hashMap.get(key);
                for (int i = 0; i < a1.getMotionSeq().length(); i++) {
                    if (a1.getMotionSeq().charAt(i) == 'G') {
                        turnLeft(a1);
                    }
                    if (a1.getMotionSeq().charAt(i) == 'D') {
                        turnRight(a1);
                    }
                    if (a1.getMotionSeq().charAt(i) == 'A') {
                        if (a1.getOrientation().equals("N")) {
                            if (checkBorder(axeH, axeV, a1, "-1V") &&
                                    checkMountain(a1, "-1V", copyCarte) &&
                                    checkAdventuror(a1, "-1V", copyCarte) &&
                                    checkTresor(a1, "-1V", carte)) {
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = ".";
                                a1.setAxeV(a1.getAxeV()-1);
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = a1;
                            } else {
                                System.out.println("impossible");
                            }
                        }
                        if (a1.getOrientation().equals("S")) {
                            if (checkBorder(axeH, axeV, a1, "+1V") &&
                                    checkMountain(a1, "+1V", copyCarte) &&
                                    checkAdventuror(a1, "+1V", copyCarte) &&
                                    checkTresor(a1, "+1V", carte)) {
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = ".";
                                a1.setAxeV(a1.getAxeV()+1);
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = a1;
                            } else {
                                System.out.println("impossible");
                            }
                        }
                        if (a1.getOrientation().equals("E")) {
                            if (checkBorder(axeH, axeV, a1, "+1H") &&
                                    checkMountain(a1, "+1H", copyCarte) &&
                                    checkAdventuror(a1, "+1H", copyCarte) &&
                                    checkTresor(a1, "+1H", carte)) {
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = ".";
                                a1.setAxeH(a1.getAxeH()+1);
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = a1;
                            } else {
                                System.out.println("impossible");
                            }
                        }
                        if (a1.getOrientation().equals("O")) {
                            if (checkBorder(axeH, axeV, a1, "-1H") &&
                                    checkMountain(a1, "-1H", copyCarte) &&
                                    checkAdventuror(a1, "-1H", copyCarte) &&
                                    checkTresor(a1, "-1H", carte)) {
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = ".";
                                a1.setAxeH(a1.getAxeH()-1);
                                copyCarte[a1.getAxeV()][a1.getAxeH()] = a1;
                            } else {
                                System.out.println("impossible");
                            }
                        }
                    }

                }
            }
        }
        fusionCarte(carte, copyCarte, axeH, axeV);
        try {
            File myObj = new File("carte2.txt");
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write("C - " + axeH + " - " + axeV +"\n");
                for (int i = 0; i < axeV; i++) {
                    for (int j = 0; j < axeH; j++) {
                        if (copyCarte[i][j].getClass() == Mountain.class) {
                            Mountain m = (Mountain) copyCarte[i][j];
                            myWriter.write("M - " + m.getAxeH() + " - " + m.getAxeV()+"\n");
                        }
                        if (copyCarte[i][j].getClass() == Treasure.class) {
                            Treasure t = (Treasure) copyCarte[i][j];
                            myWriter.write("T - " + t.getAxeH() + " - " + t.getAxeV() + " - " + t.getNbTresor()+"\n");
                        }
                        if (copyCarte[i][j].getClass() == Adventurer.class) {
                            Adventurer a = (Adventurer) copyCarte[i][j];
                            myWriter.write("A - " + a.getName() + " - " + a.getAxeH() + " - "
                                    + a.getAxeV() + " - " + a.getOrientation() + " - " + a.getNbTreasures()+"\n");
                        }
                    }
                }
                myWriter.close();
                System.out.println(" ------------- ");
                displayCarte(axeH, axeV, copyCarte);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void fusionCarte(Object[][] carte, Object[][] copyCarte, int axeH, int axeV) {
        for (int i = 0; i < axeV; i++) {
            for (int j = 0; j < axeH; j++) {
                if (carte[i][j].getClass() == Treasure.class) {
                    copyCarte[i][j] = carte[i][j];
                }
            }
        }
    }

    private static void turnRight(Adventurer a1) {
        switch (a1.getOrientation()) {
            case "N":
                a1.setOrientation("E");
                break;
            case "S":
                a1.setOrientation("O");
                break;
            case "E":
                a1.setOrientation("S");
                break;
            default:
                a1.setOrientation("N");
                break;
        }
    }

    private static void turnLeft(Adventurer a1) {
        switch (a1.getOrientation()) {
            case "N":
                a1.setOrientation("O");
                break;
            case "S":
                a1.setOrientation("E");
                break;
            case "E":
                a1.setOrientation("N");
                break;
            default:
                a1.setOrientation("S");
                break;
        }
    }


    private static boolean checkTresor(Adventurer a1, String futurOrientation, Object[][] carte) {
        Treasure treasure = null;
        if (futurOrientation.equals("-1V")) {
            if (carte[a1.getAxeV() - 1][a1.getAxeH()].getClass().equals(Treasure.class)) {
                treasure = (Treasure) carte[a1.getAxeV() - 1][a1.getAxeH()];
                a1.setNbTreasures(a1.getNbTreasures()+1);
                treasure.setNbTresor(treasure.getNbTresor()-1);
                if (treasure.getNbTresor() == 0) {
                    carte[a1.getAxeV() - 1][a1.getAxeH()] = ".";
                }
            }
        }
        if (futurOrientation.equals("+1V")) {
            if (carte[a1.getAxeV() + 1][a1.getAxeH()].getClass().equals(Treasure.class)) {
                treasure = (Treasure) carte[a1.getAxeV() + 1][a1.getAxeH()];
                a1.setNbTreasures(a1.getNbTreasures()+1);
                treasure.setNbTresor(treasure.getNbTresor()-1);
                if (treasure.getNbTresor() == 0) {
                    carte[a1.getAxeV() + 1][a1.getAxeH()] = ".";
                }
            }
        }

        if (futurOrientation.equals("-1H")) {
            if (carte[a1.getAxeV()][a1.getAxeH() - 1].getClass().equals(Treasure.class)) {
                treasure = (Treasure) carte[a1.getAxeV()][a1.getAxeH() - 1];
                a1.setNbTreasures(a1.getNbTreasures()+1);
                treasure.setNbTresor(treasure.getNbTresor()-1);
                if (treasure.getNbTresor() == 0) {
                    carte[a1.getAxeV()][a1.getAxeH() - 1] = ".";
                }
            }
        }
        if (futurOrientation.equals("+1H")) {
            if (carte[a1.getAxeV()][a1.getAxeH() + 1].getClass().equals(Treasure.class)) {
                treasure = (Treasure) carte[a1.getAxeV()][a1.getAxeH() + 1];
                a1.setNbTreasures(a1.getNbTreasures()+1);
                treasure.setNbTresor(treasure.getNbTresor()-1);
                if (treasure.getNbTresor() == 0) {
                    carte[a1.getAxeV()][a1.getAxeH() + 1] = ".";
                }
            }
        }
        return true;
    }

    private static boolean checkAdventuror(Adventurer a1, String futurOrientation, Object[][] copyCarte) {
        boolean canMoove = true;
        if (futurOrientation.equals("-1V")) {
            if (copyCarte[a1.getAxeV() - 1][a1.getAxeH()].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("+1V")) {
            if (copyCarte[a1.getAxeV() + 1][a1.getAxeH()].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }

        if (futurOrientation.equals("-1H")) {
            if (copyCarte[a1.getAxeV()][a1.getAxeH() - 1].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("+1H")) {
            if (copyCarte[a1.getAxeV()][a1.getAxeH() + 1].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }
        return canMoove;
    }

    private static boolean checkBorder(int axeH, int axeV, Adventurer a1, String futurOrientation) {
        boolean canMoove = true;
        if (futurOrientation.equals("-1V")) {
            if (a1.getAxeV() - 1 < 0) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("+1V")) {
            if (a1.getAxeV() + 1 > axeV) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("-1H")) {
            if (a1.getAxeH() - 1 < 0) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("+1H")) {
            if (a1.getAxeH() + 1 > axeH) {
                canMoove = false;
            }
        }
        return canMoove;
    }

    private static boolean checkMountain(Adventurer a1, String futurOrientation, Object[][] copyCarte) {
        boolean canMoove = true;
        if (futurOrientation.equals("-1V")) {
            if (copyCarte[a1.getAxeV() - 1][a1.getAxeH()].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("+1V")) {
            if (copyCarte[a1.getAxeV() + 1][a1.getAxeH()].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }

        if (futurOrientation.equals("-1H")) {
            if (copyCarte[a1.getAxeV()][a1.getAxeH() - 1].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }
        if (futurOrientation.equals("+1H")) {
            if (copyCarte[a1.getAxeV()][a1.getAxeH() + 1].getClass().equals(Mountain.class)) {
                canMoove = false;
            }
        }
        return canMoove;
    }
}
