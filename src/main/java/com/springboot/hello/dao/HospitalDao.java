package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import com.springboot.hello.parser.HospitalParser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    public HospitalDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    RowMapper<Hospital> rowMapper = ((rs, rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        return hospital;
    });

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

    public int DeleteAll(){
        return this.jdbcTemplate.update("DELETE FROM nation_wide_hospitals;");
    }

    public int getCount(){
        String sql = "SELECT count(*) from nation_wide_hospitals;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Hospital findById(String id){
        return this.jdbcTemplate.queryForObject("SELECT * from nation_wide_hospitals where id = ?", rowMapper, id);
    }

    public int deleteById(String id){
        return this.jdbcTemplate.update("delete from nation_wide_hospitals where id=?", id);
    }

    public List<Hospital> findByName(String name){
        return this.jdbcTemplate.query("select * from nation_wide_hospitals where hospital_name=?", rowMapper, name);
    }
}
