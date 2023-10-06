package com.holidays.holiday.view;

import com.holidays.holiday.domein.Employee;
import com.holidays.holiday.repository.EmployeeRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {
    private final EmployeeRepository employeeRepository;

    private Grid<Employee> grid = new Grid<>(Employee.class); //класс где будем отображать
    //графический интерфейс


    @Autowired
    public MainView(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        add(grid);

        ShowEmployee("");
    }

    private void ShowEmployee(String name) { // метод для отображения базы данных
        if (name.isEmpty()) {
            grid.setItems(employeeRepository.findAll());
        } else {
            grid.setItems(employeeRepository.findByName(name));
        }
    }
}

