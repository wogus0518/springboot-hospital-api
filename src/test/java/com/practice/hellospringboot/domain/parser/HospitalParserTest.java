package com.practice.hellospringboot.domain.parser;

import com.practice.hellospringboot.dao.HospitalDao;
import com.practice.hellospringboot.domain.Hospital;
import com.practice.hellospringboot.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
@DisplayName("HospitalParserTest")
class HospitalParserTest {

    @Autowired ReadLineContext<Hospital> hospitalReadLineContext;
    @Autowired
    HospitalService hospitalService;

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

    @DisplayName("csv 1줄을 hospital로 잘 만드는지 확인")
//    @Test
    void parseTest(){
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());
    }

//    @Test
    @DisplayName("10만건 이상 데이터가 파싱 되는지")
    void oneHundreadThousandRows() throws IOException {
        String filename = "src/main/resources/fulldata.txt";
        List<Hospital> hospitals = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitals.size() > 100000);
        System.out.printf("파싱된 데이터 개수: %d\n", hospitals.size());
        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getId());
        }
    }

//    @Test
    @DisplayName("전체데이터삽입")
    void insertData() throws IOException {
        String filename = "src/main/resources/fulldata.txt";
        int cnt = hospitalService.insertLargeVolumeHospitalData(filename);
        assertTrue(cnt > 100000);
        System.out.printf("파싱된 데이터 개수: %d", cnt);
    }
}