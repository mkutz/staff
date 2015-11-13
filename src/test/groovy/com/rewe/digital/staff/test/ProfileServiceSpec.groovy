package com.rewe.digital.staff.test
import com.rewe.digital.staff.Application
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@WebIntegrationTest(randomPort = true)
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class ProfileServiceSpec extends Specification {

    @Value("http://localhost:\${local.server.port}/api/profiles")
    String resourceUrl

    RestTemplate rest = new RestTemplate()

    def "GETing \"profiles\" should return an empty list"() {
        expect:
        rest.getForEntity(resourceUrl, List).body == []
    }
}
