public class Bullet {
    private double x, y;

    private final double bulletSpeedConstant = 0.7;

    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= 10 * bulletSpeedConstant;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}

