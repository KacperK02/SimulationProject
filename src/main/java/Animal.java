import java.util.Random;
public class Animal implements IAnimal{
    private int actionPoints;
    private int x, y;
    private boolean isHungry;

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

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
