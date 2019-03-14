package ddtr

import grails.gorm.services.Service

@Service(History)
interface HistoryService {

    History get(Serializable id)

    List<History> list(Map args)

    Long count()

    void delete(Serializable id)

    History save(History history)

}