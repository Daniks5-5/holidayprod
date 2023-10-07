package com.holidays.holiday.componets; //файл где не основные элементы страницы


import com.holidays.holiday.domein.Employee;
import com.holidays.holiday.repository.EmployeeRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;

@SpringComponent
@UIScope
public class EmployeeEditor extends VerticalLayout implements KeyNotifier { //KeyNotifier помогает отслеживать нажатия клавиш
    private final EmployeeRepository employeeRepository;

    private Employee employee; //поле содержит сотрудника, которого редактируем


    //выводят наши поля
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField patronymic = new TextField("patronymic");

    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Employee> binder = new Binder<>(Employee.class);
    @Setter
    public ChangeHandler changeHandler;

    public interface ChangeHandler{
        void onChange();
    }

    @Autowired
    public EmployeeEditor(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        add(lastName, firstName, patronymic, actions);
        binder.bindInstanceFields(this);
          //внешний вид кнопок
        setSpacing(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        addKeyPressListener(Key.ENTER, e -> save());


        save.addClickListener(e -> save());
        //обработчики нажатий кнопок
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editEmployee(employee));
        setVisible(false);
    }
    //удаляем данные
    private void delete() {
        employeeRepository.delete(employee);
        changeHandler.onChange();
    }

    //сохраняем данные
    private void save() {
        employeeRepository.save(employee);
        changeHandler.onChange();
    }
    public void editEmployee(Employee newEmp){
        if (newEmp == null){ //проверка, если нам вводят ничего
            setVisible(false); //выводит ничего
            return;
        }
        //ввод айдишника и его проверка
        if (newEmp.getId() != null){
           employee = employeeRepository.findById(newEmp.getId()).orElse(newEmp);
        } else{
            employee = newEmp;
        }
        binder.setBean(employee);
        //чтобы было видно форму
        setVisible(true);
        lastName.focus();
    }
}



