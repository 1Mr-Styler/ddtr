package ddtr.application

import ddtr.User
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_CAPTURE', 'ROLE_ADMIN'])
class ApplicantController {

    def springSecurityService
    ApplicantService applicantService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond applicantService.list(params), model: [applicantCount: applicantService.count()]
    }

    def show(Long id) {
        respond applicantService.get(id)
    }

    def create() {
        respond new Applicant(params)
    }

    def save(Applicant applicant) {
        if (applicant == null) {
            notFound()
            return
        }

        try {
            User user = springSecurityService.currentUser
            applicantService.save(applicant, user)
        } catch (ValidationException e) {
            respond applicant.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'applicant.label', default: 'Applicant'), applicant.id])
                redirect applicant
            }
            '*' { respond applicant, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond applicantService.get(id)
    }

    def update(Applicant applicant) {
        if (applicant == null) {
            notFound()
            return
        }

        try {
            applicantService.save(applicant)
        } catch (ValidationException e) {
            respond applicant.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'applicant.label', default: 'Applicant'), applicant.id])
                redirect applicant
            }
            '*' { respond applicant, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        applicantService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'applicant.label', default: 'Applicant'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'applicant.label', default: 'Applicant'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
