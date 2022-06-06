import java.util.Random;

public class Lion extends Animal{
    public Lion(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void move (Area [][] areaMap, Animal [][]animalMap,int x,int y, int size){
        int obstructions = 0;
        if(x+1<size && (animalMap[x+1][y] instanceof Lion || areaMap[x+1][y].movingCost > this.actionPoints)) obstructions++;
        else if (x+1>=size) obstructions++;
        if(x-1>=0 && (animalMap[x-1][y] instanceof Lion || areaMap[x-1][y].movingCost > this.actionPoints)) obstructions++;
        else if(x-1<0) obstructions++;
        if(y+1<size && (animalMap[x][y+1] instanceof Lion || areaMap[x][y+1].movingCost > this.actionPoints)) obstructions++;
        else if(y+1>=size) obstructions++;
        if(y-1>=0 && (animalMap[x][y-1] instanceof Lion || areaMap[x][y-1].movingCost > this.actionPoints)) obstructions++;
        else if(y-1<0) obstructions++;

        Random rand = new Random();
        while(isHungry && this.actionPoints > 0 && animalMap[x][y]!=null && obstructions!=4){
        int direction = rand.nextInt(4);
        switch(direction){
            case 0 -> {
                if(x+1<size && (animalMap[x+1][y] instanceof Zebra || animalMap[x+1][y] == null) && areaMap[x+1][y].movingCost <= this.actionPoints){
                    if (animalMap[x+1][y] == null) {
                        animalMap[x + 1][y] = new Lion(this.actionPoints - areaMap[x + 1][y].movingCost, x + 1, y, true);
                        animalMap[x + 1][y].move(areaMap, animalMap, x + 1, y, size);
                    }
                    else
                        animalMap[x+1][y] = new Lion(this.actionPoints - areaMap[x+1][y].movingCost, x+1, y, false);
                    animalMap[x][y] = null;
                }
            }
            case 1 -> {
                if(x-1>=0 && (animalMap[x-1][y] instanceof Zebra || animalMap[x-1][y]==null) && areaMap[x-1][y].movingCost <= this.actionPoints){
                    if (animalMap[x-1][y] == null) {
                        animalMap[x - 1][y] = new Lion(this.actionPoints - areaMap[x - 1][y].movingCost, x - 1, y, true);
                        animalMap[x - 1][y].move(areaMap, animalMap, x - 1, y, size);
                    }
                    else
                        animalMap[x-1][y] = new Lion(this.actionPoints - areaMap[x-1][y].movingCost, x-1, y, false);
                    animalMap[x][y] = null;
                }
            }
            case 2 -> {
                if(y+1<size && (animalMap[x][y+1] instanceof Zebra || animalMap[x][y+1]==null) && areaMap[x][y+1].movingCost <= this.actionPoints){
                    if (animalMap[x][y+1] == null) {
                        animalMap[x][y + 1] = new Lion(this.actionPoints - areaMap[x][y + 1].movingCost, x, y + 1, true);
                        animalMap[x][y + 1].move(areaMap, animalMap, x, y + 1, size);
                    }
                    else
                        animalMap[x][y+1] = new Lion(this.actionPoints - areaMap[x][y+1].movingCost, x, y+1, false);
                    animalMap[x][y] = null;
                }
            }
            case 3 -> {
                if(y-1>=0 && (animalMap[x][y-1] instanceof Zebra || animalMap[x][y-1]==null) && areaMap[x][y-1].movingCost <= this.actionPoints){
                    if (animalMap[x][y-1] == null) {
                        animalMap[x][y - 1] = new Lion(this.actionPoints - areaMap[x][y - 1].movingCost, x, y - 1, true);
                        animalMap[x][y - 1].move(areaMap, animalMap, x, y - 1, size);
                    }
                    else
                        animalMap[x][y-1] = new Lion(this.actionPoints - areaMap[x][y-1].movingCost, x, y-1, false);
                    animalMap[x][y] = null;
                }
            }
        }
    }

}
   /* public void eat (Area [][]areaMap, Animal [][]animalMap){
        if (this.isHungry && animalMap[this.x+1][this.y] instanceof Zebra) {
            this.isHungry = false;
            animalMap[this.x+1][this.y] = null;
        }
        else if (this.isHungry && animalMap[this.x][this.y+1] instanceof Zebra ) {
            this.isHungry = false;
            animalMap[this.x][this.y+1] = null;
        }
        else if (this.isHungry && animalMap[this.x][this.y-1] instanceof Zebra ) {
            this.isHungry = false;
            animalMap[this.x][this.y-1] = null;
        }
        else if (this.isHungry && animalMap[this.x-1][this.y] instanceof Zebra ) {
            this.isHungry = false;
            animalMap[this.x-1][this.y] = null;
        }
    }*/
}
