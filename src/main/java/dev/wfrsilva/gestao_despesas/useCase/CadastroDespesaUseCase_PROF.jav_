///CODIGO PROFESSORA

package dev.wfrsilva.gestao_despesas.useCase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.wfrsilva.gestao_despesas.entity.Despesa;
import dev.wfrsilva.gestao_despesas.repository.DespesaRepository;

@Service
public class CadastroDespesaUseCase {
    // SOLID
    // Single Responsability Principle

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa execute(Despesa despesa) {

        if (despesa.getCategoria() == null || despesa.getData() == null || despesa.getDescricao() == null
                || despesa.getEmail() == null) {

            throw new IllegalArgumentException("Preencher todos os campos");
        }

        despesa = despesaRepository.save(despesa);
        return despesa;

    }

}