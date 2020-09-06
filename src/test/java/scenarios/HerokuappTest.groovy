package scenarios

import geb.spock.GebReportingSpec
import herokuapp.pages.BrokenImages
import jodd.log.Logger
import jodd.log.LoggerFactory
import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.Specification

class HerokuappTest extends GebReportingSpec {

    static Logger logger = LoggerFactory.getLogger(HerokuappTest.class)

    def setupSpec() {
        logger.info('\n**************\n' + TestName + 'is running')
    }
/*
    @Override
    void setBaseUrl() {
        baseUrl = 'https://the-internet.herokuapp.com/'
    }*/

    class MySpec extends Specification {
        @Rule
        TestName name = new TestName()

        /* def "some test"() {
             expect: name.methodName == "some test"
         }*/
    }

    def 'Broken Images exist'() {

        given: 'the base url'
        getBaseUrl()
        when: 'we call the Broken Images Page'
        to BrokenImages
        then: 'we are on the right page and we see 2 broken images out of 3'
        BrokenImages page = at BrokenImages
        page.containsBrokenImages()

    }

    def 'Dummy Test'() {

        given: 'two numbers to add'
        int a = 3
        int b = 4
        when: 'a + b'
        a + b
        then: 'it must be 7'
        assert a + b == 7


    }
}