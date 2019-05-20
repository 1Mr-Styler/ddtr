package ddtr.application

import ddtr.User

class Applicant {

    String first
    String last
    String middle
    Date dob
    Gender gender
    String phone
    String nextOfKinPhone
    String email
    String testOfficer
    String address
    User operator

    static constraints = {
        email nullable: true
        middle nullable: true
        operator nullable: true
    }

    String toString() {
        "${this.first} (${this.phone})"
    }
}
