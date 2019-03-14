package ddtr

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
@Rollback
class HistoryServiceSpec extends Specification {

    HistoryService historyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new History(...).save(flush: true, failOnError: true)
        //new History(...).save(flush: true, failOnError: true)
        //History history = new History(...).save(flush: true, failOnError: true)
        //new History(...).save(flush: true, failOnError: true)
        //new History(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //history.id
    }

    void "test get"() {
        setupData()

        expect:
        historyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<History> historyList = historyService.list(max: 2, offset: 2)

        then:
        historyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        historyService.count() == 5
    }

    void "test delete"() {
        Long historyId = setupData()

        expect:
        historyService.count() == 5

        when:
        historyService.delete(historyId)
        sessionFactory.currentSession.flush()

        then:
        historyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        History history = new History()
        historyService.save(history)

        then:
        history.id != null
    }
}
