package enge.demo.controller;

import enge.demo.model.Crypto;
import enge.demo.service.CryptoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CryptoController {

    CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @PostMapping("/cryptos")
    public void addCryptos(@RequestBody List<Crypto> cryptos) {
            cryptoService.addCryptos(cryptos);
        }

    @GetMapping("/cryptos")
    public List<Crypto> getAllCryptoList(@RequestParam(required = false) String sort) {
        return cryptoService.getAllCryptosList(sort);
    }

    @GetMapping("/cryptos/{id}")
    public Crypto getCryptoById(@PathVariable("id") int id) {
        Crypto crypto = cryptoService.getCryptoById(id);
        if (crypto != null) {
           return crypto;
        }
        return null;
    }

    @PutMapping("/cryptos/{id}")
    public Crypto updateCrypto(@PathVariable("id") int id, @RequestBody Crypto updatedCrypto) {
        Crypto crypto = cryptoService.updateCrypto(id, updatedCrypto);
        if (crypto != null) {
            return crypto;
        }
        return null;
    }

    @GetMapping("/portfolio-value")
    public double getPortfolioValue() {
        return cryptoService.calculatePortfolioValue();
    }
}
