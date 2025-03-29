package br.com.fiap.checkpoint1.data;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarBanco(PedidoRepository pedidoRepository) {
        return args -> {
            // Limpa o banco antes de inserir os pedidos
            pedidoRepository.deleteAll();

            // Cria alguns pedidos para salvar no banco
            List<Pedido> pedidos = List.of(
                new Pedido("Cliente 1", 100.00),
                new Pedido("Cliente 2", 150.50),
                new Pedido("Cliente 3", 200.75)
            );

            // Salva todos os pedidos no banco
            pedidoRepository.saveAll(pedidos);

            // Confirmação no console
            long total = pedidoRepository.count();
            System.out.println("Total de pedidos no banco: " + total);
        };
    }
}