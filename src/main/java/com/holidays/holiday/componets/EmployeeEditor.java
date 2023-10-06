package com.holidays.holiday.componets;


import com.holidays.holiday.domein.Employee;
import com.holidays.holiday.repository.EmployeeRepository;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@SpringComponent
@UIScope
public class EmployeeEditor extends VerticalLayout implements KeyNotifier {
    private final EmployeeRepository employeeRepository;

    private Employee employee; //поле содержит сотрудника, которого редактируем


    //выводят наши поля
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField patronymic = new TextField("patronymic");



    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);





    @Autowired
    public EmployeeEditor(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}



