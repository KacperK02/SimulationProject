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
    private void move(Area[][] areaMap, Animal[][] animalMap,int x,int y) {
        Random rand = new Random();
        if (this.isHungry) {
            //value of direction will decide in which direction our animal will proceed
            int direction = rand.nextInt(3);
            if (direction == 0 && areaMap[x+1][y].movingCost <= this.actionPoints && animalMap[x+1][y]==null) {
                this.actionPoints = this.actionPoints - areaMap[x+1][y].movingCost;
                this.x++;
            }
            else if (direction == 1 && areaMap[x][y+1].movingCost <= this.actionPoints && animalMap[x][y+1]==null) {
                this.actionPoints = this.actionPoints - areaMap[x][y+1].movingCost;
                this.y++;
            }
            else if (direction == 2 && areaMap[x-1][y].movingCost <= this.actionPoints && animalMap[x-1][y]==null) {
                this.actionPoints = this.actionPoints - areaMap[x-1][y].movingCost;
                this.x--;
            }
            else if (areaMap[x][y-1].movingCost <= this.actionPoints && animalMap[x][y-1]==null) {
                this.actionPoints = this.actionPoints - areaMap[x][y-1].movingCost;
                this.y--;
            }
        }
    }
    private void die(Animal[][] animalMap){
        if (this.actionPoints == 0 && this.isHungry) {
            animalMap[this.x][this.y] = null;
        }
    }
}
