package org.chalenge3.Challenge_3BinarFood.service;

import org.chalenge3.Challenge_3BinarFood.model.Merchant;
import org.chalenge3.Challenge_3BinarFood.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
    @Autowired
    MerchantRepository merchantRepository;

    public List<Merchant> getAll(){
        return merchantRepository.findAll();
    }

    public Merchant create(Merchant merchant){
        merchant.setOpen(true);
        if (merchant.getMerchantName().isEmpty()) return null;
        return merchantRepository.save(merchant);

    }
}
