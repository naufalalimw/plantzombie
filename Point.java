public class Point{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getAbsis(){
        return this.x;
    }
    public int getOrdinat(){
        return this.y;
    }
    public void setAbsis(int x){
        this.x = x;
    }
    public void setOrdinat(int y){
        this.y = y;
    }

}