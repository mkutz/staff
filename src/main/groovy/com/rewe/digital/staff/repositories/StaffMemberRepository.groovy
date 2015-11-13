package com.rewe.digital.staff.repositories
import com.rewe.digital.staff.model.StaffMember
import org.springframework.data.repository.CrudRepository

interface StaffMemberRepository extends CrudRepository<StaffMember, UUID> {}
