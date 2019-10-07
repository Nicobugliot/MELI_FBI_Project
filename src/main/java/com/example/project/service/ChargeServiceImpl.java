package com.example.project.service;

import com.example.project.model.Charge;
import com.example.project.model.Invoice;
import com.example.project.repository.ChargeRepository;
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
        Invoice invoice = createOrUpdateInvoice(charge);

        charge.setDebt(charge.getAmount());
        charge.setPaid_out(0);
        charge.setInvoiceId(invoice.getId());
        chargeRepository.save(charge);
    }

    @Override
    public void updateAllCharges(List<Charge> charges){
        chargeRepository.saveAll(charges);
    }


    // ----------- METODOS PRIVADOS -----------


    private Invoice buildInvoice(Charge charge, Integer month, Integer year){
        Invoice invoice = new Invoice();
        invoice.setDebt(charge.getAmount());
        invoice.setUserId(charge.getUserId());
        invoice.setMonth(month);
        invoice.setYear(year);

        return invoice;
    }

    private Invoice createOrUpdateInvoice(Charge charge){

        Calendar cal = Calendar.getInstance();
        cal.setTime(charge.getDate());
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        try {
            Invoice userInvoice = invoiceService.getUserInvoiceByMonthAndYear(charge.getUserId(), month, year);
            userInvoice.setDebt( userInvoice.getDebt() + charge.getAmount() );
            return invoiceService.saveInvoice(userInvoice);
        }catch (NullPointerException e){
            Invoice invoice = buildInvoice(charge, month, year);
            return invoiceService.saveInvoice(invoice);
        }
    }

}
