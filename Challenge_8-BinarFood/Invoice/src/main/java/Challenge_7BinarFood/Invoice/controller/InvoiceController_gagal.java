//package Challenge_7BinarFood.Invoice.controller;
//
//import Challenge_7BinarFood.Invoice.service.InvoiceService_Gagal;
//import DTO.InvoiceResponse;
//import DTO.jwt.JwtFormat;
//import lombok.RequiredArgsConstructor;
//import net.sf.jasperreports.engine.JRException;
//import org.json.JSONException;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.ContentDisposition;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
////import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.FileNotFoundException;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v2/invoice")
//public class InvoiceController_gagal {
//    private final InvoiceService_Gagal invoiceService;
//    @PostMapping(
//            path = "/reportUserInvoice/{format}"
//    )
////    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<Resource> exportInvoiceUser(@PathVariable String format, @RequestHeader(value = "Authorization") String token) throws JRException, FileNotFoundException, JSONException {
//        byte[] bytes = invoiceService.exportInvoiceUser(token, format);
//        ByteArrayResource resource = new ByteArrayResource(bytes);
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(resource.contentLength())
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        ContentDisposition.attachment()
//                                .filename("item-report." + format)
//                                .build().toString())
//                .body(resource);
//    }
//
//    @GetMapping("tes")
//    public JwtFormat tes(@RequestHeader(value = "Authorization") String token) throws JSONException {
//        System.out.println(token);
//        JwtFormat jwtFormat = invoiceService.checkingJWT(token);
//        return jwtFormat;
//    }
//
//    @GetMapping("tes2")
//    public List<InvoiceResponse> tes2(@RequestHeader(value = "Authorization") String token) throws JSONException {
//        return invoiceService.tesData(token);
//    }
//
////    @GetMapping("tes2")
////    public List<InvoiceOrder> tes2(Principal principal) {
////        return invoiceService.tesData(principal);
////    }
//}
