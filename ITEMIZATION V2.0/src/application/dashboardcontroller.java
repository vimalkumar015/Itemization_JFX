package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class dashboardcontroller implements Initializable  {

    @FXML
    private JFXButton logout;
    
    @FXML
    public  BorderPane basepan;
    
    public static BorderPane bpane;
    
    @FXML
    private JFXTreeView<String> menu;
    
    @FXML
    private Label time;
    
    @FXML
    private Label date;
    
     AnchorPane fxmlLoader;
    
    ImageView homeicon = new ImageView(new Image(getClass().getResourceAsStream("/images/if_home_126572.png"), 20,20 , true,true));
    ImageView itemicon = new ImageView(new Image(getClass().getResourceAsStream("/images/if_Menu_List_Text_Line_Item_Bullet_Paragraph_1654364.png"), 20,20 , true,true));
    ImageView locationicon = new ImageView(new Image(getClass().getResourceAsStream("/images/locationpin.png"), 20,20 , true,true));
    ImageView salesandpurchaseicon = new ImageView(new Image(getClass().getResourceAsStream("/images/if_analytics_1954530.png"), 20,20 , true,true));
    ImageView reporticon = new ImageView(new Image(getClass().getResourceAsStream("/images/if_icon-45-note-list_315263.png"), 20,20 , true,true));
    ImageView usersicon = new ImageView(new Image(getClass().getResourceAsStream("/images/if_users_2561497.png"), 20,25 , true,true));
    ImageView addnewitem = new ImageView(new Image(getClass().getResourceAsStream("/images/addnew.png"),20,20,true,true));
    public void clock()
	{
		Thread clock = new Thread()
	{
			public void run()
			{
				try {
					while(true)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd, yyyy");
						SimpleDateFormat stf = new SimpleDateFormat("hh:mm:ss a");
						Calendar calendar = new GregorianCalendar();
						
					
					Platform.runLater(new Runnable()
							{

								@Override
								public void run() {
									time.setText(stf.format(calendar.getTime()));
									date.setText(sdf.format(calendar.getTime()));
									
								}
						
							});
					
					sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	};
	clock.start();
	}
    
    public void getmenu(MouseEvent event)
    {
    	TreeItem<String> item = menu.getSelectionModel().getSelectedItem();
    	if(item.getValue().equals("Items Master"))
    	{
    		Thread itemthread = new Thread() {
    			public void run()
    			{
    				Platform.runLater(new Runnable()
    						{

								@Override
								public void run() {
									try {
						    			fxmlLoader = FXMLLoader.load(getClass().getResource("itempage.fxml"));
						    			
						    		}catch(Exception e)
						    		{
						    			System.out.println("hai");
						    			e.printStackTrace();
						    		}
						    		fxmlLoader.setMaxHeight(10000);
						    		fxmlLoader.setMaxWidth(10000);
						    		basepan.setCenter(fxmlLoader);
								}
    					
    						});
    			}
    		};
    		itemthread.start();
    	}else if(item.getValue().equals("Home"))
    	{
    		Thread homethread = new Thread()
    				{
    			public void run()
    			{
    				Platform.runLater(new Runnable()
    						{

								@Override
								public void run() {
									try {
										fxmlLoader = FXMLLoader.load(dashboardcontroller.class.getResource("homepage.fxml"));
									}catch(Exception e)
									{
										e.printStackTrace();
									}
									fxmlLoader.setMaxHeight(10000);
									fxmlLoader.setMaxWidth(10000);
									basepan.setCenter(fxmlLoader);
									
								}
    					
    						});
    			}
    			};
    			homethread.start();
    	}
    }
    
	
	
	
	
	@FXML
	public void logout(ActionEvent event)
	{
		final Node source = (Node) event.getSource();
		final Stage stage =(Stage) source.getScene().getWindow();
		stage.close();
		main.getPrimaryStage().show();
		
	
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		bpane = basepan;
		final Tooltip tooltiplogout = new Tooltip();
		tooltiplogout.setText("Logout");
		tooltiplogout.setFont(Font.font("SanSerif",13));
		Tooltip.install(logout, tooltiplogout);
		TreeItem<String> root = new TreeItem<>("Root");
		TreeItem<String> home = new TreeItem<>("Home", homeicon);
		TreeItem<String> item = new TreeItem<>("Items Master", itemicon);
		TreeItem<String> addnew = new TreeItem<>("Add New" , addnewitem);
		TreeItem<String> location = new TreeItem<>("Location Master", locationicon);
		TreeItem<String> salesandpurchase = new TreeItem<>("Sales and Purchase Master", salesandpurchaseicon);
		TreeItem<String> report = new TreeItem<>("Report Master", reporticon);
		TreeItem<String> users = new TreeItem<>("Users Master", usersicon);
		root.getChildren().add(home);
		root.getChildren().add(item);
		root.getChildren().add(location);
		root.getChildren().add(salesandpurchase);
		root.getChildren().add(report);
		root.getChildren().add(users);
		item.getChildren().add(addnew);
		menu.setRoot(root);
		menu.setShowRoot(false);
		menu.getStyleClass().add("myTree");
		
		
		
		clock();
		
		
		try {
			
			fxmlLoader = FXMLLoader.load(getClass().getResource("homepage.fxml"));
			
			
		} catch (Exception e) {
			System.out.println("dashboard home error");
			e.printStackTrace();
		}
		
		fxmlLoader.setMaxHeight(10000);
		fxmlLoader.setMaxWidth(10000);
		basepan.setCenter(fxmlLoader);
		
		
		
	}
	public void itempress()
	{
		
	}
	}
