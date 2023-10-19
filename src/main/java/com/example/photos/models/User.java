package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Models the User Account
 *
 * @author Juan Aracena
 * @author Brandon Valdberg
 */
public class User implements Serializable{
    private String username;
    private ArrayList<Album> albumList;
    private static final long serialVersionUID = 1L;
    


    /**
     * Constructor that makes the User
     */
    public User() {
        this.username = "";
        this.albumList = new ArrayList<Album>();
    }

    /**
     * 
     * @param username username of the account
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * 
     * @return name of the account
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username new name of the account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return list of albums associated with the account
     */
    public ArrayList<Album> getAlbum() {
        return this.albumList;
    }

    public void addAlbum(String dirLoc, String name) {
        albumList.add(new Album(dirLoc, name));

    }

    

    @Override
    public String toString() {
        return username;
    }
}
