import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Teacher {

    private String userAction(String lineFromFile) {
        BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
        String userOutput = "";

        try {
            System.out.println(lineFromFile);
            userOutput = userReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userOutput;
    }

    private void proceed(String lineFromFile) {
        Line parsed = new Line(lineFromFile);
        String userInput = userAction(lineFromFile);

        while (!parsed.compare(userInput)) {
            String message = String.format("Znaleziono błąd w %d znaku, w słowie %s",
                    parsed.getFirstErrorIndex(), parsed.getFirstWrongWord());
            System.out.println(message);
            userInput = userAction(lineFromFile);
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
