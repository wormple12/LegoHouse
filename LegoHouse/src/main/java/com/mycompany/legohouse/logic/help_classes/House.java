/*
 */

package com.mycompany.legohouse.logic.help_classes;

/**
 * @author Simon Asholt Norup
 */
public class House {

    private final int width;
    private final int length;
    private final int height;
    
    public House(int width, int length, int height){
        if (width < 4 || length < 4 || height < 1){
            throw new IllegalArgumentException("Please pass valid dimensions. The minimum dimensions possible are 4 x 4 x 1.");
        }
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

}
