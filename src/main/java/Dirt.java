import java.util.Random;
public class Dirt extends Area{
    public Dirt(int movingCost, int x, int y) {
        super(movingCost, x, y);
        movingCost = 1;
    }
    public void grow(){
        Random rand = new Random();
        int chance = rand(10);
        if (chance < 1) 
        areaMap[this.x][this.y] = new Grass();
    // giving 10% chance for grass to regrow on dirt spots
    }
}    
