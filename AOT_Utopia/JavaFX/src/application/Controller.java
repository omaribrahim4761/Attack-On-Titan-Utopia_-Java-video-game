package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Controller {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    
    @FXML
    private Button lane1;

    @FXML
    private TextField laneSlctField;

    @FXML
    private HBox lane1weapbox;
    
    @FXML
    private HBox lane2weapbox;
    
    @FXML
    private HBox lane3weapbox;
    
    @FXML
    private AnchorPane lane1titans;
    
    @FXML
    private AnchorPane lane2titans;
    
    @FXML
    private AnchorPane lane3titans;
    
    @FXML
    private Label scorelabel;
    
    @FXML
    private Label turnLabel;
    
    @FXML
    private Label phaseLabel;
    
    @FXML
    private Label resourcesLabel;
    
    @FXML
    private ImageView lanewall1;
    
    @FXML
    private ImageView lanewall2;
    
    @FXML
    private ImageView lanewall3;
    
    @FXML
    private Label walllabel1;
    
    @FXML
    private Label walllabel2;
    
    @FXML
    private Label walllabel3;
    
    @FXML
    private HBox weapbox1labels;
    
    @FXML
    private HBox weapbox2labels;
    
    @FXML
    private HBox weapbox3labels;
    
    @FXML
    private Label gameoverlabel;
    
    public void gameInstruction(ActionEvent e) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("GameInstructions.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,610,400);
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public void goBackInstrc(ActionEvent e) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,900,600);
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToGameSceneEasy(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,1350,850);
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.setScene(scene);
        stage.show();
        ControllerEasy controllerEasy = new ControllerEasy();
		controllerEasy.startBattle();
    }

    public void switchToGameSceneHard(ActionEvent e) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("HardScene.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root,1350,900);
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        ControllerHard controllerHard = new ControllerHard();
		controllerHard.startBattle();
    }
    
    public void gameOver() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        root = loader.load();
        scene = new Scene(root,600,400);
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.show();
    }
    

}










