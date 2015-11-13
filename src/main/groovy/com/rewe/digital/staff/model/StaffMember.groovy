package com.rewe.digital.staff.model

import groovy.transform.ToString

import javax.mail.internet.InternetAddress
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@ToString
class StaffMember {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    private InternetAddress emailAddress

    private String firstName

    private String lastName

    protected StaffMember() {}

    public StaffMember(InternetAddress emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress
        this.firstName = firstName
        this.lastName = lastName
    }

    UUID getId() {
        return id
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
