package view;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import app.Album;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
public class albumLibraryController{
    
    @FXML Button album_button_create;
    @FXML Button album_button_rename;
    @FXML Button album_button_delete;
    @FXML Button album_button_logout;
    @FXML Button album_button_setName;
    @FXML Button album_button_searchfile;
    @FXML TextArea album_textfield;
    @FXML ListView<String> album_listview;
    @FXML Text album_createalbumprompt;
    @FXML Button album_button_searchfiledir;
    
    
    private FXMLLoader loader = new FXMLLoader();
    private Pane root;
    private Scene scene;
    private Stage stage;
    private String name;
    private String albumDir;
    private ArrayList<Album> albumList = new ArrayList<Album>();
    private ObservableList<String> list;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static final String storeDir = "photos46/data";
    private static final String storeFile = "albumInfo.dat";
    
    

    
/**
 * Start method that launches when the user navigatees to the album library.
 * @throws ClassNotFoundException
 * @throws IOException
 */
    public void start() throws ClassNotFoundException, IOException {
        ArrayList<String> albumNames = new ArrayList<String>();
        for(int i = 0; i < albumList.size(); i++) {
            Album getAlbum = albumList.get(i);

            System.out.println("\nInside album list:\n" + getAlbum.getName());
            albumNames.add(getAlbum.getName());

        }
        list = FXCollections.observableArrayList(albumNames);
        album_listview.setItems(list);
        album_listview.getSelectionModel().select(0);
    }

    public void createAlbum(ActionEvent e) throws ClassNotFoundException, IOException {
        
        albumList = readApp();
        
        album_textfield.setPromptText("name");
        album_createalbumprompt.setDisable(false);
        album_createalbumprompt.setVisible(true);
        album_textfield.setDisable(false);
        album_textfield.setVisible(true);
        album_button_searchfile.setDisable(false);
        album_button_searchfile.setVisible(true);

        

    }

    /**
     * Changes objects album library.fxml
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void promptAlbum() throws ClassNotFoundException, IOException {
        name = album_textfield.getText();

        album_button_searchfile.setDisable(true);
        album_button_searchfile.setVisible(false);
        album_button_searchfiledir.setDisable(false);
        album_button_searchfiledir.setVisible(true);
        album_createalbumprompt.setText("Enter directory location:");
        album_createalbumprompt.setX(-30);
        album_textfield.clear();
        album_textfield.setPromptText("File location");
        
        
        
    }

    /**
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void promptAlbumDir() throws ClassNotFoundException, IOException {
        
        albumDir = album_textfield.getText();
        addAlbum();

    }

    /**
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void addAlbum() throws IOException, ClassNotFoundException {
        Album newAlbum = new Album(albumDir, name);
        albumList.add(newAlbum);
        writeApp(albumList);

        album_createalbumprompt.setX(0);
        album_createalbumprompt.setText("Enter album name:");
        album_createalbumprompt.setDisable(true);
        album_createalbumprompt.setVisible(false);
        album_textfield.setDisable(true);
        album_textfield.setVisible(false);
        album_button_searchfiledir.setDisable(true);
        album_button_searchfiledir.setVisible(false);

        start();

    }

     /**
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void renameAlbum(){
        album_createalbumprompt.setText("Enter album name:");
        album_textfield.setPromptText("name"); 
        album_createalbumprompt.setDisable(false);
        album_createalbumprompt.setVisible(true);
        album_button_setName.setDisable(false);
        album_button_setName.setVisible(true);
        album_textfield.setDisable(false);
        album_textfield.setVisible(true);
        album_textfield.clear();

    }

    /**
     * Renames the selected album.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void fileRenamed() throws IOException, ClassNotFoundException {
        String currentAlbum;
        Album albumIndex;
        currentAlbum = album_listview.getSelectionModel().getSelectedItem();
         
        
        for(int i = 0; i < albumList.size(); i++) {
            
            albumIndex = albumList.get(i);

            if(albumIndex.getName().equals(currentAlbum)){
                albumIndex.setName(album_textfield.getText());
            }

        }
        writeApp(albumList);

        album_createalbumprompt.setDisable(true);
        album_createalbumprompt.setVisible(false);
        album_button_setName.setDisable(true);
        album_button_setName.setVisible(false);
        album_textfield.setDisable(true);
        album_textfield.setVisible(false);
        album_textfield.clear();

        start();
    }

    /**
     * Deletes selected album
     * @throws IOException
     */
    public void deleteAlbum() throws IOException {
        
        String albumName = album_listview.getSelectionModel().getSelectedItem();

        for(int i = 0; i < albumList.size(); i++) {

            if(albumList.get(i).getName().equals(albumName)) {
                album_listview.getItems().remove(albumName);
                list.remove(albumName);
                albumList.remove(i);
            }
        }

        writeApp(albumList);
         
    }

    /**
     * Navigates back to the login screen.
     * @param e
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
     * @param albumList2 ArrayList<Album> used to write on albumInfo.dat.
     * @throws IOException
     */
    public static void writeApp(ArrayList<Album> albumList2)throws IOException {
        
        try{
            oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));

        }catch(EOFException o) {
                
        }
        oos.writeObject(albumList2);
        
    }

    /**
     * 
     * @return ArrayList<User> with the list of users found in userInfo.dat
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ArrayList<Album> readApp()throws IOException, ClassNotFoundException {
        
        ArrayList<Album> gapp = new ArrayList<Album>();

        try{

            ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator +storeFile));
            gapp =  (ArrayList<Album>)ois.readObject();
            
            
        }catch(EOFException o) {
            
        }
        return gapp;
    }
    
    
    
}
