package kg.proquality.e2e.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class Utils {

    private Utils() {}

    private static final String RESOURCES_DIRECTORY = "src/testArchitecture/resources/";

    public static String loadSqlFromFile(String filePath) {
        try (FileInputStream fin = new FileInputStream(RESOURCES_DIRECTORY + filePath)) {
            return new String(fin.readAllBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Could not read resource: " + filePath, e);
        }
    }

}
