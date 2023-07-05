package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) throws IOException {
        var start = System.currentTimeMillis();
        boolean isParallel = args.length == 0 || args[0].equals("p");
        System.out.println(isParallel ? "Running in parallel mode..." : "Running in sequential mode...");
        try (var paths = Files.walk(Paths.get("/home/guerra/tmp/sample-files"))) {
            Stream<Path> stream;
            if (isParallel) {
                stream = paths.toList().parallelStream();
            } else {
                stream = paths.toList().stream();
            }
            var result = stream.filter(p -> Files.isRegularFile(p) && Files.isReadable(p)).map(Main::processFile).collect(Collectors.joining("\n"));
            System.out.println(result);
        }
        var delta = System.currentTimeMillis() - start;
        System.out.printf("Execution took %s ms.\n", delta);
    }

    /**
     * Processes the file of a given Path
     * Returns a report of the most frequent character of the file
     *
     * @param path Path of the file
     * @return String report of the most frequent character of the file
     */
    private static String processFile(Path path) {
        try {
            var maxEntry = Files.readString(path).chars()
                .mapToObj(c -> (char) c)
                .collect(groupingBy(x -> x, counting()))
                .entrySet().stream()
                .max(comparingByValue())
                .orElseThrow();
            return String.format("%s -> Char: %s - Freq: %d", path, maxEntry.getKey(), maxEntry.getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}