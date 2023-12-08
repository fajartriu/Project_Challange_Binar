package Challenge_7BinarFood.Invoice.service;

import Challenge_7BinarFood.Invoice.client.OrderClient;
import Challenge_7BinarFood.Invoice.util.JwtUtil;
import Challenge_7BinarFood.Invoice.DTO.InvoiceResponse;
import Challenge_7BinarFood.Invoice.DTO.jwt.JwtFormat;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class InvoiceService_Gagal {
    private final OrderClient orderClient;
    private final JwtUtil jwtUtil;

    public byte[] exportInvoiceUser(String token, String reportFormat) throws FileNotFoundException, JRException, JSONException {
//        token = jwtUtil.getToken(token);
//        String username = jwtUtil.decodeToken(token).getPayload().get("username");
//        System.out.println(username);
//        List<InvoiceOrder> invoiceOrders = orderClient.invoiceUser(username);
//        System.out.println(invoiceOrders);
//        return generateReportUser(invoiceOrders, reportFormat);
        return null;
    }

    public byte[] generateReportUser(List<InvoiceResponse> report, String reportFormat) throws FileNotFoundException, JRException {
        JasperReport jasperReport;

        try {
            jasperReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("InvoiceUsersTemplatesReports"));
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:reports/InvoiceUsersTemplatesReports.jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, "InvoiceUsersTemplatesReports");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("total", "Total Product: "+report.size());
        JasperPrint jasperPrint = null;
        byte[] reportContent;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            switch (reportFormat) {
                case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
                case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }

    public JwtFormat checkingJWT(String token) throws JSONException {
        token = jwtUtil.getToken(token);
        JwtFormat jwtFormat = jwtUtil.decodeToken(token);
        return jwtFormat;
    }

    public List<InvoiceResponse> tesData(String token) throws JSONException {
        token = jwtUtil.getToken(token);
        String username = jwtUtil.decodeToken(token).getPayload().get("username");
        System.out.println(username);
        List<InvoiceResponse> invoiceOrders = orderClient.invoiceUser(username);
        System.out.println(invoiceOrders);
        return invoiceOrders;
    }

//    public List<InvoiceOrder> tesData(Principal principal) {
////        token = jwtUtil.getToken(token);
////        String username = jwtUtil.decodeToken(token).getPayload().get("username");
////        System.out.println(username);
//        List<InvoiceOrder> invoiceOrders = orderClient.invoiceUser(principal);
//        System.out.println(invoiceOrders);
//        return invoiceOrders;
//    }
}
