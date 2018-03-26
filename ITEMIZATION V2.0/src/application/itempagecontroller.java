package application;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import application.itempagecontroller.item;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class itempagecontroller implements Initializable{

    @FXML
    private AnchorPane itempane;

    @FXML
    private JFXTreeTableView<item> itemlist;
    
    @FXML
    private JFXTextField searchtext;
    
    @FXML
    private JFXButton searchbtn;
    
    @FXML
    private HBox hbox;
    
    @FXML
    private JFXComboBox<String> columncombo;
    
    @FXML
    private Label tabletitle;
    
    AnchorPane fxmlLoader;
    
    class item extends RecursiveTreeObject<item>{
    	StringProperty itemname;
    	StringProperty id;
    	StringProperty purity;
    	StringProperty location;
    	StringProperty weight;
    	StringProperty createdon;
    	StringProperty itemstatus;
    	
    	public item(String itemname,String id,String purity,String location,String weight,String createdon,String itemstatus)
    	{
    		this.itemname = new SimpleStringProperty(itemname);
    		this.id = new SimpleStringProperty(id);
    		this.purity = new SimpleStringProperty(purity);
    		this.location = new SimpleStringProperty(location);
    		this.weight = new SimpleStringProperty(weight);
    		this.createdon = new SimpleStringProperty(createdon);
    		this.itemstatus = new SimpleStringProperty(itemstatus);
    	}
    }
    
    @FXML
    void searchkeypress(KeyEvent event) {
    	if(event.getCode().equals(KeyCode.ENTER))
    	{
    	itemlist.setPredicate(new Predicate<TreeItem<item>>()
		{

			@Override
			public boolean test(TreeItem<item> cont) {
				String selected = columncombo.getValue().toString();
				Boolean flag = null;
				if(selected == "All")
				{
				flag = cont.getValue().id.getValue().contains(searchtext.getText()) || cont.getValue().itemname.getValue().contains(searchtext.getText()) || cont.getValue().purity.getValue().contains(searchtext.getText()) || cont.getValue().location.getValue().contains(searchtext.getText()) || cont.getValue().weight.getValue().contains(searchtext.getText()) || cont.getValue().createdon.getValue().contains(searchtext.getText()) || cont.getValue().itemstatus.getValue().contains(searchtext.getText());
				}else if(selected == "ID")
				{
					flag = cont.getValue().id.getValue().contains(searchtext.getText());
				}else if(selected == "Item Name")
				{
					flag = cont.getValue().itemname.getValue().contains(searchtext.getText());
				}else if(selected == "Purity")
				{
					flag = cont.getValue().purity.getValue().contains(searchtext.getText());
				}else if(selected == "Location")
				{
					flag = cont.getValue().location.getValue().contains(searchtext.getText());
				}else if(selected == "Gross weight")
				{
					flag = cont.getValue().weight.getValue().contains(searchtext.getText());
				}else if(selected == "Created On")
				{
					flag = cont.getValue().createdon.getValue().contains(searchtext.getText());
				}else if(selected == "Item Status")
				{
					flag = cont.getValue().itemstatus.getValue().contains(searchtext.getText());
				}
				return flag;
			}
	
		});
    	}else if(event.getCode().equals(KeyCode.BACK_SPACE))
    	{
    		if(searchtext.getText().toString().length() == 1)
    		{
    			itemlist.setPredicate(new Predicate<TreeItem<item>>()
    			{

    				@Override
    				public boolean test(TreeItem<item> cont) {
    					String selected = columncombo.getValue().toString();
    					Boolean flag = null;
    					if(selected == "All")
    					{
    					flag = cont.getValue().id.getValue().contains(searchtext.getText()) || cont.getValue().itemname.getValue().contains(searchtext.getText()) || cont.getValue().purity.getValue().contains(searchtext.getText()) || cont.getValue().location.getValue().contains(searchtext.getText()) || cont.getValue().weight.getValue().contains(searchtext.getText()) || cont.getValue().createdon.getValue().contains(searchtext.getText()) || cont.getValue().itemstatus.getValue().contains(searchtext.getText());
    					}else if(selected == "ID")
    					{
    						flag = cont.getValue().id.getValue().contains(searchtext.getText());
    					}else if(selected == "Item Name")
    					{
    						flag = cont.getValue().itemname.getValue().contains(searchtext.getText());
    					}else if(selected == "Purity")
    					{
    						flag = cont.getValue().purity.getValue().contains(searchtext.getText());
    					}else if(selected == "Location")
    					{
    						flag = cont.getValue().location.getValue().contains(searchtext.getText());
    					}else if(selected == "Gross weight")
    					{
    						flag = cont.getValue().weight.getValue().contains(searchtext.getText());
    					}else if(selected == "Created On")
    					{
    						flag = cont.getValue().createdon.getValue().contains(searchtext.getText());
    					}else if(selected == "Item Status")
						{
							flag = cont.getValue().itemstatus.getValue().contains(searchtext.getText());
						}
    					return flag;
    				}
    		
    			});    			
    		}
    	}
    }
    @FXML
    public void searchpress(ActionEvent event)
    {
    	itemlist.setPredicate(new Predicate<TreeItem<item>>()
    			{

					@Override
					public boolean test(TreeItem<item> cont) {
						String selected = columncombo.getValue().toString();
						Boolean flag = null;
						if(selected == "All")
						{
						flag = cont.getValue().id.getValue().contains(searchtext.getText()) || cont.getValue().itemname.getValue().contains(searchtext.getText()) || cont.getValue().purity.getValue().contains(searchtext.getText()) || cont.getValue().location.getValue().contains(searchtext.getText()) || cont.getValue().weight.getValue().contains(searchtext.getText()) || cont.getValue().createdon.getValue().contains(searchtext.getText()) || cont.getValue().itemstatus.getValue().contains(searchtext.getText());
						}else if(selected == "ID")
						{
							flag = cont.getValue().id.getValue().contains(searchtext.getText());
						}else if(selected == "Item Name")
						{
							flag = cont.getValue().itemname.getValue().contains(searchtext.getText());
						}else if(selected == "Purity")
						{
							flag = cont.getValue().purity.getValue().contains(searchtext.getText());
						}else if(selected == "Location")
						{
							flag = cont.getValue().location.getValue().contains(searchtext.getText());
						}else if(selected == "Gross weight")
						{
							flag = cont.getValue().weight.getValue().contains(searchtext.getText());
						}else if(selected == "Created On")
						{
							flag = cont.getValue().createdon.getValue().contains(searchtext.getText());
						}else if(selected == "Item Status")
						{
							flag = cont.getValue().itemstatus.getValue().contains(searchtext.getText());
						}
						return flag;
					}
    		
    			});
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> columnlist = FXCollections.observableArrayList("All","ID","Item Name","Purity","Location","Gross weight","Created On","Item Status");
		columncombo.setValue("All");
		columncombo.setItems(columnlist);
		JFXTreeTableColumn<item,String> itemcol = new JFXTreeTableColumn<>("Item Name");
		itemcol.setResizable(false);
		itemcol.setSortable(false);
		itemcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

				@Override
				public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
					
					return param.getValue().getValue().itemname;
				}
				});
		
		JFXTreeTableColumn<item,String> idcol = new JFXTreeTableColumn<>("ID");
		idcol.setResizable(false);
		idcol.setSortable(false);
		idcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().id;
			}
			});
		
		JFXTreeTableColumn<item,String> puritycol = new JFXTreeTableColumn<>("Purity");
		puritycol.setResizable(false);
		puritycol.setSortable(false);
		puritycol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().purity;
			}
			});
		
		JFXTreeTableColumn<item,String> loccol = new JFXTreeTableColumn<>("Location");
		loccol.setResizable(false);
		loccol.setSortable(false);
		loccol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().location;
			}
			});
		
		JFXTreeTableColumn<item,String> weightcol = new JFXTreeTableColumn<>("Gross Weight");
		weightcol.setResizable(false);
		weightcol.setSortable(false);
		weightcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().weight;
			}
			});
		
		JFXTreeTableColumn<item,String> createdoncol = new JFXTreeTableColumn<>("Created On");
		createdoncol.setResizable(false);
		createdoncol.setSortable(false);
		createdoncol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().createdon;
			}
			});
		
		JFXTreeTableColumn<item,String> itemstatuscol = new JFXTreeTableColumn<>("Item Status");
		itemstatuscol.setResizable(false);
		itemstatuscol.setSortable(false);
		itemstatuscol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().itemstatus;
			}
			});
		itempane.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {
					idcol.setPrefWidth(((double) newValue / 7) - 15);
					itemcol.setPrefWidth((double) newValue / 7);
					puritycol.setPrefWidth((double) newValue / 7);
					loccol.setPrefWidth((double) newValue / 7);
					weightcol.setPrefWidth((double) newValue / 7);
					createdoncol.setPrefWidth((double) newValue / 7);
					itemstatuscol.setPrefWidth((double)newValue / 7);
					hbox.setPrefWidth((double) newValue);
				}	
				});
		ObservableList<item> items = FXCollections.observableArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gdlashmi?useSSL=false","root","");
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT id,product_name,Purity,location,gross_weight,Date(`date&time`),if(flag = 1, \"In Stock\", \"Sold Out\") `Item Status` from stocklist");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				items.add(new item(rs.getString(2),String.valueOf(rs.getInt(1)),rs.getString(3),rs.getString(4),String.valueOf(rs.getDouble(5))+" Gram",String.valueOf(rs.getDate(6)),rs.getString(7)));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final TreeItem<item> root = new RecursiveTreeItem<item>(items, RecursiveTreeObject::getChildren);
		itemlist.getColumns().setAll(idcol,itemcol,puritycol,loccol,weightcol,createdoncol,itemstatuscol);
		itemlist.setRoot(root);
		itemlist.setShowRoot(false);
		
	}
	
	@FXML
	public void addnewitempress()
	{
		Thread newitemthread = new Thread() {
			public void run()
			{
				Platform.runLater(new Runnable()
						{

							@Override
							public void run() {
								try {
					    			fxmlLoader = FXMLLoader.load(getClass().getResource("addnewitempage.fxml"));
					    			
					    		}catch(Exception e)
					    		{
					    			System.out.println("hai");
					    			e.printStackTrace();
					    		}
					    		fxmlLoader.setMaxHeight(10000);
					    		fxmlLoader.setMaxWidth(10000);
					    		dashboardcontroller.bpane.setCenter(fxmlLoader);
							}
					
						});
			}
		};
		newitemthread.start();
	}

}
