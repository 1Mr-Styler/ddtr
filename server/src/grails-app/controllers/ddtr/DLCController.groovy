package ddtr

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured('isFullyAuthenticated()')
class DLCController {

    DLCService DLCService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DLCService.list(params), model: [DLCCount: DLCService.count()]
    }

    def show(Long id) {
        respond DLCService.get(id)
    }

    def create() {
        respond new DLC(params)
    }

    def save(DLC DLC) {
        if (DLC == null) {
            notFound()
            return
        }

        try {
            DLCService.save(DLC)
        } catch (ValidationException e) {
            respond DLC.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'DLC.label', default: 'DLC'), DLC.id])
                redirect DLC
            }
            '*' { respond DLC, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond DLCService.get(id)
    }

    def update(DLC DLC) {
        if (DLC == null) {
            notFound()
            return
        }

        try {
            DLCService.save(DLC)
        } catch (ValidationException e) {
            respond DLC.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DLC.label', default: 'DLC'), DLC.id])
                redirect DLC
            }
            '*' { respond DLC, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        DLCService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DLC.label', default: 'DLC'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'DLC.label', default: 'DLC'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
