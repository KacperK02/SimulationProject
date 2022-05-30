public class Lion extends Animal{
    public Lion(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat (Animal [][]animalMap){
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
    }
}
