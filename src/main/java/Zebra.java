public class Zebra extends Animal{
    public Zebra(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat() {
        
        areaMap[this.x][this.y] = new Dirt();
    }
}
