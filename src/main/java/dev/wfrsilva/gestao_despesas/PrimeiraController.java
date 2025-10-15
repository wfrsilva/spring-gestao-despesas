package dev.wfrsilva.gestao_despesas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javadevweek")
public class PrimeiraController {
    
    @GetMapping("/helloworld")
    public String helloWorld()
    {
        return "Olá Mundo!";

    }//helloWorld

}//PrimeiraController
