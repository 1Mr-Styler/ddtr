package ddtr

class BootStrap {

    def init = { servletContext ->
        User user = new User(username: "08025254356", password: "pass")
        user.save()

        Authority payment = new Authority(authority: "ROLE_PAYMENT")
        Authority ref = new Authority(authority: "ROLE_REF")

        ref.save()
        payment.save()

        UserAuthority.create(user, ref)
        UserAuthority.create(user, payment)
    }
    def destroy = {
    }
}
