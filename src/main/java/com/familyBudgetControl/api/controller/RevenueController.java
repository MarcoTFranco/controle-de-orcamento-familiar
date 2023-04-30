package com.familyBudgetControl.api.controller;

import com.familyBudgetControl.api.model.revenue.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/receitas")
public class RevenueController {

    @Autowired
    private RevenueRepository revenueRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var revenue = revenueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(new RevenueResponse(revenue));
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid RevenueRequest request,
                                 UriComponentsBuilder uriComponentsBuilder) {
        var revenue = new Revenue(request);
        revenueRepository.save(revenue);
        var uri = uriComponentsBuilder.path("/receitas/{id}").buildAndExpand(revenue.getId()).toUri();
        return ResponseEntity.created(uri).body(new RevenueResponse(revenue));
    }

    @GetMapping
    public ResponseEntity findAll() {
        var list = revenueRepository.findAll();
        var listResponse = list.stream()
                .map(RevenueResponse::new)
                .toList();
        return ResponseEntity.ok(listResponse);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody @Valid UpdateRevenue updateRevenue) {
        var revenue = revenueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        revenue.update(updateRevenue);
        System.out.println("ok");
        return ResponseEntity.ok(new RevenueResponse(revenue));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var revenue = revenueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        revenueRepository.delete(revenue);
        return ResponseEntity.noContent().build();
    }

}
