package com.github.camilormz.finalreality.gui;

import com.github.camilormz.finalreality.controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Main entry point for the application.
 * All the instructions are in the app itself.
 *
 * @author Ignacio Slater Muñoz
 * @author Camilo Ramírez Canales
 */
public class FinalReality extends Application {

  private final GameController controller = new GameController();
  private final Group root = new Group();

  private Label currentCharacterTitle;
  private Label currentCharacterLabel;
  private Label playableCharactersTitle;
  private Label enemyTitle;
  private Label inventoryTitle;
  private Label instructionsLabel;

  private Button continueButton;
  private Button unEquipButton;

  private boolean startedGame = false;

  private LinkedList<Label> pcLabels, enemyLabels, inventoryLabels;

  private final LinkedList<Button> equipButtons = new LinkedList<>();
  private final LinkedList<Button> attackButtons = new LinkedList<>();

  // Enemy attack delay in milliseconds, it's intended as a way to make the game more "natural"
  private final int enemyAttackDelay = 500;

  /**
   * Main point of execution of the game
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Final Reality");
    primaryStage.setResizable(false);
    Scene scene = sceneInit();
    startAnimator();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Label createVisibleLabel(int x, int y) {
    Label label = createLabel(x, y);
    root.getChildren().add(label);
    return label;
  }

  private Label createLabel(int x, int y) {
    Label label = new Label();
    label.setLayoutX(x);
    label.setLayoutY(y);
    return label;
  }

  private Button createButton(int x, int y, String text) {
    Button button = new Button(text);
    button.setLayoutX(x);
    button.setLayoutY(y);
    root.getChildren().add(button);
    return button;
  }

  private Scene sceneInit() {
    Scene scene = new Scene(root, 600, 600);

    Label welcomeLabel = createVisibleLabel(175, 10);
    welcomeLabel.setText("Welcome to the B-class game \"Final Reality\"!");

    instructionsLabel = createVisibleLabel(10, 40);
    instructionsLabel.setText("Click the button on the right to start the game");

    currentCharacterTitle = createLabel(10, 70);
    currentCharacterTitle.setText("Waiting for the game to start...");

    currentCharacterLabel = createLabel(30, 90);

    playableCharactersTitle = createLabel(10, 130);
    playableCharactersTitle.setText("The following list corresponds to your characters:");

    inventoryTitle = createLabel(10, 270);
    inventoryTitle.setText("The following list corresponds to your weapon inventory:");

    enemyTitle = createLabel(10, 410);
    enemyTitle.setText("The following list corresponds to the enemies:");

    Button startButton = createButton(520, 35, "Start");
    startButton.setOnAction(actionEvent -> {
      controller.initController();
      startedGame = true;
      startButton.setText("Have fun!");
      startButton.setDisable(true);
      currentCharacterTitle.setText("The current turn corresponds to the following character:");
      // Allows the visualization of the labels
      root.getChildren().add(currentCharacterTitle);
      root.getChildren().add(playableCharactersTitle);
      root.getChildren().add(inventoryTitle);
      root.getChildren().add(enemyTitle);
      this.assembleLabels();
      this.showLabelsAfterInit();
      this.showButtonsAfterInit();
    });

    return scene;
  }

  private void assembleLabels() {
    pcLabels = this.assembleLabelList(controller.getAssignedCharacterString(),150);
    inventoryLabels = this.assembleLabelList(controller.getInventoryString(), 290);
    enemyLabels = this.assembleLabelList(controller.getEnemiesString(), 430);
  }

  private LinkedList<Label> assembleLabelList(LinkedList<String> stringList, int initPos) {
    LinkedList<Label> labelList = new LinkedList<>();
    for (int i=0; i<stringList.size(); i++) {
      Label stringLabel = createLabel(30, initPos+i*20);
      stringLabel.setText(stringList.get(i));
      labelList.add(i, stringLabel);
    }
    return labelList;
  }

  private void updateLabelList(LinkedList<Label> labelList, LinkedList<String> stringList) {
    for (int i=0; i<labelList.size(); i++) {
      labelList.get(i).setText(stringList.get(i));
    }
  }

  private void updateObjectLabels() {
    this.updateLabelList(pcLabels, controller.getAssignedCharacterString());
    this.updateLabelList(inventoryLabels, controller.getInventoryString());
    this.updateLabelList(enemyLabels, controller.getEnemiesString());
  }

  private void showLabelsAfterInit() {
    root.getChildren().add(currentCharacterLabel);

    for (Label label : pcLabels) {
      root.getChildren().add(label);
    }
    for (Label label : inventoryLabels) {
      root.getChildren().add(label);
    }
    for (Label label : enemyLabels) {
      root.getChildren().add(label);
    }
  }

  private void showButtonsAfterInit() {
    continueButton = createButton(520, 65, "Proceed");
    continueButton.setOnAction(ActionEvent -> {
      instructionsLabel.setText(
        "Click on the attack button of an enemy to execute an attack with the current character");
      controller.finishEquipmentProcedure();
      continueButton.setDisable(true);
    });

    unEquipButton = createButton(520, 95, "Un-equip");
    unEquipButton.setOnAction(Action -> controller.unEquipTurnCharacter());

    this.assembleEquipButtons(290);
    this.assembleAttackButtons(430);
  }

  private void assembleEquipButtons(int initPos) {
    for (int i=0; i<5; i++) {
      int finalI = i;
      Button equipButton = createButton(500, initPos-15+i*25, "Try to equip");
      equipButton.setDisable(true);
      equipButton.setOnAction(ActionEvent -> controller.tryToEquipCurrentByIndex(finalI));
      equipButtons.add(equipButton);
    }
  }

  private void assembleAttackButtons(int initPos) {
    for (int i=0; i<enemyLabels.size(); i++) {
      int finalI = i;
      Button attackButton = createButton(500, initPos-15+i*25, "Attack");
      attackButton.setDisable(true);
      attackButton.setOnAction(ActionEvent -> {
        controller.executeAttack(finalI);
        controller.turnEnd();
      });
      attackButtons.add(attackButton);
    }
  }

  private void setDisabledButtons(LinkedList<Button> buttonList, boolean state) {
    for (Button button : buttonList) {
      button.setDisable(state);
    }
  }

  private void startAnimator() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        if (startedGame) {
          currentCharacterLabel.setText(controller.getCurrentTurnCharacterString());
          updateObjectLabels();
          if (controller.isAtPickingPhase() && controller.isAnyCharacterInTurn()) {
            controller.turnStart();
            instructionsLabel.setText(
                    "Equip or un-equip the character's weapon, click proceed to continue");
            continueButton.setDisable(false);
          }
          if (controller.isAtEquipmentPhase()) {
            continueButton.setDisable(false);
            unEquipButton.setDisable(!controller.isCurrentTurnCharacterEquipped());
            setDisabledButtons(equipButtons, controller.isCurrentTurnCharacterEquipped());
          } else {
            unEquipButton.setDisable(true);
            setDisabledButtons(equipButtons, true);
          }
          if (controller.isAtAttackPhase()) {
            if (controller.isCurrentCharacterEnemy()) {
              setDisabledButtons(attackButtons, true);
              instructionsLabel.setText(
                      "This is an enemy turn, it will perform a random attack to your characters");

              try {
                Thread.sleep(enemyAttackDelay);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

              int attackIndex = ThreadLocalRandom.current().nextInt(0, 5);
              controller.executeAttack(attackIndex);
              controller.turnEnd();
            } else {
              setDisabledButtons(attackButtons, !controller.isAtAttackPhase());
            }
          } else {
            setDisabledButtons(attackButtons, true);
          }
          if (controller.getWinner().equals(GameController.WINNER_PLAYER)) {
            instructionsLabel.setText("You won the game! C O N G R A T U L A T I O N S !!");
            finishGame();
          } else if (controller.getWinner().equals(GameController.WINNER_CPU)) {
            instructionsLabel.setText("You lost, this is so sad :( ... Alexa, play Despacito");
          }
        }
      }
    };
    timer.start();
  }

  private void finishGame() {
    setDisabledButtons(attackButtons, true);
    setDisabledButtons(equipButtons, true);
    unEquipButton.setDisable(true);
    continueButton.setDisable(true);
  }
}