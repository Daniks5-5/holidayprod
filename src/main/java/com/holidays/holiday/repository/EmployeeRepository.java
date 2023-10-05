package com.holidays.holiday.repository;

import com.holidays.holiday.domein.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { //обслуживание класса Employee
    @Query("from Employee e"+
            " where concat(e.lastname,'', e.firstname,'', e.patronymic ) "+
    "like concat('%',: name,'%')") //кастомный запрос, с помощью которого можем собрать всех людей из таблицы в виде списка
    List<Employee> findByName(@Param("name") String name);

}