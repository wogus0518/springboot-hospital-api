package com.practice.hellospringboot.dao;

import com.practice.hellospringboot.domain.Hospital;
import com.practice.hellospringboot.domain.parser.HospitalParser;
import com.practice.hellospringboot.domain.parser.ReadLineContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class HospitalDaoTest {

    @Autowired HospitalDao hospitalDao;
    @Autowired HospitalParser hospitalParser;
    @Autowired ReadLineContext<Hospital> hospitalReadLineContext;

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

//    @AfterEach
    void after() {
        hospitalDao.deleteAll();
    }

//    @Test
    @DisplayName("Hospital이 insert가 잘 되는지")
    void add() {
        Hospital hospital = hospitalParser.parse(line1);
        hospitalDao.add(hospital);
        assertEquals(hospitalDao.getCount(), 1);

        Hospital findHospital = hospitalDao.findById(hospital.getId());
        assertEquals(findHospital.getId(), hospital.getId());
        assertEquals(findHospital.getOpenServiceName(), hospital.getOpenServiceName());
        assertEquals(findHospital.getOpenLocalGovernmentCode(), hospital.getOpenLocalGovernmentCode());
        assertEquals(findHospital.getManagementNumber(), hospital.getManagementNumber());
        assertEquals(findHospital.getLicenseDate(), hospital.getLicenseDate());
        assertEquals(findHospital.getBusinessStatus(), hospital.getBusinessStatus());
        assertEquals(findHospital.getBusinessStatusCode(), hospital.getBusinessStatusCode());
        assertEquals(findHospital.getPhone(), hospital.getPhone());
        assertEquals(findHospital.getFullAddress(), hospital.getFullAddress());
        assertEquals(findHospital.getRoadNameAddress(), hospital.getRoadNameAddress());
        assertEquals(findHospital.getHospitalName(), hospital.getHospitalName());
        assertEquals(findHospital.getBusinessTypeName(), hospital.getBusinessTypeName());
        assertEquals(findHospital.getHealthcareProviderCount(), hospital.getHealthcareProviderCount());
        assertEquals(findHospital.getPatientRoomCount(), hospital.getPatientRoomCount());
        assertEquals(findHospital.getTotalNumberOfBeds(), hospital.getTotalNumberOfBeds());
        assertEquals(findHospital.getTotalAreaSize(), hospital.getTotalAreaSize());
    }
}