package program.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIProgram {
    public void createAndShowWindow() {
        JFrame frame = new JFrame("System zarządzania zadaniami w zespole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JMenuBar menuBar = new JMenuBar();
        JMenu zespolMenu = new JMenu("Zespół");
        JMenu zadaniaMenu = new JMenu("Zadania");
        JMenu raportMenu = new JMenu("Raport");

        JMenuItem addTeamMemberItem = new JMenuItem("Dodaj nowego członka zespołu");
        addTeamMemberItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Kliknięto Dodaj nowego członka zespołu");
            }
        });

        JMenuItem viewAllTeamMembersItem = new JMenuItem("Pokaż wszystkich członków zespołu");
        viewAllTeamMembersItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Kliknięto Pokaż wszystkich członków zespołu");
            }
        });

        JMenuItem addNewTaskItem = new JMenuItem("Dodaj nowe zadanie");
        addNewTaskItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Kliknięto Dodaj nowe zadanie");
            }
        });

        JMenuItem assignTaskItem = new JMenuItem("Przypisz zadanie");
        assignTaskItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Kliknięto Przypisz zadanie");
            }
        });

        JMenuItem runReportItem = new JMenuItem("Raport o liczbie przypisanych zadań");
        addTeamMemberItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Kliknięto Raport");
            }
        });

        zespolMenu.add(addTeamMemberItem);
        zespolMenu.add(viewAllTeamMembersItem);
        zadaniaMenu.add(addNewTaskItem);
        zadaniaMenu.add(assignTaskItem);
        raportMenu.add(runReportItem);

        menuBar.add(zespolMenu);
        menuBar.add(zadaniaMenu);
        menuBar.add(raportMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIProgram().createAndShowWindow();
            }
        });
    }

  }

