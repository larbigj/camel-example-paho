package example.camel.paho.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.paho.PahoConstants;
import org.springframework.stereotype.Component;

@Component
public class MqttRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("paho:/test/+/sample/#?brokerUrl=tcp://test.mosquitto.org:1883").process(print);
    }

    private Processor print = new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
            Object header = exchange.getIn().getHeader(PahoConstants.MQTT_TOPIC);
            String topic = (String) header;
            Message camelMessage = exchange.getIn();

            byte[] body = (byte[]) camelMessage.getBody();
            String payload = new String(body, "utf-8");

            System.out.println("topic=" + topic + ", payload=" + payload);

            // can cast to PahoMessage
            // PahoMessage pahoMessage = (PahoMessage) camelMessage;
        }
    };
}
