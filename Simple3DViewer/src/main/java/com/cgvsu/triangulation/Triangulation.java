package com.cgvsu.triangulation;


import com.cgvsu.model.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangulation {

    public static List<Polygon> triangulate(Polygon polygon) {
        List<Polygon> triangles = new ArrayList<>();
        if (polygon.isTriangle()) {
            triangles.add(polygon);
            return triangles;
        }

        for (int verListInd = 2; verListInd < polygon.numberOfVertices(); verListInd++) {
            Polygon triangle = new Polygon(
                    Arrays.asList(polygon.getVertexIndex(0),
                            polygon.getVertexIndex(verListInd - 1),
                            polygon.getVertexIndex(verListInd)),
                    Arrays.asList(polygon.getNormalIndex(0),
                            polygon.getNormalIndex(verListInd - 1),
                            polygon.getNormalIndex(verListInd)),
                    Arrays.asList(polygon.getTextureVertexIndex(0),
                            polygon.getTextureVertexIndex(verListInd - 1),
                            polygon.getTextureVertexIndex(verListInd)));
            triangles.add(triangle);
        }
        return triangles;
    }

    public static List<Polygon> triangulateList(List<Polygon> polygons) {
        List<Polygon> triangles = new ArrayList<>();
        for (Polygon polygon : polygons) {
            triangles.addAll(triangulate(polygon));
        }
        return triangles;
    }
}
