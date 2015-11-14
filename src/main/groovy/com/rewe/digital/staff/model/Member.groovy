package com.rewe.digital.staff.model
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.annotations.GenericGenerator

import javax.mail.internet.InternetAddress
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@ToString(includeFields = true, includePackage = false)
@EqualsAndHashCode(excludes = ["id"])
class Member {

    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(unique = true)
    private InternetAddress emailAddress

    private String firstName

    private String lastName

    protected Member() {}

    public Member(InternetAddress emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress
        this.firstName = firstName
        this.lastName = lastName
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }
}
