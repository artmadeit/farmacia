package pe.edu.cibertec.farmacia.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService {
    DataSource dataSource;

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void downloadReport(HttpServletResponse response, String reportPath) {
         try {
            InputStream inputStream = new ClassPathResource("reports/" + reportPath).getInputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(inputStream);

            // JRDataSource dataSource = new JREmptyDataSource();
            Connection connection = dataSource.getConnection();

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
            connection.close();

            // OutputStream outputStream = new FileOutputStream("hola.pdf");
            response.setContentType("application/pdf");
            OutputStream outputStream =  response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (IOException | JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
