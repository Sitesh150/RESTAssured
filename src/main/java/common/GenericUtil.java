package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

public final class GenericUtil {

    /**
     * @keyword Using volatile keyword gets the proper and refreshed value for all threads.
     */
    private volatile static GenericUtil genericUtil;

    /**
     * Restricted user to create a copy of this constructor
     *
     * @Throws IOException
     */
    private GenericUtil() throws IOException {
        if (genericUtil != null) {
            throw new RuntimeException((PropertyConfig.CANNOT_CREATE_COPY_OBJECT)
                    + genericUtil.getClass().getSimpleName());
        }
    }

    /**
     * @implNote Singleton Class -> Double check code for androidMobileUtils is initiated or not.
     */
    public static GenericUtil genericUtilInstance() throws IOException {

        if (genericUtil == null) {
            synchronized (GenericUtil.class) {
                if (genericUtil == null) {
                    genericUtil = new GenericUtil();
                }
            }
        }
        return genericUtil;
    }

    /**
     * @return inputStream
     * @Throws IOException
     * @method Getting the resource loaded
     */
    public InputStream getResources(String filePath) {
        InputStream inputStream;
        try {
            inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(filePath);
            if (Objects.nonNull(inputStream)) {
                return inputStream;
            }
            return Files.newInputStream(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("FileConstants.FILE_NOT_LOADED_ERROR_MESSAGE");
        }
    }

    /**
     * @return LogManager
     * @method Getting log to get the info, error and debug
     */
    public Logger getLog() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    /**
     * @implNote Stopping the logs
     */
    public void stopLog() throws IOException {
        LogManager.shutdown();
        GenericUtil.genericUtilInstance().getLog().info("Stopping logs");
    }

    /**
     * @ObjectMapper This provides functionality for reading and writing JSON, either to or from basic POJOs
     * @implNote Deserializes a JSON string to a Java object.
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * @return mapper.readValue ---> Deserializes a JSON string to a Java object.
     */
    public <T> T getJSONData(String jsonFilePath, Class<T> tClass) throws IOException {
        InputStream inputStream;
        GenericUtil.genericUtilInstance().getLog().info("FileConstants.LOADING_JSON_FILE");
        try {
            inputStream = GenericUtil.genericUtilInstance().getResources(jsonFilePath);
            return mapper.readValue(inputStream, tClass);
        } catch (IOException exception) {
            throw new RuntimeException("FileConstants.JSON_FILE_NOT_LOADED");
        }
    }
    public String getJsonData(String jsonPath) throws IOException {
        return Files.readString(Paths.get(jsonPath));
    }
}
