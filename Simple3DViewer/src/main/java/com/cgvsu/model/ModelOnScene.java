package com.cgvsu.model;

import javax.vecmath.Vector3f;

public class ModelOnScene extends Model {
    public Model modelStart;

    public float translationX;
    public float translationY;
    public float translationZ;

    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float scaleX = 1;
    public float scaleY = 1;
    public float scaleZ = 1;
    public boolean isActive;

    public ModelOnScene(Model model) {
        super();
        this.modelStart = model;
        this.isActive = false;
    }

    public Vector3f getTranslationVector() {
        return (new Vector3f(translationX, translationY, translationZ));
    }

    public Vector3f getScaleVector() {
        return (new Vector3f(scaleX, scaleY, scaleZ));
    }

    public void setTranslationX(float translationX) {
        this.translationX += translationX;
    }

    public void setTranslationY(float translationY) {
        this.translationY += translationY;
    }

    public void setTranslationZ(float translationZ) {
        this.translationZ += translationZ;
    }

    public void setRotationX(float rotationX) {
        this.rotationX += rotationX;
    }

    public void setRotationY(float rotationY) {
        this.rotationY += rotationY;
    }

    public void setRotationZ(float rotationZ) {
        this.rotationZ += rotationZ;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }//might be *

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setScaleZ(float scaleZ) {
        this.scaleZ = scaleZ;
    }

}
