package ddtr

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

class SMS {

    String url
    int otp

    SMS() {
        String username = "stylr11@gmail.com"
        String password = "b47d8cf5d9c74367cca1d38ec1b917635a548e92"

//        this.url = "http://sms.bbnplace.com/bulksms/bulksms.php?username=${username}&password=${password}&sender=eHara"
        this.url = "http://api.ebulksms.com:8080/sendsms?username=${username}&apikey=${password}&sender=DDTR&flash=0"
    }

    def send() {
        print(this.url.toURL())
        RestBuilder rest = new RestBuilder()

        RestResponse resp = rest.get(this.url) {
            contentType('text/plain')
        }

        println(resp.text)

    }

    def addRecpt(String r) {
        this.url += "&recipients=${r}"
    }

    def setMsg(String m) {
        this.url += "&messagetext=${m}"// + URLEncoder.encode(m)
    }


}
