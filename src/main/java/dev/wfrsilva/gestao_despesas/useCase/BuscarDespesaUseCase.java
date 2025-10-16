package dev.wfrsilva.gestao_despesas.useCase;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.wfrsilva.gestao_despesas.entity.Despesa;
import dev.wfrsilva.gestao_despesas.repository.DespesaRepository;

@Service
public class BuscarDespesaUseCase {

    @Autowired
    private DespesaRepository despesaRepository;

    public List <Despesa> execute(String email, LocalDate data)
    {
        List <Despesa> despesas;

        if(data != null)
        {
            despesas = despesaRepository.findByEmailAndData(email, data);
        }//if
        else
        {
            despesas = despesaRepository.findByEmail(email);
        }//else

        return despesas;

    }//buscarPorEmailEData

    
}//BuscarDespesaUseCase
