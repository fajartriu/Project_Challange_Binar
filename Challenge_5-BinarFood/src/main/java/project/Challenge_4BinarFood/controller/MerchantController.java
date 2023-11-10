package project.Challenge_4BinarFood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.Challenge_4BinarFood.model.merchant.GetAllOpenMerchantRequest;
import project.Challenge_4BinarFood.response.MerchantResponse;
import project.Challenge_4BinarFood.response.OrderResponse;
import project.Challenge_4BinarFood.response.UsersResponse;
import project.Challenge_4BinarFood.response.WebResponse;
import project.Challenge_4BinarFood.service.MerchantService;
import project.Challenge_4BinarFood.model.merchant.CreateMerchantRequest;
import project.Challenge_4BinarFood.service.MerchantServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping(
            path = "/api/merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MerchantResponse> register(@RequestBody CreateMerchantRequest request){
        try {
            MerchantResponse merchantResponse = merchantService.createMerchant(request);
            return WebResponse.<MerchantResponse>builder().countRecord(1).data(merchantResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<MerchantResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @PostMapping(
            path = "/api/merchant/getAllMerchantOpenOrClose",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<MerchantResponse>> getAllMerchantOpen(@RequestBody GetAllOpenMerchantRequest request){
        try {
            List<MerchantResponse> allOpenMerchant = merchantService.getAllOpenMerchant(request);
            return WebResponse.<List<MerchantResponse>>builder().countRecord(allOpenMerchant.size()).data(allOpenMerchant).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<MerchantResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @PatchMapping(
            path = "/api/merchant/{merchantId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MerchantResponse> updateMerchant(@PathVariable UUID merchantId, @RequestBody Map<String, Optional> field){
        try {
            merchantId = UUID.fromString(merchantId.toString());
            MerchantResponse merchantResponse = merchantService.updateMerchant(merchantId, field);
            return WebResponse.<MerchantResponse>builder().countRecord(1).data(merchantResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<MerchantResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/merchant/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MerchantResponse> getMerchantById(@PathVariable UUID merchantId){
        try {
            MerchantResponse merchantById = merchantService.getMerchantById(merchantId);
            return WebResponse.<MerchantResponse>builder().countRecord(1).data(merchantById).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<MerchantResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @DeleteMapping(
            path = "/api/merchant/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> deleteMerchant(@PathVariable UUID merchantId){
        try {
            merchantService.deleteMerchant(merchantId);
            return WebResponse.<String>builder().data("Deleted").messages("Success").build();
        }catch (Exception e){
            return WebResponse.<String>builder().data(null).messages(e.getMessage()).build();
        }
    }

}
