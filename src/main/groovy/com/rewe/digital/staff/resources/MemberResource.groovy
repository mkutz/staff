package com.rewe.digital.staff.resources
import com.rewe.digital.staff.model.Member
import com.rewe.digital.staff.repositories.MemberRepository
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

import javax.inject.Inject

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
@RequestMapping("api/members")
class MemberResource {

    @Inject
    MemberRepository memberRepository

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public Iterable<Member> getAllMembers() {
        memberRepository.findAll()
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Member getMember(@PathVariable String id) {
        memberRepository.findOne(id)
    }

    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postMember(Member member, RequestEntity<Void> request) {
        member = memberRepository.save(member)

        URI location = UriComponentsBuilder.fromUri(request.url).path("/${member.id}").build().toUri()
        ResponseEntity.created(location).build()
    }
}
