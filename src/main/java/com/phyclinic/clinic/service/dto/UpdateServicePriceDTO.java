package com.phyclinic.clinic.service.dto;

import lombok.Data;

@Data
public class UpdateServicePriceDTO {

    private int serviceId;
    private double newPrice;
}
