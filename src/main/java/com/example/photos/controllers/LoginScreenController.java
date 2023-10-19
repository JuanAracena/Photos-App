package view;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import app.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controller for login fxml
 *
 * @author Juan Aracena
 * @author Brandon Valdberg
 */
public class LoginScreenController implements Serializable{
    
    @FXML TextField login_field_user;
    @FXML TextField login_field_password;
    @FXML Button login_button_signin;
    @FXML Text login_usernameError;

    public FXMLLoader loader = new FXMLLoader();
    private Pane root;
    private Scene scene;
    private Stage stage;
    ArrayList<User> userList;
    User currentUser;
    private static final String storeDir = "photos46/data";
    private static final String storeFile = "userInfo.dat";
    private static ObjectInputStream ois;


    /**
     * 
     * @return true if the user entered is "admin".
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public boolean isAdmin() throws ClassNotFoundException, IOException {
    
        userList = readApp();

        for(int i = 0; i < userList.size(); i++) {
            User getUser = userList.get(i);

            if((login_field_user.getText()).equals(getUser.getUsername()) && (login_field_user.getText()).equals("admin")) {
                return true;
            }
        }
        
        return false;  
    }

    /**
     * 
     * @param e ActionEvent gotten from clicking the a button.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void goToAlbumScreen(ActionEvent e) throws IOException, ClassNotFoundException {
        
        if(isAdmin()) {
            loader.setLocation(getClass().getResource("/view/admin screen.fxml"));
            root = (Pane)loader.load();


        }else{

            if(isUser()) {
                loader.setLocation(getClass().getResource("/view/album library.fxml"));
                root = (Pane)loader.load();
                albumLibraryController albumController = loader.getController();
                System.out.println("Album starting");
                albumController.start();

            }else {
                login_usernameError.setVisible(true);
                
            }
        }        
        
        
        stage = (Stage)(((Node)e.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * 
     * @return rue if the user entered is "user".
     */
    public boolean isUser() {

        for(int i = 0; i < userList.size(); i++) {
            User getUser = userList.get(i);

            if(login_field_user.getText().equals(getUser.getUsername())) {
                currentUser = getUser;
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return ArrayList<User> with the list of users found in userInfo.dat
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ArrayList<User> readApp()throws IOException, ClassNotFoundException {
        
        ArrayList<User> gapp = new ArrayList<User>();

        try{

            ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator +storeFile));
            gapp =  (ArrayList<User>)ois.readObject();
            
            
        }catch(EOFException o) {
            
        }
        return gapp;
    }

}
