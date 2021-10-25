package com.scream.testtask.controllers;

import com.scream.testtask.models.Client;
import com.scream.testtask.service.BankService;
import com.scream.testtask.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final BankService bankService;
    public ClientController(ClientService clientService, BankService bankService){
       this.clientService = clientService;
        this.bankService = bankService;
    }
   @GetMapping("")
    public String clientHome(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "client_list";
    }
    @GetMapping("/create")
    public String addClientForm(Model model, Client client){
        model.addAttribute("banks", bankService.getAllBanks());
        return "client_create";
    }

   @PostMapping("/create")
   public String addClient(@Valid Client client, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("banks", bankService.getAllBanks());
            return "client_create";
        }
        clientService.saveClient(client);
        return "redirect:/client";
   }
   @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") String id){
       clientService.deleteClient(id);
       return "redirect:/client";
   }
    @GetMapping("/update/{id}")
    public String updateCreditForm(@PathVariable("id") String id, Model model){
        model.addAttribute("banks", bankService.getAllBanks());
        model.addAttribute("clients", clientService.getClient(id));
        return "client_update";
    }
    @PostMapping("/update")
    public String updateCredit(@Valid Client client, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("banks", bankService.getAllBanks());
            return "client_update";
        }
        clientService.saveClient(client);
        return "redirect:/client";
    }
}
