package project.Challenge_4BinarFood.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;
import project.Challenge_4BinarFood.entity.Order;
import project.Challenge_4BinarFood.model.invoice.InvoiceOrderRequest;
import project.Challenge_4BinarFood.model.invoice.ReportMerchantRequest;
import project.Challenge_4BinarFood.repository.MerchantRepository;
import project.Challenge_4BinarFood.repository.OrderDetailRepository;
import project.Challenge_4BinarFood.repository.OrderRepository;
import project.Challenge_4BinarFood.response.InvoiceResponse;
import project.Challenge_4BinarFood.response.ReportMerchantResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    private final static Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);
    private final MerchantRepository merchantRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final OrderRepository orderRepository;

    public InvoiceServiceImpl(MerchantRepository merchantRepository, OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
        this.merchantRepository = merchantRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public String exportInvoiceUser(UUID userId, String reportFormat) throws FileNotFoundException, JRException{
        String path = "report";
        List<InvoiceOrderRequest> allUserOrderDetailInvoice = orderDetailRepository.getAllUserOrderDetailInvoice(userId);
        List<InvoiceResponse> invoiceResponses = new ArrayList<>();

        if (!allUserOrderDetailInvoice.isEmpty()){
            Double sumTotal = 0.0;
            for (InvoiceOrderRequest invoiceOrderRequest : allUserOrderDetailInvoice) {
                if (!invoiceOrderRequest.isCompleted()){
                    updateComplete(invoiceOrderRequest.getOrderId());
                    sumTotal+=invoiceOrderRequest.getTotalPrice();
                    invoiceResponses.add(new InvoiceResponse(invoiceOrderRequest.getOrderTime().toLocalDate(), invoiceOrderRequest.getOrderTime().toLocalTime(),
                            invoiceOrderRequest.getUsername(), invoiceOrderRequest.getProductName(),
                            invoiceOrderRequest.getMerchantName(), invoiceOrderRequest.getDestinationAddress(),
                            true, invoiceOrderRequest.getPrice(),
                            invoiceOrderRequest.getQuantity(), invoiceOrderRequest.getPrice(),
                            sumTotal
                    ));
                }else{
                    sumTotal+=invoiceOrderRequest.getTotalPrice();
                    invoiceResponses.add(new InvoiceResponse(invoiceOrderRequest.getOrderTime().toLocalDate(), invoiceOrderRequest.getOrderTime().toLocalTime(),
                            invoiceOrderRequest.getUsername(), invoiceOrderRequest.getProductName(),
                            invoiceOrderRequest.getMerchantName(), invoiceOrderRequest.getDestinationAddress(),
                            invoiceOrderRequest.isCompleted(), invoiceOrderRequest.getPrice(),
                            invoiceOrderRequest.getQuantity(), invoiceOrderRequest.getPrice(),
                            sumTotal
                    ));
                }
            }
        }
        else {
            logger.info("empty");
        }
        File file = ResourceUtils.getFile("classpath:reports/InvoiceUsersTemplatesReports.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceResponses);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created by", "Fajar TU");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\InvoiceUser.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\InvoiceUser.pdf");
        }
        return "report generated in path : " + path;

    }

//    @Override
//    public List<ReportMerchantResponse> generateReportMerchant(UUID merchantID, LocalDate date) {
//        List<ReportMerchantRequest> allReportMerchant = merchantRepository.getAllReportMerchant(merchantID);
//        List<ReportMerchantResponse> reportMerchantResponses = new ArrayList<>();
//
//        if (!allReportMerchant.isEmpty()){
//            Double sumTotal = 0.0;
//            int iter = 0;
//            for (ReportMerchantRequest reportMerchantRequest : allReportMerchant) {
//                if ((reportMerchantRequest.getOrder_time().toLocalDate()).isBefore(date.plusDays(1))
//                        && (reportMerchantRequest.getOrder_time().toLocalDate()).isAfter(date.minusDays(8))){
//                    sumTotal+=reportMerchantRequest.getTotal_price();
//                    reportMerchantResponses.add(new ReportMerchantResponse(reportMerchantRequest.getId_merchant(),
//                            reportMerchantRequest.getMerchant_name(), reportMerchantRequest.getMerchant_location(),
//                            reportMerchantRequest.getOrder_time().toLocalDate(), reportMerchantRequest.getOrder_time().toLocalTime(),
//                            reportMerchantRequest.getId_order_detail(), reportMerchantRequest.getUsername(),
//                            reportMerchantRequest.getDestination_address(), reportMerchantRequest.getId_product(),
//                            reportMerchantRequest.getProduct_name(), reportMerchantRequest.isCompleted(),
//                            reportMerchantRequest.getPrice(), reportMerchantRequest.getQuantity(),
//                            reportMerchantRequest.getTotal_price(), sumTotal
//                    ));
//                    iter++;
//                }
//
//            }
//        }
//
//        return reportMerchantResponses;
//    }

    @Override
    public String exportReportMerchant(UUID merchantId, String reportFormat) throws FileNotFoundException, JRException {
        String path = "report";
        List<ReportMerchantRequest> allReportMerchant = merchantRepository.getAllReportMerchant(merchantId);
        List<ReportMerchantResponse> reportMerchantResponses = new ArrayList<>();

        if (!allReportMerchant.isEmpty()) {
            Double sumTotal = 0.0;
            for (ReportMerchantRequest reportMerchantRequest : allReportMerchant) {
                sumTotal+=reportMerchantRequest.getTotal_price();
                reportMerchantResponses.add(new ReportMerchantResponse(reportMerchantRequest.getId_merchant(),
                        reportMerchantRequest.getMerchant_name(), reportMerchantRequest.getMerchant_location(),
                        reportMerchantRequest.getOrder_time().toLocalDate(), reportMerchantRequest.getOrder_time().toLocalTime(),
                        reportMerchantRequest.getId_order_detail(), reportMerchantRequest.getUsername(),
                        reportMerchantRequest.getDestination_address(), reportMerchantRequest.getId_product(),
                        reportMerchantRequest.getProduct_name(), reportMerchantRequest.isCompleted(),
                        reportMerchantRequest.getPrice(), reportMerchantRequest.getQuantity(),
                        reportMerchantRequest.getTotal_price(), sumTotal
                ));
            }
        }
        generateReport(reportMerchantResponses, reportFormat);
        return "report generated in path : " + path;
    }

    @Override
    public String exportReportMerchantWeek(UUID merchantId, String reportFormat, LocalDate date) throws FileNotFoundException, JRException {
        String path = "report";
        List<ReportMerchantRequest> allReportMerchant = merchantRepository.getAllReportMerchant(merchantId);
        List<ReportMerchantResponse> reportMerchantResponses = new ArrayList<>();

        if (!allReportMerchant.isEmpty()){
            Double sumTotal = 0.0;
            for (ReportMerchantRequest reportMerchantRequest : allReportMerchant) {
                if ((reportMerchantRequest.getOrder_time().toLocalDate()).isBefore(date.plusDays(1))
                        && (reportMerchantRequest.getOrder_time().toLocalDate()).isAfter(date.minusDays(8))){
                    sumTotal = checkingCompleted(reportMerchantResponses, sumTotal, reportMerchantRequest);
                }

            }
        }
        generateReport(reportMerchantResponses, reportFormat);
        return "report generated in path : " + path;
    }

    @Override
    public String exportReportMerchantMonth(UUID merchantId, String reportFormat, String month)
            throws FileNotFoundException, JRException {
        String path = "report";
        List<ReportMerchantRequest> allReportMerchant = merchantRepository.getAllReportMerchant(merchantId);
        List<ReportMerchantResponse> reportMerchantResponses = new ArrayList<>();

        if (!allReportMerchant.isEmpty()){
            Double sumTotal = 0.0;
            for (ReportMerchantRequest reportMerchantRequest : allReportMerchant) {
                if ((reportMerchantRequest.getOrder_time().toLocalDate()).getMonth().toString().toLowerCase().equals(month)){
                    sumTotal = checkingCompleted(reportMerchantResponses, sumTotal, reportMerchantRequest);
                }

            }
        }
        generateReport(reportMerchantResponses, reportFormat);
        return "report generated in path : " + path;
    }

    @Override
    public String exportReportMerchantCustom(UUID merchantId, String reportFormat, LocalDate startDate, LocalDate endDate)
            throws FileNotFoundException, JRException {
        String path = "report";
        List<ReportMerchantRequest> allReportMerchant = merchantRepository.getAllReportMerchant(merchantId);
        List<ReportMerchantResponse> reportMerchantResponses = new ArrayList<>();

        if (!allReportMerchant.isEmpty()){
            Double sumTotal = 0.0;
            for (ReportMerchantRequest reportMerchantRequest : allReportMerchant) {
                if ((reportMerchantRequest.getOrder_time().toLocalDate()).isBefore(endDate)
                        && (reportMerchantRequest.getOrder_time().toLocalDate()).isAfter(startDate)){
                    sumTotal = checkingCompleted(reportMerchantResponses, sumTotal, reportMerchantRequest);
                }

            }
        }
        generateReport(reportMerchantResponses, reportFormat);
        return "report generated in path : " + path;
    }

    @Override
    public void updateComplete(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not found"));
        order.setCompleted(true);
        order.setOrder_time(order.getOrder_time());
        order.setDestination_address(order.getDestination_address());
        orderRepository.save(order);
    }

    @Override
    public void generateReport(List<ReportMerchantResponse> report, String reportFormat) throws FileNotFoundException, JRException{
        String path = "report";
        File file = ResourceUtils.getFile("classpath:reports/MerchantTemplatesReports.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created by", "Fajar TU");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\MerchantReport.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\MerchantReport.pdf");
        }
    }

    public Double checkingCompleted(List<ReportMerchantResponse> reportMerchantResponses, Double sumTotal, ReportMerchantRequest reportMerchantRequest) {
        if (reportMerchantRequest.isCompleted()){
            sumTotal+=reportMerchantRequest.getTotal_price();
            reportMerchantResponses.add(new ReportMerchantResponse(reportMerchantRequest.getId_merchant(),
                    reportMerchantRequest.getMerchant_name(), reportMerchantRequest.getMerchant_location(),
                    reportMerchantRequest.getOrder_time().toLocalDate(), reportMerchantRequest.getOrder_time().toLocalTime(),
                    reportMerchantRequest.getId_order_detail(), reportMerchantRequest.getUsername(),
                    reportMerchantRequest.getDestination_address(), reportMerchantRequest.getId_product(),
                    reportMerchantRequest.getProduct_name(), true,
                    reportMerchantRequest.getPrice(), reportMerchantRequest.getQuantity(),
                    reportMerchantRequest.getTotal_price(), sumTotal
            ));
        }
        return sumTotal;
    }


}
