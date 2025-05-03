package com.example.producer.controller;

import com.example.producer.config.RabbitMQConfig;
import com.example.producer.dto.PedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidosController {

    private final RabbitTemplate rabbitTemplate;

    public PedidosController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/pedidos")
    public String enviarPedido(@RequestBody PedidoDTO pedido) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.PEDIDOS_EXCHANGE,
            RabbitMQConfig.PEDIDOS_ROUTING_KEY,
            pedido
        );
        return "Pedido enviado com sucesso!";
    }
} 