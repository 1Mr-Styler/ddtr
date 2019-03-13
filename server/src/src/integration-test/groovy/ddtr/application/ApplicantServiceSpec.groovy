package ddtr.application

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
@Rollback
class ApplicantServiceSpec extends Specification {

    ApplicantService applicantService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Applicant(...).save(flush: true, failOnError: true)
        //new Applicant(...).save(flush: true, failOnError: true)
        //Applicant applicant = new Applicant(...).save(flush: true, failOnError: true)
        //new Applicant(...).save(flush: true, failOnError: true)
        //new Applicant(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //applicant.id
    }

    void "test get"() {
        setupData()

        expect:
        applicantService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Applicant> applicantList = applicantService.list(max: 2, offset: 2)

        then:
        applicantList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        applicantService.count() == 5
    }

    void "test delete"() {
        Long applicantId = setupData()

        expect:
        applicantService.count() == 5

        when:
        applicantService.delete(applicantId)
        sessionFactory.currentSession.flush()

        then:
        applicantService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Applicant applicant = new Applicant()
        applicantService.save(applicant)

        then:
        applicant.id != null
    }
}
