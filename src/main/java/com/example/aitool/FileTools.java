package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * AI tools that let the model work with files, which is what turns the
 * chatbot into an agent that can build real websites.
 * <p>
 * All operations are restricted to a single "workspace" folder
 * ({@code generated-sites}). Every path coming from the model is checked by
 * {@link #safePath(String)} so it cannot read or write files outside that folder.
 * <p>
 * Tool methods return plain text messages (including error messages) instead
 * of throwing, so the model can read the error and try again.
 */
@Component
public class FileTools {

    /** Absolute path of the folder where all generated files are stored. */
    private final Path workspace;

    /**
     * Creates the tools and makes sure the {@code generated-sites}
     * workspace folder exists.
     *
     * @throws RuntimeException if the workspace folder cannot be created
     */
    public FileTools() {
        this.workspace = Paths.get("generated-sites")
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(workspace);
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to create workspace" + e);
        }
    }

    /**
     * Converts a relative path into a safe absolute path inside the workspace.
     * <p>
     * Protects against path traversal: a value like {@code ../../secret.txt}
     * resolves outside the workspace and is rejected.
     *
     * @param unresolved the relative path provided by the model
     * @return the normalized absolute path inside the workspace
     * @throws IllegalArgumentException if the path points outside the workspace
     */
    public Path safePath(String unresolved) {
        Path resolved = workspace.resolve(unresolved).normalize();

        // Reject anything that escapes the workspace folder
        if(!resolved.startsWith(workspace)) {
            throw new IllegalArgumentException("Access denied: path is outside the workspace");
        }
        return resolved;
    }

    /**
     * Creates a directory inside the workspace, including any missing parents.
     *
     * @param unresolved relative directory path, for example {@code portfolio}
     * @return a success message, or an error message if creation failed
     */
    @Tool(description = "Create a new directory inside the website workspace. " +
            "Parent directories are created automatically")
    public String createDirectory(
            @ToolParam(description = "Relative directory path, e.g. 'portfolio'")
            String unresolved) {

        try {
            Path resolved = safePath(unresolved);
            Files.createDirectories(resolved);
            return "Directory created: " + unresolved;
        }
        catch (Exception e) {
            return "Failed to create directory: " + e.getMessage();
        }
    }

    /**
     * Creates a file with the given content, or overwrites it if it already exists.
     *
     * @param unresolvedFile relative file path, for example {@code portfolio/index.html}
     * @param content        the full text content to write
     * @return a success message, or an error message if writing failed
     */
    @Tool(description = "Create a file, or overwrite it if it exists, with the given content.")
    public String writeFile(

            @ToolParam(description = "Relative file path, e.g. 'portfolio/index.html'")
            String unresolvedFile,

            @ToolParam(description = "The full text content of the file")
            String content) {

        try {
            Path resolvedFile = safePath(unresolvedFile);

            // Make sure the parent folder exists before writing
            Files.createDirectories(resolvedFile.getParent());
            Files.writeString(resolvedFile, content);

            return "File written successfully: " + unresolvedFile;
        }
        catch(Exception e) {
            return "Failed to write File: " + e.getMessage();
        }
    }

    /**
     * Reads a text file from the workspace.
     * The agent uses this to verify its work and to edit existing sites.
     *
     * @param unresolvedFile relative file path
     * @return the file content, or an error message if reading failed
     */
    @Tool(description = "Read and return the text content of a file.")
    public String readFile(
            @ToolParam(description = "Relative file path")
            String unresolvedFile) {

        try{
            Path resolvedFile = safePath(unresolvedFile);

            return Files.readString(resolvedFile);
        }
        catch (Exception e) {
            return "Failed to read File: " + e.getMessage();
        }
    }


    /**
     * Lists the files and folders inside a workspace directory.
     *
     * @param path relative directory path, or {@code .} for the workspace root
     * @return one entry per line (relative to the workspace), or an error message
     */
    @Tool(description = "List all files and folders inside a directory.")
    public String listFiles(
            @ToolParam(description = "Relative directory path, or '.' for the workspace root") String path) {
        // try-with-resources closes the directory stream automatically
        try (Stream<Path> stream = Files.list(safePath(path))) {
            return stream
                    .map(p -> workspace.relativize(p).toString())
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Failed to list files: " + e.getMessage();
        }
    }
}
