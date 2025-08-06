package common;

public enum PropertyConfig {

    /**
     * @File Paths
     * Error and Exception Messages
     */
    JSON_FILE_PATH("src/test/resources/JSONFiles/CreateLocation.json"),

    /**
     * @Error&Info
     * Error and Exception Messages
     */
    CANNOT_CREATE_COPY_OBJECT("Cannot be created the another copy of this object");


    private final String _value;

    PropertyConfig(String value) {
        _value = value;
    }

    @Override
    public String toString() {
        return _value;
    }
}
