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
  public void implement() {
    if (effects[0]) {
      User.changeHealth(heal);
    }
    if (effects[1]) {
      User.changeLife(addLife);
    }
    if (effects[2]) {
      User.changeDamage(addDamage);
    }
  }
}
