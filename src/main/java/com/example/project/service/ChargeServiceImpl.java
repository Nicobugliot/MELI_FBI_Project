package com.example.project.service;

import com.example.project.exception.InvalidCurrencyException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.repository.ChargeRepository;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeRepository chargeRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public List<Charge> listCharges(){
        return chargeRepository.findAll();
    }

    @Override
    public List<Charge> findChargesByUserId(Long id) {
        return chargeRepository.findByUserId(id);
    }

    @Override
    public List<Charge> findChargesByUserIdAndMonth(Long id, Integer month) {
        return chargeRepository.findByUserIdAndMonth(id, month);
    }

    @Override
    public Double getTotalCharges(Long id){
        return chargeRepository.getTotalCharges(id);
    }

    @Override
    public void saveCharge(Charge charge){
        if (UtilValidator.validateCurrency(charge.getCurrency())){
            throw new InvalidCurrencyException("Currency is wrong, the values accepted are 'USD' or 'AR'");
        }
        if (UtilValidator.validateEventType(charge.getEventType())){
            throw new InvalidEventTypeException("Event type is wrong");
        }

        // Inicializo los valores.
        charge.setDebt(charge.getAmount());
        charge.setPaid_out(0);
        chargeRepository.save(charge);

        createOrUpdateInvoice(charge);

    }

    @Override
    public void updateAllCharges(List<Charge> charges){
        chargeRepository.saveAll(charges);
    }


    // ----------- METODOS PRIVADOS -----------


    private Invoice setNewUserInvoice(Charge charge, Integer month, Integer year){
        Invoice invoice = new Invoice();
        invoice.setDebt(charge.getAmount());
        invoice.setUserId(charge.getUserId());
        invoice.setMonth(month);
        invoice.setYear(year);

        return invoice;
    }

    // Me fijo que la factura exista, si no, la creo
    private void createOrUpdateInvoice(Charge charge){

        Calendar cal = Calendar.getInstance();
        cal.setTime(charge.getDate());
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        try {
            Invoice userInvoice = invoiceService.getUserInvoiceByMonthAndYear(charge.getUserId(), month, year);
            userInvoice.setDebt( userInvoice.getDebt() + charge.getAmount() );

            // Actualizo la fila
            invoiceService.saveInvoice(userInvoice);
        }catch (NullPointerException e){

            // Si no existe la factura entonces la creo
            Invoice invoice = setNewUserInvoice(charge, month, year);
            invoiceService.saveInvoice(invoice);
        }
    }

}
