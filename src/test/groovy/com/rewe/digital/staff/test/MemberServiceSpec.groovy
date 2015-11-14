package com.rewe.digital.staff.test
import com.rewe.digital.staff.Application
import com.rewe.digital.staff.model.Member
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

import javax.mail.internet.InternetAddress

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@WebIntegrationTest(randomPort = true)
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class MemberServiceSpec extends Specification {

    @Value("http://localhost:\${local.server.port}/api/members")
    String resourceUrl

    RestTemplate rest = new RestTemplate()

    def "GETing \"members/\" should return an empty list as there are no persisted members yet"() {
        when:
        ResponseEntity response = rest.getForEntity(resourceUrl, List)

        then:
        response.statusCode == OK
        and:
        response.body == []
    }

    @Ignore("currently MemberRepository.findAll() returns Members with only id set")
    def "GETing \"members/\" should return all persisted members as list"() {
        given:
        Collection<Member> members = [generateMember(), generateMember(), generateMember()]
        and:
        members.each { Member member -> rest.postForEntity(resourceUrl, member, String) }

        when:
        ResponseEntity response = rest.getForEntity(resourceUrl, List)

        then:
        response.statusCode == OK
        and:
        response.body == members
    }

    def "POSTing \"members/\" should persist and return location"() {
        given:
        Member member = generateMember()

        when:
        ResponseEntity response = rest.postForEntity(resourceUrl, member, String)

        then:
        response.statusCode == CREATED
        and:
        response.headers.getLocation()
    }

    def "A previously POSTed member should be GETable at returned location"() {
        given:
        Member member = generateMember()
        and:
        ResponseEntity response = rest.postForEntity(resourceUrl, member, String)

        when:
        Member gotMember = rest.getForEntity(response.headers.getLocation(), Member).body

        then:
        gotMember == member
    }

    private static Member generateMember() {
        new Member(new InternetAddress("stafftest+${UUID.randomUUID()}@rewe-digital.com"), "Staff", "Test")
    }
}
