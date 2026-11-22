package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.exceptions.DadoInvalidoException;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ItemPeca;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Peca;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemPecaService {

    public ItemPeca criar(Peca peca, BigDecimal desconto, Integer quantidade, String observacoes) {

        ItemPeca itemPeca = new ItemPeca();
        itemPeca.setPeca(peca);
        itemPeca.setValorCustoUnitario(peca.getCusto());
        itemPeca.setValorUnitario(peca.getValorVenda());
        itemPeca.setDesconto(desconto);
        itemPeca.setQuantidade(quantidade);
        itemPeca.setValorTotal(calcularValorTotal(desconto, quantidade, itemPeca.getValorUnitario()));
        itemPeca.setObservacoes(observacoes);

        return itemPeca;
    }

    private BigDecimal calcularValorTotal(BigDecimal desconto, Integer quantidade, BigDecimal valorUnitario) {

        verificarQuantidade(quantidade);

        if (desconto == null || desconto.compareTo(BigDecimal.ZERO) < 0) {

            desconto = BigDecimal.ZERO;
        }

        return valorUnitario.multiply(BigDecimal.valueOf(quantidade)).subtract(desconto);
    }

    private void verificarQuantidade(Integer quantidade) {

        if (quantidade == null || quantidade <= 0) {
            throw new DadoInvalidoException("Por favor informe quantidade");
        }
    }
}
