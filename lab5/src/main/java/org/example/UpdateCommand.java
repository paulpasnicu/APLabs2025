package org.example;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Command to update an existing image in the repository.
 */
public class UpdateCommand implements Command {
    private ImageRepository repository;

    /**
     * Constructs an UpdateCommand with the given repository.
     *
     * @param repo the ImageRepository to update images in
     */
    public UpdateCommand(ImageRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the update command. Expects arguments: existingName, newName, newDate(yyyy-MM-dd), newTags (comma separated), newFilePath.
     *
     * @param args the arguments for updating an image
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: update <existingName> <newName> <newDate(yyyy-MM-dd)> <newTags(comma separated)> <newFilePath>");
            return;
        }
        try {
            String existingName = args[0];
            String newName = args[1];
            Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(args[2]);
            List<String> newTags = Arrays.asList(args[3].split(","));
            String newFilePath = args[4];
            ImageItem newImage = new ImageItem(newName, newDate, newTags, newFilePath);
            repository.updateImage(existingName, newImage);
        } catch (Exception e) {
            System.out.println("Error updating image: " + e.getMessage());
        }
    }
}
