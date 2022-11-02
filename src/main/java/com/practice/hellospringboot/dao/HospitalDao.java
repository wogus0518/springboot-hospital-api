package com.practice.hellospringboot.dao;

import com.practice.hellospringboot.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Repository
@RequiredArgsConstructor
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;

    public void add(Hospital hospital) {
        String sql = "INSERT INTO `likelion-db`.`nation_wide_hospitals` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        this.jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize()
        );
    }

    public Hospital findById(int id) {
        String sql = "SELECT * FROM nation_wide_hospitals WHERE id=?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    RowMapper<Hospital> rowMapper = new RowMapper<Hospital>() {
        @Override
        public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hospital hospital = new Hospital(rs.getInt("id"), rs.getString("open_service_name"), rs.getInt("open_local_government_code"),
                    rs.getString("management_number"), rs.getObject("license_date", LocalDateTime.class), rs.getInt("business_status"),  rs.getInt("business_status_code"),  rs.getString("phone"),  rs.getString("full_address"),  rs.getString("road_name_address"),
                    rs.getString("hospital_name"),  rs.getString("business_type_name"),  rs.getInt("healthcare_provider_count"),  rs.getInt("patient_room_count"), rs.getInt("total_number_of_beds"), rs.getFloat("total_area_size"));
            return hospital;
        }
    };

    public int getCount() {
        String sql = "SELECT count(id) FROM nation_wide_hospitals;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM nation_wide_hospitals");
    }
}
