package com.example.gui;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button editNameButton;
    @FXML
    private TextField nameField;
    @FXML
    private Canvas gameCanvas;
    @FXML
    private Label nameLabel;
    @FXML
    private Button saveCharacterButton, strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton, charismaRollButton, wisdomRollButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel;

    int x=100, y=100;
    int x1=100, y1=100;
    int saveCounter=0;
    boolean changedName = false;
    boolean characterCreated = false;
    Player player;

    @FXML
    public void initialize() {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        nameField.setEditable(false);
        nameField.setVisible(false);
        saveCharacterButton.setVisible(false);
        player = new Player("guy.png", 100,100);

        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //gc.clearRect(0,0,800,600);
                gc.setFill(Color.BISQUE);
                gc.fillRect(0,0,800,600);

                //if they haven't created a character on the Character tab
                if(!characterCreated) {
                    gc.setFill(Color.RED);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(2);
                    gc.fillText("No Character", x, y);
                    gc.fillText("Create Character", x, y + 100);
                }
                //else, character has been created, play the game
                else {
                    movement();
                    player.testCollision(gc);
                    player.draw(gc);
                }
            }
        };

        anim.start();

    }

    protected void movement(){
        if(HelloApplication.currentlyActiveKeys.contains(KeyCode.A.toString())){
            player.moveLeft();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.D.toString())) {
            player.moveRight();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.S.toString())) {
            player.moveDown();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.W.toString())) {
            player.moveUp();
        }
    }

    @FXML
    protected void onSaveMenuClicked() throws FileNotFoundException {
        File file = HelloApplication.openSaveDialog();
        if(file != null){
            file = new File(file.getAbsolutePath()+".deg");
            Formatter output = new Formatter(file);
            output.format("%s,%s,%s,%s,%s,%s,%s",nameField.getText(),strengthValueLabel.getText(),dexterityValueLabel.getText(),constitutionValueLabel.getText(),intelligenceValueLabel.getText(),wisdomValueLabel.getText(),charismaValueLabel.getText());
            output.close();
        }

    }
    @FXML
    protected void onOpenMenuClicked() throws FileNotFoundException{
        Label[] lists = {strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel};
        ArrayList<String> stats = new ArrayList<>();
        File file = HelloApplication.openFileDialog();
        if(file != null){
            Scanner scanner = new Scanner(file);
            String stat = scanner.next();
            String s="";
            for(int i =0; i<stat.length(); i++){
                if(!stat.substring(i,i+1).equals(",")){
                    s += stat.substring(i,i+1);
                }
                else {
                    stats.add(s);
                    s ="";
                }
            }
            stats.add(s);
            nameLabel.setText(stats.get(0));
            for(int i =0; i<lists.length; i++){
                lists[i].setText(stats.get(i+1));
            }
            onSaveButtonClicked();
//            System.out.println(stats);
        }
    }
    @FXML
    protected void setEditNameButtonClick() {
        String buttonText = editNameButton.getText();
        if(buttonText.equals("Edit")) {
            editNameButton.setText("Save");
            editNameButton.setLayoutX(300);
            nameLabel.setVisible(false);
            nameField.setVisible(true);
            nameField.setEditable(true);
        }
        else if(buttonText.equals("Save")) {
            editNameButton.setText("Edit");
            editNameButton.setLayoutX(25);
            nameLabel.setVisible(true);
            nameField.setVisible(false);
            nameField.setEditable(false);
            nameLabel.setText(nameField.getText());
            if(!changedName)
                saveCounter++;
            changedName = true;
        }
    }

    @FXML
    protected void onSaveButtonClicked() {
        saveCharacterButton.setVisible(false);
        strengthRollButton.setVisible(false);
        dexterityRollButton.setVisible(false);
        intelligenceRollButton.setVisible(false);
        constitutionRollButton.setVisible(false);
        charismaRollButton.setVisible(false);
        wisdomRollButton.setVisible(false);
        editNameButton.setVisible(false);
        characterCreated = true;
    }

    protected int rolld20() {
        return (int)(1 + Math.random() * 20);
    }

    @FXML
    protected void onRollButton(ActionEvent event) {
        Button b = (Button)event.getSource();

        if(b == strengthRollButton && b.isVisible()) {
            strengthValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == dexterityRollButton) {
            dexterityValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == constitutionRollButton) {
            constitutionValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == intelligenceRollButton) {
            intelligenceValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == wisdomRollButton) {
            wisdomValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }
        else if(b == charismaRollButton) {
            charismaValueLabel.setText("" + rolld20());
            if(b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            }
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120,30);
                saveCounter++;
            }
        }

        if (saveCounter >= 7)
            saveCharacterButton.setVisible(true);
    }//end onRollButton

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}