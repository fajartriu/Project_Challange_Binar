package project.Challenge_4BinarFood.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;
import project.Challenge_4BinarFood.response.InvoiceResponse;
import project.Challenge_4BinarFood.response.ReportMerchantResponse;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public interface InvoiceService {
    String exportInvoiceUser(UUID userId, String reportFormat) throws FileNotFoundException, JRException;
//    List<ReportMerchantResponse> generateReportMerchant(UUID merchantID, LocalDate date);

    String exportReportMerchant(UUID merchantId,String reportFormat) throws FileNotFoundException, JRException;

    String exportReportMerchantWeek(UUID merchantId, String reportFormat, LocalDate date) throws FileNotFoundException, JRException;

    String exportReportMerchantMonth(UUID merchantId, String reportFormat, String month) throws FileNotFoundException, JRException;

    String exportReportMerchantCustom(UUID merchantId, String reportFormat, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException;
    void updateComplete(UUID orderId);
    void generateReport(List<ReportMerchantResponse> report, String reportFormat) throws FileNotFoundException, JRException;
}
