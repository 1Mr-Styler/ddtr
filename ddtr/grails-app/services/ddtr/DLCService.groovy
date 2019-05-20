package ddtr

import grails.gorm.services.Service

@Service(DLC)
interface DLCService {

    DLC get(Serializable id)

    List<DLC> list(Map args)

    Long count()

    void delete(Serializable id)

    DLC save(DLC DLC)

}