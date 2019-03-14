package ddtr


import ddtr.application.CardStatus
import ddtr.card.Perm
import ddtr.card.Temp
import grails.events.annotation.Subscriber
import grails.gorm.transactions.Transactional
import org.grails.datastore.mapping.engine.event.PostUpdateEvent

import java.time.LocalTime

@Transactional
class EventService {

    @Subscriber
    def afterUpdate(PostUpdateEvent event) {
        println(event.entityObject)
        if (event.entityObject instanceof ddtr.application.Application) {
            ddtr.application.Application application = event.entityObject as ddtr.application.Application
            def card
            if (Perm.findByApplication(application) != null) {
                card = Perm.findByApplication(application)
            } else card = Temp.findByApplication(application)
            String message = "Dear ${card.applicant.first}, \n"

            if (application.statusIsDirty) {
                if (application.status == CardStatus.READY) {
                    application.otp = LocalTime.now().toSecondOfDay().toString()
                    application.save(flush: true)
                    sleep(100)

                    //Send Sms
                    message += "Your OTP for this transaction is ${application.otp}. You will require this code at the point of collecting your temporary license. \n Regards."
                    SMS sms = new SMS()
                    sms.addRecpt(card.applicant.phone)
                    sms.setMsg(message)
                    sms.send()

                } else if (application.status == CardStatus.COLLECTED) {
                    String by = application?.proxy?.names ?: "you"
                    message += "This is to notify you that your temporary license has been collected by $by. Please reach out to us if this is false.\n" +
                            "Mobile Number: 08034220179\n" +
                            "Email: email@ddtrlicence.gov.ng\n" +
                            " Regards."
                    SMS sms = new SMS()
                    sms.addRecpt(card.applicant.phone)
                    sms.setMsg(message)
                    sms.send()
                }
            } else if (application.paymentIsDirty) {
                if (application.payment) {
                    //Send Sms
                    message += "This is to notify you that your payment for license application management services and home delivery is successful. kindly reach us on:\n" +

                            "Mobile Number: 08034220179\n" +

                            "Email: email@ddtrlicence.gov.ng. \n Regards."
                }
                SMS sms = new SMS()
                sms.addRecpt(card.applicant.phone)
                sms.setMsg(message)
                sms.send()
            }

            println(application.errors)

        }
    }
}
