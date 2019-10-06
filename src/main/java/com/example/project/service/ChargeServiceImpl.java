package com.example.project.service;

import com.example.project.exception.InvalidAmountException;
import com.example.project.exception.InvalidCurrencyException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.repository.ChargeRepository;
import com.example.project.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ChargeServiceImpl implements ChargeService {

    private final Logger log = Logger.getLogger(this.getClass().getName());

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
    public List<Charge> findChargesByUserIdMonthAndYear(Long id, Integer month, Integer year) {
        return chargeRepository.findByUserIdMonthAndYear(id, month, year);
    }

    @Override
    public List<Charge> findChargesByUserIdMonthAndYearNotPaid(Long id, Integer month, Integer year) {
        return chargeRepository.findByUserIdMonthAndYearNotPaid(id, month, year);
    }

    @Override
    public List<Charge> findChargesByUserIdNotPaid(Long user_id){
        return chargeRepository.findChargesByUserIdNotPaid(user_id);
    }


    @Override
    @Transactional
    public void saveCharge(Charge charge){
        try {
            // Me fijo si hay una factura de ese mes si no la creo.
            Invoice invoice = createOrUpdateInvoice(charge);

            // Inicializo los valores.
            charge.setDebt(charge.getAmount());
            charge.setPaid_out(0);
            charge.setInvoiceId(invoice.getId());
            chargeRepository.save(charge);

        }catch (Exception e){
            log.info(e.getMessage());
        }
            //if (chargeRepository.findByEventId(charge.getEventId()) != null){
                // TODO
            //    throw new InvalidAmountException("ASD");
            //}
            // Elimina toda la factura cuando tendria que eliminar el ultimo cargo
            //invoiceService.deleteInvoiceById(invoice.getId());

        //}

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
    private Invoice createOrUpdateInvoice(Charge charge){

        Calendar cal = Calendar.getInstance();
        cal.setTime(charge.getDate());
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        try {
            Invoice userInvoice = invoiceService.getUserInvoiceByMonthAndYear(charge.getUserId(), month, year);
            userInvoice.setDebt( userInvoice.getDebt() + charge.getAmount() );

            // Actualizo la fila
            return invoiceService.saveInvoice(userInvoice);
        }catch (NullPointerException e){

            // Si no existe la factura entonces la creo
            Invoice invoice = setNewUserInvoice(charge, month, year);
            return invoiceService.saveInvoice(invoice);
        }
    }

}
