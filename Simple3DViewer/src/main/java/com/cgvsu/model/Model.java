package com.cgvsu.model;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Vector3f> vertices;
    private List<Vector2f> normals;
    private List<Vector3f> textureVertices;
    private List<Polygon> polygons;

    public Model(List<Vector3f> vertices, List<Vector2f> normals, List<Vector3f> textureVertices, List<Polygon> polygons) {
        this.vertices = vertices;
        this.normals = normals;
        this.textureVertices = textureVertices;
        this.polygons = polygons;
    }

    public Model() {
        this.vertices = new ArrayList<>();
        this.normals = new ArrayList<>();
        this.textureVertices = new ArrayList<>();
        this.polygons = new ArrayList<>();
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vector3f> vertices) {
        this.vertices = vertices;
    }

    public List<Vector2f> getNormals() {
        return normals;
    }

    public void setNormals(List<Vector2f> normals) {
        this.normals = normals;
    }

    public List<Vector3f> getTextureVertices() {
        return textureVertices;
    }

    public void setTextureVertices(List<Vector3f> textureVertices) {
        this.textureVertices = textureVertices;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }
}