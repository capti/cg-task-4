package math.Vector;

import math.MathMethods;

import java.util.List;
import java.util.Objects;

public class Vector3f {
    private float x;
    private float y;
    private float z;

    private static final float EPSILON = 1e-10f;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() {
    }


    public Vector3f add(Vector3f other) {
        float newX = this.x + other.x;
        float newY = this.y + other.y;
        float newZ = this.z + other.z;
        return new Vector3f(newX, newY, newZ);
    }


    public Vector3f subtraction(Vector3f other) {
        float newX = this.x - other.x;
        float newY = this.y - other.y;
        float newZ = this.z - other.z;
        return new Vector3f(newX, newY, newZ);
    }

    public Vector3f multiply(float scalar) {
        float newX = this.x * scalar;
        float newY = this.y * scalar;
        float newZ = this.z * scalar;
        return new Vector3f(newX, newY, newZ);
    }

    public Vector3f divide(float scalar) {
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        float newX = this.x / scalar;
        float newY = this.y / scalar;
        float newZ = this.z / scalar;
        return new Vector3f(newX, newY, newZ);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f normalize() {
        float magnitude = length();
        return divide(magnitude);
    }

    public float dotProduct(Vector3f v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z;
    }

    public void crossProduct(Vector3f v1, Vector3f v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vector3f can not be null");
        }
            float x = v1.y * v2.z - v1.z * v2.y;
            float y = v2.x * v1.z - v2.z * v1.x;

            this.z = v1.x * v2.y - v1.y * v2.x;
            this.x = x;
            this.y = y;
    }

    public static Vector3f fromTwoPoint(Vector3f v1, Vector3f v2) {
        return new Vector3f(
                v2.x - v1.x,
                v2.y - v1.y,
                v2.z - v1.z);
    }


    public static Vector3f sum(List<Vector3f> vectors) {
        if (vectors == null || vectors.isEmpty()) {
            throw new IllegalArgumentException("List of vectors is null or empty");
        }

        float sumX = 0.0F;
        float sumY = 0.0F;
        float sumZ = 0.0F;

        for (Vector3f vector : vectors) {
            sumX += vector.getX();
            sumY += vector.getY();
            sumZ += vector.getZ();
        }

        return new Vector3f(sumX, sumY, sumZ);
    }



    public void scaleX(float scale) {
        this.x *= scale;
    }

    public void scaleY(float scale) {
        this.y *= scale;
    }

    public void scaleZ(float scale) {
        this.z *= scale;
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Math.abs(vector3f.getX() - x) < EPSILON &&
                Math.abs(vector3f.getY() - y) < EPSILON &&
                Math.abs(vector3f.getZ() - z) < EPSILON;
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
