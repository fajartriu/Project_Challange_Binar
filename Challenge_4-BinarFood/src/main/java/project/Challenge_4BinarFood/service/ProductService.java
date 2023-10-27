package project.Challenge_4BinarFood.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.Challenge_4BinarFood.entity.Merchant;
import project.Challenge_4BinarFood.entity.Product;
import project.Challenge_4BinarFood.model.product.CreateProductRequest;
import project.Challenge_4BinarFood.response.ProductResponse;
import project.Challenge_4BinarFood.response.ProductUpdateResponse;
import project.Challenge_4BinarFood.respository.MerchantRepository;
import project.Challenge_4BinarFood.respository.ProductRepository;

import java.util.*;

@Service
public interface ProductService {
    ProductResponse createProduct(UUID merchantId, CreateProductRequest request);
    ProductUpdateResponse updateDetailProduct(UUID productId, Map<String, Optional> field);
    List<ProductResponse> getAllProduct();
    void deleteProduct(UUID productId);
    List<ProductResponse> paginationWithSorting(int page, int pageSize, String data);
    List<ProductResponse> paginationWithoutSorting(int page, int pageSize);
}
