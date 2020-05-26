package Application.UserInterface.Frames.Readers;

import Application.Controllers.ReadersPageController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/*
    Форма вывода поиска читателей
 */
public class ReadersTable extends JFrame {

    private final JTable resultTable;
    private final DefaultTableModel tableModel;
    private ArrayList<String[]> currentReaders;

    private final ReadersPageController controller;
    private final JButton removeRowButton;
    private final JButton backButton;
    private final JButton libraryButton;
    private final JButton professionButton;
    private final JButton filterButton;
    private final JTextField filterTextField;

    private final TableRowSorter<TableModel> sorter;

    public ReadersTable(ReadersPageController controller) {
        currentReaders = new ArrayList<>();
        this.controller = controller;
        removeRowButton = new JButton("Удалить выбранную строку");
        backButton = new JButton("Очистить и выйти");
        libraryButton = new JButton("Библиотека");
        professionButton = new JButton("Инфо о профессии");
        filterButton = new JButton("Фильтровать");

        setTitle("Результаты поиска");
        setSize(800, 400);


        tableModel = new DefaultTableModel() {
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        // Заголовки столбцов
        Object[] columnsHeader = new String[]{"ID читателя", "Тип читателя", "Имя", "Фамилия", "Отчество", "ID библиотеки"};
        tableModel.setColumnIdentifiers(columnsHeader);

        sorter = new TableRowSorter<>(
                tableModel);

        resultTable = new JTable(tableModel);
        //resultTable.setAutoCreateRowSorter(true);
        resultTable.setRowSorter(sorter);

        // Раскрывающийся список
        JComboBox<String> combo = new JComboBox<>(new String[]{"pensioner", "schoolkid", "scientist", "student", "teacher", "worker"});
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
        buttons.add(professionButton);
        buttons.add(filterButton);

        filterTextField = new JTextField();

        getContentPane().add(filterTextField, "North");
        getContentPane().add(buttons, "South");

        initializationListeners();
    }

    private void initializationListeners() {

        resultTable.getModel().addTableModelListener(e -> {
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
                    String type;
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
        });


        removeRowButton.addActionListener(e -> {
            // Номер выделенной строки
            int row = resultTable.getSelectedRow();
            if (row < 0)
                row = 0;
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

        professionButton.addActionListener(e -> {
            int row = resultTable.getSelectedRow();
            if (row >= 0)
                controller.showCurrentProfession(Long.parseLong(String.valueOf(tableModel.getValueAt(row, 0))));
        });

        filterButton.addActionListener(e -> {
            String text = filterTextField.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    System.err.println("Bad regex pattern");
                }
            }
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
                Object[] rows = {Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], Integer.parseInt(row[5])};
                //tableModel.addRow(row);
                tableModel.addRow(rows);
                currentReaders.add(row);
            }
        }
    }

}
