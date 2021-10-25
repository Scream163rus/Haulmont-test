package com.scream.testtask.controllers;

import com.scream.testtask.models.Credit;
import com.scream.testtask.service.BankService;
import com.scream.testtask.service.CreditService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;
    private final BankService bankService;
    public CreditController(CreditService creditService, BankService bankService){
        this.creditService = creditService;
        this.bankService = bankService;
    }
    @GetMapping()
    public String creditHome(Model model){
        model.addAttribute("credits", creditService.getAllCredits());
        return "credit_list";
    }
    @GetMapping("/create")
    public String addCreditForm(Credit credit, Model model){
        model.addAttribute("banks", bankService.getAllBanks());
        return "credit_create";
    }

    @PostMapping("/create")
    public String addCredit(@Valid Credit credit, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("banks", bankService.getAllBanks());
            return "credit_create";
        }
        creditService.saveCredit(credit);
        return "redirect:/credit";
    }
    @GetMapping("/delete/{id}")
    public String deleteCredit(@PathVariable("id") String id){
        creditService.deleteCredit(id);
        return "redirect:/credit";
    }
    @GetMapping("/update/{id}")
    public String updateCreditForm(@PathVariable("id") String id, Model model){
        model.addAttribute("banks", bankService.getAllBanks());
        model.addAttribute("credit", creditService.getCredit(id));
        return "credit_update";
    }
    @PostMapping("/update")
    public String updateCredit(@Valid Credit credit, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("banks", bankService.getAllBanks());
            return "credit_update";
        }
        creditService.saveCredit(credit);
        return "redirect:/credit";
    }
}
