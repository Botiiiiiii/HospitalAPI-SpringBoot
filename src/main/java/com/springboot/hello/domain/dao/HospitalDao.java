package com.springboot.hello.domain.dao;

import com.springboot.hello.domain.dto.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public HospitalDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    public void add(final Hospital hospital) {
        String sql = "INSERT INTO `likelion-db`.`nation_wide_hospitals` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`)" +
                " VALUES (?,?,?," +
                " ?,?,?," +
                " ?,?,?," +
                " ?,?,?," +
                " ?,?,?," +
                " ?);"; // 16개

        this.jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize()
        );
    }
}
