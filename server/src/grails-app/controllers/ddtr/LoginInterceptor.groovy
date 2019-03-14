package ddtr


class LoginInterceptor {

    def springSecurityService

    public LoginInterceptor() {
        match(controller: "login", action: "authenticate")
    }

    boolean before() { true }

    boolean after() {
        if (springSecurityService.currentUser != null) {
            new History(operator: springSecurityService.currentUser)
        }
        true
    }

    void afterView() {
        // no-op
    }
}
