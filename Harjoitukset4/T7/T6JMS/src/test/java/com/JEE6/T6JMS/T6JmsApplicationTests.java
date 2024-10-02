package com.JEE6.T6JMS;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.JEE6.T6JMS.jms.Receiver;
import com.JEE6.T6JMS.jms.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
class T6JmsApplicationTests {

	  @Autowired
	  private Sender sender;

	  @Autowired
	  private Receiver receiver;

	  @Test
	  public void testReceive() throws Exception {
	    sender.send("Hello Spring JMS ActiveMQ!");

	    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	  }

}
