package io.netifi.proteus.quickstart.client;

import io.netifi.proteus.quickstart.service.protobuf.HelloRequest;
import io.netifi.proteus.quickstart.service.protobuf.HelloServiceClient;
import io.netifi.proteus.spring.core.annotation.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/** Calls the Hello Service */
@Component
public class ClientRunner implements CommandLineRunner {
  private static final Logger logger = LogManager.getLogger(ClientRunner.class);

  @Group("quickstart.services.helloservices")
  private HelloServiceClient client;

  @Override
  public void run(String... args) throws Exception {
    // Create Request to HelloService
    HelloRequest request = HelloRequest.newBuilder().setName("World").build();

    logger.info("Sending 'World' to HelloService...");

    while (true) {
      while (true) {
        try {
          // Call the HelloService
          logger.info(client.sayHello(request).block());
          break;
        } catch (Throwable t) {
        }
      }
    }
  }
}
