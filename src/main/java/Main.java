import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        System.out.print("Podaj rozmiar mapy: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        Animal animalMap[][] = new Animal[size][size];
        Area areaMap[][] = new Area[size][size];

    }
}
