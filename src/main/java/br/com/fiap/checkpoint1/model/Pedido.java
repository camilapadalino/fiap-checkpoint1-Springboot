package br.com.fiap.checkpoint1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String clienteNome;

    private LocalDate dataPedido = LocalDate.now();

    @Min(value = 0, message = "O valor total não pode ser negativo")
    private double valorTotal;

    // Construtor de conveniência para facilitar a criação de pedidos
    public Pedido(String clienteNome, double valorTotal) {
        this.clienteNome = clienteNome;
        this.valorTotal = valorTotal;
        this.dataPedido = LocalDate.now();
    }
}