public class Mountain extends Area{
    public Mountain(int movingCost, int x, int y, int maxCost) {
        super(movingCost, x, y);
        movingCost = maxCost + 1;
    }
}
