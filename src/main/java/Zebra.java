public class Zebra extends Animal{
    public Zebra(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat(Area [][]areaMap) {
        if (this.isHungry && areaMap[this.x][this.y] instanceof Grass){
            areaMap[this.x][this.y] = new Dirt(1,this.x,this.y);
            this.isHungry = false;
        }
    }
}
