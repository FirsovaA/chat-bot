import java.io.FileWriter;

public class ErrLogger {
    private static String logfile = "logs";

    public static void log(Exception e) {
        System.out.println(">>>>>");
        e.printStackTrace();
        System.out.println("<<<<");
        try (FileWriter writer = new FileWriter(logfile)) {
            writer.append("well....");
        } catch (Exception ex) {
        }
    }
}
