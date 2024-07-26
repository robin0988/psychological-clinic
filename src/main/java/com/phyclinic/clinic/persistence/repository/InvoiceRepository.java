package com.phyclinic.clinic.persistence.repository;

import com.phyclinic.clinic.persistence.entity.InvoiceEntity;
import com.phyclinic.clinic.persistence.projection.InvoiceSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends ListCrudRepository<InvoiceEntity, Integer> {
    List<InvoiceEntity> findAllByInvoiceDateAfter(LocalDateTime date);
    List<InvoiceEntity> findAllByMethodIn(List<String> methods);
    @Query(nativeQuery = true, value = "SELECT * FROM invoice where patientid = :id")
    List<InvoiceEntity> findPatientInvoice(@Param("id") int patientId );
    @Query(nativeQuery = true, value = "SELECT i.invoice_id," +
            "   p.first_name as patientName," +
            "   i.invoice_date," +
            "   i.total_invoice," +
            "   STRING_AGG(s.service_name,',') as serviceName" +
            " FROM invoice i" +
            " INNER JOIN patient p on i.patientid=p.patientid" +
            " INNER JOIN invoice_item ii on i.invoice_id=ii.invoice_id" +
            " INNER JOIN service s on ii.service_id=s.service_id" +
            " WHERE i.invoice_id= :invoiceId" +
            " GROUP BY i.invoice_id,p.first_name,i.invoice_date,i.total_invoice")
    InvoiceSummary findSummary(@Param("invoiceId") int invoiceId);

    @Procedure(value = "take_random_service_order", outputParameterName = "service_taken")
    boolean saveRandomService(@Param("patientId") int patientId,@Param("method") String method);
}
