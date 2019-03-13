package ddtr.card

import ddtr.application.Application

class Temp {
    String license
    String ref
    Validity validity
    Application application

    static constraints = {
        ref nullable: true
        license nullable: true
    }
}
