public class Enemy extends Being {
  public boolean isOnEdge() {
    return true;
  }
  public void move() {}
  public Laser shoot() {
    Laser l = new Laser(attack,xPos,yPos,this);
    return l;
  }
}
