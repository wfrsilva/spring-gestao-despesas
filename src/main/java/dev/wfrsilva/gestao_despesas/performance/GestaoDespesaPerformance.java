package dev.wfrsilva.gestao_despesas.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wfrsilva.gestao_despesas.entity.Despesa;
import dev.wfrsilva.gestao_despesas.repository.DespesaRepository;

@RequestMapping("/gestao/performance")
@RestController
@EnableCaching
public class GestaoDespesaPerformance {

    @Autowired
    DespesaRepository repository;

    @GetMapping("/sem-paginacao")
    public ResponseEntity<List<Despesa>> listarSemPaginacao() 
    {
        long inicio = System.currentTimeMillis();
        var despesas = repository.findAll();

        long fim = System.currentTimeMillis();
        System.out.println("Tempo (sem paginação): " + (fim - inicio) + " ms");
        return ResponseEntity.ok(despesas);
    }//listarSemPaginacao

    @GetMapping("/com-paginacao")
    public ResponseEntity<Page<Despesa>> listarComPaginacao(Pageable pageable)
    {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var despesas = repository.findAll(pageable);
        stopWatch.stop();

        System.out.println("Tempo (compaginacao): " + stopWatch.getTotalTimeMillis() + " ms");
        return ResponseEntity.ok(despesas);


    }//listarComPaginacao

    @GetMapping("/com-paginacao/{email}")
    public ResponseEntity <Page <Despesa> > listarComPaginacao(@PathVariable String email, Pageable pageable)
    {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var despesas = repository.findByEmail(email, pageable);
        stopWatch.stop();

        System.out.println("Tempo (com paginação): " + stopWatch.getTotalTimeMillis() + " ms");

        return ResponseEntity.ok(despesas);

    }//listarComPaginacao/{email}


    @Cacheable(value = "gastosPorEmailCache", key = "#email + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-'")
    @GetMapping("/cache/{email}")
    public ResponseEntity < Page <Despesa> > cacheComPaginacao(@PathVariable String email, Pageable pageable)
    {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var despesas = repository.findByEmail(email, pageable);
        stopWatch.stop();

        System.out.println("Tempo (com paginação): " + stopWatch.getTotalTimeMillis() + " ms");

        return ResponseEntity.ok(despesas);


    }//cacheComPaginacao 
    
}//GestaoDespesaPerformance
