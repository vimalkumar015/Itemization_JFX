package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class dashboardcontroller implements Initializable {

    @FXML
    private ImageView logout;
    
    @FXML
    public BorderPane basepan;

    @FXML
    private JFXButton home;

    @FXML
    private JFXButton item_master;

    @FXML
    private JFXButton location_master;

    @FXML
    private JFXButton sales_and_purchase_master;

    @FXML
    private JFXButton report_master;

    @FXML
    private JFXButton users_manager;
    
    @FXML
    private Label time;
    
    AnchorPane fxmlLoader;
    
    public void clock()
	{
		Thread clock = new Thread()
	{
			public void run()
			{
				try {
					while(true)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy hh:mm:ss a");
						Calendar calendar = new GregorianCalendar();
						
					
					Platform.runLater(new Runnable()
							{

								@Override
								public void run() {
									time.setText(sdf.format(calendar.getTime()));
									
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
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		final Tooltip tooltiplogout = new Tooltip();
		tooltiplogout.setText("Logout");
		tooltiplogout.setFont(Font.font("SanSerif",13));
		Tooltip.install(logout, tooltiplogout);
		
		clock();
		
		
		try {
			
			fxmlLoader = FXMLLoader.load(getClass().getResource("homepage.fxml"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fxmlLoader.setMaxHeight(10000);
		fxmlLoader.setMaxWidth(10000);
		basepan.setCenter(fxmlLoader);
		
		
	}

	@FXML
	public void itempress(ActionEvent event)
	{
		try {
			fxmlLoader = FXMLLoader.load(getClass().getResource("itempage.fxml"));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		fxmlLoader.setMaxHeight(10000);
		fxmlLoader.setMaxWidth(10000);
		basepan.setCenter(fxmlLoader);
	}
	
	@FXML
	public void logout(MouseEvent event)
	{
		final Node source = (Node) event.getSource();
		final Stage stage =(Stage) source.getScene().getWindow();
		stage.close();
		main.getPrimaryStage().show();
		
	
	}
	
	@FXML
	public void homepress(ActionEvent event)
	{
try {
			
			fxmlLoader = FXMLLoader.load(getClass().getResource("homepage.fxml"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fxmlLoader.setMaxHeight(10000);
		fxmlLoader.setMaxWidth(10000);
		basepan.setCenter(fxmlLoader);
		//System.out.println("home pressed");
	}

	
}
