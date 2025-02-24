package game.gui;

import game.engine.Battle;
import game.engine.interfaces.Attackee;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Battle battle;
    private Stage mainWindow;
    private AnchorPane mainPane;
    private Button startButton;
    private Scene second;
    private AnchorPane easyPane;
    private VBox buttonBox;
    private Button easyButton;
    private Button hardButton;
    private Scene easy;
    private Label cs;
    private Label ct;
    private Label cp;
    private Label cr;
    private Button[] weaponButton;
    private Button passTurn;
    private HBox hbox;
    private Label[] lanesLabels;
    private VBox[] lanesWeapons;
    private Pane[] lanesTitans;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainWindow = primaryStage;
        mainWindow.setTitle("Attack on Titans : Utopia");
        mainPane = new AnchorPane();
        Scene first = new Scene(mainPane, 1000, 600);
        Image image = new Image("title (1).png");
        ImageView imageView = new ImageView(image);
        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();
        imageView.setLayoutX((1000 - imageWidth) / 2);
        imageView.setLayoutY(imageHeight);

        // Load button image start
        Image buttonImage = new Image("startgame (1).png");
        ImageView buttonImageView = new ImageView(buttonImage);

        // Create button start
        startButton = new Button();
        startButton.setGraphic(buttonImageView);
        startButton.setOnAction(this);
        // Center button horizontally
        double buttonImageWidth = buttonImage.getWidth();
        startButton.setLayoutX((1000 - buttonImageWidth) / 2);
        startButton.setLayoutY(160); // Adjust the vertical position as needed

        // Second scene
        AnchorPane secondPane = new AnchorPane();
        second = new Scene(secondPane, 1000, 800);
        Image easyImage = new Image("easy.png");
        ImageView buttoneasyView = new ImageView(easyImage);
        Image hardImage = new Image("hard.png");
        ImageView buttonhardView = new ImageView(hardImage);

        easyButton = new Button();
        easyButton.setGraphic(buttoneasyView);
        easyButton.setOnAction(this);

        hardButton = new Button();
        hardButton.setGraphic(buttonhardView);
        hardButton.setOnAction(this);

        buttonBox = new VBox(10); // spacing between buttons
        buttonBox.getChildren().addAll(easyButton, hardButton);
        buttonBox.setLayoutX((1000 - easyImage.getWidth()) / 2);
        buttonBox.setLayoutY((800 - hardImage.getHeight() * 2 - 10) / 2); // center vertically

        // Add VBox to the second scene
        secondPane.getChildren().add(buttonBox);

        mainPane.getChildren().addAll(imageView, startButton);
        mainWindow.setScene(first);
        mainWindow.show();
        // Third scene (easy)
        easyPane = new AnchorPane();
        easy = new Scene(easyPane, 1000, 800);

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == startButton) {
            mainWindow.setScene(second);
        }
        if (event.getSource() == easyButton) {
            try {
                battle = new Battle(1, 0, 300, 3, 250);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buildEasyMode();
            WeaponShop();
            mainWindow.setScene(easy);
            mainWindow.show();
        }
    }

    public void WeaponShop() {
        cs = new Label("Example Label CS");
        ct = new Label("Example Label CT");
        cr = new Label("Example Label CR");
        cp = new Label("Example Label CP");
        hbox = new HBox();
        weaponButton = new Button[4];

        // Create and configure weapon buttons
        for (int i = 0; i < weaponButton.length; i++) {
            weaponButton[i] = new Button("Weapon " + i);
            weaponButton[i].setOnAction(this);
            weaponButton[i].setPrefSize(250, 250); // Set preferred size for the buttons
            hbox.getChildren().add(weaponButton[i]);
        }

        passTurn = new Button("Pass Turn");
        passTurn.setOnAction(this);

        double labelX = 20;
        double vboxX = 125;
        double passTurnX = 250;
        double startY = 400;
        double verticalGap = 25;

        cs.setLayoutX(labelX);
        cs.setLayoutY(startY);
        cs.setPrefWidth(100);
        cs.setPrefHeight(25);

        ct.setLayoutX(labelX);
        ct.setLayoutY(startY + verticalGap);
        ct.setPrefWidth(100);
        ct.setPrefHeight(25);

        cr.setLayoutX(labelX);
        cr.setLayoutY(startY + 2 * verticalGap);
        cr.setPrefWidth(100);
        cr.setPrefHeight(25);

        cp.setLayoutX(labelX);
        cp.setLayoutY(startY + 3 * verticalGap);
        cp.setPrefWidth(100);
        cp.setPrefHeight(25);

        hbox.setLayoutX(vboxX);
        hbox.setLayoutY(startY + 4 * verticalGap);
        hbox.setPrefWidth(475);
        hbox.setPrefHeight(75);

        passTurn.setLayoutX(passTurnX);
        passTurn.setLayoutY(startY + 3 * verticalGap);
        passTurn.setPrefWidth(156);
        passTurn.setPrefHeight(25);

        // Add labels, VBox, and buttons to easyPane
        easyPane.getChildren().addAll(cs, ct, cr, cp, hbox, passTurn);

        getValues();
    }


    public void buildEasyMode() {
        easyPane.getChildren().clear();

        lanesLabels = new Label[3];
        lanesWeapons = new VBox[3];
        lanesTitans = new Pane[3];

        for (int i = 0; i < 3; i++) {
            lanesLabels[i] = new Label("Example Lane Label " + (i + 1));
            lanesLabels[i].setLayoutX(20);
            lanesLabels[i].setLayoutY(20 + 150 * i); // Adjust Y position for each label
            lanesLabels[i].setPrefWidth(100);
            lanesLabels[i].setPrefHeight(50);
            easyPane.getChildren().add(lanesLabels[i]);

            lanesWeapons[i] = new VBox();
            lanesWeapons[i].setLayoutX(120);
            lanesWeapons[i].setLayoutY(20 + 150 * i); // Adjust Y position for each vertical box
            lanesWeapons[i].setPrefWidth(50);
            lanesWeapons[i].setPrefHeight(150);
            easyPane.getChildren().add(lanesWeapons[i]);

            lanesTitans[i] = new Pane();
            lanesTitans[i].setLayoutX(200);
            lanesTitans[i].setLayoutY(20 + 150 * i); // Adjust Y position for each horizontal box
            lanesTitans[i].setPrefWidth(350);
            lanesTitans[i].setPrefHeight(100);
            easyPane.getChildren().add(lanesTitans[i]);
        }

        getValues();
    }

    private void getValues() {
        if (battle == null || battle.getOriginalLanes() == null) {
            System.err.println("Battle or lanes not initialized.");
            return;
        }

        for (int i = 0; i < battle.getOriginalLanes().size(); i++) {
            Lane lane = battle.getOriginalLanes().get(i);

            if (lane == null) {
                System.err.println("Lane " + i + " is null.");
                continue;
            }

            String laneInfo = "Danger: " + lane.getDangerLevel() + "\n" + "Health: ";
            if (lane instanceof Attackee) {
                laneInfo += ((Attackee) lane).getCurrentHealth();
            } else {
                laneInfo += "N/A";
            }
            lanesLabels[i].setText(laneInfo);

            if (lane.isLaneLost()) {
                lanesWeapons[i].getChildren().clear();

                for (Weapon weapon : lane.getWeapons()) {
                    String weaponName = "";
                    if (weapon instanceof PiercingCannon) {
                        weaponName = "spear.jpg";
                    } else if (weapon instanceof SniperCannon) {
                        weaponName = "sniper.jpg";
                    } else if (weapon instanceof VolleySpreadCannon) {
                        weaponName = "cannon2.png";
                    } else if (weapon instanceof WallTrap) {
                        weaponName = "trap (1).png";
                    }

                    ImageView imageView = new ImageView(new Image(weaponName));
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    lanesWeapons[i].getChildren().add(imageView);
                }

                lanesTitans[i].getChildren().clear();

                for (Titan titan : lane.getTitans()) {
                    String titanName;
                    if (titan instanceof PureTitan) {
                        titanName = "pure.png";
                    } else if (titan instanceof AbnormalTitan) {
                        titanName = "abnormal.png";
                    } else if (titan instanceof ArmoredTitan) {
                        titanName = "armored.png";
                    } else if (titan instanceof ColossalTitan) {
                        titanName = "colossal.png";
                    } else {
                        titanName = "default_titan_image.png";
                    }

                    ImageView titanImageView = new ImageView(new Image(titanName));
                    titanImageView.setFitWidth(30);
                    titanImageView.setFitHeight(30);

                    Text text = new Text(titan.getCurrentHealth() + "**");
                    text.setFont(new Font(14));
                    text.setStyle("-fx-fill: black;");

                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.setLayoutX(titan.getDistance());
                    anchorPane.setLayoutY(Math.random() * 70);
                    anchorPane.getChildren().addAll(titanImageView, text);

                    lanesTitans[i].getChildren().add(anchorPane);
                }
            }
        }
    }

    private String getImageNameOfWeapon(Weapon weapon) {
        String name = "";
        if (weapon instanceof PiercingCannon) {
            name = "spear.jpg";
        } else if (weapon instanceof SniperCannon) {
            name = "sniper.jpg";
        } else if (weapon instanceof VolleySpreadCannon) {
            name = "cannon2.png";
        } else if (weapon instanceof WallTrap) {
            name = "trap (1).png";
        }
        return name;
    }

    private String getImageNameOfTitan(Titan titan) {
        String name = "";
        if (titan instanceof PureTitan) {
            name = "pure.png";
        } else if (titan instanceof AbnormalTitan) {
            name = "abnormal.png";
        } else if (titan instanceof ArmoredTitan) {
            name = "armored.png";
        } else if (titan instanceof ColossalTitan) {
            name = "colossal.png";
        }
        return name;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
