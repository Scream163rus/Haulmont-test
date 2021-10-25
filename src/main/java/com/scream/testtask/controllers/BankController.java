package com.scream.testtask.controllers;

import com.scream.testtask.models.Bank;
import com.scream.testtask.service.BankService;
import com.scream.testtask.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;
    private final ClientService clientService;
    public BankController(BankService bankService, ClientService clientService){
        this.bankService = bankService;
        this.clientService = clientService;
    }
    @GetMapping("")
    public String bankHome(Model model){
        model.addAttribute("banks", bankService.getAllBanks());
        return "bank_list";
    }
    @GetMapping("/create")
    public String addBankForm(Model model, Bank bank){
        model.addAttribute("clients", clientService.getAllClients());
        return "bank_create";
    }

    @PostMapping("/create")
    public String addBank(@Valid Bank bank, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("clients", clientService.getAllClients());
            return "bank_create";
        }
        bankService.saveBank(bank);
        return "redirect:/bank";
    }
    @GetMapping("/delete/{id}")
    public String deleteBank(@PathVariable("id") String id){
        bankService.deleteBank(id);
        return "redirect:/bank";
    }
    @GetMapping("/update/{id}")
    public String updateBankForm(@PathVariable("id") String id, Model model){
        model.addAttribute("bank", bankService.getBank(id));
        model.addAttribute("clientsBank", clientService.getAllClients());
        return "bank_update";
    }
    @PostMapping("/update")
    public String updateBank(@Valid Bank bank, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "bank_update";
        }
        bankService.saveBank(bank);
        return "redirect:/bank";
    }
}
