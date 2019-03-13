package ddtr.card

import com.opencsv.CSVReader
import grails.gorm.services.Service
import org.springframework.web.multipart.MultipartFile

interface PermServiceInterface {

    Perm get(Serializable id)

    List<Perm> list(Map args)

    Long count()

    void delete(Serializable id)

    Perm save(Perm perm)

}

@Service(Perm)
abstract class PermService implements PermServiceInterface {

    boolean loadCard(MultipartFile file) {

        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);

        def reader = new CSVReader(new FileReader(convFile))
        def data = reader.with { rows ->
            rows.collect { row ->
                row
            }
        }
        data.remove(0)
        data.each {
            println(it)
            //Find Applicants by phone
        }

        true
    }
}