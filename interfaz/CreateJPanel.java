/**
 * Package interfaz that includes the class MainJFrame, class CreateJPanel and DisplayPanel.
 * 
 * @author 
 * @version 3.0
 */
package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.toedter.calendar.JDateChooser;

import business.Person;
import placeholders.TextPrompt;

/**
 * Class CreateJPanel
 * 
 * Creates the CreateJPanel panel where personal profiles are created.
 * 
 * @author ankit
 * @version 3.0
 */
public class CreateJPanel extends javax.swing.JPanel {

    /**
     * List of enumerates for select department
     */
    enum Department {
	MARKETING, TIC, ADMINISTRATION
    }

    /**
     * Represents the comboBox option to select the enum that includes the
     * department selection.
     */
    int comboOption;
    /**
     * Represents the comboBox option once enum is selected for casting to String.
     */
    String comboOptEnum;

    /**
     * Represents the date of birth once is selected on JDateChooser for casting to
     * String and save it on a variable.
     */
    String dobJcalendar;

    /**
     * Represents the date of start once is selected on JDateChooser for casting to
     * String and save it on a variable.
     */
    String dosJcalendar;

    /**
     * Declaration of object person of class Person
     */
    Person person;

    /**
     * Declaration of object pat of class Pattern to check data from textFields.
     */
    Pattern pat;

    /**
     * Declaration of object pat of class Pattern to compare data from textFields to
     * pattern.
     */
    Matcher mat;

    /**
     * Declaration of object JDateChooser for "dob".
     */
    JDateChooser dobDChooser = new JDateChooser();

    /**
     * Declaration of object JDateChooser for "dos".
     */
    JDateChooser dosDChooser = new JDateChooser();

    /**
     * Declaration of object JDateChooser LocalDatefor to create a variable with
     * today's date in order to compare limit of age (18 years old).
     */
    LocalDate today = LocalDate.now();

    /**
     * Creates an arraylist for all object Person
     */
    private static List<Person> profileList = new ArrayList<>();

    /**
     * Get the elements included in profileList
     * 
     * @return profileList
     */
    public static List<Person> getProfileList() {
	return profileList;
    }

    /**
     * Set the elements included in profileList
     * 
     * @param personList
     */
    public static void setProfileList(List<Person> personList) {
	profileList = personList;
    }

    /**
     * Initialize all components included on CreateJPanel panel. Creates border
     * style for panel
     *
     * @param person
     */
    public CreateJPanel(Person person) {
	setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	initComponents();
	this.person = person;

	@SuppressWarnings("unused")
	TextPrompt name = new TextPrompt("50 characters max.", nameTextField);
	@SuppressWarnings("unused")
	TextPrompt geo = new TextPrompt("50 characters max.", geoTextField);
	@SuppressWarnings("unused")
	TextPrompt phone = new TextPrompt("6 or 7+XXXXXXX", phoneTextField);
	@SuppressWarnings("unused")
	TextPrompt mail = new TextPrompt("text@text.xx-xxx", emailTextField);
	@SuppressWarnings("unused")
	TextPrompt ssn = new TextPrompt("10 digits", ssnTextField);
	@SuppressWarnings("unused")
	TextPrompt bank = new TextPrompt("ESXX-XXXX-XXXX-XX-XXXXXXXXXX", bankTextField);

    }

    /**
     * Returns the department selection with is an enumerate variable.
     * 
     * @param comboOpt
     * @return enum
     */
    public String selectDepartment(int comboOpt) {

	int depart = comboOpt;

	if (depart == 0) {
	    return Department.MARKETING.name();
	} else if (depart == 1) {
	    return Department.TIC.name();
	} else {
	    return Department.ADMINISTRATION.name();
	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	jLabel1 = new javax.swing.JLabel();
	jButton1 = new javax.swing.JButton();
	jButton1.setFont(new Font("Silom", Font.BOLD, 12));
	picTextPath = new javax.swing.JTextField();
	createBtn = new javax.swing.JButton();
	jLabel2 = new javax.swing.JLabel();
	jLabel2.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel3 = new javax.swing.JLabel();
	jLabel3.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel4 = new javax.swing.JLabel();
	jLabel4.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel5 = new javax.swing.JLabel();
	jLabel5.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel7 = new javax.swing.JLabel();
	jLabel7.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel8 = new javax.swing.JLabel();
	jLabel8.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel9 = new javax.swing.JLabel();
	jLabel9.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel10 = new javax.swing.JLabel();
	jLabel10.setFont(new Font("Silom", Font.PLAIN, 13));
	jLabel11 = new javax.swing.JLabel();
	jLabel11.setFont(new Font("Silom", Font.PLAIN, 13));

	/**
	 * It controls of name format by pattern. Text is set green if format is
	 * correct, red if incorrect and once focus is lost is set on black.
	 */
	nameTextField = new javax.swing.JTextField();
	nameTextField.setFont(new Font("Dialog", Font.PLAIN, 13));
	nameTextField.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {

		pat = Pattern.compile("^.{1,50}");
		mat = pat.matcher(nameTextField.getText());

		if (!mat.matches()) {
		    nameTextField.setForeground(Color.RED);
		    nameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    nameTextField.setForeground(Color.RED);
			}
		    });
		} else {
		    nameTextField.setForeground(new Color(34, 139, 34));
		    nameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    nameTextField.setForeground(Color.BLACK);
			}
		    });
		}

	    }
	});

	/**
	 * It controls of geographical data format by pattern. Text is set green if
	 * format is correct, red if incorrect and once focus is lost is set on black.
	 */
	geoTextField = new javax.swing.JTextField();
	geoTextField.setFont(new Font("Dialog", Font.PLAIN, 13));
	geoTextField.addKeyListener(new KeyAdapter() {

	    @Override
	    public void keyReleased(KeyEvent e) {

		pat = Pattern.compile("^.{1,50}");
		mat = pat.matcher(geoTextField.getText());

		if (!mat.matches()) {
		    geoTextField.setForeground(Color.RED);
		    geoTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    geoTextField.setForeground(Color.RED);
			}
		    });
		} else {
		    geoTextField.setForeground(new Color(34, 139, 34));
		    geoTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
			    geoTextField.setForeground(Color.BLACK);

			}
		    });
		}

	    }
	});

	/**
	 * It controls of date of birth format by pattern. Text is set green if format
	 * is correct, red if incorrect and once focus is lost is set on black. Date is
	 * picked up from JDateChooser and compared to today's date with LocalDate class
	 * and Period class to check limit of age. Date is saved into a variable to show
	 * it on textField.
	 */
	dobDChooser.setDateFormatString("dd/MM/yyyy");
	dobDChooser.addPropertyChangeListener(new PropertyChangeListener() {
	    public void propertyChange(PropertyChangeEvent evt) {

		if (evt != null) {

		    try {

			SimpleDateFormat dobFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dobDChooser.getDate();
			dobJcalendar = dobFormat.format(date);

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dobNewUser = LocalDate.parse(dobJcalendar, fmt);
			Period ageCompare = Period.between(dobNewUser, today);
			
			if (ageCompare.getYears() < 18) {
				JOptionPane.showMessageDialog(null, "Employee must be 18 years old at least");
				dobDChooser.setCalendar(null);
			    }

		    } catch (NullPointerException e) {
			System.out.println("Error date of birth");
		    }
		}
	    }
	});

	/**
	 * It controls of phone format by pattern. Text is set green if format is
	 * correct, red if incorrect and once focus is lost is set on black.
	 */
	phoneTextField = new javax.swing.JTextField();
	phoneTextField.setFont(new Font("Dialog", Font.PLAIN, 13));
	phoneTextField.addKeyListener(new KeyAdapter() {

	    @Override
	    public void keyReleased(KeyEvent e) {

		pat = Pattern.compile("^[67]\\d{8}$");
		mat = pat.matcher(phoneTextField.getText());

		if (!mat.matches()) {
		    phoneTextField.setForeground(Color.RED);
		    phoneTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    phoneTextField.setForeground(Color.RED);
			}
		    });
		} else {
		    phoneTextField.setForeground(new Color(34, 139, 34));
		    phoneTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
			    phoneTextField.setForeground(Color.BLACK);

			}
		    });
		}

	    }
	});

	/**
	 * It controls of social security number format by pattern. Text is set green if
	 * format is correct, red if incorrect and once focus is lost is set on black.
	 */
	ssnTextField = new javax.swing.JTextField();
	ssnTextField.setFont(new Font("Dialog", Font.PLAIN, 13));
	ssnTextField.addKeyListener(new KeyAdapter() {

	    @Override
	    public void keyReleased(KeyEvent e) {

		pat = Pattern.compile("^\\d{10}$");
		mat = pat.matcher(ssnTextField.getText());

		if (!mat.matches()) {
		    ssnTextField.setForeground(Color.RED);
		    ssnTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    ssnTextField.setForeground(Color.RED);
			}
		    });
		} else {
		    ssnTextField.setForeground(new Color(34, 139, 34));
		    ssnTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
			    ssnTextField.setForeground(Color.BLACK);

			}
		    });
		}

	    }
	});

	/**
	 * It controls of email format by pattern. Text is set green if format is
	 * correct, red if incorrect and once focus is lost is set on black.
	 */
	emailTextField = new javax.swing.JTextField();
	emailTextField.setFont(new Font("Dialog", Font.PLAIN, 13));
	emailTextField.addKeyListener(new KeyAdapter() {

	    @Override
	    public void keyReleased(KeyEvent e) {

		pat = Pattern.compile("^\\w+@\\w+\\.[a-z]{2,3}$");
		mat = pat.matcher(emailTextField.getText());

		if (!mat.matches()) {
		    emailTextField.setForeground(Color.RED);
		    emailTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    emailTextField.setForeground(Color.RED);
			}
		    });
		} else {
		    emailTextField.setForeground(new Color(34, 139, 34));
		    emailTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
			    emailTextField.setForeground(Color.BLACK);

			}
		    });
		}

	    }
	});

	/**
	 * It controls of bank format by pattern. Text is set green if format is
	 * correct, red if incorrect and once focus is lost is set on black.
	 */
	bankTextField = new javax.swing.JTextField();
	bankTextField.setFont(new Font("Dialog", Font.PLAIN, 13));
	bankTextField.addKeyListener(new KeyAdapter() {

	    @Override
	    public void keyReleased(KeyEvent e) {

		pat = Pattern.compile("^ES\\d{2}-\\d{4}-\\d{4}-\\d{2}-\\d{10}$");
		mat = pat.matcher(bankTextField.getText());

		if (!mat.matches()) {
		    bankTextField.setForeground(Color.RED);
		    bankTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			    bankTextField.setForeground(Color.RED);
			}
		    });
		} else {
		    bankTextField.setForeground(new Color(34, 139, 34));
		    bankTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
			    bankTextField.setForeground(Color.BLACK);

			}
		    });
		}
	    }
	});

	/**
	 * It controls of date of start format by pattern. Text is set green if format
	 * is correct, red if incorrect and once focus is lost is set on black. Date is
	 * picked up from JDateChooser and saved into a variable to show it on
	 * textField.
	 */
	dosDChooser.setDateFormatString("dd/MM/yyyy");
	dosDChooser.addPropertyChangeListener(new PropertyChangeListener() {

	    public void propertyChange(PropertyChangeEvent evt) {

		if (evt != null) {

		    try {

			SimpleDateFormat dosFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date2 = dosDChooser.getDate();
			dosJcalendar = dosFormat.format(date2);

		    } catch (NullPointerException e1) {
			System.out.println("Falla fecha de comienzo");
		    }

		}

	    }
	});

	/**
	 * ComboBox component to make a selection for department.
	 */
	departmComboBox = new javax.swing.JComboBox();
	departmComboBox.setFont(new Font("Silom", Font.BOLD, 12));
	/**
	 * Generates the comboBox for department selection where enum names are passed
	 * by param.
	 */
	departmComboBox.setModel(new DefaultComboBoxModel(Department.values()));
	separator = new javax.swing.JSeparator();

	jLabel1.setFont(new Font("Silom", Font.BOLD, 27)); // NOI18N
	jLabel1.setText("Create Profile");

	/**
	 * Generates an event to use the image url to show it on personal profile panel.
	 */
	jButton1.setText("Select Photo");
	jButton1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton1ActionPerformed(evt);
	    }
	});

	/**
	 * Generates an event to use the image url to show it on personal profile panel
	 * using a path.
	 */
	picTextPath.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		picTextPathActionPerformed(evt);
	    }
	});

	/**
	 * Generates an event to creates and save personal profiles once is clicked the
	 * create button.
	 */
	createBtn.setFont(new Font("Silom", Font.BOLD, 15)); // NOI18N
	createBtn.setText("Create");
	createBtn.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		createBtnActionPerformed(evt);
	    }
	});

	jLabel2.setText("Name: ");
	jLabel3.setText("Geographic Data: ");
	jLabel4.setText("Date of Birth: ");
	jLabel5.setText("Phone: ");
	jLabel7.setText("Email: ");
	jLabel8.setText("Social Security Number: ");
	jLabel9.setText("Bank Account Number: ");
	jLabel10.setText("Date of start :");
	jLabel11.setText("Department :");

	emailTextField.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		emailTextFieldActionPerformed(evt);
	    }
	});

	separator.setForeground(Color.GRAY);
	separator.setBackground(Color.GRAY);

	/**
	 * Generates group layout with all components.
	 */
	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
		.addGroup(layout.createSequentialGroup().addGap(54)
			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addComponent(separator, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 751,
					Short.MAX_VALUE)
				.addGroup(layout
					.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel8)
							.addPreferredGap(ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(ssnTextField, 244, 244, 244))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel2)
							.addPreferredGap(ComponentPlacement.RELATED, 137,
								Short.MAX_VALUE)
							.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 244,
								GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel9)
							.addPreferredGap(
								ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(
								bankTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel10).addComponent(jLabel11))
							.addPreferredGap(ComponentPlacement.RELATED, 84,
								Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(dosDChooser, GroupLayout.PREFERRED_SIZE,
									244, GroupLayout.PREFERRED_SIZE)
								.addComponent(departmComboBox,
									GroupLayout.PREFERRED_SIZE, 242,
									GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel3)
								.addGroup(layout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jLabel4)))
							.addPreferredGap(ComponentPlacement.RELATED, 59,
								Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dobDChooser, GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(geoTextField, GroupLayout.DEFAULT_SIZE,
									244, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(layout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(jLabel7)
									.addPreferredGap(ComponentPlacement.RELATED,
										140, Short.MAX_VALUE)
									.addComponent(emailTextField,
										GroupLayout.PREFERRED_SIZE, 244,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(layout
									.createSequentialGroup().addComponent(jLabel5)
									.addPreferredGap(ComponentPlacement.RELATED,
										133, Short.MAX_VALUE)
									.addComponent(phoneTextField,
										GroupLayout.PREFERRED_SIZE, 244,
										GroupLayout.PREFERRED_SIZE)))))
					.addGap(30)
					.addComponent(picTextPath, GroupLayout.PREFERRED_SIZE, 149,
						GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton1).addGap(7)))
			.addGap(229))
		.addGroup(layout.createSequentialGroup().addGap(319).addComponent(createBtn).addContainerGap(625,
			Short.MAX_VALUE))
		.addGroup(layout.createSequentialGroup().addGap(268).addComponent(jLabel1).addContainerGap(559,
			Short.MAX_VALUE)));
	layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
		.addGap(64).addComponent(jLabel1).addGap(26)
		.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
			.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addGroup(layout.createSequentialGroup().addComponent(jLabel2).addGap(2)))
		.addPreferredGap(ComponentPlacement.RELATED)
		.addGroup(layout.createParallelGroup(Alignment.TRAILING)
			.addComponent(geoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addComponent(jLabel3))
		.addGap(10)
		.addGroup(layout.createParallelGroup(Alignment.LEADING)
			.addComponent(dobDChooser, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			.addComponent(jLabel4, Alignment.TRAILING))
		.addGroup(layout.createParallelGroup(Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(7).addComponent(phoneTextField,
				GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGroup(layout.createSequentialGroup().addGap(18).addComponent(jLabel5)))
		.addPreferredGap(ComponentPlacement.RELATED)
		.addGroup(layout.createParallelGroup(Alignment.TRAILING)
			.addGroup(layout.createSequentialGroup()
				.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					GroupLayout.PREFERRED_SIZE)
				.addGap(14))
			.addGroup(layout.createSequentialGroup().addComponent(jLabel7)
				.addPreferredGap(ComponentPlacement.UNRELATED)))
		.addGroup(layout.createParallelGroup(Alignment.TRAILING)
			.addComponent(ssnTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addComponent(jLabel8))
		.addPreferredGap(ComponentPlacement.RELATED)
		.addGroup(layout.createParallelGroup(Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addGap(8).addComponent(jLabel9).addGap(18)
				.addComponent(jLabel10))
			.addGroup(layout.createSequentialGroup()
				.addComponent(bankTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					GroupLayout.PREFERRED_SIZE)
				.addGap(8).addComponent(dosDChooser, GroupLayout.PREFERRED_SIZE,
					GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		.addGap(13)
		.addComponent(
			separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		.addGap(15)
		.addGroup(layout.createParallelGroup(Alignment.BASELINE)
			.addComponent(departmComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addComponent(jLabel11).addComponent(jButton1).addComponent(picTextPath,
				GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		.addGap(26).addComponent(createBtn).addGap(319)));
	layout.linkSize(SwingConstants.HORIZONTAL, ssnTextField, emailTextField, bankTextField);

	this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Creates an object JFileChooser to associate the url image file and show it on
     * personal profile panel.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

	JFileChooser chooser = new JFileChooser();
	chooser.showOpenDialog(null);
	File f = chooser.getSelectedFile();
	String fileName = f.getAbsolutePath();
	picTextPath.setText(fileName);

    }// GEN-LAST:event_jButton1ActionPerformed

    private void picTextPathActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_picTextPathActionPerformed
    }// GEN-LAST:event_picTextPathActionPerformed

    /**
     * Creates objects Person and save then on arraylist (profileList). Set null all
     * textFields once a profile is created.
     */
    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createBtnActionPerformed

	if (nameTextField.getText().isEmpty() || geoTextField.getText().isEmpty() || phoneTextField.getText().isEmpty()
		|| emailTextField.getText().isEmpty() || ssnTextField.getText().isEmpty()
		|| bankTextField.getText().isEmpty() || picTextPath.getText().isEmpty() || dobDChooser == null
		|| dosDChooser == null) {

	    JOptionPane.showMessageDialog(null, "Please, fill all the boxes before creating a profile. ");

	} else {
	    person = new Person();

	    profileList.add(new Person(nameTextField.getText(), geoTextField.getText(), dobJcalendar,
		    phoneTextField.getText(), emailTextField.getText(), ssnTextField.getText(), bankTextField.getText(),
		    dosJcalendar, selectDepartment(comboOption), picTextPath.getText(), Person.getIncrement()));

	    JOptionPane.showMessageDialog(null, "Profile created successfully !!!");

	    nameTextField.setText(null);
	    geoTextField.setText(null);
	    dobDChooser.setDate(null);
	    phoneTextField.setText(null);
	    emailTextField.setText(null);
	    ssnTextField.setText(null);
	    bankTextField.setText(null);
	    dosDChooser.setDate(null);
	    picTextPath.setText(null);
	    departmComboBox.setSelectedIndex(0);
	}

    }// GEN-LAST:event_createBtnActionPerformed

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_emailTextFieldActionPerformed
    }// GEN-LAST:event_emailTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Declaration of JButton createBtn object.
     */
    private javax.swing.JButton createBtn;

    /**
     * Declaration of JButton jButton1 object.
     */
    private javax.swing.JButton jButton1;

    /**
     * Declaration of JLabel jLabel1 object.
     */
    private javax.swing.JLabel jLabel1;

    /**
     * Declaration of JLabel jLabel2 object.
     */
    private javax.swing.JLabel jLabel2;

    /**
     * Declaration of JLabel jLabel3 object.
     */
    private javax.swing.JLabel jLabel3;

    /**
     * Declaration of JLabel jLabel4 object.
     */
    private javax.swing.JLabel jLabel4;

    /**
     * Declaration of JLabel jLabel5 object.
     */
    private javax.swing.JLabel jLabel5;

    /**
     * Declaration of JButton jLabel7 object.
     */
    private javax.swing.JLabel jLabel7;

    /**
     * Declaration of JLabel jLabel8 object.
     */
    private javax.swing.JLabel jLabel8;

    /**
     * Declaration of JButton jLabel9 object.
     */
    private javax.swing.JLabel jLabel9;

    /**
     * Declaration of JButton jLabel10 object.
     */
    private javax.swing.JLabel jLabel10;

    /**
     * Declaration of JButton jLabel11 object.
     */
    private javax.swing.JLabel jLabel11;

    /**
     * Declaration of JTextField nameTextField object.
     */
    private javax.swing.JTextField nameTextField;

    /**
     * Declaration of JTextField geoTextField object.
     */
    private javax.swing.JTextField geoTextField;

    /**
     * Declaration of JTextField phoneTextField object.
     */
    private javax.swing.JTextField phoneTextField;

    /**
     * Declaration of JTextField emailTextField object.
     */
    private javax.swing.JTextField emailTextField;

    /**
     * Declaration of JTextField ssnTextField object.
     */
    private javax.swing.JTextField ssnTextField;

    /**
     * Declaration of JTextField bankTextField object.
     */
    private javax.swing.JTextField bankTextField;

    /**
     * Declaration of JTextField picTextPath object.
     */
    private javax.swing.JTextField picTextPath;

    @SuppressWarnings("rawtypes")
    /**
     * Declaration of JComboBox departmComboBox object.
     */
    private javax.swing.JComboBox departmComboBox;

    /**
     * Declaration of JSeparator separator object.
     */
    private javax.swing.JSeparator separator;
}



SimpleDateFormat fechaAlqFormat = new SimpleDateFormat("d MMM y", Locale.ENGLISH);
Date date = fechaAlq.getDate();
fechaAlqFormJCal = fechaAlqFormat.format(date);

DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMM y");
fechaConvertAlq = LocalDate.parse(fechaAlqFormJCal, fmt);
Period fechaCompareAlq = Period.between(fechaConvertAlq, LocalDate.now());

if (((ChronoLocalDate) fechaAlq).isBefore(LocalDate.now())) {
	JOptionPane.showMessageDialog(null, "La fecha no puede estar en pasado");
	fechaAlq.setCalendar(null);
}

} catch (NullPointerException e) {
e.printStackTrace();
System.out.println("Error date of birth");

}