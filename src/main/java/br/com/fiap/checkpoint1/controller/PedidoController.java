package br.com.fiap.checkpoint1.controller;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.salvar(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        Optional<Pedido> pedidoExistente = pedidoService.buscarPorId(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setClienteNome(pedidoAtualizado.getClienteNome());
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());
            Pedido pedidoSalvo = pedidoService.salvar(pedido);
            return ResponseEntity.ok(pedidoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (pedidoService.buscarPorId(id).isPresent()) {
            pedidoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
