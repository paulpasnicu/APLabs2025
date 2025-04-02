package org.example;

import java.awt.Desktop;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of ImageItem objects and provides methods to add, remove,
 * update, display, and persist images.
 */
public class ImageRepository {
    private List<ImageItem> images = new ArrayList<>();

    /**
     * Adds an image to the repository.
     *
     * @param image the ImageItem to add
     */
    public void addImage(ImageItem image) {
        images.add(image);
        System.out.println("Image added: " + image.getName());
    }

    /**
     * Removes an image by name from the repository.
     *
     * @param name the name of the image to remove
     * @throws RepositoryException if the image is not found
     */
    public void removeImage(String name) throws RepositoryException {
        boolean removed = images.removeIf(img -> img.getName().equalsIgnoreCase(name));
        if (!removed) {
            throw new RepositoryException("Image not found: " + name);
        }
        System.out.println("Image removed: " + name);
    }

    /**
     * Updates an existing image identified by its name.
     *
     * @param name     the name of the image to update
     * @param newImage the new ImageItem data to replace the old image
     * @throws RepositoryException if the image is not found
     */
    public void updateImage(String name, ImageItem newImage) throws RepositoryException {
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).getName().equalsIgnoreCase(name)) {
                images.set(i, newImage);
                System.out.println("Image updated: " + name);
                return;
            }
        }
        throw new RepositoryException("Image not found: " + name);
    }

    /**
     * Finds and returns an image by its name.
     *
     * @param name the name of the image to find
     * @return the found ImageItem
     * @throws RepositoryException if the image is not found
     */
    public ImageItem findImage(String name) throws RepositoryException {
        return images.stream()
                .filter(img -> img.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Image not found: " + name));
    }

    /**
     * Returns the list of all images in the repository.
     *
     * @return list of ImageItem objects
     */
    public List<ImageItem> getAllImages() {
        return images;
    }

    /**
     * Displays an image on the screen using the Desktop class.
     *
     * @param name the name of the image to display
     * @throws RepositoryException if the image is not found
     * @throws IOException         if the file does not exist or cannot be opened
     */
    public void displayImage(String name) throws RepositoryException, IOException {
        ImageItem img = findImage(name);
        File file = new File(img.getFilePath());
        if (file.exists() && Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
            System.out.println("Displaying image: " + img.getName());
        } else {
            throw new IOException("File does not exist or Desktop not supported: " + img.getFilePath());
        }
    }

    /**
     * Saves the repository to a file using object serialization.
     * Uses NIO and buffered streams to write the data.
     *
     * @param fileName the file to save the repository to
     * @throws IOException if an I/O error occurs during writing
     */
    public void saveRepository(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (OutputStream fos = Files.newOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(images);
            System.out.println("Repository saved to " + fileName);
        }
    }

    /**
     * Loads the repository from a file using object deserialization.
     *
     * @param fileName the file to load the repository from
     * @throws IOException            if an I/O error occurs during reading
     * @throws ClassNotFoundException if the file content does not match the expected class
     */
    public void loadRepository(String fileName) throws IOException, ClassNotFoundException {
        Path path = Paths.get(fileName);
        try (InputStream fis = Files.newInputStream(path);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            images = (List<ImageItem>) in.readObject();
            System.out.println("Repository loaded from " + fileName);
        }
    }
}
