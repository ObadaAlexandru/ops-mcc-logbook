package de.tum.moveii.ops.logbook.endpoint

import com.mysema.query.types.Predicate
import de.tum.moveii.ops.logbook.alarm.model.Alert
import de.tum.moveii.ops.logbook.alarm.service.AlertService
import de.tum.moveii.ops.logbook.api.endpoint.AlertController
import de.tum.moveii.ops.logbook.api.endpoint.AlertProperties
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper
import de.tum.moveii.ops.logbook.api.message.AlertMessage
import de.tum.moveii.ops.logbook.error.AlertNotFoundException
import spock.lang.Specification

/**
 * Created by Alexandru Obada on 29/01/17.
 */
class AlarmControllerTest extends Specification {

    def alertService = Mock(AlertService)
    def alertMapper = Mock(AlertMapper)

    def alertController = new AlertController(alertService, alertMapper)

    def 'Create alarm'() {
        given:
        def alertMessage = new AlertMessage()
        when:
        def actualAlertMessage = alertController.createAlert(alertMessage)
        then:
        1 * alertMapper.toResource(alertMessage) >> new Alert()
        1 * alertService.create(_) >> new Alert()
        1 * alertMapper.toMessage(_) >> alertMessage
        actualAlertMessage == alertMessage
    }

    def 'Get existing alert'() {
        given:
        def alertId = 1L
        def alert = new Alert();
        def expectedAlertMessage = new AlertMessage()
        when:
        def actualAlertMessage = alertController.getAlert(alertId)
        then:
        1 * alertService.getAlert(alertId) >> Optional.of(alert)
        1 * alertMapper.toMessage(alert) >> expectedAlertMessage
        actualAlertMessage == expectedAlertMessage
    }

    def 'If alert is not found the call should fail'() {
        when:
        alertController.getAlert(1L)
        then:
        1 * alertService.getAlert(_) >> Optional.empty()
        thrown(AlertNotFoundException)
    }

    def 'Get alerts by properties'() {
        given:
        def alertProperties = Mock(AlertProperties)
        alertMapper.toMessage(_) >> new AlertMessage()
        alertProperties.buildPredicate() >> Mock(Predicate)
        when:
        def actualResult = alertController.getAlerts(new AlertProperties())
        then:
        1 * alertService.getAlerts(_) >> alerts
        actualResult.size() == alerts.size()
        where:
        alerts                     | _
        [new Alert(), new Alert()] | _
        []                         | _

    }
}
