package pr9;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();

        Path workDir = Path.of("data", "v18");
        Path inputFile = workDir.resolve("paths.txt.txt");
        Path backupFile = workDir.resolve("paths_copy.txt");
        Path outputFile = workDir.resolve("output.txt");

        try {
            // Создаём data/v18/paths.txt.txt, если его ещё нет
            fileService.initSampleFileIfMissing(inputFile);

            // Копирование файла с проверкой существования
            System.out.println("=== 1. КОПИРОВАНИЕ ФАЙЛА ===");
            fileService.copy(inputFile, backupFile);
            System.out.println("Файл успешно скопирован в: " + backupFile);

            // Подсчёт статистики
            System.out.println("\n=== 2. СТАТИСТИКА ФАЙЛА paths.txt.txt ===");
            FileStats stats = fileService.calculateStats(inputFile);
            System.out.println(stats);

            // Фильтрация строк (путь оканчивается на '/') -> output.txt
            System.out.println("\n=== 3. ФИЛЬТРАЦИЯ (ПУТЬ ОКАНЧИВАЕТСЯ НА '/') ===");
            List<String> directories = fileService.filterPathsEndingWithSlash(inputFile, outputFile);
            System.out.println("Найдено директорий: " + directories.size() + " (записано в " + outputFile + ")");
            for (String dir : directories) {
                System.out.println("  -> " + dir);
            }

            // Обход каталога (файлы с расширением .txt)
            System.out.println("\n=== 4. ОБХОД КАТАЛОГА data/v18 (РАСШИРЕНИЕ .txt) ===");
            List<Path> txtFiles = fileService.listFilesByExtension(workDir, ".txt");
            System.out.println("Нерекурсивный обход (Files.list):");
            for (Path p : txtFiles) {
                System.out.println("  * " + p.getFileName());
            }

            List<Path> recursiveTxtFiles = fileService.listFilesRecursive(Path.of("data"), ".txt");
            System.out.println("Рекурсивный обход каталога data (Files.walk): найдено файлов — " + recursiveTxtFiles.size());

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}