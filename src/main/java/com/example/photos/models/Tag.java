package app;

/**
 * Models the user's tags for the User Account
 *
 * @author Juan Aracena
 * @author Brandon Valdberg
 */
public class Tag {

    private String tagName;
    private String tagValue;
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param tagName name of the tag
     * @param tagValue value of the tag
     */
    public Tag(String tagName, String tagValue){
        this.tagName = tagName;
        this.tagValue = tagValue;
    }

    /**
     * 
     * @param tagName new name for the tag
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 
     * @param tagValue new tag value for the tag
     */
    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    /**
     * 
     * @return name of tag
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 
     * @return value of tag
     */
    public String getTagValue() {
        return tagValue;
    }

}
