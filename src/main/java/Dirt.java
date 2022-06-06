import java.util.Random;
public class Dirt extends Area{
    public Dirt(int movingCost, int x, int y) {
        super(movingCost, x, y);
        movingCost = 1;
    }
    public void grow(Area[][] areaMap){
        Random rand = new Random();
        int chance = rand.nextInt(6);
        if (chance < 1) areaMap[this.x][this.y] = new Grass(1,this.x,this.y);
    }
}    
