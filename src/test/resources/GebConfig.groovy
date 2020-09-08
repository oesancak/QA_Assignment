import geb.report.ScreenshotReporter
import geb.spock.GebReportingSpec
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import java.util.logging.Level
import java.util.logging.Logger

// Quiet HTML warnings
Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.INFO)
Logger.getLogger("org.openqa").setLevel(Level.INFO)

driver = {
    def OS = System.getProperty("os.name").toLowerCase()
    ChromeOptions options = new ChromeOptions()
    options.addArguments("--window-size=1680,1050")
    options.addArguments('--start-maximized')
    if (OS.startsWith('mac')) {
        System.setProperty('webdriver.chrome.driver', 'drivers/macos/chromedriver')
    } else if (OS.startsWith('linux')) {
        System.setProperty('webdriver.chrome.driver', '/usr/bin/chromedriver')

    }
    if (OS.startsWith('windows')) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2\\chromedriver.exe")
        new ChromeDriver(options)
    } else {
        throw new IllegalArgumentException("No Configuration for: " + OS)
    }
    // println 'Starting ChromeDriver from ' + System.getProperty('webdriver.chrome.driver') + ' with Options ' + options.asMap()
    // new ChromeDriver(options)
}

waiting {
    timeout = 30
    retryInterval = 3.0
    includeCauseMessage = true
}
atCheckWaiting = true

environments {
    testEnv {
        baseUrl = 'https://the-internet.herokuapp.com'
        // System.setProperty('geb.env.pwd', 'G.6Z7_ke?XV>w[f-dev')
    }

    reportingListener = new GebReportingSpec()
    reportsDir = 'reports/herokuapp-test-reports'


    println 'Finished evaluating GebConfig'

    baseUrl = "https://the-internet.herokuapp.com"
    reportsDir = new File("target/runtime_reports_dir")
    reporter = new ScreenshotReporter()

}
