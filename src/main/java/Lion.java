public class Lion extends Animal{
    public Lion(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat (Animal animalMap[][]){
        if (this.isHungry && animalMap[this.x+1][this.y] == ) { //do poprawy jak u zebry
            this.isHungry = false;
            animalMap[this.x+1][this.y] = null;
        }
        else if (this.isHungry && animalMap[this.x][this.y+1] == ) {
            this.isHungry = false;
            animalMap[this.x][this.y+1] = null;
        }
        else if (this.isHungry && animalMap[this.x][this.y-1] == ) {
            this.isHungry = false;
            animalMap[this.x][this.y-1] = null;
        }
        else if (this.isHungry && animalMap[this.x-1][this.y] == ) {
            this.isHungry = false;
            animalMap[this.x-1][this.y] = null;
        }
    }
}
