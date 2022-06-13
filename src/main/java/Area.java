public class Area implements IArea{
    private int movingCost;
    private int x, y;
    public int getMovingCost() {
        return movingCost;
    }

    public void setMovingCost(int movingCost) {
        this.movingCost = movingCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Area(int movingCost, int x, int y) {
        this.movingCost = movingCost;
        this.x = x;
        this.y = y;
    }

    public void grow(Area[][] areaMap){

    }
}
