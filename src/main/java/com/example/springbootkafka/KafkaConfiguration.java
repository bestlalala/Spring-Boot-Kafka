package com.example.springbootkafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import java.util.HashMap;
import java.util.Map;

//환경 설정 클래스라는 것을 알려주는 어노테이션
//인스턴스를 생성해서 직접 관리하지 않고 프레임워크가 생명 주기를 관리: 제어의 역전
@Configuration
public class KafkaConfiguration {
    // 설정 파일에서 값을 가지고 와서 주입하는 코드
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //메시지를 게시하는 프로듀서의 설정
    @Bean
    public ProducerFactory<String, String> producerFactory() {

        Map<String,Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory(configs);
    }

    //카프카 사용을 위한 인스턴스를 생성해주는 메서드
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

