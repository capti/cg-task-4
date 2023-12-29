package com.cgvsu.objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.IncorrectFileException;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ObjWriterTest {

    @Test
    void write() throws IOException, IncorrectFileException {
        Model model = createSampleModel();
        String fileName = "model_norm.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);
        Model testModel = ObjReader.read(fileContent);
        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();
        assertTrue(new File(fileName).exists());



        assertEquals(3, vertexCount);
        assertEquals(3, textureVertexCount);
        assertEquals(3, normalCount);
        assertEquals(1, polygonCount);
    }

    private Model createSampleModel() {
        Model model = new Model();
        Vector3f vertex1 = new Vector3f(1.0f, 2.0f, 3.0f);
        Vector3f vertex2 = new Vector3f(4.0f, 5.0f, 6.0f);
        Vector3f vertex3 = new Vector3f(7.0f, 8.0f, 9.0f);

        Vector2f textureVertex1 = new Vector2f(0.1f, 0.2f);
        Vector2f textureVertex2 = new Vector2f(0.3f, 0.4f);
        Vector2f textureVertex3 = new Vector2f(0.5f, 0.6f);

        Vector3f normal1 = new Vector3f(0.7f, 0.8f, 0.9f);
        Vector3f normal2 = new Vector3f(1.0f, 1.1f, 1.2f);
        Vector3f normal3 = new Vector3f(1.3f, 1.4f, 1.5f);

        model.vertices.add(vertex1);
        model.vertices.add(vertex2);
        model.vertices.add(vertex3);

        model.textureVertices.add(textureVertex1);
        model.textureVertices.add(textureVertex2);
        model.textureVertices.add(textureVertex3);

        model.normals.add(normal1);
        model.normals.add(normal2);
        model.normals.add(normal3);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        polygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        polygon.setNormalIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));


        model.polygons.add(polygon);

        return model;
    }

    @Test
    public void testWriteModelWithoutTextureAndNormals() throws IOException, IncorrectFileException {
        Model model = new Model();

        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));

        model.polygons.add(polygon);

        String fileName = "model_without_texture_normals.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);
        Model testModel = ObjReader.read(fileContent);
        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();
        assertTrue(new File(fileName).exists());



        assertEquals(3, vertexCount);
        assertEquals(0, textureVertexCount);
        assertEquals(0, normalCount);
        assertEquals(1, polygonCount);
    }


    @Test
    public void testWriteModelWithTextureWithoutNormals() throws IOException, IncorrectFileException {
        Model model = new Model();
        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));
        model.textureVertices.add(new Vector2f(0.0f, 0.0f));
        model.textureVertices.add(new Vector2f(1.0f, 1.0f));
        model.textureVertices.add(new Vector2f(2.0f, 2.0f));

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        polygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        model.polygons.add(polygon);

        String fileName = "model_with_texture_without_normals.obj";

        ObjWriter.write(fileName, model);

        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);

        Model testModel = ObjReader.read(fileContent);

        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();
        assertTrue(new File(fileName).exists());



        assertEquals(3, vertexCount);
        assertEquals(3, textureVertexCount);
        assertEquals(0, normalCount);
        assertEquals(1, polygonCount);
    }
    @Test
    public void testWriteModelWithoutTextureAndWithNormals() throws IOException, IncorrectFileException {
        Model model = new Model();

        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));

        Vector3f normal1 = new Vector3f(0.7f, 0.8f, 0.9f);
        Vector3f normal2 = new Vector3f(1.0f, 1.1f, 1.2f);
        Vector3f normal3 = new Vector3f(1.3f, 1.4f, 1.5f);

        model.normals.add(normal1);
        model.normals.add(normal2);
        model.normals.add(normal3);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        polygon.setNormalIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));

        model.polygons.add(polygon);

        String fileName = "model_without_texture_with_normals.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);
        Model testModel = ObjReader.read(fileContent);
        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();
        assertTrue(new File(fileName).exists());



        assertEquals(3, vertexCount);
        assertEquals(0, textureVertexCount);
        assertEquals(3, normalCount);
        assertEquals(1, polygonCount);
    }
 }