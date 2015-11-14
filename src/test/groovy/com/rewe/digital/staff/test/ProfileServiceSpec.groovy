package com.rewe.digital.staff.test
import com.rewe.digital.staff.Application
import com.rewe.digital.staff.model.StaffMember
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.mail.internet.InternetAddress

@WebIntegrationTest(randomPort = true)
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class ProfileServiceSpec extends Specification {

    @Value("http://localhost:\${local.server.port}/api/profiles")
    String resourceUrl

    RestTemplate rest = new RestTemplate()

    StaffMember staffMember = new StaffMember(new InternetAddress("some.body@rewe-digital.com"), "Some", "Body")

    def "GETing \"profiles\" should return an empty list"() {
        expect:
        rest.getForEntity(resourceUrl, List).body == []
    }

    def "POSTing should work"() {
        when:
        ResponseEntity response = rest.postForEntity(resourceUrl, staffMember, String)

        then:
        response.statusCode == HttpStatus.CREATED
        and:
        response.headers.getLocation()
    }

    def "A previously POSTed profile should be GETable at returned location"() {
        given:
        ResponseEntity response = rest.postForEntity(resourceUrl, staffMember, String)

        when:
        StaffMember gotStaffMember = rest.getForEntity(response.headers.getLocation(), StaffMember).body
        gotStaffMember.id = null

        then:
        gotStaffMember == staffMember
    }
}
