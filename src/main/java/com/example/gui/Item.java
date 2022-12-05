package com.example.gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Item extends Prop{

    public Item(String path, int x, int y, boolean collideable) {
        super(path, x, y, collideable);
    }

    public void testCollision(GraphicsContext gc){
//        Rectangle2D myHitBox = new Rectangle2D(x,y,50,50);
//        if(testItem.colliding(myHitBox)){
//            gc.fillText("hello",x,y);
//        }
    }
}
