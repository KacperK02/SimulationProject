import java.util.Random;
public class Animal {
    private int actionPoints;
    protected int x, y;
    protected boolean isHungry;

    public Animal(int actionPoints, int x, int y, boolean isHungry) {
        this.actionPoints = 5;
        this.x = x;
        this.y = y;
        this.isHungry = true;
    }
    private void move(Area[][] areaMap) {
        Random rand = new Random();
        if (this.isHungry) {
            //value of direction will decide in which direction our animal will proceed
            int direction = rand.nextInt(3);
            if (direction == 0 && areaMap[x++][y].movingCost <= this.actionPoints)
                this.x++;
            else if (direction == 1 && areaMap[x][y++].movingCost <= this.actionPoints) 
                this.y++;
            else if (direction == 2 && areaMap[x--][y].movingCost <= this.actionPoints) 
                this.x--;
            else if (areaMap[x][y--].movingCost <= this.actionPoints)
                this.y--;
        }
    }
}
