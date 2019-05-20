package ddtr.application

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_CAPTURE', 'ROLE_ADMIN'])
class ProxyController {

    ProxyService proxyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond proxyService.list(params), model: [proxyCount: proxyService.count()]
    }

    def show(Long id) {
        respond proxyService.get(id)
    }

    def create() {
        respond new Proxy(params)
    }

    def save(Proxy proxy) {
        if (proxy == null) {
            notFound()
            return
        }

        try {
            proxyService.save(proxy)
        } catch (ValidationException e) {
            respond proxy.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proxy.label', default: 'Proxy'), proxy.id])
                redirect proxy
            }
            '*' { respond proxy, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond proxyService.get(id)
    }

    def update(Proxy proxy) {
        if (proxy == null) {
            notFound()
            return
        }

        try {
            proxyService.save(proxy)
        } catch (ValidationException e) {
            respond proxy.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proxy.label', default: 'Proxy'), proxy.id])
                redirect proxy
            }
            '*' { respond proxy, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        proxyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proxy.label', default: 'Proxy'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proxy.label', default: 'Proxy'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
