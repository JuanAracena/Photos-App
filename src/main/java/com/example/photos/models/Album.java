package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Models the user's albums for the User Account
 *
 * @author Juan Aracena
 * @author Brandon Valdberg
 */
public class Album implements Serializable{
    private String dirLocation;
    private String name;
    public ArrayList<Image> photoList;
    public static final long serialVersionUID = -7861188948798125734L;

    /**
     * 
     * @param dirLocation directory address
     * @param name Name of the album
     */
    public Album(String dirLocation, String name) {
        this.dirLocation = dirLocation;
        this.name = name;
        photoList = new ArrayList<Image>();
    }

    /**
     * 
     * @return directory address of the album
     */
    public String getDirLocation() {
        return this.dirLocation;
    }

    /**
     * 
     * @return name of the album
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param name name of album
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
