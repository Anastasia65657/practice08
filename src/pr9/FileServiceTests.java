package pr9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTests {

    @Test // Тест 1: Копирование файла и ошибка при отсутствии исходного файла
    public void testCopyFile(@TempDir Path tempDir) throws IOException {
        FileService service = new FileService();
        Path src = tempDir.resolve("source.txt");
        Path dst = tempDir.resolve("copy.txt");
        Path missing = tempDir.resolve("not_exists.txt");

        Files.writeString(src, "test content", StandardCharsets.UTF_8);

        service.copy(src, dst);
        assertTrue(Files.exists(dst));
        assertEquals("test content", Files.readString(dst, StandardCharsets.UTF_8));

        assertThrows(NoSuchFileException.class, () -> service.copy(missing, dst));
    }

    @Test // Тест 2: Подсчёт строк, слов и символов
    public void testCalculateStats(@TempDir Path tempDir) throws IOException {
        FileService service = new FileService();
        Path file = tempDir.resolve("stats.txt");
        Files.write(file, List.of("/var/log/", "/etc/hosts file"), StandardCharsets.UTF_8);

        FileStats stats = service.calculateStats(file);

        assertEquals(2, stats.getLinesCount());
        assertEquals(3, stats.getWordsCount()); // "/var/log/" (1) + "/etc/hosts" (2) + "file" (3)
        assertEquals(24, stats.getCharsCount()); // 9 + 15 = 24 символа
    }

    @Test // Тест 3: Фильтрация строк, оканчивающихся на '/'
    public void testFilterPathsEndingWithSlash(@TempDir Path tempDir) throws IOException {
        FileService service = new FileService();
        Path input = tempDir.resolve("paths.txt.txt");
        Path output = tempDir.resolve("output.txt");

        Files.write(input, List.of(
                "/usr/bin/",
                "/etc/passwd",
                "/home/alex/"
        ), StandardCharsets.UTF_8);

        List<String> filtered = service.filterPathsEndingWithSlash(input, output);

        assertEquals(2, filtered.size());
        assertEquals(List.of("/usr/bin/", "/home/alex/"), filtered);
        assertEquals(filtered, Files.readAllLines(output, StandardCharsets.UTF_8));
    }

    @Test // Тест 4: Поиск файлов с расширением .txt в каталоге
    public void testListFilesByExtension(@TempDir Path tempDir) throws IOException {
        FileService service = new FileService();
        Files.createFile(tempDir.resolve("a.txt"));
        Files.createFile(tempDir.resolve("b.txt"));
        Files.createFile(tempDir.resolve("c.csv"));

        List<Path> txtFiles = service.listFilesByExtension(tempDir, ".txt");

        assertEquals(2, txtFiles.size());
    }
}
