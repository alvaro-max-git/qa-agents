package core;

public class TestConfig {
    private static final String DEFAULT_BASE_URL = "https://qaplayground.dev";
    private static final long DEFAULT_IMPLICIT_WAIT = 0L;      // no usamos implícitas.
    private static final long DEFAULT_EXPLICIT_WAIT = 10L;     // segundos
    private static final String DEFAULT_BROWSER = "chrome";    // extensible a otros navegadres

    public String baseUrl() {
        return get("BASE_URL", "baseUrl", DEFAULT_BASE_URL);
    }

    public long implicitWaitSeconds() {
        return Long.parseLong(get("IMPLICIT_WAIT", "implicitWait", String.valueOf(DEFAULT_IMPLICIT_WAIT)));
    }

    public long explicitWaitSeconds() {
        return Long.parseLong(get("EXPLICIT_WAIT", "explicitWait", String.valueOf(DEFAULT_EXPLICIT_WAIT)));
    }

    public String browser() {
        return get("BROWSER", "browser", DEFAULT_BROWSER).toLowerCase();
    }

    private String get(String envKey, String sysKey, String fallback) {
        String env = System.getenv(envKey);
        if (env != null && !env.isBlank()) return env;
        String sys = System.getProperty(sysKey);
        if (sys != null && !sys.isBlank()) return sys;
        return fallback;
    }
}
