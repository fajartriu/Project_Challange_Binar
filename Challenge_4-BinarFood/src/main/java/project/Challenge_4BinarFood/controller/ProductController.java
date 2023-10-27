package project.Challenge_4BinarFood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.Challenge_4BinarFood.model.product.CreateProductRequest;
import project.Challenge_4BinarFood.response.*;
import project.Challenge_4BinarFood.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(
            path = "/api/merchant/{merchantId}/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> createProduct(@PathVariable UUID merchantId, @RequestBody CreateProductRequest request){
        try {
            ProductResponse product = productService.createProduct(merchantId, request);
            return WebResponse.<ProductResponse>builder().countRecord(1).data(product).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<ProductResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @PatchMapping(
            path = "/api/merchant/{merchantId}/products/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductUpdateResponse> updateMerchant(@PathVariable UUID productId, @RequestBody Map<String, Optional> field){
        try {
            ProductUpdateResponse productResponse = productService.updateDetailProduct(productId, field);
            return WebResponse.<ProductUpdateResponse>builder().countRecord(1).data(productResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<ProductUpdateResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/merchant/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> getAllProduct(){
        try {
            List<ProductResponse> allProduct = productService.getAllProduct();
            return WebResponse.<List<ProductResponse>>builder().countRecord(allProduct.size()).data(allProduct).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<ProductResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/merchant/products/pagination/{page}/{pageSize}/{sortData}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> paginationWithSorting(@PathVariable int page, @PathVariable int pageSize, @PathVariable String sortData){
        try {
            List<ProductResponse> paginationWithSortingResponse = productService.paginationWithSorting(page, pageSize, sortData);
            return WebResponse.<List<ProductResponse>>builder().countRecord(paginationWithSortingResponse.size()).data(paginationWithSortingResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<ProductResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/merchant/products/pagination/{page}/{pageSize}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> paginationWithoutSorting(@PathVariable int page, @PathVariable int pageSize){
        try {
            List<ProductResponse> paginationWithoutSortingResponse = productService.paginationWithoutSorting(page, pageSize);
            return WebResponse.<List<ProductResponse>>builder().countRecord(paginationWithoutSortingResponse.size()).data(paginationWithoutSortingResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<ProductResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @DeleteMapping(
            path = "/api/merchant/{merchantId}/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> deleteProduct(@PathVariable UUID productId){
        try {
            productService.deleteProduct(productId);
            return WebResponse.<String>builder().data("Deleted").messages("Success").build();
        }catch (Exception e){
            return WebResponse.<String>builder().data(null).messages(e.getMessage()).build();
        }
    }
}
