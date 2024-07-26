package com.phyclinic.clinic.persistence.projection;

import java.time.LocalDateTime;

public interface InvoiceSummary {

    Integer getInvoiceId();
    String getPatientName();
    LocalDateTime getInvoiceDate();
    String getServiceName();
}
