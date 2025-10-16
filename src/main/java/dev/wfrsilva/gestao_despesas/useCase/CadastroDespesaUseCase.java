package dev.wfrsilva.gestao_despesas.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.wfrsilva.gestao_despesas.entity.Despesa;
import dev.wfrsilva.gestao_despesas.repository.DespesaRepository;


@Service
public class CadastroDespesaUseCase {
    //controller recebe informacao e envia para o useCase

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa execute(Despesa despesa)
    {

        System.out.println("===== ANTES DE SALVAR ====");
        System.out.println(despesa);

        if(despesa.getCategoria() == null || despesa.getData() == null || despesa.getDescricao() == null || despesa.getEmail() == null )
        {
            throw new IllegalArgumentException("Preencher todos os campos!");
        }//if

        despesa = despesaRepository.save(despesa);

        System.out.println("===== DEPOIS DE SALVAR ====");
        System.out.println(despesa);

        return despesa;

    }//execute

    
    
}//CadastroDespesaUseCase
