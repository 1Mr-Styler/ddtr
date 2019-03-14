package ddtr

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured('ROLE_ADMIN')
class HistoryController {

    HistoryService historyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond historyService.list(params), model: [historyCount: historyService.count()]
    }

    def show(Long id) {
        respond historyService.get(id)
    }

    def create() {
        respond new History(params)
    }

    def save(History history) {
        if (history == null) {
            notFound()
            return
        }

        try {
            historyService.save(history)
        } catch (ValidationException e) {
            respond history.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'history.label', default: 'History'), history.id])
                redirect history
            }
            '*' { respond history, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond historyService.get(id)
    }

    def update(History history) {
        if (history == null) {
            notFound()
            return
        }

        try {
            historyService.save(history)
        } catch (ValidationException e) {
            respond history.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'history.label', default: 'History'), history.id])
                redirect history
            }
            '*' { respond history, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        historyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'history.label', default: 'History'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'history.label', default: 'History'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
