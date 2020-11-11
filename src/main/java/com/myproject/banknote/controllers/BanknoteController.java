package com.myproject.banknote.controllers;

import java.util.List;
import java.util.UUID;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.services.BanknoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javassist.NotFoundException;

@RestController
@RequestMapping("banknote")
public class BanknoteController {
    private final BanknoteService banknoteService;

    @Autowired
    public BanknoteController(BanknoteService banknoteService) {
        this.banknoteService = banknoteService;
    }

    @GetMapping
    public ResponseEntity<List<Banknote>> show() {
        return ResponseEntity.ok(banknoteService.getBanknote());
    }

    @GetMapping("{id}")
    public ResponseEntity<Banknote> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(banknoteService.getBanknoteById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws NotFoundException {
        banknoteService.deleteBanknoteById(id);
        return ResponseEntity.noContent().build();
    }
}
