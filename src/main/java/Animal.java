import java.util.Random;
public class Animal {
    protected int actionPoints;
    protected int x, y;
    protected boolean isHungry;

    public Animal(int actionPoints, int x, int y, boolean isHungry) {
        this.actionPoints = actionPoints;
        this.x = x;
        this.y = y;
        this.isHungry = isHungry;
    }

    public void move(Area[][] areaMap, Animal[][] animalMap, int x, int y, int size){

    }

    public void eat(Area [][]areaMap, Animal [][]animalMap){

    }
}
