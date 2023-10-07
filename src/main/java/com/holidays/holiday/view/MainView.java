package com.holidays.holiday.view;


import com.holidays.holiday.componets.EmployeeEditor;
import com.holidays.holiday.domein.Employee;
import com.holidays.holiday.repository.EmployeeRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {
    private final EmployeeRepository employeeRepository;


    private Grid<Employee> grid = new Grid<>(Employee.class); //класс где будем отображать
    //графический интерфейс

    private final TextField filter = new TextField("", "Type for filter");
    private final Button addNewBtn = new Button("add new");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter,addNewBtn);
    private final EmployeeEditor editor;


    @Autowired
    public MainView(EmployeeRepository employeeRepository, EmployeeEditor editor) {
        this.employeeRepository = employeeRepository;
        this.editor = editor;

        add(toolbar,grid,editor); //расположение элементов

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName", "patronymic ");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");


        filter.setValueChangeMode(ValueChangeMode.EAGER); //обновляет статус после каждого нажатия кнопки
        filter.addValueChangeListener(e -> ShowEmployee(e.getValue()));


        grid.asSingleSelect().addValueChangeListener(e -> { //выбираем строку
            editor.editEmployee(e.getValue());
        });


        addNewBtn.addClickListener(e -> editor.editEmployee(new Employee()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            ShowEmployee(filter.getValue());
        });
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

