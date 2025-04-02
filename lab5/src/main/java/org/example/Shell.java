package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple command shell to interact with the image repository.
 */
public class Shell {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Constructs the Shell and registers available commands.
     *
     * @param repository the ImageRepository to operate on
     */
    public Shell(ImageRepository repository) {
        commands.put("add", new AddCommand(repository));
        commands.put("remove", new RemoveCommand(repository));
        commands.put("update", new UpdateCommand(repository));
        commands.put("load", new LoadCommand(repository));
        commands.put("save", new SaveCommand(repository));
        commands.put("report", new ReportCommand(repository));
    }

    /**
     * Runs the shell, reading and executing commands until the user exits.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Image Repository Shell. Enter commands (or 'exit' to quit):");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                break;
            }
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String cmdName = parts[0].toLowerCase();
            Command cmd = commands.get(cmdName);
            if (cmd == null) {
                System.out.println("Unknown command: " + cmdName);
                continue;
            }
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);
            cmd.execute(args);
        }
        scanner.close();
    }
}

