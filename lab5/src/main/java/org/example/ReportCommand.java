package org.example;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command to generate an HTML report of the image repository and open it in the default browser.
 */
public class ReportCommand implements Command {
    private ImageRepository repository;

    /**
     * Constructs a ReportCommand with the given repository.
     *
     * @param repo the ImageRepository to report on
     */
    public ReportCommand(ImageRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the report command. Generates an HTML file and opens it using the default browser.
     *
     * @param args no arguments expected
     */
    @Override
    public void execute(String[] args) {
        String reportFile = "report.html";
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Image Repository Report</title></head><body>\n");
        html.append("<h1>Image Repository Report</h1>\n<ul>\n");
        for (ImageItem image : repository.getAllImages()) {
            html.append("<li>")
                    .append(image.toString())
                    .append("</li>\n");
        }
        html.append("</ul>\n</body></html>");

        Path path = Paths.get(reportFile);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(html.toString());
            System.out.println("Report generated: " + reportFile);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(path.toUri());
            }
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}
