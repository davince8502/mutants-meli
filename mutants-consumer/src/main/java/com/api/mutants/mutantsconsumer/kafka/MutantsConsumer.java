package com.api.mutants.mutantsconsumer.kafka;


import com.api.mutants.mutantsconsumer.entity.DnaVerified;
import com.api.mutants.mutantsconsumer.repository.DnaVerifiedRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class MutantsConsumer {


    @Autowired
    private DnaVerifiedRepository dnaVerifiedRepository;

    @KafkaListener(topics = "mutants", groupId = "group_id")
    public void consume(String data) throws IOException {

        DnaVerified dnaVerified = new Gson().fromJson(data, DnaVerified.class);
        if(!dnaVerifiedRepository.existsById(dnaVerified.getId())){
            dnaVerified.setCreateDate(new Date());
            dnaVerified.setModificationDate(new Date());
            dnaVerifiedRepository.save(dnaVerified);
        }

        log.info(String.format("#### -> Consumed message -> %s", data));
    }
}