package com.cgvsu.math.Matrix;

import com.cgvsu.math.Vector.Vector3f;

public class Matrix3f {
    private float[][] matrix;

    public Matrix3f() {
        matrix = new float[3][3];
    }

    public Matrix3f(float[][] matrix) {
        if (matrix.length != 3 || matrix[0].length != 3) {
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }
        this.matrix = matrix;
    }


    public Matrix3f add(Matrix3f other) {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f subtract(Matrix3f other) {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] - other.matrix[i][j];
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f multiply(float scalar) {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] * scalar;
            }
        }
        return new Matrix3f(result);
    }



    public Vector3f multiply(Vector3f vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не может быть нулевым");
        }
        float[] result = new float[3];
        for (int i = 0; i < 3; i++) {
            result[i] = this.matrix[i][0] * vector.getX() +
                    this.matrix[i][1] * vector.getY() +
                    this.matrix[i][2] * vector.getZ();
        }
        return new Vector3f(result[0], result[1], result[2]);
    }

    public Matrix3f multiply(Matrix3f other) {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f transpose() {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[j][i];
            }
        }
        return new Matrix3f(result);
    }

    public static Matrix3f identity() {
        float[][] result = new float[3][3];
        result[0][0] = 1;
        result[1][1] = 1;
        result[2][2] = 1;
        return new Matrix3f(result);
    }

    public static Matrix3f zero() {
        float[][] result = new float[3][3];
        return new Matrix3f(result);
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }
}
