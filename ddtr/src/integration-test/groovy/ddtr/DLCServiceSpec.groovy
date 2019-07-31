package ddtr

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
@Rollback
class DLCServiceSpec extends Specification {

    DLCService DLCService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DLC(...).save(flush: true, failOnError: true)
        //new DLC(...).save(flush: true, failOnError: true)
        //DLC DLC = new DLC(...).save(flush: true, failOnError: true)
        //new DLC(...).save(flush: true, failOnError: true)
        //new DLC(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //DLC.id
    }

    void "test get"() {
        setupData()

        expect:
        DLCService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DLC> DLCList = DLCService.list(max: 2, offset: 2)

        then:
        DLCList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        DLCService.count() == 5
    }

    void "test delete"() {
        Long DLCId = setupData()

        expect:
        DLCService.count() == 5

        when:
        DLCService.delete(DLCId)
        sessionFactory.currentSession.flush()

        then:
        DLCService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DLC DLC = new DLC()
        DLCService.save(DLC)

        then:
        DLC.id != null
    }
}
