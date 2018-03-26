package application;


import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class logincontroller implements EventHandler<KeyEvent> {
	
	@FXML
    private JFXTextField uname;

	@FXML
    public JFXPasswordField pass;
	
	
	@FXML
    private JFXCheckBox show_pass;
	
	 @FXML
	private JFXTextField pass_hidden;
	 
	    @FXML
	    private JFXButton Login_btn;

	    @FXML
	    private ImageView minimize;
    
	    
	    @FXML
	    void mini() {
	    	Stage s = main.getPrimaryStage();
	    	s.setIconified(true);
	    }

	@FXML
	private void keypress(KeyEvent e)
	{
		if(e.getCode().equals(KeyCode.ENTER))
		{
			String username = uname.getText().toString();
			String password;
			if(show_pass.isSelected())
			{
				password = pass_hidden.getText().toString();
			}
			else
			{
				password = pass.getText().toString();
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gdlashmi?useSSL=false","root","");
				PreparedStatement pst = (PreparedStatement) con.prepareStatement("Select uname,pass,rights from login where uname = ? and pass =?");
				pst.setString(1, username);
				pst.setString(2, password);
				ResultSet rs= pst.executeQuery();
				if(rs.next())
				{
					String rights = rs.getString(3).toString();
					if(rights.equals("admin"))
					{
						try {
					
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
						Parent root1 = fxmlLoader.load();
						Stage stage = new Stage();
						stage.setTitle("ITEMIZATION");
						Image logo = new Image("images/itemization_logo.png");
						stage.getIcons().add(logo);
						stage.setScene(new Scene(root1));
						stage.setMaximized(true);
						stage.show();
						}catch(Exception ee)
						{
							JOptionPane.showMessageDialog(null, "Something Went Wrong");
							System.out.println("loginpage error");
							ee.printStackTrace();
						}
						main.getPrimaryStage().close();
					}
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Username/Password is wrong");
				}
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	@FXML
	private void logingo(ActionEvent event)
	{
		String username = uname.getText().toString();
		String password;
		if(show_pass.isSelected())
		{
			password = pass_hidden.getText().toString();
		}
		else
		{
			password = pass.getText().toString();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gdlashmi?useSSL=false","root","");
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("Select uname,pass,rights from login where uname = ? and pass =?");
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs= pst.executeQuery();
			if(rs.next())
			{
				String rights = rs.getString(3).toString();
				if(rights.equals("admin"))
				{
					try {
				
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
					Parent root1 = fxmlLoader.load();
					Stage stage = new Stage();
					stage.setTitle("Itemization");
					stage.setScene(new Scene(root1));
					stage.show();
					}catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Something Went Wrong");
					}
					main.getPrimaryStage().close();
				}
			}else
			{
				JOptionPane.showMessageDialog(null, "Username/Password is wrong");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void exit()
	{
		System.exit(0);
	}
	
	@FXML
	private void show_password()
	{
		if(show_pass.isSelected())
		{
			pass.setDisable(true);
			pass.setPromptText("");
			pass_hidden.setDisable(false);
			pass_hidden.setPromptText("Password");
			pass_hidden.setText(pass.getText().toString());
			pass.setText("");
		}
		else
		{
			pass.setText(pass_hidden.getText().toString());
			pass.setDisable(false);
			pass.setPromptText("Password");
			pass_hidden.setDisable(true);
			pass_hidden.setPromptText("");
			pass_hidden.setText("");
			
		}
	}

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
	}
	
	
}
