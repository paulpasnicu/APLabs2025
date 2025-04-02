package org.example;

/**
 * Command to save the image repository to a file.
 */
public class SaveCommand implements Command {
    private ImageRepository repository;

    /**
     * Constructs a SaveCommand with the given repository.
     *
     * @param repo the ImageRepository to save
     */
    public SaveCommand(ImageRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the save command. Expects a single argument: fileName.
     *
     * @param args the arguments for saving the repository
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: save <fileName>");
            return;
        }
        try {
            repository.saveRepository(args[0]);
        } catch (Exception e) {
            System.out.println("Error saving repository: " + e.getMessage());
        }
    }
}
