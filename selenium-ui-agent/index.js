const report = require ("multiple-cucumber-html-reporter")

report.generate({
    jsonDir: "./target/cucumber-reports",
    reportPath: "./target/cucumber-reports",
    metadata: {
        browser: {
            name: "chrome",
            version: "60",
        },
        device: "Local test machine",
        platform: {
            name: "windows"
        },
    },
    customData: {
        title: "Run info",
        data: [
        {label: "Project", value: "Custom project"},
        {label: "Release", value: "1.2.3"},
        {label: "Cycle", value: "B11221.34321"},
        ]
    }
});