public class Powerup implements HasLocation {
  private int xPos;
  private int yPos;
  private int heal;
  private int addLife;
  private int addDamage;
  private int time;
  private boolean[] effects;
  public Powerup(int h, int l, int d, int t, boolean[] e) {
    heal = h;
    addLife = l;
    addDamage = d;
    time = t;
    effects = e;
  }
  public int getXPos() {
    return xPos;
  }
  public int getYPos() {
    return yPos;
  }
  public void implement(User u) {
    if (effects[0]) {
      u.changeHealth(heal);
    }
    if (effects[1]) {
      u.changeLife(addLife);
    }
    if (effects[2]) {
      u.changeDamage(addDamage);
    }
  }
}
