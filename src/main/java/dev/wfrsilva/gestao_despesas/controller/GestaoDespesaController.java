package dev.wfrsilva.gestao_despesas.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.wfrsilva.gestao_despesas.custom_message.ErrorMessage;
import dev.wfrsilva.gestao_despesas.entity.Despesa;
import dev.wfrsilva.gestao_despesas.useCase.BuscarDespesaUseCase;
import dev.wfrsilva.gestao_despesas.useCase.CadastroDespesaUseCase;

@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController {

    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;
    
    @Autowired
    BuscarDespesaUseCase buscarDespesaUseCase;
    

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Despesa despesa)
    {
        try
        {
            var result = cadastroDespesaUseCase.execute(despesa);
        }//try
        catch(IllegalArgumentException e)
        {
            var errorMessage = new ErrorMessage(e.getMessage(), "INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMessage);
        }//catch

    }//create


    @GetMapping("/{email}")
    public List <Despesa> findByEmailAndDate(@PathVariable String email, @RequestParam(required = false) LocalDate data)
    {

        System.out.println("Email: " + email);
        System.out.println("Data: " + data);

        return buscarDespesaUseCase.buscarPorEmailEData(email, data);
        
    }//findByEmailAndDate

    
}//GestaoDespesaController
