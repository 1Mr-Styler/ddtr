package ddtr.application

import grails.gorm.services.Service

@Service(Proxy)
interface ProxyService {

    Proxy get(Serializable id)

    List<Proxy> list(Map args)

    Long count()

    void delete(Serializable id)

    Proxy save(Proxy proxy)

}