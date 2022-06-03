import java.util.Random;
public class Animal {
    protected int actionPoints;
    protected int x, y;
    protected boolean isHungry;

    public Animal(int actionPoints, int x, int y, boolean isHungry) {
        this.actionPoints = 5;
        this.x = x;
        this.y = y;
        this.isHungry = true;
    }
    /*public void move(Area[][] areaMap, Animal[][] animalMap, int x, int y) {
        Random rand = new Random();
        while(isHungry && actionPoints>0){
            int direction = rand.nextInt(4);
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
            else if (direction == 3 && areaMap[x][y-1].movingCost <= this.actionPoints && animalMap[x][y-1]==null) {
                this.actionPoints = this.actionPoints - areaMap[x][y-1].movingCost;
                this.y--;
            }
        }
    }*/

    public void move(Area[][] areaMap, Animal[][] animalMap, int x, int y, int size){

    }

    public void eat(Area [][]areaMap, Animal [][]animalMap){

    }
}
