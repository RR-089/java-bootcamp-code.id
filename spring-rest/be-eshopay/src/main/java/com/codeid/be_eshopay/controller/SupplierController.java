package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.SupplierDTO;
import com.codeid.be_eshopay.service.BaseCrudService;
import com.codeid.be_eshopay.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController extends BaseCrudController<SupplierDTO, Long> {

    private final SupplierService supplierService;

    @Override
    protected BaseCrudService<SupplierDTO, Long> getService() {
        return supplierService;
    }

    @Override
    protected String getEntityName() {
        return "supplier";
    }


}
