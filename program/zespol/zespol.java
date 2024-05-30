package program.zespol;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class zespol  {
    private static List<String> teamMembers = new ArrayList<>();

    
    public static void addTeamMember(JFrame frame) {
        JTextField nameField = new JTextField(10);
        JTextField surnameField = new JTextField(10);

        JPanel okno = new JPanel();
        okno.add(new JLabel("Imię: "));
        okno.add(nameField);
        okno.add(new JLabel("Nazwisko: "));
        okno.add(surnameField);

        int result = JOptionPane.showConfirmDialog(frame, okno, "Dodaj nowego członka zespołu", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String surname = surnameField.getText();
            JOptionPane.showMessageDialog(frame, "Dodano nowego członka zespołu: " + name + " " + surname);
            teamMembers.add("Imię: " + name + ", Nazwisko: " + surname);
        }
        
    }
    public static void viewAllTeamMembers(JFrame frame) {
        StringBuilder allMembers = new StringBuilder("Wszyscy członkowie zespołu:\n");
        for (String member : teamMembers) {
            allMembers.append(member).append("\n");
        }
        JOptionPane.showMessageDialog(frame, allMembers.toString());
    }
}
