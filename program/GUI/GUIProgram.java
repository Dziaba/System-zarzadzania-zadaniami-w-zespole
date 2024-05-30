package program.GUI;

import javax.swing.*;
import javax.swing.border.Border;

import program.zadania.Task;
import program.zadania.TaskManager;
import program.zespol.TeamManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIProgram {
    private DefaultListModel<Task> newTasksModel = new DefaultListModel<>();
    private DefaultListModel<Task> inProgressTasksModel = new DefaultListModel<>();
    private DefaultListModel<Task> completedTasksModel = new DefaultListModel<>();

    public void createAndShowWindow() {
        JFrame frame = new JFrame("System zarządzania zadaniami w zespole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(1, 3));

        JPanel newTasksPanel = new JPanel();
        newTasksPanel.setLayout(new BoxLayout(newTasksPanel, BoxLayout.Y_AXIS));
        newTasksPanel.setBorder(BorderFactory.createTitledBorder("Nowe"));
        JList<Task> newTasksList = new JList<>(newTasksModel);
        newTasksList.setCellRenderer(new TaskCellRenderer());
        newTasksPanel.add(new JScrollPane(newTasksList));

        JPanel inProgressTasksPanel = new JPanel();
        inProgressTasksPanel.setLayout(new BoxLayout(inProgressTasksPanel, BoxLayout.Y_AXIS));
        inProgressTasksPanel.setBorder(BorderFactory.createTitledBorder("W trakcie"));
        JList<Task> inProgressTasksList = new JList<>(inProgressTasksModel);
        inProgressTasksPanel.add(new JScrollPane(inProgressTasksList));
        JButton completeTaskButton = new JButton("Zakończ zadanie");
        completeTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskManager.completeTask(frame, inProgressTasksModel, completedTasksModel);
            }
        });
        inProgressTasksPanel.add(completeTaskButton);

        JPanel completedTasksPanel = new JPanel();
        completedTasksPanel.setLayout(new BoxLayout(completedTasksPanel, BoxLayout.Y_AXIS));
        completedTasksPanel.setBorder(BorderFactory.createTitledBorder("Zakończone"));
        JList<Task> completedTasksList = new JList<>(completedTasksModel);
        completedTasksPanel.add(new JScrollPane(completedTasksList));

        frame.add(newTasksPanel);
        frame.add(inProgressTasksPanel);
        frame.add(completedTasksPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu zespolMenu = new JMenu("Zespół");
        JMenu zadaniaMenu = new JMenu("Zadania");
        JMenu raportMenu = new JMenu("Raport");

        JMenuItem addTeamMemberItem = new JMenuItem("Dodaj nowego członka zespołu");
        addTeamMemberItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TeamManager.addTeamMember(frame);
            }
        });

        JMenuItem viewAllTeamMembersItem = new JMenuItem("Pokaż wszystkich członków zespołu");
        viewAllTeamMembersItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TeamManager.viewAllTeamMembers(frame);
            }
        });

        JMenuItem addTaskItem = new JMenuItem("Dodaj nowe zadanie");
        addTaskItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskManager.addTask(frame, newTasksModel);
            }
        });

        JMenuItem assignTaskItem = new JMenuItem("Przypisz zadanie");
        assignTaskItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskManager.assignTask(frame, newTasksModel, inProgressTasksModel);
            }
        });

        JMenuItem viewAllTasksItem = new JMenuItem("Pokaż wszystkie zadania");
        viewAllTasksItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<Task> allTasksModel = new DefaultListModel<>();
                for (int i = 0; i < newTasksModel.size(); i++) {
                    allTasksModel.addElement(newTasksModel.getElementAt(i));
                }
                for (int i = 0; i < inProgressTasksModel.size(); i++) {
                    allTasksModel.addElement(inProgressTasksModel.getElementAt(i));
                }
                for (int i = 0; i < completedTasksModel.size(); i++) {
                    allTasksModel.addElement(completedTasksModel.getElementAt(i));
                }
                TaskManager.viewAllTasks(frame, allTasksModel);
            }
        });

        JMenuItem reportItem = new JMenuItem("Raport");
        reportItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReport();
            }
        });

        zespolMenu.add(addTeamMemberItem);
        zespolMenu.add(viewAllTeamMembersItem);
        zadaniaMenu.add(addTaskItem);
        zadaniaMenu.add(assignTaskItem);
        zadaniaMenu.add(viewAllTasksItem);
        raportMenu.add(reportItem);
        menuBar.add(zespolMenu);
        menuBar.add(zadaniaMenu);
        menuBar.add(raportMenu);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private void showReport() {
        JFrame reportFrame = new JFrame("Raport zadań");
        reportFrame.setSize(600, 400);
        reportFrame.setLayout(new BoxLayout(reportFrame.getContentPane(), BoxLayout.Y_AXIS));

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        StringBuilder report = new StringBuilder();
        report.append("Nowe zadania:\n");
        for (int i = 0; i < newTasksModel.size(); i++) {
            report.append(newTasksModel.getElementAt(i).toString()).append("\n");
        }

        report.append("\nZadania w trakcie:\n");
        for (int i = 0; i < inProgressTasksModel.size(); i++) {
            report.append(inProgressTasksModel.getElementAt(i).toString()).append("\n");
        }

        report.append("\nZakończone zadania:\n");
        for (int i = 0; i < completedTasksModel.size(); i++) {
            report.append(completedTasksModel.getElementAt(i).toString()).append("\n");
        }

        reportArea.setText(report.toString());
        reportFrame.add(new JScrollPane(reportArea));
        reportFrame.setVisible(true);
    }

    public static void main(String[] args) {
        GUIProgram program = new GUIProgram();
        program.createAndShowWindow();
    }
    private static class TaskCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Task) {
                Task task = (Task) value;
                if (task.isNew()) {
                    Border border = BorderFactory.createLineBorder(Color.RED, 2);
                    ((JComponent) c).setBorder(border);
                } else {
                    ((JComponent) c).setBorder(null);
                }
            }
            return c;
        }
    }
}
