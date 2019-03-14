package ddtr

import ddtr.application.Delivery
import ddtr.application.Proxy

class BootStrap {

    def init = { servletContext ->
        DLC dlc = new DLC(name: "FCT_DLC", state: "Abuja", lga: "Mabushi")
        dlc.save()
        User user = new User(username: "08025254356", password: "pass", first: "Jerry", last: "U.", dlc: dlc)
        user.save()

        Authority payment = new Authority(authority: "ROLE_PAYMENT")
        Authority ref = new Authority(authority: "ROLE_REF")
        Authority admin = new Authority(authority: "ROLE_ADMIN")
        Authority capture = new Authority(authority: "ROLE_CAPTURE")

        ref.save()
        admin.save()
        capture.save()
        payment.save()

        UserAuthority.create(user, ref)
        UserAuthority.create(user, admin)
        UserAuthority.create(user, capture)
        UserAuthority.create(user, payment)


        new ddtr.application.Application(delivery: Delivery.SELF).save()
        new ddtr.application.Application(delivery: Delivery.PROXY, proxy: new Proxy(names: "James Smith", phone: "02343523", idnum: "21232", idtype: "DL").save()).save()
    }
    def destroy = {
    }
}
