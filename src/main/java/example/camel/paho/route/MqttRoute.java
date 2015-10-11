package example.camel.paho.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.paho.MqttProperties;
import org.springframework.stereotype.Component;

@Component
public class MqttRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("paho:/test/topic?brokerUrl=tcp://test.mosquitto.org:1883").process(print);
    }

    private Processor print = new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
            Object header = exchange.getIn().getHeader("MqttProperties");
            MqttProperties prop = null;
            if (header instanceof MqttProperties) {
                prop = (MqttProperties) header;
            }

            byte[] body = (byte[]) exchange.getIn().getBody();
            String payload = new String(body, "utf-8");

            if (prop != null) {
                System.out.println("topic=" + prop.getTopic() + ", payload=" + payload);
            } else {
                System.out.println("payload=" + payload);
            }
        }
    };
}
