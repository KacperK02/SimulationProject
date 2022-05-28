import java.io.Console;
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
                else
                    animalMap[i][j] = null;
            }
        }
    }

    public static void generateAreaMap(Area [][]areaMap, int size){
        Random rand = new Random();
        for (int i=0; i<size;i++) {
            for (int j = 0; j < size; j++) {
                int chance = rand.nextInt(6);
                if (chance >= 0 && chance <=2)
                    areaMap[i][j] = new Grass(1, i, j);
                else if (chance == 3)
                    areaMap[i][j] = new Mountain(9, i, j);
                else if (chance == 4)
                    areaMap[i][j] = new Sand(2, i, j);
                else
                    areaMap[i][j] = new Dirt(1, i, j);
            }
        }
    }
    //wyswietlanie - umowa:
    //Trawa - kolor zielony
    //Piasek - kolor zółty
    //Góry - kolor niebieski
    //Ziemia - kolor czarny (domyslny)
    //Zebra - "Z"
    //Lew - "L"
    public static void showMap(Animal [][]animalMap, Area [][]areaMap, int size){
        String blackBackground = "\033[40m";
        String background = "";
        for(int i=0; i<size; i++){
            System.out.print(blackBackground + "|");
            for(int j=0; j<size; j++){
                if(areaMap[i][j] instanceof Grass) background = "\033[42m";
                else {
                    if(areaMap[i][j] instanceof Sand) background = "\033[43m";
                    else {
                        if(areaMap[i][j] instanceof Mountain) background = "\033[44m";
                        else background = blackBackground;
                    }
                }

                if(animalMap[i][j] instanceof Zebra) System.out.print(background + "Z" + blackBackground);
                else {
                    if(animalMap[i][j] instanceof Lion) System.out.print(background + "L" + blackBackground);
                    else System.out.print(blackBackground + " ");
                }
            }
            System.out.println(blackBackground + "|");
        }
    }
    public static void main(String []args){
        System.out.print("Podaj rozmiar mapy: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Random rand = new Random();

        //Inicjalizacja tablic
        Animal [][]animalMap = new Animal[size][size];
        Area [][]areaMap = new Area[size][size];

        //Generowanie map
        generateAreaMap(areaMap, size);
        generateAnimalMap(animalMap, size, areaMap);

        showMap(animalMap, areaMap, size);
    }
}
