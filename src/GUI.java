import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class GUI {
    JLabel none = new JLabel("                               Start by making a class!");
    ArrayList<String> buttons = new ArrayList<String>();
    ArrayList<JButton> buttonO = new ArrayList<JButton>();
    JFrame frame = new JFrame("JARP Grade Book Services");
    JMenuBar bar = new JMenuBar();
    JMenu saver = new JMenu("Save");
    JMenu adder = new JMenu("Add");

    JPanel center = new JPanel(new GridLayout(6,1));

    JFrame show = new JFrame();
    ArrayList<String> students = new ArrayList<String>();
    ArrayList<JTextField> fields = new ArrayList<JTextField>();
    JPanel west = new JPanel(new GridLayout(10,1));
    JPanel middle = new JPanel(new GridLayout(10,1));
    JPanel east = new JPanel(new GridLayout(10,1));

    Box box = new Box();
    File file = new File("hold2.txt");
    Scanner ask = new Scanner(file);
    ArrayList<String> info = new ArrayList<String>();
    public GUI() 
    throws FileNotFoundException {
        //JOptionPane.showMessageDialog(null, "Trying it out.");
        Dimension d = new Dimension(350,400);
        frame.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
        frame.setBackground(Color.RED);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(d);
        frame.setLocationRelativeTo(null);
        bar.add(saver);
        bar.add(adder);

        frame.setLayout(new BorderLayout());
        frame.add(BorderLayout.NORTH, bar);

        GUIStream();

        JMenuItem add = new JMenuItem("ADD CLASS");
        JButton delete = new JButton("DELETE CLASS");

        add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    //PrintStream out = new PrintStream(new File("hold2.txt"));
                    String name = JOptionPane.showInputDialog(null, "Name of new Class");
                    if(!name.trim().equals("")) {
                        JButton add = new JButton(name);
                        Class cla = new Class();
                        box.add(cla);
                        info.add(name);
                        info.add("0");
                        buttonO.add(add);
                        center.add(add);
                        buttons.add(name);
                        noClasses();
                        frame.setVisible(true);
                        add.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    classes(add);
                                }
                            });
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You have to name your class something.");
                    }
                }
            });

        delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if(!noClasses()) {
                        ArrayList<JCheckBox> delete = new ArrayList<JCheckBox>();
                        JFrame destroy = new JFrame("Delete");
                        destroy.setVisible(true);
                        destroy.setLayout(new GridLayout(buttons.size()+2,1));
                        destroy.add(new JLabel("What classes would you like to delete?"));
                        for(int i=0; i<buttons.size(); i++) {
                            delete.add(new JCheckBox(buttons.get(i)));
                            destroy.add(delete.get(i));
                        }
                        destroy.pack();
                        destroy.setLocationRelativeTo(null);
                        JButton done = new JButton("DONE");
                        done.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    int length = buttons.size();
                                    int count = 0;
                                    for( int i=0; i<length; i++) {
                                        if(delete.get(i).isSelected()) {
                                            center.remove(buttonO.get(i-count));
                                            buttonO.remove(i-count);
                                            buttons.remove(i-count);
                                            count++;
                                            box.remove(i);
                                        }
                                    }
                                    //noClasses();
                                    destroy.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });

                        destroy.add(done);

                        noClasses();
                        frame.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You have no classes to delete.");
                    }
                }
            });

        adder.add(add);
        frame.add(BorderLayout.CENTER, center);
        frame.add(BorderLayout.SOUTH, delete);
        frame.add(BorderLayout.NORTH, bar);

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    save();
                }
            });

        saver.add(save);

        noClasses();
        frame.setVisible(true);

    }

    public boolean noClasses() {
        if(buttons.size()==0) {
            center.add(none);
            frame.setVisible(true);
            return true;
        }
        else {
            center.remove(none);
            frame.setVisible(true);
            return false;
        }

    }

    int student = 1;
    public void classes(JComponent selected) {
        JTextField AssignName = new JTextField(10);
        JTextField OutOf = new JTextField(10);

        Dimension d2 = new Dimension(500,400);
        show.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
        show.setBackground(Color.RED);
        show.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        show.setVisible(true);
        show.setSize(d2);
        show.setLocationRelativeTo(null);
        show.add(new JScrollBar());

        show.setLayout(new BorderLayout());
        show.add(BorderLayout.NORTH, new JLabel(buttons.get(buttonO.indexOf(selected))));

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    for(int i=0; i<fields.size(); i++) {
                        students.add(fields.get(i).getText());
                    }
                }
            });

        show.add(BorderLayout.SOUTH, submit);
        show.add(BorderLayout.WEST, west);
        show.add(BorderLayout.CENTER, middle);
        show.add(BorderLayout.EAST, east);

        JMenuBar two = new JMenuBar();
        JMenu addy = new JMenu("Student");
        JMenu assign = new JMenu("Assignments");
        JMenuItem add = new JMenuItem("Add Student");
        JMenuItem assignment = new JMenuItem("Add Assignment");
        addy.add(add);
        assign.add(assignment);
        two.add(addy);
        two.add(assign);
        show.add(BorderLayout.NORTH, two);

        add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JTextField texty = new JTextField(10);
                    int size = students.size();
                    west.add(texty);
                    fields.add(texty);
                    middle.add(new JLabel("Grade TBD"));
                    show.pack();
                    show.setVisible(true);
                    student++;
                }
            });

        assignment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JFrame ass = new JFrame("Assignments");
                    JPanel panWest = new JPanel(new GridLayout(10,1));
                    JPanel panEast = new JPanel(new GridLayout(10,1));
                    Dimension d2 = new Dimension(300,350);
                    ass.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
                    ass.setBackground(Color.RED);
                    ass.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    ass.setVisible(true);
                    ass.setSize(d2);
                    ass.setLocationRelativeTo(null);

                    ass.setLayout(new BorderLayout());
                    panWest.add(new JLabel("Assignment name:"));
                    panEast.add(AssignName);
                    panWest.add(new JLabel("Point Total (out of):"));
                    panEast.add(OutOf);
                    JButton sub = new JButton("Submit");
                    ass.add(BorderLayout.SOUTH, sub);
                    panWest.add(new JLabel("Scores:"));
                    panEast.add(new JLabel());
                    for(int i=0; i<students.size(); i++) {
                        panWest.add(new JLabel(students.get(i)));
                        panEast.add(new JTextField(10));
                    }
                    ass.add(BorderLayout.WEST, panWest);
                    ass.add(BorderLayout.EAST, panEast);
                    ass.setVisible(true);
                }
            });

        JTextField text = new JTextField(10);
        west.add(text);
        fields.add(text);
        middle.add(new JLabel("Grade TBD"));

        show.pack();
    }

    public void GUIStream() 
    throws FileNotFoundException {
        while(ask.hasNextLine()) {
            String name = ask.nextLine();
            while(ask.hasNextInt()) {
                int num = ask.nextInt();
                JButton add = new JButton(name);
                info.add(name);
                info.add(""+num+"");
                for(int i = 1; i<=num; i++) {
                    String student = ask.nextLine();
                    JCheckBox check = new JCheckBox();
                    west.add(check);
                    check.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent event) {

                            }
                        });

                    middle.add(new JTextField(student, 10));
                    east.add(new JLabel("Grade TBD"));
                    show.pack();
                    show.setVisible(true);
                }
                Class cla = new Class();
                box.add(cla);
                buttonO.add(add);
                center.add(add);
                buttons.add(name);
                frame.setVisible(true);
                add.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            classes(add);
                        }
                    });
            }
        }
    }

    public void save() {
        try{
            PrintStream out = new PrintStream(file);
            for(int i=0; i<info.size(); i++) {
                out.println(info.get(i));
            }
        }
        catch(FileNotFoundException err) {
            System.out.println("The file that is holding your information has been moved or deleted. Please move the file back or a create a new one.");
        }

    }

}