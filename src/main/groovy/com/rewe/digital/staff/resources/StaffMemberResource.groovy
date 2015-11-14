package com.rewe.digital.staff.resources
import com.rewe.digital.staff.model.StaffMember
import com.rewe.digital.staff.repositories.StaffMemberRepository
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
@RequestMapping("api/profiles")
class StaffMemberResource {

    @Inject
    StaffMemberRepository staffMemberRepository

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public List<StaffMember> getAllProfiles() {
        staffMemberRepository.findAll().toList()
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public StaffMember getProfile(@PathVariable String id) {
        staffMemberRepository.findOne(id)
    }

    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postProfile(StaffMember profile, RequestEntity<Void> request) {
        profile = staffMemberRepository.save(profile)

        URI location = UriComponentsBuilder.fromUri(request.url).path("/${profile.id}").build().toUri()
        ResponseEntity.created(location).build()
    }
}
