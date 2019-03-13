package ddtr.card

import ddtr.application.Applicant
import ddtr.application.Application

class Temp {
    String license
    String ref
    Validity validity
    Application application
    Applicant applicant

    static constraints = {
        ref nullable: true
        license nullable: true
    }
}
