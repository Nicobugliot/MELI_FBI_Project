package com.example.project.service;

import com.example.project.exception.InvalidAmountException;
import com.example.project.exception.InvalidEventTypeException;
import com.example.project.model.Invoice;
import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepository;
import com.example.project.request.PaymentRequest;
import com.example.project.util.UtilConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private AsociatePaymentService asociatePaymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public List<Payment> listPayment(){
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findPaymentByUserId(Long id){
        return paymentRepository.findByUserId(id);
    }

    @Override
    public List<Payment> findPaymentByUserMonthAndYear(Long id, Integer month, Integer year){
        return paymentRepository.findPaymentByUserMonthAndYear(id, month, year);
    }

    @Override
    public void savePayment(PaymentRequest paymentRequest){

        Integer month = Calendar.getInstance().get(Calendar.MONTH);
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Long user_id = paymentRequest.getUserId();
        Double amount = paymentRequest.getAmount();
        String currency = paymentRequest.getCurrency();

        Invoice invoice = invoiceService.getUserInvoiceByMonthAndYear(user_id, month + 1, year);

        if (invoice == null){
            throw new InvalidEventTypeException("There is no charges associated to the user");
        }
        if (validatePaymentAmount(amount, invoice)){
            throw new InvalidAmountException("The amount exceeds the total amount to be paid");
        }

        Payment payment = buildPayment(user_id, amount, currency);

        Payment userPayment = paymentRepository.save(payment);

        invoiceService.addPaymentToInvoice(invoice, UtilConverter.currencyConverter(amount, currency));

        associatePaymentAsync(userPayment);

    }

    private Boolean validatePaymentAmount(Double amount, Invoice invoice) {
        return ((invoice.getDebt() - amount) < 0);
    }

    /**
     * Utilizo una función asincrónica para poder devolver rápido un status 201 y mientras tanto
     * hacer el proceso de guardado del pago, así puedo acortar tiempos de respuesta.
     */
    private void associatePaymentAsync(Payment payment){

        new Thread(() -> {
            asociatePaymentService.associate(payment);
        }).start();
    }

    private Payment buildPayment(Long user_id, Double amount, String currency){
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setUserId(user_id);
        payment.setCurrency(currency);

        return payment;
    }

}
