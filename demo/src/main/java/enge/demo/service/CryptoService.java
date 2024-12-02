package enge.demo.service;

import enge.demo.model.Crypto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CryptoService {

    public List<Crypto> cryptos = new ArrayList<>();

    public void addCrypto(Crypto crypto) {
        cryptos.add(crypto);
    }

    public List<Crypto> getAllCryptosList() {
        return cryptos;
    }

    public void sort() {
        cryptos.sort(null);
    }

    public void sort(Comparator<Crypto> comparator) {
        cryptos.sort(comparator);
    }

    public List<Crypto> getSortedCryptos(String sort) {
        if (sort.equals("price")) {
            cryptos.sort(Comparator.comparing(Crypto::getPrice));
        } else if (sort.equals("name")) {
            cryptos.sort(Comparator.comparing(Crypto::getName));
        } else if (sort.equals("quantity")) {
            cryptos.sort(Comparator.comparing(Crypto::getName));
        }
        return cryptos;
    }

    public Crypto getCryptoById(int id) {
        return cryptos.stream()
                .filter(crypto -> crypto.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Crypto updateCrypto(int id, Crypto updatedCrypto) {
        for (int i = 0; i < cryptos.size(); i++) {
            Crypto crypto = cryptos.get(i);
            if (crypto.getId() == id) {
                crypto.setName(updatedCrypto.getName());
                crypto.setSymbol(updatedCrypto.getSymbol());
                crypto.setPrice(updatedCrypto.getPrice());
                crypto.setQuantity(updatedCrypto.getQuantity());
                return crypto;
            }
        }
        return null;
    }

    public double calculatePortfolioValue() {
        double totalValue = 0.0;
        for (Crypto crypto : cryptos) {
            totalValue += crypto.getPrice() * crypto.getQuantity();
        }
        return totalValue;
    }
}
