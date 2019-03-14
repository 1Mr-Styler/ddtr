package ddtr.card

import ddtr.User
import grails.gorm.services.Service

import java.time.LocalTime

interface TempServiceInstance {

    Temp get(Serializable id)

    List<Temp> list(Map args)

    Long count()

    void delete(Serializable id)

}

@Service(Temp)
abstract class TempService implements TempServiceInstance {
    Temp save(Temp temp, User operator) {
        String ref = "${operator.dlc.lga.toUpperCase()}/RTT/DTT/${LocalTime.now().toSecondOfDay().toString().take(5)}"
        temp.ref = ref

        def arr = ["ABC", "RSH", "KUJ", "EPE"]
        Collections.shuffle(arr)
        temp.license = arr.first()
        temp.license += LocalTime.now().toSecondOfDay().toString().take(6)

        if (!temp.save(flush: true) || temp.hasErrors())
            println("Has errors")
        temp
    }
}