import java.lang.management.MonitorInfo;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void GenerateAnimalMap(Animal animalMap[][], int size, Area areaMap[][]){
        Random rand = new Random();
        for (int i=0; i<size;i++) {
            for (int j=0;j<size;j++) {
                int chance = rand.nextInt(6);
                if (chance == 1 && !(areaMap[i][j] instanceof Mountain))
                    animalMap[i][j] = new Lion (5,i,j,true);
                else if (chance == 2)
                    animalMap[i][j] = new Zebra (5,i,j,true);
                else
                    animalMap[i][j] = null;
            }
        }
    }

    public static void GenerateAreaMap(Area areaMap[][], int size){
        Random rand = new Random();
        for (int i=0; i<size;i++) {
            for (int j = 0; j < size; j++) {
                int chance = rand.nextInt(6);
                if (chance == 0)
                    areaMap[i][j] = new Grass(1, i, j);
                else if (chance == 1)
                    areaMap[i][j] = new Mountain(9, i, j);
                else if (chance == 2)
                    areaMap[i][j] = new Sand(2, i, j);
                else
                    areaMap[i][j] = new Dirt(1, i, j);
            }
        }
    }
    public static void main(String args[]){
        System.out.print("Podaj rozmiar mapy: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Random rand = new Random();

        //Inicjalizacja tablic w obiekty
        Animal animalMap[][] = new Animal[size][size];
        Area areaMap[][] = new Area[size][size];

        //Generowanie map
        GenerateAnimalMap(animalMap, size, areaMap);
        GenerateAreaMap(areaMap, size);
    }
}
