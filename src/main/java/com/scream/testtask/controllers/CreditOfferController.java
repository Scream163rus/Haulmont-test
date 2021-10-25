package com.scream.testtask.controllers;

import com.scream.testtask.models.CreditOffer;
import com.scream.testtask.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@RequestMapping("/creditOffer")
public class CreditOfferController {

    private final CreditOfferService creditOfferService;
    private final ProcessingCreditService processingCreditService;
    private final ClientService clientService;
    private final CreditService creditService;
    private final PlannedPaymentService plannedPaymentService;

    public CreditOfferController(CreditOfferService creditOfferService,
                                 ProcessingCreditService processingCreditService, ClientService clientService,
                                 CreditService creditService, PlannedPaymentService plannedPaymentService) {
        this.creditOfferService = creditOfferService;
        this.processingCreditService = processingCreditService;
        this.clientService = clientService;
        this.creditService = creditService;
        this.plannedPaymentService = plannedPaymentService;
    }


    @GetMapping()
    public String creditOfferHome(Model model){
        model.addAttribute("creditOffers", creditOfferService.getAll());
        model.addAttribute("plannedPaymentService", plannedPaymentService );
        return "credit_offer_list";
    }
    @GetMapping("/create")
    public String addCreditOfferForm(CreditOffer creditOffer, Model model){
        model.addAttribute("credits", creditService.getAllCredits());
        model.addAttribute("clients", clientService.getAllClients());
        return "credit_offer_create";
    }
    @PostMapping("/create")
    public String addCreditOffer(@RequestParam(name = "month") String month, @Valid CreditOffer creditOffer, BindingResult bindingResult, Model model){
        if(creditOffer.getClient().getBanks().stream().noneMatch(creditOffer.getCredit().getBank()::equals)) {
           bindingResult.rejectValue("credit","creditBank" ,"Client and credit are in different banks");
        }
        if(creditOffer.getCredit().getCreditLimit().compareTo(creditOffer.getSumCredit()) < 0) {
            bindingResult.rejectValue("sumCredit", "errorCreditSum", "Credit amount is greater than the limit amount");
        }

        if(month.isEmpty()){
            bindingResult.reject("month", "Month is mandatory");

        }
        if(bindingResult.hasErrors()){
            model.addAttribute("credits", creditService.getAllCredits());
            model.addAttribute("clients", clientService.getAllClients());
            return "credit_offer_create";
        }
      processingCreditService.creditPayment(creditOfferService.save(creditOffer).getId(), Integer.parseInt(month));
        return "redirect:/creditOffer";
    }
    @GetMapping("/{id}/paymentSchedule")
    public String getPaymentSchedule(@PathVariable(name = "id") String id, Model model){
        model.addAttribute("paymentSchedules", creditOfferService.get(id).getPaymentSchedule());
        return "payment_schedule_list";
    }
    @GetMapping("/delete/{id}")
    public String deleteCreditOffer(@PathVariable(name = "id") String id){
        creditOfferService.deleteById(id);
        return "redirect:/creditOffer";
    }

}
