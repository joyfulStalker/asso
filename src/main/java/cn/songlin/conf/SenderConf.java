package cn.songlin.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConf {

	@Bean(name = "trace")
	public Queue queueMessage() {
		return new Queue("trace");
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("asso");
	}

	@Bean
	Binding bindingExchangeMessage(@Qualifier("trace") Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("trace");
	}

}