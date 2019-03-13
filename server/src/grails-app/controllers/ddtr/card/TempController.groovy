package ddtr.card

import ddtr.User
import ddtr.application.Applicant
import ddtr.application.Application
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_CAPTURE', 'ROLE_ADMIN'])
class TempController {

    TempService tempService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tempService.list(params), model: [tempCount: tempService.count()]
    }

    def show(Long id) {
        respond tempService.get(id)
    }

    def create() {
        respond new Temp(params)
    }

    def save() {
        Temp temp = new Temp(
                applicant: Applicant.get(params.long('applicant.id')),
                application: Application.get(params.long('application.id')),
                validity: params.validity)
        if (temp == null) {
            notFound()
            return
        }
        User user = springSecurityService.currentUser

        try {
            temp = tempService.save(temp, user)
            temp.save(flush: true)
        } catch (ValidationException e) {
            respond temp.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'temp.label', default: 'Temp'), temp.id])
                redirect temp
            }
            '*' { respond temp, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tempService.get(id)
    }

    def update(Temp temp) {
        if (temp == null) {
            notFound()
            return
        }

        try {
            tempService.save(temp)
        } catch (ValidationException e) {
            respond temp.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'temp.label', default: 'Temp'), temp.id])
                redirect temp
            }
            '*' { respond temp, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        tempService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'temp.label', default: 'Temp'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'temp.label', default: 'Temp'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
