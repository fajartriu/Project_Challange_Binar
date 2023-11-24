package project.Challenge_6BinarFood.controller;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Challenge_6BinarFood.service.invoice.InvoiceService;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping(
            path = "/reportMerchantAllPayOrNot/{merchantId}/{format}"
    )
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<Resource> exportReportMerchant(@PathVariable String format, @PathVariable UUID merchantId) throws JRException, FileNotFoundException {
        byte[] bytes = invoiceService.exportReportMerchant(merchantId, format);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("item-report." + format)
                                .build().toString())
                .body(resource);
    }

    @PostMapping(
            path = "/reportMerchantWeek/{merchantId}/{date}/{format}"
    )
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<Resource> exportReportMerchantWeek(@PathVariable String format, @PathVariable UUID merchantId, @PathVariable LocalDate date) throws JRException, FileNotFoundException {
        byte[] bytes = invoiceService.exportReportMerchantWeek(merchantId, format, date);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("item-report." + format)
                                .build().toString())
                .body(resource);
    }

    @PostMapping(
            path = "/reportMerchantMonth/{merchantId}/{month}/{format}"
    )
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<Resource> exportReportMerchantMonth(@PathVariable String format, @PathVariable UUID merchantId, @PathVariable String month) throws JRException, FileNotFoundException {
        byte[] bytes = invoiceService.exportReportMerchantMonth(merchantId, format, month);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("item-report." + format)
                                .build().toString())
                .body(resource);
    }

    @PostMapping(
            path = "/reportMerchantCustom/{merchantId}/{startDate}/{endDate}/{format}"
    )
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<Resource> exportReportMerchantCustom(@PathVariable String format, @PathVariable UUID merchantId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws JRException, FileNotFoundException {
        byte[] bytes = invoiceService.exportReportMerchantCustom(merchantId, format, startDate, endDate);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("item-report." + format)
                                .build().toString())
                .body(resource);
    }

    @PostMapping(
            path = "/reportUserInvoice/{format}"
    )
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Resource> exportInvoiceUser(@PathVariable String format, Principal principal) throws JRException, FileNotFoundException {
        byte[] bytes = invoiceService.exportInvoiceUser(principal, format);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("item-report." + format)
                                .build().toString())
                .body(resource);
    }
}
