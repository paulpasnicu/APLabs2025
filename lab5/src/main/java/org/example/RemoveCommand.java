package org.example;

/**
 * Command to remove an image from the repository.
 */
public class RemoveCommand implements Command {
    private ImageRepository repository;

    /**
     * Constructs a RemoveCommand with the given repository.
     *
     * @param repo the ImageRepository from which to remove images
     */
    public RemoveCommand(ImageRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the remove command. Expects a single argument: name.
     *
     * @param args the arguments for removing an image
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: remove <name>");
            return;
        }
        try {
            repository.removeImage(args[0]);
        } catch (RepositoryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
