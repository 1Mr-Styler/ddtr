package ddtr.application

class Application {
    Date date = new Date()
    CardStatus status = CardStatus.PROCESSING
    boolean payment = false
    Delivery delivery
    PaymentMode paymentMode = PaymentMode.NONE
    String otp
    Proxy proxy
    String address

    static constraints = {
        otp nullable: true
        proxy nullable: true
        address nullable: true
    }

    String toString() {
        "ID: ${this.id} - ${status.toString()}"
    }
}
