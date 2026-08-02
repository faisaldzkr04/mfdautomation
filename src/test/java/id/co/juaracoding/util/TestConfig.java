package id.co.juaracoding.util;

/**
 * Central configuration untuk Selenium test.
 *
 * Nilai dapat diubah melalui system property sehingga test tidak
 * perlu melakukan hardcode configuration di class Test maupun Page Object.
 *
 * Contoh:
 * mvn clean test -Dbase.url=http://localhost:8080
 */
public final class TestConfig {

    private TestConfig() {
        // Prevent instantiation.
    }

    /**
     * URL utama aplikasi.
     */
    public static final String BASE_URL =
            System.getProperty("base.url", "http://localhost:8080");

    /**
     * Username valid untuk login.
     */
    public static final String VALID_USERNAME =
            System.getProperty("valid.username", "testuser");

    /**
     * Password valid untuk login.
     */
    public static final String VALID_PASSWORD =
            System.getProperty("valid.password", "Test@12345");
}