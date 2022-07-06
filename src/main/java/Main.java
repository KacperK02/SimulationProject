import java.io.*;
import java.lang.management.MonitorInfo;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void generateRandomAnimalMap(Animal [][]animalMap, int size, Area [][]areaMap){
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

    public static void generateAnimalMap(Animal [][]animalMap, int size, Area [][]areaMap){
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int numberOfLions = 3 * size;
        while(numberOfLions > 2 * size){
            System.out.print("Podaj ile lwow umiescic na planszy (max 2x rozmiar): ");
            numberOfLions = scanner.nextInt();
        }
        int numberOfZebra = 3 * size;
        while(numberOfZebra > 2 * size){
            System.out.print("Podaj ile zebr umiescic na planszy (max 2x rozmiar): ");
            numberOfZebra = scanner.nextInt();
        }
        while(numberOfLions>0){
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if(animalMap[x][y]==null)
                if(!(areaMap[x][y] instanceof Mountain)){
                    animalMap[x][y] = new Lion (5,x,y,true);
                    numberOfLions--;
                }
        }
        while(numberOfZebra>0){
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if(animalMap[x][y]==null)
                if(!(areaMap[x][y] instanceof Mountain)){
                    animalMap[x][y] = new Zebra (5,x,y,true);
                    numberOfZebra--;
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

    public static void showAndSaveStats(int lionPopulation, int zebraPopulation, PrintWriter save){
        System.out.println("Populacja lwow: " + lionPopulation);
        System.out.println("Populacja zebr: " + zebraPopulation);
        System.out.println();

        save.println("Populacja lwow: " + lionPopulation);
        save.println("Populacja zebr: " + zebraPopulation);
        save.println();
    }

    public static int countPopulation(Animal[][] animalMap, int size, int parametr){
        //0 = count Lion, 1 = count Zebra
        int population = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(parametr==0){
                    if(animalMap[i][j] instanceof Lion) population++;
                }
                else if(animalMap[i][j] instanceof Zebra) population++;
            }
        }
        return population;
    }

    public static void prepareNewTurn(Animal[][] animalMap, Area[][] areaMap, int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((animalMap[i][j] instanceof Zebra || animalMap[i][j] instanceof Lion) && animalMap[i][j].isHungry())
                    animalMap[i][j] = null;
                if (animalMap[i][j] instanceof Zebra || animalMap[i][j] instanceof Lion){
                    animalMap[i][j].setActionPoints(5);
                    animalMap[i][j].setHungry(true);
                }
                if (areaMap[i][j] instanceof Dirt) areaMap[i][j].grow(areaMap);
            }
        }
    }
    public static void main(String []args) throws FileNotFoundException {
        //Pobranie danych do symulacji od użytkownika
        Scanner scanner = new Scanner(System.in);
        int size = 0;
        while(size<1){
            System.out.print("Podaj rozmiar mapy: ");
            size = scanner.nextInt();
        }
        int numberOfTurns = 0;
        while(numberOfTurns<1){
            System.out.print("Ile tur symulacji przeprowadzic?: ");
            numberOfTurns = scanner.nextInt();
        }
        System.out.print("W jaki sposob rozstawic zwierzeta? (1 - losowo, 0 - podac ilosc): ");
        int animalChoice = scanner.nextInt();
        System.out.println("Czy chcesz ogladac kazdy krok symulacji, czy jednynie stan na planszy po calej turze?");
        System.out.println("1 - kazdy krok symulacji");
        System.out.println("0 - Jedynie po calej turze");
        int userChoice = scanner.nextInt();

        //Inicjalizacja tablic
        Animal [][]animalMap = new Animal[size][size];
        Area [][]areaMap = new Area[size][size];

        //Generowanie map
        generateAreaMap(areaMap, size);
        if(animalChoice == 1) generateRandomAnimalMap(animalMap, size, areaMap);
        else generateAnimalMap(animalMap, size, areaMap);

        int lionPopulation, zebraPopulation;
        PrintWriter save = new PrintWriter("simulation.txt");

        //przebieg symulacji
        for(int turn = 1; turn <= numberOfTurns; turn++) {

            //zliczanie ilosci lwow i zebr
            lionPopulation = countPopulation(animalMap, size, 0);
            zebraPopulation = countPopulation(animalMap, size, 1);

            //Wypisanie informacji o stanie sytuacji na planszy
            System.out.println();
            System.out.println("Tura " + turn);
            save.println("Tura " + turn);
            showAndSaveStats(lionPopulation, zebraPopulation, save);

            showMap(animalMap, areaMap, size);
            System.out.println();

            //symulacja
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (animalMap[i][j] instanceof Zebra) {
                        animalMap[i][j].eat(areaMap, animalMap);
                        animalMap[i][j].move(areaMap, animalMap, i, j, size);
                        if(userChoice >= 1) {
                            System.out.println();
                            showMap(animalMap, areaMap, size);
                        }
                    } else if (animalMap[i][j] instanceof Lion) {
                        animalMap[i][j].eat(areaMap, animalMap);
                        animalMap[i][j].move(areaMap, animalMap, i, j, size);
                        if(userChoice >= 1) {
                            System.out.println();
                            showMap(animalMap, areaMap, size);
                        }
                   }
                }
            }

            prepareNewTurn(animalMap, areaMap, size);

        }

        lionPopulation = countPopulation(animalMap, size, 0);
        zebraPopulation = countPopulation(animalMap, size, 1);

        System.out.println("Po zakonczeniu symulacji:");
        save.println("Po zakończeniu symulacji:");
        showAndSaveStats(lionPopulation, zebraPopulation, save);
        showMap(animalMap, areaMap, size);

        save.close();
    }
}

//TODO:
//dodac mozliwosc podawania przez uzytkownika czy generowac gory/pustynie
//dodac mozliwosc podawania przez uzytkownika ilosc punktow ruchu dla zwierzat
