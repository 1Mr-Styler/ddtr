package ddtr.application

import ddtr.User
import grails.gorm.services.Service

interface ApplicantServiceInterface {

    Applicant get(Serializable id)

    List<Applicant> list(Map args)

    Long count()

    void delete(Serializable id)

}

@Service(Applicant)
abstract class ApplicantService implements ApplicantServiceInterface {
    Applicant save(Applicant applicant, User operator) {
        applicant.operator = operator
        applicant.save(flush: true)
        applicant
    }
}