package application;

import java.io.IOException;
import java.util.PriorityQueue;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ControllerEasy extends Controller{

//  private static Stage stage;
//  private static Scene scene;
//  private static Parent root;
  private static Battle battle;
  private static Lane[] lanes = new Lane[3];
  private int selectedWeapon = -1;
  private int turn = 0;
  
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
  
  public void startBattle() throws IOException {
    battle = new Battle(1, 0, 720, 3, 250);
    for (int i = 0; i < 3; i++) {
        lanes[i] = battle.getOriginalLanes().get(i);
    }
  }
  
  public void buyFinalAction(ActionEvent e) throws IOException {
  	Button b = (Button) e.getSource();
      String selectedLane = b.getId();
      int laneNum = switch(selectedLane) {
      	case "lane1lsct" -> 1;
      	case "lane2lsct" -> 2;
      	case "lane3lsct" -> 3;
      	default -> 0;
      };
      if(!(selectedWeapon == -1)) {
      	try {
              battle.purchaseWeapon(selectedWeapon, lanes[laneNum - 1]);
              
              if(selectedWeapon == 1) {
              	Image PC = new Image("PiercCannon.jpg");
              	ImageView PCV = new ImageView(PC);
              	switch(laneNum) {
              	case 1: lane1weapbox.getChildren().add(PCV); break;
              	case 2: lane2weapbox.getChildren().add(PCV); break;
              	case 3: lane3weapbox.getChildren().add(PCV);
              	}
              	Label l = new Label("Anti-Titan Shell\nPiercing Cannon\n25\n10");
              	l.setFont(new Font(9));
              	switch(laneNum) {
              	case 1: weapbox1labels.getChildren().add(l); break;
              	case 2: weapbox2labels.getChildren().add(l); break;
              	case 3: weapbox3labels.getChildren().add(l);
              	}
              }
              
              if(selectedWeapon == 2) {
              	Image SC = new Image("SniperCannon.jfif");
              	ImageView SCV = new ImageView(SC);
              	switch(laneNum) {
              	case 1: lane1weapbox.getChildren().add(SCV); break;
              	case 2: lane2weapbox.getChildren().add(SCV); break;
              	case 3: lane3weapbox.getChildren().add(SCV); break;
              	}
              	Label l = new Label("Long Range Spear\nSniper Cannon\n25\n35");
              	l.setFont(new Font(9));
              	switch(laneNum) {
              	case 1: weapbox1labels.getChildren().add(l); break;
              	case 2: weapbox2labels.getChildren().add(l); break;
              	case 3: weapbox3labels.getChildren().add(l);
              	}
              }
              
              if(selectedWeapon == 3) {
              	Image VSC = new Image("VolleyCannon.jpg");
              	ImageView VSCV = new ImageView(VSC);
              	switch(laneNum) {
              	case 1: lane1weapbox.getChildren().add(VSCV); break;
              	case 2: lane2weapbox.getChildren().add(VSCV); break;
              	case 3: lane3weapbox.getChildren().add(VSCV); break;
              	}
              	Label l = new Label("Wall Spread Cannon\nVolley Spread Cannon\n100\n5");
              	l.setFont(new Font(9));
              	switch(laneNum) {
              	case 1: weapbox1labels.getChildren().add(l); break;
              	case 2: weapbox2labels.getChildren().add(l); break;
              	case 3: weapbox3labels.getChildren().add(l);
              	}
              }
              
              if(selectedWeapon == 4) {
              	Image WT = new Image("WallTrap.jfif");
              	ImageView WTV = new ImageView(WT);
              	switch(laneNum) {
              	case 1: lane1weapbox.getChildren().add(WTV); break;
              	case 2: lane2weapbox.getChildren().add(WTV); break;
              	case 3: lane3weapbox.getChildren().add(WTV); break;
              	}
              	Label l = new Label("Proximity Trap\nWall Trap\n75\n100");
              	l.setFont(new Font(9));
              	switch(laneNum) {
              	case 1: weapbox1labels.getChildren().add(l); break;
              	case 2: weapbox2labels.getChildren().add(l); break;
              	case 3: weapbox3labels.getChildren().add(l);
              	}
              }
              
          } catch (InsufficientResourcesException e1) {
              Label l = new Label("Not enough resources, resources provided = " + battle.getResourcesGathered());
              l.setFont(new Font(20));
              Scene s = new Scene(l);
              Stage eStage = new Stage();
              eStage.setScene(s);
              eStage.show();
              e1.printStackTrace();
          } catch (InvalidLaneException e1) {
              if (battle.getOriginalLanes().get(laneNum - 1).isLaneLost()) {
              	Label l = new Label("Lane is already lost, can't place weapon here");
                  l.setFont(new Font(20));
                  Scene s = new Scene(l);
                  Stage eStage = new Stage();
                  eStage.setScene(s);
                  eStage.show();
              }
              else {
              	Label l = new Label("Invalid lane number, number must be between 1 and 3 inclusive");
                  l.setFont(new Font(20));
                  Scene s = new Scene(l);
                  Stage eStage = new Stage();
                  eStage.setScene(s);
                  eStage.show();
              }
              e1.printStackTrace();
          }
  	}
      else {
      	Label l = new Label("Please choose a weapon");
          l.setFont(new Font(20));
          Scene s = new Scene(l);
          Stage eStage = new Stage();
          eStage.setScene(s);
          eStage.show();
      }
      selectedWeapon = -1;
      titanMoveLanes();
      updateScore();
      updateTurn();
      updatePhase();
      updateRes();
      updateWallData();
  }

  public void buyPiercingCannon(ActionEvent e) throws IOException {
  	selectedWeapon = 1;
  }
  
  public void buySniperCannon(ActionEvent e) throws IOException {
  	selectedWeapon = 2;
  }
  
  public void buyVolleySpreadCannon(ActionEvent e) throws IOException {
  	selectedWeapon = 3;
  }
  
  public void buyWallTrap(ActionEvent e) throws IOException {
  	selectedWeapon = 4;
  }
  
  public void turnPassBtn(ActionEvent e) throws IOException {
  	battle.passTurn();
  	titanMoveLanes();
  	updateScore();
      updateTurn();
      updatePhase();
      updateRes();
      updateWallData();
      Lose();
  }
  
  public void titanMoveLanes() {
  	lane1titans.getChildren().clear();
  	lane2titans.getChildren().clear();
  	lane3titans.getChildren().clear();
  	
  	for(int i = 0; i<3; i++){
  		if(!lanes[i].isLaneLost()) {
      		PriorityQueue<Titan> titans = lanes[i].getTitans();
      		
      		for(Titan titan : titans) {					//place existing titans with their new distances
      			if(i==0) {
      				if(titan instanceof game.engine.titans.PureTitan) {
      					Image PT = new Image("PureTitan.png");
      			    	ImageView PTV = new ImageView(PT);
      					lane1titans.getChildren().add(PTV);
      					AnchorPane.setRightAnchor(PTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(PTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane1titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.AbnormalTitan) {
      					Image AT = new Image("abnormal titan.jpg");
      			    	ImageView ATV = new ImageView(AT);
      					lane1titans.getChildren().add(ATV);
      					AnchorPane.setRightAnchor(ATV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(ATV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane1titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.ArmoredTitan) {
      					Image ArmT = new Image("armored titan.jpg");
      			    	ImageView ArmTV = new ImageView(ArmT);
      					lane1titans.getChildren().add(ArmTV);
      					AnchorPane.setRightAnchor(ArmTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(ArmTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane1titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.ColossalTitan) {
      					Image CT = new Image("colossal titan.jpg");
      			    	ImageView CTV = new ImageView(CT);
      					lane1titans.getChildren().add(CTV);
      					AnchorPane.setRightAnchor(CTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(CTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane1titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      			}
      			if(i==1) {
      				if(titan instanceof game.engine.titans.PureTitan) {
      					Image PT = new Image("PureTitan.png");
      			    	ImageView PTV = new ImageView(PT);
      					lane2titans.getChildren().add(PTV);
      					AnchorPane.setRightAnchor(PTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(PTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane2titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.AbnormalTitan) {
      					Image AT = new Image("abnormal titan.jpg");
      			    	ImageView ATV = new ImageView(AT);
      					lane2titans.getChildren().add(ATV);
      					AnchorPane.setRightAnchor(ATV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(ATV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane2titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.ArmoredTitan) {
      					Image ArmT = new Image("armored titan.jpg");
      			    	ImageView ArmTV = new ImageView(ArmT);
      					lane2titans.getChildren().add(ArmTV);
      					AnchorPane.setRightAnchor(ArmTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(ArmTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane2titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.ColossalTitan) {
      					Image CT = new Image("colossal titan.jpg");
      			    	ImageView CTV = new ImageView(CT);
      					lane2titans.getChildren().add(CTV);
      					AnchorPane.setRightAnchor(CTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(CTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane2titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      			}
      			if(i==2) {
      				if(titan instanceof game.engine.titans.PureTitan) {
      					Image PT = new Image("PureTitan.png");
      			    	ImageView PTV = new ImageView(PT);
      					lane3titans.getChildren().add(PTV);
      					AnchorPane.setRightAnchor(PTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(PTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane3titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.AbnormalTitan) {
      					Image AT = new Image("abnormal titan.jpg");
      			    	ImageView ATV = new ImageView(AT);
      					lane3titans.getChildren().add(ATV);
      					AnchorPane.setRightAnchor(ATV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(ATV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane3titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.ArmoredTitan) {
      					Image ArmT = new Image("armored titan.jpg");
      			    	ImageView ArmTV = new ImageView(ArmT);
      					lane3titans.getChildren().add(ArmTV);
      					AnchorPane.setRightAnchor(ArmTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(ArmTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane3titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      				else if(titan instanceof game.engine.titans.ColossalTitan) {
      					Image CT = new Image("colossal titan.jpg");
      			    	ImageView CTV = new ImageView(CT);
      					lane3titans.getChildren().add(CTV);
      					AnchorPane.setRightAnchor(CTV, 720 - (double)titan.getDistance());
      					AnchorPane.setTopAnchor(CTV,7.0);
      					
      					Label l = new Label("HP: "+titan.getCurrentHealth());
      					lane3titans.getChildren().add(l);
      					AnchorPane.setRightAnchor(l, 720 - (double)titan.getDistance());
      					AnchorPane.setBottomAnchor(l,10.0);
      				}
      			}
      		}
      	}
  		}
  	}

  public void updateRes() {
  	resourcesLabel.setText("Resources: "+battle.getResourcesGathered());
  }
  
  public void updateWallData() {
  	Image broken = new Image("broken wall icon.jpg");
  	for(int i=0; i<3; i++) {
  		Lane lane = lanes[i];
  		if(lane.isLaneLost()) {
  			if(i==0) {
  				lanewall1.setImage(broken);
  				walllabel1.setText("Lane lost!");
  			}
  			else if(i==1) {
  				lanewall2.setImage(broken);
  				walllabel2.setText("Lane lost!");
  			}
  			else if(i==2) {
  				lanewall3.setImage(broken);
  				walllabel3.setText("Lane lost!");
  			}
  		}
  		else {
      		int HP = lane.getLaneWall().getCurrentHealth();
      		int danger = lane.getDangerLevel();
      		if(i==0) {
      			walllabel1.setText("HP: "+HP+"Danger level: "+danger);
  			}
  			else if(i==1) {
  				walllabel2.setText("HP: "+HP+"Danger level: "+danger);
  			}
  			else if(i==2) {
  				walllabel3.setText("HP: "+HP+"Danger level: "+danger);
  			}
  		}
  	}
  }
  
  public void updatePhase() {
  	phaseLabel.setText("Phase: "+battle.getBattlePhase());
  }
  
  public void updateTurn() {
  	turn++;
  	turnLabel.setText("Turn: "+turn);
  }
  
  public void updateScore() {
  	scorelabel.setText("Score: "+battle.getScore());
  }

  public void Lose() throws IOException {
  	if(battle.isGameOver()) {
          Controller cont = new Controller();
          cont.gameOver();
  	}
  }
	
}
