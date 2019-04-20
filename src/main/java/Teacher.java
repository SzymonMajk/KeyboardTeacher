import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Teacher {

    public void proceed(String lineFromFile) {
        BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
        Line parsed = new Line(lineFromFile);
        String userInput;

        try {
            System.out.println(lineFromFile);
            userInput = userReader.readLine();

            while (!parsed.compare(userInput)) {
                String message = String.format("Znaleziono błąd w słowie %s, dokładniej w znaku %d",
                        parsed.getFirstWrongWord(), parsed.getFirstErrorIndex());
                System.out.println(message);

                System.out.println(lineFromFile);
                userInput = userReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Please provide text file path as first argument.");
            System.exit(1);
        }

        String trainingFilePath = args[0];
        Instant start = Instant.now();

        try {
            Files.lines(Paths.get(trainingFilePath)).forEach(s -> new Teacher().proceed(s));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Instant finish = Instant.now();
        long executionDuration = Duration.between(start, finish).toMinutes();
        String finalMessage = String.format("Gratulacje! \nCałkowity czas = %d minut", executionDuration);
        System.out.println(finalMessage);
    }
}
