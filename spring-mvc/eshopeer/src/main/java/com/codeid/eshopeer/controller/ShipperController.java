package com.codeid.eshopeer.controller;

import com.codeid.eshopeer.model.Shipper;
import com.codeid.eshopeer.service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shippers")
public class ShipperController {
    private final ShipperService shipperService;

    public ShipperController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }


    @GetMapping
    public String findAllShippers(Model model) {
        List<Shipper> shippers = shipperService.findAllShipppers();

        model.addAttribute("shippers", shippers);

        return "shippers/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("shipper", new Shipper());
        model.addAttribute("action", "Create Shipper");
        return "shippers/upsert";
    }

    @PostMapping
    public String createShipper(@Valid @ModelAttribute("shipper") Shipper shipper,
                                BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation error: " + error.getDefaultMessage())
            );

            String action = (shipper.getId() == null) ? "Create Shipper" : "Update " +
                    "Shipper";
            model.addAttribute("action", action);
            return "shippers/upsert";
        }

        shipperService.saveShipper(shipper);

        return "redirect:/shippers";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Shipper> shipper = shipperService.findShipperById(id);

        model.addAttribute("shipper", shipper.get());
        model.addAttribute("action", "Update Shipper");

        return "shippers/upsert";
    }

    @GetMapping("/delete/{id}")
    public String deleteShipper(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        shipperService.deleteShipper(id);

        redirectAttributes.addFlashAttribute("message", "Shipper ID:  " + id +
                " has been successfully deleted.");

        return "redirect:/shippers";
    }


}
