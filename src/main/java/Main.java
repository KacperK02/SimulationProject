import java.io.*;
import java.lang.management.MonitorInfo;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void generateAnimalMap(Animal [][]animalMap, int size, Area [][]areaMap){
        Random rand = new Random();
        for (int i=0; i<size;i++) {
            for (int j=0;j<size;j++) {
                int chance = rand.nextInt(6);
                if (chance == 1 && !(areaMap[i][j] instanceof Mountain))
                    animalMap[i][j] = new Lion (5,i,j,true);

                else if (chance == 2 && !(areaMap[i][j] instanceof Mountain))
                    animalMap[i][j] = new Zebra (5,i,j,true);

                else animalMap[i][j] = null;
            }
        }
    }

    public static void generateAreaMap(Area [][]areaMap, int size){
        Random rand = new Random();
        for (int i=0; i<size;i++) {
            for (int j = 0; j < size; j++) {
                int chance = rand.nextInt(6);
                switch(chance){
                    case 0 -> areaMap[i][j] = new Grass(1, i, j);
                    case 1 -> areaMap[i][j] = new Grass(2, i, j);
                    case 2 -> areaMap[i][j] = new Mountain(9, i, j);
                    case 3 -> areaMap[i][j] = new Sand(2, i, j);
                    default -> areaMap[i][j] = new Dirt(1, i, j);
                }
            }
        }
    }
    //wyswietlanie - umowa:
    //Trawa - kolor zielony
    //Piasek - kolor zółty
    //Góry - kolor czerwony
    //Ziemia - kolor czarny (domyslny)
    //Zebra - "Z"
    //Lew - "L"
    public static void showMap(Animal [][]animalMap, Area [][]areaMap, int size){
        String blackBackground = "\033[40m";
        String background = "";
        for(int i=0; i<size; i++){
            System.out.print(blackBackground + "|");
            for(int j=0; j<size; j++){
                if(areaMap[i][j] instanceof Dirt) background = blackBackground;
                else if(areaMap[i][j] instanceof Mountain) background = "\033[41m";
                else if(areaMap[i][j] instanceof Grass) background = "\033[42m";
                else background = "\033[43m";

                if(animalMap[i][j] instanceof Zebra) System.out.print(background + "Z");
                else {
                    if(animalMap[i][j] instanceof Lion) System.out.print(background + "L");
                    else System.out.print(background + " ");
                }
            }
            System.out.println(blackBackground + "|");
        }
    }
    public static void main(String []args) throws FileNotFoundException {
        System.out.print("Podaj rozmiar mapy: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        System.out.print("Ile tur symulacji przeprowadzic?: ");
        int numberOfTurns = scanner.nextInt();

        //Inicjalizacja tablic
        Animal [][]animalMap = new Animal[size][size];
        Area [][]areaMap = new Area[size][size];

        //Generowanie map
        generateAreaMap(areaMap, size);
        generateAnimalMap(animalMap, size, areaMap);

        int lionPopulation, zebraPopulation;
        PrintWriter save = new PrintWriter("simulation.txt");

        //przebieg symulacji
        for(int turn = 1; turn <= numberOfTurns; turn++){

            //zliczanie ilosci lwow i zebr
            lionPopulation = 0;
            zebraPopulation = 0;
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    if(animalMap[i][j] instanceof Lion) lionPopulation++;
                    else if(animalMap[i][j] instanceof Zebra) zebraPopulation++;
                }
            }

            //Wypisanie informacji o stanie sytuacji na planszy
            System.out.println();
            System.out.println("Tura " + turn);
            System.out.println("Populacja lwow: " + lionPopulation);
            System.out.println("Populacja zebr: " + zebraPopulation);

            //zapis informacji o stanie symulacji do pliku
            save.println("Tura " + turn);
            save.println("Populacja lwow: " + lionPopulation);
            save.println("Populacja zebr: " + zebraPopulation);
            save.println();

            //wyswietlenie mapy
            showMap(animalMap, areaMap, size);
            System.out.println();
            for(int i=0; i<size+2; i++) System.out.print("-");

            //faza poruszania
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                        if(animalMap[i][j] instanceof Zebra){
                            animalMap[i][j].eat(areaMap, animalMap);
                            animalMap[i][j].move(areaMap, animalMap, i, j, size);
                            System.out.println();
                            showMap(animalMap,areaMap,size);
                        }
                        else if (animalMap[i][j] instanceof Lion) {
                            //animalMap[i][j].eat(areaMap, animalMap);
                            animalMap[i][j].move(areaMap, animalMap, i, j, size);
                            System.out.println();
                            showMap(animalMap, areaMap, size);
                        }
                    }
                }

            //faza umierania i odrastania trawy
            for(int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    if ((animalMap[i][j] instanceof Zebra || animalMap[i][j] instanceof Lion) && animalMap[i][j].isHungry)
                        animalMap[i][j] = null;
                    if(areaMap[i][j] instanceof Dirt) areaMap[i][j].grow(areaMap);
                }
            }

            //odzyskiwanie punktów ruchu
            for(int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    if (animalMap[i][j] instanceof Zebra || animalMap[i][j] instanceof Lion) {
                        animalMap[i][j].actionPoints = 5;
                        animalMap[i][j].isHungry = true;
                    }
                }
            }

        }
        System.out.println();
        showMap(animalMap, areaMap, size);
        save.close();
        System.out.println("Koniec");
    }
}
