package com.example.producer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange 1 - Pedidos
    public static final String PEDIDOS_EXCHANGE = "pedidos.exchange";
    public static final String PEDIDOS_QUEUE = "pedidos.fila";
    public static final String PEDIDOS_ROUTING_KEY = "pedidos.rota";

    // Exchange 2 - Pagamentos
    public static final String PAGAMENTOS_EXCHANGE = "pagamentos.exchange";
    public static final String PAGAMENTOS_QUEUE = "pagamentos.fila";
    public static final String PAGAMENTOS_ROUTING_KEY = "pagamentos.rota";

    // Exchange 3 - Notificações
    public static final String NOTIFICACOES_EXCHANGE = "notificacoes.exchange";
    public static final String NOTIFICACOES_QUEUE = "notificacoes.fila";
    public static final String NOTIFICACOES_ROUTING_KEY = "notificacoes.rota";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    // Pedidos
    @Bean
    public DirectExchange pedidosExchange() {
        return new DirectExchange(PEDIDOS_EXCHANGE, true, false);
    }

    @Bean
    public Queue pedidosQueue() {
        return new Queue(PEDIDOS_QUEUE, true, false, false);
    }

    @Bean
    public Binding pedidosBinding(Queue pedidosQueue, DirectExchange pedidosExchange) {
        return BindingBuilder
                .bind(pedidosQueue)
                .to(pedidosExchange)
                .with(PEDIDOS_ROUTING_KEY);
    }

    // Pagamentos
    @Bean
    public DirectExchange pagamentosExchange() {
        return new DirectExchange(PAGAMENTOS_EXCHANGE, true, false);
    }

    @Bean
    public Queue pagamentosQueue() {
        return new Queue(PAGAMENTOS_QUEUE, true, false, false);
    }

    @Bean
    public Binding pagamentosBinding(Queue pagamentosQueue, DirectExchange pagamentosExchange) {
        return BindingBuilder
                .bind(pagamentosQueue)
                .to(pagamentosExchange)
                .with(PAGAMENTOS_ROUTING_KEY);
    }

    // Notificações
    @Bean
    public DirectExchange notificacoesExchange() {
        return new DirectExchange(NOTIFICACOES_EXCHANGE, true, false);
    }

    @Bean
    public Queue notificacoesQueue() {
        return new Queue(NOTIFICACOES_QUEUE, true, false, false);
    }

    @Bean
    public Binding notificacoesBinding(Queue notificacoesQueue, DirectExchange notificacoesExchange) {
        return BindingBuilder
                .bind(notificacoesQueue)
                .to(notificacoesExchange)
                .with(NOTIFICACOES_ROUTING_KEY);
    }
} 