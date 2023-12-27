package com.cgvsu.math.Matrix;

import com.cgvsu.math.Vector.Vector4f;

public class Matrix4f {
    private float[][] matrix;

    public Matrix4f() {

        matrix = new float[4][4];
    }

    public Matrix4f(float[][] matrix) {
        if (matrix.length != 4 || matrix[0].length != 4) {
            throw new IllegalArgumentException("Invalid matrix dimensions");
        }
        this.matrix = matrix;
    }
    // добавила новый конструктор
    public Matrix4f(
            float m00, float m01, float m02, float m03,
            float m10, float m11, float m12, float m13,
            float m20, float m21, float m22, float m23,
            float m30, float m31, float m32, float m33) {
        matrix = new float[][]{
                {m00, m01, m02, m03},
                {m10, m11, m12, m13},
                {m20, m21, m22, m23},
                {m30, m31, m32, m33}
        };
    }

    public Matrix4f(Matrix4f t) {
        float [][] m = t.toArray();
        this.matrix = m;
    }

    public float[][] toArray() {
        return matrix;
    }





    public Matrix4f add(Matrix4f other) {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }
        return new Matrix4f(result);
    }

    public Matrix4f subtract(Matrix4f other) {
       float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[i][j] - other.matrix[i][j];
            }
        }
        return new Matrix4f(result);
    }

    public Matrix4f multiply(float scalar) {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[i][j] * scalar;
            }
        }
        return new Matrix4f(result);
    }

    public Vector4f multiply(Vector4f vector) {
        float[] result = new float[4];
        for (int i = 0; i < 4; i++) {
            result[i] = this.matrix[i][0] * vector.getX() +
                    this.matrix[i][1] * vector.getY() +
                    this.matrix[i][2] * vector.getZ() +
                    this.matrix[i][3] * vector.getW();
        }
        return new Vector4f(result[0], result[1], result[2], result[3]);
    }

    public Matrix4f multiply(Matrix4f other) {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return new Matrix4f(result);
    }

    public Matrix4f transpose() {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[j][i];
            }
        }
        return new Matrix4f(result);
    }

    public static Matrix4f identity() {
        float[][] result = new float[4][4];
        result[0][0] = 1;
        result[1][1] = 1;
        result[2][2] = 1;
        result[3][3] = 1;
        return new Matrix4f(result);
    }

    public static Matrix4f zero() {
        float[][] result = new float[4][4];
        return new Matrix4f(result);
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }
}
