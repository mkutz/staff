package com.rewe.digital.staff.repositories
import com.rewe.digital.staff.model.Member
import org.springframework.data.repository.CrudRepository

interface MemberRepository extends CrudRepository<Member, String> {}
