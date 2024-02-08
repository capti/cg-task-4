package com.cgvsu.scene;


import com.cgvsu.model.ModelOnScene;
import com.cgvsu.render_engine.Camera;

import javax.vecmath.Vector3f;
import java.util.ArrayList;

public class Scene {
    public ArrayList<ModelOnScene> modelsList;
    public Camera camera;

    public Scene(ArrayList<ModelOnScene> modelsList, Camera camera) {
        this.modelsList = modelsList;
        this.camera = camera;
    }

    public Scene() {
        this.modelsList = new ArrayList<ModelOnScene>();
        this.camera = new Camera(new Vector3f(0, 0, 10), new Vector3f(0, 0, 0), 1F, 1, 0.01F, 100);
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Vector3f getCameraPosition() {
        return this.camera.getPosition();
    }

    public Vector3f getCameraTarget() {
        return this.camera.getTarget();
    }
}
