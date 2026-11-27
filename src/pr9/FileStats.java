package pr9;

public class FileStats {
    private final long linesCount;
    private final long wordsCount;
    private final long charsCount;

    public FileStats(long linesCount, long wordsCount, long charsCount) {
        this.linesCount = linesCount;
        this.wordsCount = wordsCount;
        this.charsCount = charsCount;
    }

    public long getLinesCount() { return linesCount; }
    public long getWordsCount() { return wordsCount; }
    public long getCharsCount() { return charsCount; }

    @Override
    public String toString() {
        return String.format("Строк: %d | Слов: %d | Символов: %d",
                linesCount, wordsCount, charsCount);
    }
}