package Challenge_7BinarFood.Invoice.service;

import Challenge_7BinarFood.Invoice.model.user.User;
import Challenge_7BinarFood.Invoice.DTO.InvoiceResponse;
import Challenge_7BinarFood.Invoice.DTO.ReportMerchantResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface InvoiceService {
    byte[] exportInvoiceUser(String user, String reportFormat) throws FileNotFoundException, JRException;
//    List<ReportMerchantResponse> generateReportMerchant(UUID merchantID, LocalDate date);

    byte[] exportReportMerchant(UUID merchantId,String reportFormat) throws FileNotFoundException, JRException;

    byte[] exportReportMerchantWeek(UUID merchantId, String reportFormat, LocalDate date) throws FileNotFoundException, JRException;

    byte[] exportReportMerchantMonth(UUID merchantId, String reportFormat, String month) throws FileNotFoundException, JRException;

    byte[] exportReportMerchantCustom(UUID merchantId, String reportFormat, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException;
    void updateComplete(UUID orderId);
    byte[] generateReport(List<ReportMerchantResponse> report, String reportFormat) throws FileNotFoundException, JRException;

    byte[] generateReportUser(List<InvoiceResponse> report, String reportFormat) throws FileNotFoundException, JRException;

    List<InvoiceResponse> invoiceOrderByUser(String token);

    User getIdUser(String name);
}
