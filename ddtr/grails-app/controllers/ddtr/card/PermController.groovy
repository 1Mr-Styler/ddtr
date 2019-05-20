package ddtr.card

import ddtr.application.Applicant
import ddtr.application.Application
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_CAPTURE', 'ROLE_ADMIN'])
class PermController {

    PermService permService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond permService.list(params), model: [permCount: permService.count()]
    }

    def show(Long id) {
        respond permService.get(id)
    }

    def create() {
        respond new Perm(params)
    }

    def load() {
        if (params.csv == null) {
            render view: "load"
            return
        }

        def file = request.getFile("csv")
        boolean f = permService.loadCard(file)
        redirect(action: "index")
        return
    }

    def save() {
        Perm perm = new Perm(applicant: Applicant.get(params.long('applicant.id')),
                application: Application.get(params.long('application.id')), validity: params.validity)
        if (perm == null) {
            notFound()
            return
        }

        try {
            permService.save(perm)
        } catch (ValidationException e) {
            respond perm.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'perm.label', default: 'Perm'), perm.id])
                redirect perm
            }
            '*' { respond perm, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond permService.get(id)
    }

    def update(Perm perm) {
        if (perm == null) {
            notFound()
            return
        }

        try {
            permService.save(perm)
        } catch (ValidationException e) {
            respond perm.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'perm.label', default: 'Perm'), perm.id])
                redirect perm
            }
            '*' { respond perm, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        permService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'perm.label', default: 'Perm'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'perm.label', default: 'Perm'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
