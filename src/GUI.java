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
    JPanel west = new JPanel(new GridLayout(20,1));
    JPanel middle = new JPanel(new GridLayout(20,1));
    JPanel east = new JPanel(new GridLayout(20,1));
    ArrayList<JLabel> grades = new ArrayList<JLabel>();
    
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();

    Box box = new Box();
    File file = new File("hold2.txt");
    Scanner ask = new Scanner(file);
    ArrayList<String> info = new ArrayList<String>();
    public GUI() 
    throws FileNotFoundException {
        //JOptionPane.showMessageDialog(null, "Trying it out.");
    	try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	
        Dimension d = new Dimension(350,400);
        frame.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
        frame.setBackground(Color.RED);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        	  public void windowClosing(WindowEvent e) {
        		  int confirmed = JOptionPane.showConfirmDialog(null, 
        			    "Do you want to save your work?", "Exit Program Message Box",
        			    JOptionPane.YES_NO_OPTION);

        		  if (confirmed == JOptionPane.YES_OPTION) {
        			    save();
        		  }
        	  }
        });
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
                    String name = JOptionPane.showInputDialog(null, "Name of new Class");
                    if(!name.trim().equals("")) {
                        JButton add = new JButton(name);
                        Class cla = new Class(name);
                        box.add(cla);
                        info.add(name);
                        info.add("");
                        buttonO.add(add);
                        center.add(add);
                        buttons.add(name);
                        noClasses();
                        frame.setVisible(true);
                        add.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    int ID = box.getID(cla);
                                	classes(add, ID);
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
                    if(!isNoClasses()) {
                        ArrayList<JCheckBox> delete = new ArrayList<JCheckBox>();
                        JFrame destroy = new JFrame("Delete");
                        destroy.setLayout(new GridLayout(buttons.size()+2,1));
                        destroy.add(new JLabel("What classes would you like to delete?"));
                        for(int i=0; i<buttons.size(); i++) {
                            delete.add(new JCheckBox(buttons.get(i)));
                            destroy.add(delete.get(i));
                        }
                        destroy.pack();
                        destroy.setLocationRelativeTo(null);
                        JButton done = new JButton("DONE");
                        destroy.setVisible(true);
                        done.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    int count = 0;
                                    for( int i=0; i<delete.size(); i++) {
                                        if(delete.get(i).isSelected()) {
                                            center.remove(buttonO.get(i-count));
                                            buttonO.remove(i-count);
                                            buttons.remove(i-count);
                                            box.remove(i-count);
                                            count++;
                                        }
                                    }
                                    destroy.dispose();
                                    noClasses();
                                    frame.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });

                        destroy.add(done);
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

    public void noClasses() {
        if(buttons.size()==0) {
            center.add(none);
            frame.setVisible(true);
        }
        else {
            center.remove(none);
            frame.setVisible(true);
        }
    }
    
    public boolean isNoClasses() {
    	if(buttons.size()==0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    int student = 1;
    public void classes(JComponent selected, int ID) {     	
        JTextField AssignName = new JTextField(10);
        JTextField OutOf = new JTextField(10);
        Class cla = box.getClass(ID);
        
        Dimension d2 = new Dimension(500,600);
        show.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
        show.setBackground(Color.RED);
        show.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        show.setVisible(true);
        show.setSize(d2);
        show.setLocationRelativeTo(null);

        show.setLayout(new BorderLayout());
        show.add(BorderLayout.NORTH, new JLabel(buttons.get(buttonO.indexOf(selected))));
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                	for(int i=0; i<fields.size(); i++) {
                		if(cla.searchName(fields.get(i).getText())==-1){
                			students.add(fields.get(i).getText());
	                        Student stud = new Student(fields.get(i).getText());
	                        cla.addStudent(stud);
                		}
                    }
                }
            });
     
        show.add(BorderLayout.WEST, west);
        show.add(BorderLayout.CENTER, middle);
        show.add(BorderLayout.EAST, east);
        show.add(BorderLayout.SOUTH, submit);

        JMenuBar two = new JMenuBar();
        JMenu addy = new JMenu("Student");
        JMenu assign = new JMenu("Assignments");
        JMenuItem add = new JMenuItem("Add Student");
        JMenuItem all = new JMenuItem("All Assignments");
        JMenuItem assignment = new JMenuItem("Add Assignment");
        addy.add(add);
        assign.add(assignment);
        assign.add(all);
        two.add(addy);
        two.add(assign);
        show.add(BorderLayout.NORTH, two);
        
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JFrame tot = new JFrame("Assignments");
            	Dimension d = new Dimension(350,400);
                tot.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
                tot.setBackground(Color.RED);
                tot.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tot.setVisible(true);
                tot.setSize(d);
                tot.setLocationRelativeTo(null);
                
                tot.setLayout(new FlowLayout());
                JPanel toter = new JPanel(new GridLayout(15,3));
                tot.add(toter);
                
            	for(Assignment a : assignments) {
            		if(toter.getComponentCount()>=45) {
            			
            		}
            		else if(a.getID()==cla.getID()) {
            			toter.add(new JLabel(a.getName()));
            			toter.add(new JLabel(""+a.getOutOf()));
            			toter.add(new JLabel(""+a.getAverage()));
            		}
            	}
            }
        });

        add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JTextField texty = new JTextField(10);
                    west.add(texty);
                    fields.add(texty);
                    JLabel lab = new JLabel("        TBD");
        	        middle.add(lab);
        	        grades.add(lab);
        	        east.add(new JLabel("  %"));
                    show.pack();
                    show.setVisible(true);
                    student++;
                }
            });

        assignment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                	Class cl = cla;
                	ArrayList<JTextField> scores = new ArrayList<JTextField>();
                    JFrame ass = new JFrame("Assignments");
                    JPanel panWest = new JPanel(new GridLayout(20,1));
                    JPanel panEast = new JPanel(new GridLayout(20,1));
                    Dimension d2 = new Dimension(300,550);
                    ass.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
                    ass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ass.setVisible(true);
                    ass.setSize(d2);
                    ass.setLocationRelativeTo(null);
                    
                    ass.addWindowListener(new WindowAdapter() {
                  	  	public void windowClosing(WindowEvent e) {
                  	  		panWest.removeAll();
                  	  		panEast.removeAll();
                  	  		scores.clear();
                  	  	}
                    });

                    ass.setLayout(new BorderLayout());
                    panWest.add(new JLabel("Assignment name:"));
                    panEast.add(AssignName);
                    panWest.add(new JLabel("Point Total (out of):"));
                    panEast.add(OutOf);
                    JButton sub = new JButton("Submit");
                    sub.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                        	int t = Integer.parseInt(OutOf.getText());
                        	Assignment first = new Assignment(AssignName.getText(), t, cla.getID());
                        	assignments.add(first);
                        	for(int i=0; i<cl.get().size(); i++) {
                        		cl.getStudent(i).addScore(t, Integer.parseInt(scores.get(i).getText()));
                        		first.update(Integer.parseInt(scores.get(i).getText()));
                        		String g=""+ cl.get().get(i).getGrade()+"";
                        		grades.get(i).setText(g);
                        	}
                        	ass.dispose();
                        }
                    });
                    ass.add(BorderLayout.SOUTH, sub);
                    panWest.add(new JLabel("Scores:"));
                    panEast.add(new JLabel());
                    for(int i=0; i<students.size(); i++) {
                        panWest.add(new JLabel(cl.getStudent(i).getName()));
                        JTextField dude = new JTextField(10);
                        panEast.add(dude);
                        scores.add(dude);
                        
                    }
                    ass.add(BorderLayout.WEST, panWest);
                    ass.add(BorderLayout.EAST, panEast);
                    ass.setVisible(true);
                }
            });
        
        show.addWindowListener(new WindowAdapter() {
      	  	public void windowClosing(WindowEvent e) {
      	  		west.removeAll();
      	  		middle.removeAll();
      	  		east.removeAll();
      	  		show.remove(submit);
      	  		fields.clear();
      	  		students.clear();
      	  		grades.clear();
      	  		show.remove(two);
      	  		two.removeAll();
      	  		addy.removeAll();
      	  		assign.removeAll();
      	  	}
        });
        
        for(int k=0; k<cla.get().size(); k++) {
        	JTextField field = new JTextField(cla.get().get(k).getName(), 8);
        	west.add(field);
            fields.add(field);
            String str = "        "+cla.getStudent(k).getGrade()+"";
            JLabel lab = new JLabel(str);
	        middle.add(lab);
	        grades.add(lab);
	        students.add(cla.getStudent(k).getName());
        }
        
        for(int i=0; i<west.getComponentCount(); i++) {
        	east.add(new JLabel("  %"));
        }

        if(fields.size()==0) {
	        JTextField text = new JTextField(10);
	        west.add(text);
	        fields.add(text);
	        JLabel lab = new JLabel("        TBD");
	        middle.add(lab);
	        grades.add(lab);
        }

        show.pack();
    }

    public void GUIStream() 
    throws FileNotFoundException {
        while(ask.hasNextLine()) {
            String name = ask.nextLine();
            JButton add = new JButton(name);
            info.add(name);
            Class cla = new Class(name);
            box.add(cla);
            if(ask.hasNextLine()) {
            	String studenT;
            	do{
                		studenT = ask.nextLine();
	                    if(studenT.equals("")) {
	                    }
	                    else {
	                	//JTextField field = new JTextField(studenT, 8);
	                	int t = ask.nextInt();
	                	int s = ask.nextInt();
	                	ask.nextLine();
	                	Student stud = new Student(studenT);
	                	cla.addStudent(stud);
	                	stud.addScore(t,s);
	                	//students.add(stud.getName());
	                    //west.add(field);
	                    //fields.add(field);
	                    //String str = "        "+stud.getGrade()+"";
	                    //JLabel lab = new JLabel(str);
	        	        //middle.add(lab);
	        	        //grades.add(lab);
	                    }
                } while(!studenT.equals(""));
                
            }
            show.setVisible(true);
            show.setVisible(false);
            buttonO.add(add);
            center.add(add);
            buttons.add(name);
            frame.setVisible(true);
            add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        classes(add, box.getID(cla));
                    }
                });
        }
    }

    public void save() {
        try{
            PrintStream out = new PrintStream(file);
            for(int i=0; i<box.get().size(); i++) {
                Class cla = box.getClass(i);
            	out.println(cla.getName());
            	for(int j=0; j<cla.get().size(); j++) {
            		Student stud = cla.getStudent(j);
            		out.println(stud.getName());
            		out.println(stud.getTotal());
            		out.println(stud.getMy());
            	}
            	out.println();
            }
            
            out.close();
        }
        catch(FileNotFoundException err) {
            System.out.println("The file that is holding your information has been moved or deleted. Please move the file back to its original location.");
        }

    }

}