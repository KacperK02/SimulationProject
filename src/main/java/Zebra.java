public class Zebra extends Animal{
    public Zebra(int actionPoints, int x, int y, boolean isHungry) {
        super(actionPoints, x, y, isHungry);
    }
    public void eat(Area[][] areaMap) {
        if (this.isHungry && areaMap[this.x][this.y] == )// tu trzeba to polepszyc jakos przyrownac warunek kiedy na danym polu jest trawa
        areaMap[this.x][this.y] = new Dirt(1,this.x,this.y); // to jest opcja na pominiecie metody remove z klasy grass to juz jak uwazasz
    }
}
