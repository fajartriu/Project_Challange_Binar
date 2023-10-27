package project.Challenge_4BinarFood.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.Challenge_4BinarFood.entity.Merchant;
import project.Challenge_4BinarFood.model.merchant.GetAllOpenMerchantRequest;
import project.Challenge_4BinarFood.response.MerchantResponse;
import project.Challenge_4BinarFood.respository.MerchantRepository;
import project.Challenge_4BinarFood.model.merchant.CreateMerchantRequest;

import java.util.*;

@Service
public interface MerchantService {
    MerchantResponse createMerchant(CreateMerchantRequest request);
    List<MerchantResponse> getAllOpenMerchant(GetAllOpenMerchantRequest request);
    MerchantResponse updateMerchant(UUID id, Map<String, Optional> field);

}
