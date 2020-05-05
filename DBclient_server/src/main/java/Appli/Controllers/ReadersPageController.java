package Appli.Controllers;

import Appli.Entities.AllReader;

import Appli.UserInterface.Pages.ReadersPage.ReadersForm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ReadersPageController {
    private ReadersForm form;
    private AllReader reader;


    public ReadersPageController(ReadersForm form){
        this.form = form;
        reader = new AllReader();

        initializationListeners();


      /*  AllReader reader1 = new AllReader();
        reader1.setName("Алекс");
        reader1.setSurname("За");
        reader1.setId_library((long) 1);
        reader1.setPatronymic("ОО");
        reader1.setType("none");
*/
      //  allReaderRepository.save(reader1);
      //  System.out.println(allReaderRepository.findAll().toString());
    }

    private void initializationListeners(){


        form.nameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                String name = form.nameTextField.getText();
              /*  if(!name.equals(reader.getName())){
                    if(Checker.getInstance().checkString(name)){
                        reader.setName(name);
                    }
                    else{
                        JOptionPane.showMessageDialog(form, "Введите корректное имя");
                    }
                }*/
            }
        });

       /* form.nameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Отображение введенного текста
                String surname = form.nameTextField.getText();
                if(!surname.equals(reader.getName())){
                    if(Checker.getInstance().checkString(surname)){
                        reader.setName(surname);
                    }
                    else{
                        JOptionPane.showMessageDialog(form, "Введите корректную фамилию");
                    }
                }
            }
        });
        */
    }
}
