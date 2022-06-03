import java.util.Random;

public class Zebra extends Animal{
    public Zebra(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat(Area [][]areaMap, Animal [][]animalMap) {
        if (this.isHungry && areaMap[this.x][this.y] instanceof Grass){
            areaMap[this.x][this.y] = new Dirt(1,this.x,this.y);
            this.isHungry = false;
        }
    }

    public void move(Area[][] areaMap, Animal[][] animalMap, int x, int y, int size){
        Random rand = new Random();
        while(isHungry && this.actionPoints > 0){
            int direction = rand.nextInt(4);
            switch(direction){
                case 0 -> {
                    if(x+1<size && animalMap[x+1][y]==null && areaMap[x+1][y].movingCost <= this.actionPoints){
                        this.actionPoints = this.actionPoints - areaMap[x+1][y].movingCost;
                        //this.x++;
                    }
                }
                case 1 -> {
                    if(x-1>=0 && animalMap[x-1][y]==null && areaMap[x-1][y].movingCost <= this.actionPoints){
                        this.actionPoints = this.actionPoints - areaMap[x-1][y].movingCost;
                        //this.x--;
                    }
                }
                case 2 -> {
                    if(y+1<size && animalMap[x][y+1]==null && areaMap[x][y+1].movingCost <= this.actionPoints){
                        this.actionPoints = this.actionPoints - areaMap[x][y+1].movingCost;
                        //this.y++;
                    }
                }
                case 3 -> {
                    if(y-1>=0 && animalMap[x][y-1]==null && areaMap[x][y-1].movingCost <= this.actionPoints){
                        this.actionPoints = this.actionPoints - areaMap[x][y-1].movingCost;
                        //this.y--;
                    }
                }
            }
        }

    }

}
