package project.Challenge_4BinarFood.service;

import org.springframework.stereotype.Service;
import project.Challenge_4BinarFood.model.product.CreateProductRequest;
import project.Challenge_4BinarFood.response.ProductResponse;
import project.Challenge_4BinarFood.response.ProductUpdateResponse;

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
