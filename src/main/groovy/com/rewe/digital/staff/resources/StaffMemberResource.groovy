package com.rewe.digital.staff.resources
import com.rewe.digital.staff.model.StaffMember
import com.rewe.digital.staff.repositories.StaffMemberRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.inject.Inject

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@RequestMapping("api/profiles")
class StaffMemberResource {

    @Inject
    StaffMemberRepository staffMemberRepository

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public List<StaffMember> getAllProfiles() {
        staffMemberRepository.findAll().toList()
    }
}
