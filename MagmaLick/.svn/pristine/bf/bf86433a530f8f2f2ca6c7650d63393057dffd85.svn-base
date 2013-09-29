package scavengr

import grails.test.mixin.TestFor
import scavengr.Notification
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Notification)
class NotificationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void 'test notification creation'() {
        when:
        def n = new Notification(sender: new User(), recipient: new User(), 
            subject:'hello', message:'how are you', action:'leave here', link:'google.com')
        then:
        n.validate()
    }
}