package com.kongju.middleware;

import com.kongju.middleware.sensorLog.service.SensorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Slf4j
@RequiredArgsConstructor
@EnableIntegration
@SpringBootApplication
public class KMiddlewareApplication {

    private final SensorLogService sensorLogService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(KMiddlewareApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
//		SpringApplication.run(KMiddlewareApplication.class, args);
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883", "testClient",
                        "factory/sensor/#"
                        ,"factory/plc/#"
                );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                System.out.println("handleMessage : TOPIC : " + topic);
                String payload = message.getPayload().toString();
                if(topic.contains("factory/sensor/")){
                    sensorLogService.processAndSaveData(payload);
                }else if(topic.contains("factory/plc/")){
                    // TODO
                    //plcProductionLogService.processAndSaveData(payload)'\;
                    System.out.println("TODO! factroy/plc/");
                }

            }

        };
    }

}
