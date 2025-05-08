package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ShipperDTO;
import com.codeid.be_eshopay.service.BaseCrudService;
import com.codeid.be_eshopay.service.ShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shippers")
@RequiredArgsConstructor
public class ShipperController extends BaseCrudController<ShipperDTO, Long> {

    private final ShipperService shipperService;

    @Override
    protected BaseCrudService<ShipperDTO, Long> getService() {
        return shipperService;
    }

    @Override
    protected String getEntityName() {
        return "shipper";
    }
}
