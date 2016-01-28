/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

/**
 *
 * @author jonny
 */
public class PdfLocation {
    
    private float xPos;

    /**
     * Get the value of xPos
     *
     * @return the value of xPos
     */
    public float getxPos() {
        return xPos;
    }

    /**
     * Set the value of xPos
     *
     * @param xPos new value of xPos
     */
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    private float yPos;

    /**
     * Get the value of yPos
     *
     * @return the value of yPos
     */
    public float getyPos() {
        return yPos;
    }

    /**
     * Set the value of yPos
     *
     * @param yPos new value of yPos
     */
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    private float width;

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    private float height;

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    
    public PdfLocation(float xPos, float yPos, float width, float height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
}
