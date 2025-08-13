package com.borba.storage.service;

import com.borba.storage.model.Pedido;
import com.borba.storage.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void salvar(Pedido pedido){
        pedidoRepository.salvar(pedido);
    }

    public Pedido buscarPorId(String id){
        return pedidoRepository.buscarPorId(id);
    }

    public void deletar(String id){
        pedidoRepository.deletar(id);
    }

}
