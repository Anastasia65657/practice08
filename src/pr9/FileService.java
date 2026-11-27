package pr9;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileService {

    // Вспомогательный метод: создаёт каталог data/v18 и файл paths.txt, если их ещё нет
    public void initSampleFileIfMissing(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            return;
        }
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }
        List<String> samplePaths = List.of(
                "/var/log/nginx/",
                "/etc/hosts",
                "/home/user/documents/",
                "/usr/local/bin/java",
                "/opt/app/config/",
                "/tmp/backup_2026.tar.gz",
                "/srv/www/html/",
                "/root/.bashrc"
        );
        Files.write(filePath, samplePaths, StandardCharsets.UTF_8);
    }

    // Утилита копирования файла copy(src, dst) с проверкой существования
    public void copy(Path src, Path dst) throws IOException {
        if (Files.notExists(src) || !Files.isRegularFile(src)) {
            throw new NoSuchFileException("Исходный файл не найден: " + src);
        }
        if (dst.getParent() != null) {
            Files.createDirectories(dst.getParent());
        }
        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
    }

    // Подсчёт статистики входного файла: строки / слова / символы
    public FileStats calculateStats(Path path) throws IOException {
        if (Files.notExists(path)) {
            throw new NoSuchFileException("Файл для подсчёта статистики не найден: " + path);
        }
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        long linesCount = lines.size();
        long wordsCount = 0;
        long charsCount = 0;

        for (String line : lines) {
            charsCount += line.length();
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                wordsCount += trimmed.split("\\s+").length;
            }
        }
        return new FileStats(linesCount, wordsCount, charsCount);
    }

    // Фильтрация строк по правилу варианта (путь оканчивается на '/') -> запись в output.txt
    public List<String> filterPathsEndingWithSlash(Path src, Path dst) throws IOException {
        if (Files.notExists(src)) {
            throw new NoSuchFileException("Входной файл не найден: " + src);
        }
        List<String> lines = Files.readAllLines(src, StandardCharsets.UTF_8);
        List<String> filtered = new ArrayList<>();

        for (String line : lines) {
            if (line.endsWith("/")) {
                filtered.add(line);
            }
        }

        if (dst.getParent() != null) {
            Files.createDirectories(dst.getParent());
        }
        Files.write(dst, filtered, StandardCharsets.UTF_8);
        return filtered;
    }

    // Обход каталога (нерекурсивно): список файлов с расширением .txt
    public List<Path> listFilesByExtension(Path dir, String extension) throws IOException {
        if (Files.notExists(dir) || !Files.isDirectory(dir)) {
            throw new NotDirectoryException("Каталог не существует: " + dir);
        }
        List<Path> result = new ArrayList<>();
        try (Stream<Path> stream = Files.list(dir)) {
            for (Path entry : (Iterable<Path>) stream::iterator) {
                if (Files.isRegularFile(entry) && entry.getFileName().toString().endsWith(extension)) {
                    result.add(entry);
                }
            }
        }
        return result;
    }

    // Дополнительный рекурсивный обход каталога через Files.walk
    public List<Path> listFilesRecursive(Path dir, String extension) throws IOException {
        if (Files.notExists(dir) || !Files.isDirectory(dir)) {
            throw new NotDirectoryException("Каталог не существует: " + dir);
        }
        List<Path> result = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(dir)) {
            for (Path entry : (Iterable<Path>) stream::iterator) {
                if (Files.isRegularFile(entry) && entry.getFileName().toString().endsWith(extension)) {
                    result.add(entry);
                }
            }
        }
        return result;
    }
}