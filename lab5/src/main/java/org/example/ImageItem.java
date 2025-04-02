package org.example;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents an image with a name, date, tags, and file system path.
 */
public class ImageItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Date date;
    private List<String> tags;
    private String filePath; // location in the file system

    /**
     * Constructs an ImageItem with the specified attributes.
     *
     * @param name     the name of the image
     * @param date     the date associated with the image
     * @param tags     a list of tags for the image
     * @param filePath the file system path of the image
     */
    public ImageItem(String name, Date date, List<String> tags, String filePath) {
        this.name = name;
        this.date = date;
        this.tags = tags;
        this.filePath = filePath;
    }

    /**
     * Returns the name of the image.
     *
     * @return the image name
     */
    public String getName() { return name; }

    /**
     * Returns the date associated with the image.
     *
     * @return the image date
     */
    public Date getDate() { return date; }

    /**
     * Returns the list of tags associated with the image.
     *
     * @return the image tags
     */
    public List<String> getTags() { return tags; }

    /**
     * Returns the file system path of the image.
     *
     * @return the file path
     */
    public String getFilePath() { return filePath; }

    /**
     * Sets the name of the image.
     *
     * @param name the new image name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the date of the image.
     *
     * @param date the new image date
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Sets the tags for the image.
     *
     * @param tags the new list of tags
     */
    public void setTags(List<String> tags) { this.tags = tags; }

    /**
     * Sets the file system path of the image.
     *
     * @param filePath the new file path
     */
    public void setFilePath(String filePath) { this.filePath = filePath; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("Image Name: " + name + "\n");
        sb.append("Date: " + sdf.format(date) + "\n");
        sb.append("Tags: " + tags + "\n");
        sb.append("File Path: " + filePath + "\n");
        return sb.toString();
    }
}
