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

    boolean statusIsDirty = false
    boolean paymentIsDirty = false
    static transients = ['paymentIsDirty', 'statusIsDirty']

    static constraints = {
        id(nullable: true)
        status(nullable: false)
        otp nullable: true
        proxy nullable: true
        address nullable: true
    }

    def beforeUpdate() {
        statusIsDirty = this.isDirty('status')
        paymentIsDirty = this.isDirty('payment')
        true
    }

    String toString() {
        "ID: ${this.id} - ${status.toString()}"
    }
}
