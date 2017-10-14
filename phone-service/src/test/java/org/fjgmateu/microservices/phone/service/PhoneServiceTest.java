package org.fjgmateu.microservices.service.impl;

import org.fjgmateu.microservices.service.config.PhoneServiceApplication;
import org.fjgmateu.microservices.service.domain.api.CarrierSuccessRequest;
import org.fjgmateu.microservices.service.domain.api.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  FJGMATEU
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhoneServiceApplication.class)
public class PhoneServiceTest {

    @Autowired
    private PhoneService PhoneService;


    @Test
    public void carrier_Event_Success_With_Phone() {
        CarrierSuccessRequest input=new CarrierSuccessRequest ();
        input.setPacklinkReference("ABCD123456");
        input.setCarrier("UPS");
        input.setIntegrationCode("UPS_ES_A");
        input.setServiceId("28123");

        Parcel parcel=new Parcel();
        parcel.setWeight(0.5);
        parcel.setWidth(10);
        parcel.setHeight(10);
        parcel.setLenght(10);
        List<Parcel> parcels=new ArrayList<Parcel>();
        parcels.add(parcel);

        List<String> tracking=new ArrayList<String>();
        tracking.add("XYZ123");
        input.setTrackingNumbers(tracking);
        input.setParcels(parcels);

        assertTrue ( PhoneService.carrierSuccess(input));

    }


    @Test
    public void carrier_Event_Success_With_Not_Phone() {
        CarrierSuccessRequest input=new CarrierSuccessRequest ();
        input.setPacklinkReference("ABCD123456");
        input.setCarrier("UPS");
        input.setIntegrationCode("UPS_ES_A");
        input.setServiceId("28123");

        Parcel parcel=new Parcel();
        parcel.setWeight(100);
        parcel.setWidth(10);
        parcel.setHeight(10);
        parcel.setLenght(10);
        List<Parcel> parcels=new ArrayList<Parcel>();
        parcels.add(parcel);

        List<String> tracking=new ArrayList<String>();
        tracking.add("XYZ123");
        input.setTrackingNumbers(tracking);
        input.setParcels(parcels);

        assertFalse(PhoneService.carrierSuccess(input));

    }

    // write test cases here
}