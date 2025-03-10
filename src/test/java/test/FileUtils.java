package test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
	public static void clearFolder(String folderPath) {
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            System.out.println("Folder does not exist: " + folderPath);
            return;
        }

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            System.out.println("Folder cleared: " + folderPath);
        } catch (IOException e) {
            System.err.println("Failed to clear folder: " + e.getMessage());
        }
    }
}
