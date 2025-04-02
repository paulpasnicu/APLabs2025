package org.example;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Command to add a new image to the repository.
 */
public class AddCommand implements Command {
    private ImageRepository repository;

    /**
     * Constructs an AddCommand with the given repository.
     *
     * @param repo the ImageRepository to add images to
     */
    public AddCommand(ImageRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the add command. Expects arguments: name, date (yyyy-MM-dd), tags (comma separated), filePath.
     *
     * @param args the arguments for adding an image
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: add <name> <date(yyyy-MM-dd)> <tags(comma separated)> <filePath>");
            return;
        }
        try {
            String name = args[0];
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(args[1]);
            List<String> tags = Arrays.asList(args[2].split(","));
            String filePath = args[3];
            ImageItem image = new ImageItem(name, date, tags, filePath);
            repository.addImage(image);
        } catch (Exception e) {
            System.out.println("Error adding image: " + e.getMessage());
        }
    }
}
