package math.Vector;

public class Vector4f {
    private float x;
    private float y;
    private float z;
    private float w;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f add(Vector4f other) {
        float newX = this.x + other.x;
        float newY = this.y + other.y;
        float newZ = this.z + other.z;
        float newW = this.w + other.w;
        return new Vector4f(newX, newY, newZ, newW);
    }

    public Vector4f subtract(Vector4f other) {
        float newX = this.x - other.x;
        float newY = this.y - other.y;
        float newZ = this.z - other.z;
        float newW = this.w - other.w;
        return new Vector4f(newX, newY, newZ, newW);
    }

    public Vector4f multiply(float scalar) {
        float newX = this.x * scalar;
        float newY = this.y * scalar;
        float newZ = this.z * scalar;
        float newW = this.w * scalar;
        return new Vector4f(newX, newY, newZ, newW);
    }

    public Vector4f divide(float scalar) {
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        float newX = this.x / scalar;
        float newY = this.y / scalar;
        float newZ = this.z / scalar;
        float newW = this.w / scalar;
        return new Vector4f(newX, newY, newZ, newW);
    }

    public float length() {
        return (float) Math.sqrt(x*x + y*y + z*z + w*w);
    }

    public Vector4f normalize() {
        float magnitude = length();
        return divide(magnitude);
    }

    public float dotProduct(Vector4f other) {
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setW(float w) {
        this.w = w;
    }

}
