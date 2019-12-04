import org.apache.log4j.Logger;

public class MyLogger {
    public static void log(Class cls, Throwable err){
        Logger logger = Logger.getLogger(cls);
        logger.error("", err);
    }
}
