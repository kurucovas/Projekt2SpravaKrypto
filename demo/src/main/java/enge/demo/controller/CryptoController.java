package enge.demo.controller;

import enge.demo.model.Crypto;
import enge.demo.service.CryptoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cryptos")
public class CryptoController {
    CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @PostMapping("/add")
    public List<Crypto> addCryptos(@RequestBody List<Crypto> cryptos) {
            cryptoService.addCrypto(new Crypto(12345, "Bitcoin", "BTC", 80000, 1000));
            cryptoService.addCrypto(new Crypto(67890, "Ethereum", "ETH", 3500, 100000));
            return cryptoService.cryptos;
        }

    @GetMapping("/list")
    public List<Crypto> getAllCryptoList() {
        return cryptoService.getAllCryptosList();
    }

    @GetMapping("/sort")
    public List<Crypto> sortedCryptoList(@RequestParam String sort) {
        return cryptoService.getSortedCryptos(sort);
    }

    @GetMapping("/{id}")
    public Crypto getCryptoById(@PathVariable("id") int id) {
        Crypto crypto = cryptoService.getCryptoById(id);
        if (crypto != null) {
           return crypto;
        }
        return null;

    }
    @PutMapping("/{id}")
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
