package application;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import application.itempagecontroller.item;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
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
    
    class item extends RecursiveTreeObject<item>{
    	StringProperty itemname;
    	StringProperty id;
    	StringProperty purity;
    	StringProperty location;
    	StringProperty weight;
    	StringProperty createdon;
    	
    	public item(String itemname,String id,String purity,String location,String weight,String createdon)
    	{
    		this.itemname = new SimpleStringProperty(itemname);
    		this.id = new SimpleStringProperty(id);
    		this.purity = new SimpleStringProperty(purity);
    		this.location = new SimpleStringProperty(location);
    		this.weight = new SimpleStringProperty(weight);
    		this.createdon = new SimpleStringProperty(createdon);
    	}
    }
    
    @FXML
    public void searchpress(ActionEvent event)
    {
    	itemlist.setPredicate(new Predicate<TreeItem<item>>()
    			{

					@Override
					public boolean test(TreeItem<item> cont) {
						Boolean flag = cont.getValue().id.getValue().contains(searchtext.getText()) || cont.getValue().itemname.getValue().contains(searchtext.getText()) || cont.getValue().purity.getValue().contains(searchtext.getText()) || cont.getValue().location.getValue().contains(searchtext.getText()) || cont.getValue().weight.getValue().contains(searchtext.getText()) || cont.getValue().createdon.getValue().contains(searchtext.getText());
						return flag;
					}
    		
    			});
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<item,String> itemcol = new JFXTreeTableColumn<>("Item Name");
		itemcol.setResizable(false);
		itemcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

				@Override
				public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
					
					return param.getValue().getValue().itemname;
				}
				});
		
		JFXTreeTableColumn<item,String> idcol = new JFXTreeTableColumn<>("ID");
		idcol.setResizable(false);
		idcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().id;
			}
			});
		
		JFXTreeTableColumn<item,String> puritycol = new JFXTreeTableColumn<>("Purity");
		puritycol.setResizable(false);
		puritycol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().purity;
			}
			});
		
		JFXTreeTableColumn<item,String> loccol = new JFXTreeTableColumn<>("Location");
		loccol.setResizable(false);
		loccol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().location;
			}
			});
		
		JFXTreeTableColumn<item,String> weightcol = new JFXTreeTableColumn<>("Gross Weight");
		weightcol.setResizable(false);
		weightcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().weight;
			}
			});
		
		JFXTreeTableColumn<item,String> createdoncol = new JFXTreeTableColumn<>("Created On");
		createdoncol.setResizable(false);
		createdoncol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<item, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<item, String> param) {
				
				return param.getValue().getValue().createdon;
			}
			});
		itempane.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,Number newValue) {
					idcol.setPrefWidth((double) newValue / 6);
					itemcol.setPrefWidth((double) newValue / 6);
					puritycol.setPrefWidth((double) newValue / 6);
					loccol.setPrefWidth((double) newValue / 6);
					weightcol.setPrefWidth((double) newValue / 6);
					createdoncol.setPrefWidth((double) newValue / 6);
					hbox.setPrefWidth((double) newValue);
				}	
				});
		ObservableList<item> items = FXCollections.observableArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gdlashmi?useSSL=false","root","");
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT id,product_name,Purity,location,gross_weight,Date(`date&time`) FROM `stocklist`");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				items.add(new item(rs.getString(2),String.valueOf(rs.getInt(1)),rs.getString(3),rs.getString(4),String.valueOf(rs.getDouble(5))+" Gram",String.valueOf(rs.getDate(6))));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final TreeItem<item> root = new RecursiveTreeItem<item>(items, RecursiveTreeObject::getChildren);
		itemlist.getColumns().setAll(idcol,itemcol,puritycol,loccol,weightcol,createdoncol);
		itemlist.setRoot(root);
		itemlist.setShowRoot(false);
		
	}

}
