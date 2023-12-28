package com.cgvsu.triangulation;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.List;

public class TriangulatedModel extends Model {
    private List<Polygon> triangles;

    public TriangulatedModel(
            List<Vector3f> vertices,
            List<Vector2f> normals,
            List<Vector3f> textureVertices,
            List<Polygon> polygons,
            List<Polygon> triangles) {

        super(vertices, normals, textureVertices, polygons);
        this.triangles = triangles;
    }

    public TriangulatedModel(
            List<Vector3f> vertices,
            List<Vector2f> normals,
            List<Vector3f> textureVertices,
            List<Polygon> polygons) {

        super(vertices, normals, textureVertices, polygons);
        this.triangles = Triangulation.triangulateList(polygons);
    }

    public List<Polygon> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<Polygon> triangles) {
        this.triangles = triangles;
    }

    @Override
    public void setPolygons(List<Polygon> polygons) {
        super.setPolygons(polygons);
        setTriangles(Triangulation.triangulateList(polygons));
    }
}