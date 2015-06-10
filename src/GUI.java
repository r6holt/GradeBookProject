import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.EmptyBorder;
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
    ArrayList<JTextField> lasts = new ArrayList<JTextField>();
    JPanel west = new JPanel(new GridLayout(20,1));
    JPanel west2 = new JPanel(new GridLayout(20,1));
    JPanel middle = new JPanel(new GridLayout(20,1));
    JPanel east = new JPanel(new GridLayout(20,1));
    ArrayList<JLabel> grades = new ArrayList<JLabel>();
    
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();

    Box box = new Box();
    File file = new File("hold2.txt");
    File a = new File("a.txt");
    Scanner ask = new Scanner(file);
    Scanner as = new Scanner(a);
    ArrayList<String> info = new ArrayList<String>();
    public GUI() 
    throws FileNotFoundException {
        //JOptionPane.showMessageDialog(null, "tester");
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
        frame.setResizable(false);
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
    	JPanel cray = new JPanel(new GridLayout(1,2));
        JTextField AssignName = new JTextField(10);
        JTextField OutOf = new JTextField(10);
        Class cla = box.getClass(ID);
        
        Dimension d2 = new Dimension(500,600);
        show.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
        show.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        show.setVisible(true);
        show.setSize(d2);
        show.setResizable(false);
        show.setLocationRelativeTo(null);

        show.setLayout(new BorderLayout());
        show.add(BorderLayout.NORTH, new JLabel(buttons.get(buttonO.indexOf(selected))));
        
        west.add(new JLabel("   FIRST"));
        west2.add(new JLabel("   LAST"));
        middle.add(new JLabel("Grade(%)"));
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                	for(int i=0; i<fields.size(); i++) {
                		if(cla.searchName(fields.get(i).getText(), lasts.get(i).getText())==-1){
                			students.add(fields.get(i).getText());
	                        Student stud = new Student(fields.get(i).getText(), lasts.get(i).getText());
	                        cla.addStudent(stud);
	                        for(int j=0; j<assignments.size(); j++) {
	                        	stud.addAss(assignments.get(j));
	                        	assignments.get(j).addScore(0);
	                        }
                		}
                    }
                }
            });
     
        show.add(BorderLayout.WEST, cray);
        show.add(BorderLayout.CENTER, middle);
        show.add(BorderLayout.SOUTH, submit);
        cray.add(west2);
        cray.add(west);
        
        west.setBorder(new EmptyBorder(0, 0, 0, 0));
        west2.setBorder(new EmptyBorder(0, 0, 0, 0));
        cray.setBorder(new EmptyBorder(0,0,0,0));

        JMenuBar two = new JMenuBar();
        JMenu addy = new JMenu("Student");
        JMenu assign = new JMenu("Assignments");
        JMenu view = new JMenu("Sort");
        JMenuItem order = new JMenuItem("Alphabetical");
        JMenuItem add = new JMenuItem("Add Student");
        JMenuItem all = new JMenuItem("All Assignments");
        JMenuItem assignment = new JMenuItem("Add Assignment");
        view.add(order);
        addy.add(add);
        assign.add(assignment);
        assign.add(all);
        two.add(addy);
        two.add(assign);
        two.add(view);
        show.add(BorderLayout.NORTH, two);
        
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	/*for(int i=0; i<fields.size(); i++) {
            		if(cla.searchName(fields.get(i).getText(), lasts.get(i).getText())==-1){
            			students.add(fields.get(i).getText());
                        Student stud = new Student(fields.get(i).getText(), lasts.get(i).getText());
                        cla.addStudent(stud);
            		}
                }*/
            	
            	ArrayList<String> lasty = new ArrayList<String>();
            	ArrayList<Student> id = new ArrayList<Student>();
            	int i=0;
            	for(JTextField t : lasts) {
            		if(!t.getText().trim().equals("")) {
            			lasty.add(t.getText());
            			id.add(cla.getStudent(i));
            			i++;
            		}
            	}
            	Collections.sort(lasty);
            	cla.arrange(lasty);
            	
            	west.removeAll();
      	  		west2.removeAll();
      	  		fields.clear();
      	  		students.clear();
      	  		lasts.clear();
      	  		middle.removeAll();
      	  		
      	  		west.add(new JLabel("     FIRST"));
      	  		west2.add(new JLabel("     LAST"));
      	  		middle.add(new JLabel("Grade(%)"));
      	  		
      	  		for(Student s : cla.get()) {
      	  			JTextField first = new JTextField(6);
      	  			JTextField last = new JTextField(6);
      	  			first.setText(s.getFirst());
      	  			last.setText(s.getLast());
      	  			west.add(first);
      	  			west2.add(last);
      	  			fields.add(first);
      	  			lasts.add(last);
      	  			students.add(s.getName());
      	  			middle.add(new JLabel(""+s.getGrade()));
      	  		}
      	  		show.pack();
      	  		show.setVisible(true);
            }
        });
        
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	if(cla.getA().size()==0) {
            		JOptionPane.showMessageDialog(null, "There are currently no assignments for this class.");
            	}
            	else {
	            	JFrame tot = new JFrame("Assignments");
	            	Dimension d = new Dimension(350,400);
	                tot.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
	                tot.setBackground(Color.RED);
	                tot.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                tot.setVisible(true);
	                tot.setSize(d);
	                tot.setLocationRelativeTo(null);
	                
	                tot.setLayout(new FlowLayout());
	                JPanel toter = new JPanel(new GridLayout(cla.getA().size()+2,3,5,2));
	                tot.add(toter);
	                
	                tot.addWindowListener(new WindowAdapter() {
	              	  public void windowClosing(WindowEvent e) {
	              		  toter.removeAll();
	              	  }
	              });
	                
	        			toter.add(new JLabel("NAME"));
	        			toter.add(new JLabel("TOTAL"));
	        			toter.add(new JLabel("AVERAGE"));
	        				for(Assignment a : cla.getA()) {
		            			JButton marty = new JButton(a.getName());
		            			marty.addActionListener(new ActionListener() {
		            				@Override
		            				public void actionPerformed(ActionEvent event) {
		            					ArrayList<JTextField> newGrades = new ArrayList<JTextField>();
		            					JFrame change = new JFrame();
		            					Dimension d = new Dimension(350,400);
		            	                change.setIconImage(new ImageIcon(getClass().getResource("gradebook.png")).getImage());
		            	                change.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            	                change.setVisible(true);
		            	                change.setSize(d);
		            	                change.setLocationRelativeTo(null);
		            	                
		            	                change.setLayout(new GridLayout(cla.get().size()+2, 3));
		            	                
		            	                change.add(new JLabel("LAST"));
		            	                change.add(new JLabel("FIRST"));
		            	                change.add(new JLabel("SCORE"));
		            	                
		            	                for(int i=0; i<cla.get().size(); i++) {
		            	                	JTextField las = new JTextField(lasts.get(i).getText());
		            	                	change.add(las);
		            	                	las.setEditable(false);
		            	                	
		            	                	JTextField fir = new JTextField(fields.get(i).getText());
		            	                	change.add(fir);
		            	                	fir.setEditable(false);
		            	                	
		            	                	JTextField gra = new JTextField(""+a.getScore(i));
		            	                	change.add(gra);
		            	                	newGrades.add(gra);
		            	                }
		            	                change.add(new JLabel());
		            	                JButton su = new JButton("Submit");
		            	                change.add(su);
		            	                change.add(new JLabel());
		            	                su.addActionListener(new ActionListener() {
				            				@Override
				            				public void actionPerformed(ActionEvent event) {
				            					for(int i=0; i<cla.get().size(); i++) {
				            						Student s = cla.getStudent(i);
				            						s.removeScore(a.outOf, a.getScore(i));
				            						s.addScore(a.outOf, Integer.parseInt(newGrades.get(i).getText()));
				            						a.getS().set(i, Integer.parseInt(newGrades.get(i).getText()));
				            					}
				            					a.update();
				            					change.dispose();
				            				}
		            	                });
		            	                change.pack();
		            				}
		            			});
		            			toter.add(marty);
		        				JTextField mart = new JTextField(""+a.getOutOf());
		            			mart.setEditable(false);
		            			toter.add(mart);
		        				JTextField mar = new JTextField(""+a.getAverage());
		            			mar.setEditable(false);
		            			toter.add(mar);
		        			}
	            	tot.pack();
            	}
            }
        });

        add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JTextField first = new JTextField(2);
                    JTextField last = new JTextField(2);
                    west.add(first);
                    west2.add(last);
                    fields.add(first);
                    lasts.add(last);
                    JLabel lab = new JLabel("        TBD");
        	        middle.add(lab);
        	        grades.add(lab);
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
                        	Assignment first = new Assignment(AssignName.getText(), t, cl.getID());
                        	assignments.add(first);
                        	cl.addA(first);
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
      	  		west2.removeAll();
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
      	  		cray.removeAll();
      	  	}
        });
        
        for(int k=0; k<cla.get().size(); k++) {
        	JTextField first = new JTextField(cla.get().get(k).getFirst(), 8);
        	JTextField last = new JTextField(cla.get().get(k).getLast(), 8);
        	west.add(first);
        	west2.add(last);
            fields.add(first);
            lasts.add(last);
            String str = "        "+cla.getStudent(k).getGrade()+"";
            JLabel lab = new JLabel(str);
	        middle.add(lab);
	        grades.add(lab);
	        students.add(cla.getStudent(k).getName());
        }

        if(fields.size()==0) {
	        JTextField text = new JTextField(6);
	        JTextField last = new JTextField(6);
	        last.setSize(6,1);
	        text.setSize(6,1);
	        west.add(text);
	        west2.add(last);
	        fields.add(text);
	        lasts.add(last);
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
            	String first;
            	do{
                		first = ask.nextLine();
	                    if(first.equals("")) {
	                    }
	                    else {
	                    String last = ask.nextLine();
	                	//JTextField field = new JTextField(studenT, 8);
	                	int t = ask.nextInt();
	                	int s = ask.nextInt();
	                	ask.nextLine();
	                	Student stud = new Student(first, last);
	                	cla.addStudent(stud);
	                	stud.addScore(t,s);
	                    }
                } while(!first.equals(""));
              
            }
            
            while(as.hasNextLine()) {
        		String n = as.nextLine();
        		int out = as.nextInt();
        		int id = as.nextInt();
        		double av = as.nextDouble();
        		int tak = as.nextInt();
        		as.nextLine();
        		Assignment a = new Assignment(n, out, id);
        		for(int i=0; i<box.getClass(id).get().size(); i++) {
        			box.getClass(id).getStudent(i).addAss(a);
        		}
        		a.setAverage(av);
        		a.setTaking(tak);
        		assignments.add(a);
        		int score = as.nextInt();
        		while(score!=-1) {
        			a.addScore(score);
        			score=as.nextInt();
        		}
        		as.nextLine();
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
        for(Assignment a : assignments) {
        	box.get().get(a.getID()).addA(a);
        }
    }

    public void save() {
        try{
            PrintStream out = new PrintStream(file);
            PrintStream asi = new PrintStream(a);
            
            for(int i=0; i<box.get().size(); i++) {
                Class cla = box.getClass(i);
            	out.println(cla.getName());
            	for(int j=0; j<cla.get().size(); j++) {
            		Student stud = cla.getStudent(j);
            		out.println(stud.getFirst());
            		out.println(stud.getLast());
            		out.println(stud.getTotal());
            		out.println(stud.getMy());
            	}
            	out.println();
            }
            for(int i=0; i<assignments.size(); i++) {
            	asi.println(assignments.get(i).getName());
            	asi.println(assignments.get(i).getOutOf());
            	asi.println(assignments.get(i).getID());
            	asi.println(assignments.get(i).getAverage());
            	asi.println(assignments.get(i).getTaking());
            	for(int k=0; k<assignments.get(i).getS().size(); k++) {
            		asi.println(assignments.get(i).getScore(k));
            	}
            	asi.println(-1);
            }
            
            out.close();
            asi.close();
        }
        catch(FileNotFoundException err) {
            System.out.println("The file that is holding your information has been moved or deleted. Please move the file back to its original location.");
        }

    }

}