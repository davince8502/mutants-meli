package com.api.mutants.kafka;


import com.api.mutants.domain.entity.DnaVerified;
import com.api.mutants.domain.repository.DnaVerifiedRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class MutantsConsumer {

    @Autowired
    private DnaVerifiedRepository dnaVerifiedRepository;

    private CountDownLatch latch = new CountDownLatch(1);

    private String payload = null;

    @KafkaListener(topics = "${io.confluent.kafka.topic.mutants}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        log.info("received payload='{}'", consumerRecord.toString());
        setPayload(consumerRecord.toString());

        DnaVerified dnaVerified = new Gson().fromJson(consumerRecord.value().toString(), DnaVerified.class);
        if(!dnaVerifiedRepository.existsById(dnaVerified.getId())){
            dnaVerified.setCreateDate(new Date());
            dnaVerified.setModificationDate(new Date());
            dnaVerifiedRepository.save(dnaVerified);
        }


        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}