package com.phyclinic.clinic.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemId implements Serializable {

    private Integer invoiceId;
    private Integer itemId;

    @Override
    public boolean equals(Object o){
        if(this == o) return  true;
        if(!(o instanceof  InvoiceItemId that))return  false;

        return Objects.equals(invoiceId, that.invoiceId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {return Objects.hash(invoiceId,itemId);}
}
