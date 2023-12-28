package com.cgvsu.model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Integer> vertexIndices;
    private List<Integer> normalIndices;
    private List<Integer> textureVertexIndices;

    public Polygon(List<Integer> vertexIndices, List<Integer> normalIndices, List<Integer> textureVertexIndices) {
        this.vertexIndices = vertexIndices;
        this.normalIndices = normalIndices;
        this.textureVertexIndices = textureVertexIndices;
    }

    public Polygon() {
        this.vertexIndices = new ArrayList<>();
        this.normalIndices = new ArrayList<>();
        this.textureVertexIndices = new ArrayList<>();
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public void setVertexIndices(ArrayList<Integer> vertexIndices) {
        this.vertexIndices = vertexIndices;
    }

    public List<Integer> getNormalIndices() {
        return normalIndices;
    }

    public void setNormalIndices(ArrayList<Integer> normalIndices) {
        this.normalIndices = normalIndices;
    }

    public List<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }

    public void setTextureVertexIndices(ArrayList<Integer> textureVertexIndices) {
        this.textureVertexIndices = textureVertexIndices;
    }

    public boolean isTriangle() {
        return vertexIndices.size() == 3;
    }

    public int getVertexIndex(int index) {
        return vertexIndices.get(index);
    }

    public int getNormalIndex(int index) {
        return normalIndices.get(index);
    }

    public int getTextureVertexIndex(int index) {
        return textureVertexIndices.get(index);
    }

    public int numberOfVertices() {
        return normalIndices.size();
    }
}