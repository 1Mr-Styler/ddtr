package ddtr.card

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
@Rollback
class PermServiceSpec extends Specification {

    PermService permService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Perm(...).save(flush: true, failOnError: true)
        //new Perm(...).save(flush: true, failOnError: true)
        //Perm perm = new Perm(...).save(flush: true, failOnError: true)
        //new Perm(...).save(flush: true, failOnError: true)
        //new Perm(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //perm.id
    }

    void "test get"() {
        setupData()

        expect:
        permService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Perm> permList = permService.list(max: 2, offset: 2)

        then:
        permList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        permService.count() == 5
    }

    void "test delete"() {
        Long permId = setupData()

        expect:
        permService.count() == 5

        when:
        permService.delete(permId)
        sessionFactory.currentSession.flush()

        then:
        permService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Perm perm = new Perm()
        permService.save(perm)

        then:
        perm.id != null
    }
}
