public class Barrier{
  private int x;
  private int y;

    public Barrier(int newx, int newy){
      x = newx;
      y = newy;
    }

    public void die(){
      x = 0;
      y = 0;
    }
}
