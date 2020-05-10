package Appli.UserInterface.Frames;

import Appli.Controllers.ReadersPageController;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class SearchReadersForm extends JFrame {

    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ArrayList<String[]> currentReaders;

    private ReadersPageController controller;
    private JButton removeRowButton;
    private JButton backButton;
    private JButton libraryButton;
    private JButton proffesionButton;

    // Заголовки столбцов
    private final Object[] columnsHeader = new String[]{"ID читателя", "Тип читателя", "Имя", "Фамилия", "Отчество", "ID библиотеки"};

    public SearchReadersForm(ReadersPageController controller) {
        currentReaders = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        backButton = new JButton("Очистить и выйти");
        libraryButton = new JButton("Библиотека");
        proffesionButton = new JButton("Инфо о профессии");

        setTitle("Результаты поиска");
        setSize(800, 400);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);


        resultTable = new JTable(tableModel);

        // Раскрывающийся список
        JComboBox<String> combo = new JComboBox<String>(new String[]{"pensioner", "schoolkid", "scientist", "student", "teacher", "worker"});
        // Редактор ячейки с раскрывающимся списком
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        // Определение редактора ячеек для колонки
        resultTable.getColumnModel().getColumn(1).setCellEditor(editor);


        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(resultTable));
        getContentPane().add(contents);


        JPanel buttons = new JPanel();
        buttons.add(removeRowButton);
        buttons.add(backButton);
        buttons.add(libraryButton);
        buttons.add(proffesionButton);
        getContentPane().add(buttons, "South");

        initializationListeners();
        //setVisible(true);
    }

    private void initializationListeners() {

        resultTable.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                System.out.println("поменяли данные");
                System.out.println(resultTable.getSelectedRow());
                int row = resultTable.getSelectedRow();
                int column = resultTable.getSelectedColumn();
                if (column == 0) {
                    JOptionPane.showMessageDialog(resultTable, "Столбец с ID нельзя менять");
                } else if (row >= 0) {
                    try {
                        System.out.println(tableModel.getValueAt(row, 0));
                        System.out.println(tableModel.getValueAt(row, 1));
                        System.out.println(tableModel.getValueAt(row, 2));
                        System.out.println(tableModel.getValueAt(row, 3));
                        System.out.println(tableModel.getValueAt(row, 4));
                        System.out.println(tableModel.getValueAt(row, 5));
                        String type = "";
                        if (column == 1)
                            type = "type";
                        else
                            type = "notType";
                        controller.queryForUpdate(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), String.valueOf(tableModel.getValueAt(row, 1)),
                                String.valueOf(tableModel.getValueAt(row, 2)), String.valueOf(tableModel.getValueAt(row, 3)),
                                String.valueOf(tableModel.getValueAt(row, 4)), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 5))), type);
                    } catch (ArrayIndexOutOfBoundsException ignored) {

                    }
                }
            }
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            // Удаление выделенной строки
            controller.queryForDelete(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))), String.valueOf(tableModel.getValueAt(row, 1)),
                    String.valueOf(tableModel.getValueAt(row, 2)), String.valueOf(tableModel.getValueAt(row, 3)),
                    String.valueOf(tableModel.getValueAt(row, 4)), Long.parseLong(String.valueOf(tableModel.getValueAt(row, 5))));
            tableModel.removeRow(row);
        });

        backButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            currentReaders = new ArrayList<>();
            this.dispose();
        });

        libraryButton.addActionListener(e -> {
            int row = resultTable.getSelectedRow();
            if (row >= 0)
                controller.showCurrentLibrary(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 5))));
        });

        proffesionButton.addActionListener(e -> {
            int row = resultTable.getSelectedRow();
            if (row >= 0)
                controller.showCurrentProfession(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
        });
    }

    public void updateTable(ArrayList<String[]> array) {
        for (String[] row : array) {
            boolean adding = true;
            for (String[] cur : currentReaders) {
                if (cur[0].equals(row[0])) {
                    adding = false;
                    break;
                }
            }
            if (adding) {
                tableModel.addRow(row);
                currentReaders.add(row);
            }
        }
    }

}
