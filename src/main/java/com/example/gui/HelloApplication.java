package com.example.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class HelloApplication extends Application {
    private static Stage window;
    static Scene scene;
    static HashSet<String> currentlyActiveKeys;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        window = stage;
        window.setTitle("Hello World");
        window.setScene(scene);
        prepareActionHandlers();
        window.show();
    }

    private static void prepareActionHandlers() {
        currentlyActiveKeys = new HashSet<String>();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.add(event.getCode().toString());

            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.remove(event.getCode().toString());

            }
        });
    }
    public static File openSaveDialog(){
        File recordsDir = new File(System.getProperty("user.home"),"freshmen/Dohyun/DungeonJavaFx/records");
        if(!recordsDir.exists()){
            recordsDir.mkdirs();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(recordsDir);
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All File","*.*"));
        File file = fileChooser.showSaveDialog(window);
        return file;
    }

    public static File openFileDialog(){
        File recordsDir = new File(System.getProperty("user.home"),"freshmen/Dohyun/DungeonJavaFx/records");
        if(!recordsDir.exists()){
            recordsDir.mkdirs();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(recordsDir);
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All File","*.*"));
        File file = fileChooser.showOpenDialog(window);
        return file;
    }
    public static void main(String[] args) {
        launch();
    }
}