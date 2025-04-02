package org.example;

/**
 * Command to load the image repository from a file.
 */
public class LoadCommand implements Command {
    private ImageRepository repository;

    /**
     * Constructs a LoadCommand with the given repository.
     *
     * @param repo the ImageRepository to load images into
     */
    public LoadCommand(ImageRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the load command. Expects a single argument: fileName.
     *
     * @param args the arguments for loading the repository
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: load <fileName>");
            return;
        }
        try {
            repository.loadRepository(args[0]);
        } catch (Exception e) {
            System.out.println("Error loading repository: " + e.getMessage());
        }
    }
}
