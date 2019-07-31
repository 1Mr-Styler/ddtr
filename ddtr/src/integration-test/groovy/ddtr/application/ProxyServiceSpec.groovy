package ddtr.application

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.hibernate.SessionFactory
import spock.lang.Specification

@Integration
@Rollback
class ProxyServiceSpec extends Specification {

    ProxyService proxyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Proxy(...).save(flush: true, failOnError: true)
        //new Proxy(...).save(flush: true, failOnError: true)
        //Proxy proxy = new Proxy(...).save(flush: true, failOnError: true)
        //new Proxy(...).save(flush: true, failOnError: true)
        //new Proxy(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //proxy.id
    }

    void "test get"() {
        setupData()

        expect:
        proxyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Proxy> proxyList = proxyService.list(max: 2, offset: 2)

        then:
        proxyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        proxyService.count() == 5
    }

    void "test delete"() {
        Long proxyId = setupData()

        expect:
        proxyService.count() == 5

        when:
        proxyService.delete(proxyId)
        sessionFactory.currentSession.flush()

        then:
        proxyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Proxy proxy = new Proxy()
        proxyService.save(proxy)

        then:
        proxy.id != null
    }
}
