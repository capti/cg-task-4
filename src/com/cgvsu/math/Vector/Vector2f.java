package math.Vector;

public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f add(Vector2f other) {
        float newX = this.x + other.x;
        float newY = this.y + other.y;
        return new Vector2f(newX, newY);
    }

    public Vector2f subtract(Vector2f other) {
        float newX = this.x - other.x;
        float newY = this.y - other.y;
        return new Vector2f(newX, newY);
    }

    public Vector2f multiply(float scalar) {
        float newX = this.x * scalar;
        float newY = this.y * scalar;
        return new Vector2f(newX, newY);
    }

    public Vector2f divide(float scalar) {
        float newX = this.x / scalar;
        float newY = this.y / scalar;
        return new Vector2f(newX, newY);
    }

    public float length() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public Vector2f normalize() {
        float magnitude = length();
        return divide(magnitude);
    }

    public float dotProduct(Vector2f other) {
        return this.x * other.x + this.y * other.y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(vector2f.x, x) == 0 && Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
