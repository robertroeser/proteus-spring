/**
 * Copyright 2018 Netifi Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.netifi.proteus.demo;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import io.netifi.proteus.demo.core.RandomString;
import io.netifi.proteus.demo.vowelcount.service.VowelCountRequest;
import io.netifi.proteus.demo.vowelcount.service.VowelCountService;
import io.netifi.proteus.spring.core.annotation.Group;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(DemoRunner.class);

  @Autowired private RandomString randomString;

  @Group("io.netifi.proteus.demo.vowelcount")
  private VowelCountService client;

  @Override
  public void run(String... args) throws Exception {

    // Generate stream of random strings
    Flux<VowelCountRequest> requests =
        Flux.interval(Duration.ofMillis(100))
            .map(
                cnt ->
                    VowelCountRequest.newBuilder()
                        .setMessage(randomString.next(10, ThreadLocalRandom.current()))
                        .build());

/*
    // Send stream of random strings to vowel count service
    VowelCountResponse response = client.countVowels(requests).block();

    LOGGER.info("Total Vowels: {}", response.getVowelCnt());

    System.exit(0);
 */
  
    // Send stream of random strings to vowel count service
    byte[] byteArray1;
    client.countVowels(requests, Unpooled.EMPTY_BUFFER).doOnError(Throwable::printStackTrace).subscribe(response -> LOGGER.info("Total Vowels: {}", response.getVowelCnt()));
  
  }
}
