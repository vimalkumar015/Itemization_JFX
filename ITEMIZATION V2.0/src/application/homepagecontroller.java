package application;

import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class homepagecontroller implements Initializable{

    @FXML
    private AnchorPane pane;
    
    @FXML
    private static HBox hbox1;
    
    @FXML
    private StackPane stcpane1;
    
    @FXML
    private StackPane stkpane2;
    
    @FXML
    private HBox hbox2;

    @FXML
    private JFXButton item;

    @FXML
    private JFXButton sales_and_purchase;

    @FXML
    private JFXButton location_master;

    @FXML
    private JFXButton report_master;

    @FXML
    private JFXButton users_manager;
    
    @FXML
    public TilePane tilpane;
    
    @FXML
    public LineChart<String,Double> graph;
    
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
   AnchorPane fxmlLoader;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pane.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {
				
			}
	
		});
		XYChart.Series<String,Double> series = new XYChart.Series<>();
		XYChart.Series<String, Double> series2 = new XYChart.Series<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gdlashmi?useSSL=false","root","");
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT MonthName(`soldoutdate`.`sold date`) AS Date ,SUM(gross_weight) as `Total Weight` FROM soldoutdate JOIN stocklist WHERE soldoutdate.id = stocklist.id and Year(`sold date`) = Year(CURRENT_DATE) AND (stocklist.Purity = \"Regular\" OR stocklist.Purity = \"KDM\") GROUP BY Month(`soldoutdate`.`sold date`)");
			PreparedStatement pst1 = (PreparedStatement) con.prepareStatement("SELECT MonthName(`soldoutdate`.`sold date`) AS Date ,SUM(`stocklist`.`gross_weight`) as `Total Weight` FROM soldoutdate JOIN stocklist WHERE soldoutdate.id = stocklist.id and Year(`sold date`) = Year(CURRENT_DATE) AND (stocklist.Purity = \"92M-Silver\" OR stocklist.Purity = \"Silver\") GROUP BY Month(`soldoutdate`.`sold date`)");
			ResultSet rs = pst.executeQuery();
			ResultSet rs1 = pst1.executeQuery();
			while(rs.next())
			{
				
				series.getData().add(new XYChart.Data<>(rs.getString(1),Double.valueOf(rs.getDouble(2))));
				series.setName("Gold");
				
			}
			while(rs1.next())
			{
				//series2.getData().add(new XYChart.Data<>(rs1.getString(1),Integer.valueOf(rs1.getInt(2))));
				series2.getData().add(new XYChart.Data<>(rs1.getString(1),Double.valueOf(rs1.getDouble(2))));
				series2.setName("Silver");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		graph.getData().add(series);
		graph.getData().add(series2);
		
	}
    
	public void repostiongraph()
	{
		graph.setLayoutY(tilpane.getPrefHeight()+10);
	}
   
	@FXML
	public void itempress()
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
							fxmlLoader = FXMLLoader.load(dashboardcontroller.class.getResource("itempage.fxml"));
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						fxmlLoader.setMaxHeight(10000);
						fxmlLoader.setMaxWidth(10000);
						dashboardcontroller.bpane.setCenter(fxmlLoader);
						
					}
			
				});
	}
	};
	homethread.start();
}
}