package com.example.aitool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileTools {

    private final Path workspace;

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

    public Path safePath(String unresolved) {
        Path resolved = workspace.resolve(unresolved).normalize();

        if(!resolved.startsWith(workspace)) {
            throw new IllegalArgumentException("Access denied: path is outside the workspace");
        }
        return resolved;
    }

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

    @Tool(description = "Create a file, or overwrite it if it exists, with the given content.")
    public String writeFile(

            @ToolParam(description = "Relative file path, e.g. 'portfolio/index.html'")
            String unresolvedFile,

            @ToolParam(description = "The full text content of the file")
            String content) {

        try {
            Path resolvedFile = safePath(unresolvedFile);

            Files.createDirectories(resolvedFile.getParent());
            Files.writeString(resolvedFile, content);

            return "File written successfully: " + unresolvedFile;
        }
        catch(Exception e) {
            return "Failed to write File: " + e.getMessage();
        }
    }

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


    @Tool(description = "List all files and folders inside a directory.")
    public String listFiles(
            @ToolParam(description = "Relative directory path, or '.' for the workspace root") String path) {
        try (Stream<Path> stream = Files.list(safePath(path))) {
            return stream
                    .map(p -> workspace.relativize(p).toString())
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Failed to list files: " + e.getMessage();
        }
    }
}

