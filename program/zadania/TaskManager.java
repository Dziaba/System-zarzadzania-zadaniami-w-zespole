package program.zadania;

import program.zespol.TeamManager;
import javax.swing.*;

public class TaskManager {
    public static void addTask(JFrame frame, DefaultListModel<Task> newTasksModel) {
        JTextField titleField = new JTextField(10);
        JTextField descriptionField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Tytuł:"));
        panel.add(titleField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Opis:"));
        panel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Dodaj nowe zadanie", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String description = descriptionField.getText();
            Task newTask = new Task(title, description); // Tworzenie nowego zadania
            newTasksModel.addElement(newTask);
            JOptionPane.showMessageDialog(panel, "Dodano nowe zadanie: " + title);
        }
    }

    public static void assignTask(JFrame frame, DefaultListModel<Task> newTasksModel, DefaultListModel<Task> inProgressTasksModel) {
        if (newTasksModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Brak zadań do przypisania.");
            return;
        }

        String[] taskTitles = new String[newTasksModel.size()];
        for (int i = 0; i < newTasksModel.size(); i++) {
            taskTitles[i] = newTasksModel.get(i).getTitle();
        }

        String selectedTask = (String) JOptionPane.showInputDialog(frame, "Wybierz zadanie do przypisania:", "Przypisz zadanie",
                JOptionPane.PLAIN_MESSAGE, null, taskTitles, taskTitles[0]);

        if (selectedTask != null) {
            String[] teamMembers = TeamManager.getAllTeamMembers();
            String selectedMember = (String) JOptionPane.showInputDialog(frame, "Wybierz członka zespołu:", "Przypisz zadanie",
                    JOptionPane.PLAIN_MESSAGE, null, teamMembers, teamMembers[0]);

            if (selectedMember != null) {
                for (int i = 0; i < newTasksModel.size(); i++) {
                    Task task = newTasksModel.get(i);
                    if (task.getTitle().equals(selectedTask)) {
                        task.setAssignedTo(selectedMember);
                        task.setStatus("W trakcie");
                        task.start(); // Rozpoczęcie śledzenia czasu
                        newTasksModel.remove(i);
                        inProgressTasksModel.addElement(task);
                        break;
                    }
                }
            }
        }
    }

    public static void completeTask(JFrame frame, DefaultListModel<Task> inProgressTasksModel, DefaultListModel<Task> completedTasksModel) {
        if (inProgressTasksModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Brak zadań w trakcie do zakończenia.");
            return;
        }

        String[] taskTitles = new String[inProgressTasksModel.size()];
        for (int i = 0; i < inProgressTasksModel.size(); i++) {
            taskTitles[i] = inProgressTasksModel.get(i).getTitle();
        }

        String selectedTask = (String) JOptionPane.showInputDialog(frame, "Wybierz zadanie do zakończenia:", "Zakończ zadanie",
                JOptionPane.PLAIN_MESSAGE, null, taskTitles, taskTitles[0]);

        if (selectedTask != null) {
            for (int i = 0; i < inProgressTasksModel.size(); i++) {
                Task task = inProgressTasksModel.get(i);
                if (task.getTitle().equals(selectedTask)) {
                    task.setStatus("Zakończone");
                    task.stop(); // Zatrzymanie śledzenia czasu
                    inProgressTasksModel.remove(i);
                    completedTasksModel.addElement(task);
                    break;
                }
            }
        }
    }

    public static void viewAllTasks(JFrame frame, DefaultListModel<Task> taskListModel) {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskListModel.getSize(); i++) {
            Task task = taskListModel.getElementAt(i);
            tasks.append(task.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, tasks.toString(), "Lista wszystkich zadań", JOptionPane.INFORMATION_MESSAGE);
    }
}
