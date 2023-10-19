package view;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import app.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controller for admin fxml
 *
 * @author Juan Aracena
 * @author Brandon Valdberg
 */
public class adminScreenController implements Serializable{

    @FXML Button admin_button_listusers;
    @FXML Button admin_button_adduser;
    @FXML Button admin_button_deleteuser;
    @FXML Button admin_button_logout;
    @FXML Button admin_button_add;
    @FXML ListView<User> admin_listview;
    @FXML TextField admin_textfield_adduser;
    @FXML Text admin_text_adduserprompt;
    


    private ArrayList<User> userList = new ArrayList<User>();
    private ObservableList<User> list;
    private static final String storeDir = "photos46/data";
    private static final String storeFile = "userInfo.dat";
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private FXMLLoader loader = new FXMLLoader();
    private Pane root;
    private Scene scene;
    private Stage stage;

    
    /**
     * 
     * @param e ActionEvent gotten from clicking the a button.
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void listUsers(ActionEvent e) throws ClassNotFoundException, IOException {

        userList = readApp();
        list = FXCollections.observableArrayList(userList);
        for(int i = 0; i < userList.size(); i++) {
            System.out.println("\nInside user list:\n" + userList.get(i));

        }
        
        admin_listview.setItems(list);
        admin_listview.getSelectionModel().select(0);

    }
    
    /**
     * 
     * @param e ActionEvent gotten from clicking the a button.
     */
    public void addUser(ActionEvent e) {
       
        admin_text_adduserprompt.setDisable(false);
        admin_text_adduserprompt.setVisible(true);
        admin_button_add.setDisable(false);
        admin_button_add.setVisible(true);
        admin_textfield_adduser.setDisable(false);
        admin_textfield_adduser.setVisible(true);



    }

    /**
     * 
     * @param e ActionEvent gotten from clicking a button.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void addTextToList(ActionEvent e) throws IOException, ClassNotFoundException {
        User newUser = new User(admin_textfield_adduser.getText());
        userList.add(newUser);
        writeApp(userList);
        
        admin_text_adduserprompt.setDisable(true);
        admin_text_adduserprompt.setVisible(false);
        admin_textfield_adduser.setDisable(true);
        admin_textfield_adduser.setVisible(false);
        admin_button_add.setDisable(true);
        admin_button_add.setVisible(false);

        listUsers(e);
    }

    /**
     * 
     * @param e ActionEvent gotten from clicking the a button
     * @throws IOException
     */
    public void deleteUser(ActionEvent e) throws IOException {
        User selectedUser = admin_listview.getSelectionModel().getSelectedItem();
        admin_listview.getItems().remove(selectedUser);
        list.remove(selectedUser);
        userList.remove(selectedUser); 
        writeApp(userList);
    } 

    /**
     * 
     * @param e ActionEvent gotten from clicking the a button
     * @throws IOException
     */
    public void logOut(ActionEvent e) throws IOException {
        loader.setLocation(getClass().getResource("/view/login screen_nocancel.fxml"));
        root = (Pane) loader.load();
        stage = (Stage)(((Node)e.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 
     * @param gapp ArrayList<User> used for writing in userInfo.dat
     * @throws IOException
     */
    public static void writeApp(ArrayList<User> gapp)throws IOException {
        
        try{
            oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));

        }catch(EOFException o) {
                
        }
        oos.writeObject(gapp);
        
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
