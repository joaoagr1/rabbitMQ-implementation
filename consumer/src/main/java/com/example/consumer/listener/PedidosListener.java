package com.example.consumer.listener;

import com.example.consumer.config.RabbitMQConfig;
import com.example.consumer.dto.PedidoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidosListener {

    @RabbitListener(queues = RabbitMQConfig.PEDIDOS_QUEUE)
    public void receiveMessage(PedidoDTO pedido) {
        System.out.println("Pedido recebido:");
        System.out.println("Produto: " + pedido.getProduto());
        System.out.println("Quantidade: " + pedido.getQuantidade());
        System.out.println("Valor: " + pedido.getValor());
        System.out.println("Valor total: " + (pedido.getQuantidade() * pedido.getValor()));
    }
} 