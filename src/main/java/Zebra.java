public class Zebra extends Animal{
    public Zebra(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat() {
        if (isHungry == true && areaMap[this.x][this.y] == // tu trzeba to polepszyc jakos przyrownac warunek kiedy na danym polu jest trawa
        areaMap[this.x][this.y] = new Dirt(); // to jest opcja na pominiecie metody remove z klasy grass to juz jak uwazasz
    }
}
