package com.example.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Prop {

    private int x,y;
    private Image image;
    private boolean collideable;

    public Prop(String path, int x, int y, boolean collideable){
        this.x = x;
        this.y = y;

        File imageFile = new File(path);
        if(!imageFile.exists()){
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        //load external resouces
        final Image image;
        try{
            image = new Image(new FileInputStream("src/main/resources/com/example/gui/guy.png"));
        }catch (FileNotFoundException e){
            throw new RuntimeException("Could not load prop Image");
        }

        this.x=x;
        this.y=y;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image,x,y);
    }
}
