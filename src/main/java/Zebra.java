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
        int obstructions = 0;
        if(x+1<size && (animalMap[x+1][y]!=null || areaMap[x+1][y].movingCost > this.actionPoints)) obstructions++;
        else if (x+1>=size) obstructions++;
        if(x-1>=0 && (animalMap[x-1][y]!=null || areaMap[x-1][y].movingCost > this.actionPoints)) obstructions++;
        else if(x-1<0) obstructions++;
        if(y+1<size && (animalMap[x][y+1]!=null || areaMap[x][y+1].movingCost > this.actionPoints)) obstructions++;
        else if(y+1>=size) obstructions++;
        if(y-1>=0 && (animalMap[x][y-1]!=null || areaMap[x][y-1].movingCost > this.actionPoints)) obstructions++;
        else if(y-1<0) obstructions++;

        Random rand = new Random();
        while(isHungry && this.actionPoints > 0 && animalMap[x][y]!=null && obstructions!=4){
            int direction = rand.nextInt(4);
            switch(direction){
                case 0 -> {
                    if(x+1<size && animalMap[x+1][y]==null && areaMap[x+1][y].movingCost <= this.actionPoints){
                        //this.actionPoints = this.actionPoints - areaMap[x+1][y].movingCost;
                        animalMap[x+1][y] = new Zebra(this.actionPoints - areaMap[x+1][y].movingCost,x+1,y,true);
                        animalMap[x+1][y].eat(areaMap,animalMap);
                        animalMap[x+1][y].move(areaMap,animalMap,x+1,y,size);
                        animalMap[x][y] = null;
                    }
                }
                case 1 -> {
                    if(x-1>=0 && animalMap[x-1][y]==null && areaMap[x-1][y].movingCost <= this.actionPoints){
                        //this.actionPoints = this.actionPoints - areaMap[x-1][y].movingCost;
                        animalMap[x-1][y] = new Zebra(this.actionPoints - areaMap[x-1][y].movingCost,x-1,y,true);
                        animalMap[x-1][y].eat(areaMap,animalMap);
                        animalMap[x-1][y].move(areaMap,animalMap,x-1,y,size);
                        animalMap[x][y] = null;
                    }
                }
                case 2 -> {
                    if(y+1<size && animalMap[x][y+1]==null && areaMap[x][y+1].movingCost <= this.actionPoints){
                        //this.actionPoints = this.actionPoints - areaMap[x][y+1].movingCost;
                        animalMap[x][y+1] = new Zebra(this.actionPoints - areaMap[x][y+1].movingCost,x,y+1,true);
                        animalMap[x][y+1].eat(areaMap,animalMap);
                        animalMap[x][y+1].move(areaMap,animalMap,x,y+1,size);
                        animalMap[x][y] = null;
                    }
                }
                case 3 -> {
                    if(y-1>=0 && animalMap[x][y-1]==null && areaMap[x][y-1].movingCost <= this.actionPoints){
                        //this.actionPoints = this.actionPoints - areaMap[x][y-1].movingCost;
                        animalMap[x][y-1] = new Zebra(this.actionPoints - areaMap[x][y-1].movingCost,x,y-1,true);
                        animalMap[x][y-1].eat(areaMap,animalMap);
                        animalMap[x][y-1].move(areaMap,animalMap,x,y-1,size);
                        animalMap[x][y] = null;
                    }
                }
            }
        }

    }

}
