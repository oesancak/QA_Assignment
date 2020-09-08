package scenarios

import geb.Browser
import geb.spock.GebReportingSpec
import herokuapp.pages.BrokenImages
import org.junit.Rule
import org.junit.rules.TestName
import org.openqa.selenium.chrome.ChromeDriver

class HerokuappTest extends GebReportingSpec {

    @Rule
    TestName name = new TestName()
//    static Logger logger = LoggerFactory.getLogger(HerokuappTest.class)

    def setupSpec() {
        //      logger.info('\n**************\n' + TestName + 'is running')
    }

/*    void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2\\chromedriver.exe")
        def browser = new Browser(driver: new ChromeDriver())
        setBaseUrl('https://the-internet.herokuapp.com')
    }*/

    def 'Broken Images exist'() {
        when: 'we call the Broken Images Page'
        setBaseUrl(getBaseUrl())
        to BrokenImages
        then: 'we are on the right page and we see 2 broken images out of 3'
        BrokenImages page = at BrokenImages
        page.containsBrokenImages()

    }

    def 'Dummy Test'() {
        //  given: 'two numbers to add'
        when: 'a + b'
        int a = 3
        int b = 4
        a + b
        then: 'it must be 7'
        assert a + b == 7
        print(a + b)

    }

    def 'Dummy2'() {
        //  given: 'taken the base url'
        when: 'I see the heading'
        go(baseUrl)
        $('h1', text: 'Welcome to the-internet')
        then: 'it is displayed'
        assert { $('h1', text: 'Welcome to the-internet') }
    }
}