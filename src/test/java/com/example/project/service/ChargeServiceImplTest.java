package com.example.project.service;

import com.example.project.ProjectApplication;
import com.example.project.controller.ChargeController;
import com.example.project.model.Charge;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@SpringBootTest()
public class ChargeServiceImplTest {

    @InjectMocks
    private ChargeController chargeController;

    //@Autowired
    @Mock
    private ChargeService chargeService;

    @Test
    public void testGetChargesById(){
        Charge charge = new Charge();

        charge.setAmount(500.0);
        charge.setDate(new Date());
        charge.setCurrency("AR");
        charge.setEventId(30L);
        charge.setEventType("VENTA");
        charge.setUserId(22L);

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);
        Mockito.when(chargeService.findChargesByUserId(any())).thenReturn(chargeList);

        List<Charge> userCharge = chargeController.listChargesById(charge.getUserId());
        Assert.assertEquals(chargeList, userCharge);
    }

    @Test
    public void testListCharges(){
        Charge charge = new Charge();

        charge.setAmount(500.0);
        charge.setDate(new Date());
        charge.setCurrency("AR");
        charge.setEventId(30L);
        charge.setEventType("VENTA");
        charge.setUserId(22L);

        Charge charge2 = new Charge();

        charge2.setAmount(500.0);
        charge2.setDate(new Date());
        charge2.setCurrency("AR");
        charge2.setEventId(35L);
        charge2.setEventType("VENTA");
        charge2.setUserId(23L);

        List<Charge> chargeList = new ArrayList<>();
        chargeList.add(charge);
        chargeList.add(charge2);
        Mockito.when(chargeService.listCharges()).thenReturn(chargeList);

        List<Charge> userCharge = chargeController.list();

        Assert.assertEquals(chargeList, userCharge);
    }



}