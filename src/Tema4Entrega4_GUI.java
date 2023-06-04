import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;


/**
 * @author FERNANDO ROMERO DE ÁVILA - 1º DAW 2022-24
 * @version 4.3 02/01/2023
 */
public class Tema4Entrega4_GUI {

    /**
     * Constantes.
     *
     * Incluye: Precios para mostrarse en las etiquetas. Nombres de camareros
     * desplegable del menú apartado Personal. Formatos para la muestra del ticket.
     * Precios en double para el cálculo del total y operaciones con descuentos.
     */
    static final String PVP_PRIM_1 = "48.55 €";
    static final String PVP_PRIM_2 = "43.35 €";
    static final String PVP_PRIM_3 = "41.45 €";
    static final String PVP_SEG = "63.89 €";
    static final String PVP_POSTR = "24.50 €";
    static final String PVP_AGUA = "3.50 €";
    static final String PVP_COP_VINO = "7.50 €";
    static final String PVP_CERVE = "5.20 €";
    static final String PVP_REFRES = "3.80 €";
    static final String PVP_CAFE_CORT = "2.80 €";
    static final String PVP_CAFE_EXP = "2.00 €";
    static final String PVP_CAFE_LECH = "3.55 €";

    static final String CAMARERO1 = "Carlos G.";
    static final String CAMARERO2 = "Yoana T.";
    static final String CAMARERO3 = "Cecilia R.";
    static final String NUMPLAT = "1";

    // Grupo de formateadores de ticket, para dar aspecto de pantalla o panel de TPV
    // realista
    static final String ESPAG = "    ";
    static final String ESPAP = " ";

    static final String SEPAX = "x";
    static final String SEPATICK = "------------------------------------------";
    static final String SEPATICK2 = "================================";
    // Se usa una línea ya formateada para mostrar las cantidades (siempre 1), más
    // el precio del plato, espaciados en el editorpane del ticket.
    static final String LINEA_PREC_CANT = "\n" + ESPAP + NUMPLAT + ESPAP + SEPAX + ESPAP;

    static final double DPVP_PRIM_1 = 48.55;
    static final double DPVP_PRIM_2 = 43.35;
    static final double DPVP_PRIM_3 = 41.45;
    static final double DPVP_SEG = 63.89;
    static final double DPVP_POSTR = 24.50;
    static final double DPVP_AGUA = 3.50;
    static final double DPVP_COP_VINO = 7.50;
    static final double DPVP_CERVE = 5.20;
    static final double DPVP_REFRES = 3.80;
    static final double DPVP_CAFE_CORT = 2.80;
    static final double DPVP_CAFE_EXP = 2;
    static final double DPVP_CAFE_LECH = 3.55;

    /**
     * Variables.
     *
     * Incluye: Cadenas de nombres de los platos. Variables de datos para
     * cumplimentar ticket y operaciones de muestra de tarjeta codificada. Variables
     * para dar formato al ticket (cabecera, total, etc).
     */
    double sumaTot = 0.0;
    // Lo utilizo para saber si se ha pulsado el botón de ticket y hay que cobrar o
    // anular pedido
    int pendienteCobro;
    int descuentoApli = 0;
    int aplicadoDescuent = 0;

    String prim1Str = "Risotto de langosta y percebes";
    String prim2Str = "Pasta de anguilas glaseadas";
    String prim3Str = "Arroz de carabineros con vieiras ";
    String seg1Str = "Solomillo de vaca vieja al carbón";
    String seg2Str = "Tacos de atún con sala de almejas";
    String seg3Str = "Pichón de las Landas en su jugo";
    String post1Str = "Esferas de canela con mandarina";
    String post2Str = "Flan de jengibre con melón";
    String cabeceraServComi = "SERVICIO DE COMIDA";
    String cabeceraConcept = "CANT.     CONCEPTOS                      IMPORTE:";
    String total = " TOTAL:      ";
    String total2 = " TOTAL:   ";
    // En estas variables guardamos la selección de las opciones de la barra de
    // menú.
    String camareroX = "";
    String mesaX = "";
    String nComensalesX = "";
    // Se usa para mostrar el número de tarjeta oculto en asteríscos, guardando los
    // últimos 4 dígitos en esta cadena de String.
    String ultDigts = "";
    // El boton de "<" borrar acción, muestra el último ticket (String) que he
    // guardado del editorPane en este String, solo borra una acción lógicamente.
    String utlimoTicket = "";
    String fechaReserva;

    /**
     * Objetos declarados para trabjar en ámbito global. Incluye:
     *
     * Ventana principal. EditorPane para el ticket. Calendario para obtención de
     * fecha y hora. Formato para decimales. Patrón y comprobador de número de
     * tarjeta.
     */
    private JFrame frameRestaurantePDA;

    JEditorPane editPanTick = new JEditorPane();
    Calendar calendario = Calendar.getInstance();
    DecimalFormat formatoSumaTot = new DecimalFormat("###.##");
    Pattern pat = Pattern.compile("^\\d{16}$");
    Matcher mat;
    private JTextField textFieldFechaRese;
    SimpleDateFormat fechaRese = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Lanza la aplicación.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Tema4Entrega4_GUI window = new Tema4Entrega4_GUI();
		    window.frameRestaurantePDA.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Crea la aplicación inicializando todos sus componentes medienta initialize()
     * y muestra el ticket con pantalla de inicio mediante pantallaInicio().
     *
     * @see pantallaInicio()
     */
    public Tema4Entrega4_GUI() {
	initialize();
	pantallaInicio();

    }

    /**
     * Muestra saludo e indicación por pasos para iniciar un pedido siguiendo un
     * orden. Se invoca también cuando se pulsa Reset.
     */
    void pantallaInicio() {
	editPanTick.setBorder(new LineBorder(new Color(255, 253, 248)));

	editPanTick.setText(" BUENOS DÍAS!!");
	editPanTick.setText(editPanTick.getText() + "\n\n" + " Para comenzar:");
	editPanTick.setText(editPanTick.getText() + "\n\n" + " 1. Seleccione servicio en pestaña Nuevo Pedido.");
	editPanTick.setText(editPanTick.getText() + "\n" + " 2. Seleccione nombre de personal.");
	editPanTick.setText(editPanTick.getText() + "\n" + " 3. Seleccione nº de mesa.");
	editPanTick.setText(editPanTick.getText() + "\n" + " 4. Seleccione nº de comensales.");
	editPanTick.setText(editPanTick.getText() + "\n\n" + " Para cerrar la aplicación, pulse Salir.");

    }

    /**
     * Muestra cabecera del ticket con el nº de ticket y la fecha/hora al iniciar el
     * pedido, una vez pulsado Nuevo Pedido.
     *
     * @see obtenerFecha()
     */
    void cabeceraTickInicio() {

	editPanTick.setText("");
	editPanTick.setText(editPanTick.getText() + "\n" + "                      PEDIDO TICKET Nº: 157");
	editPanTick.setText(editPanTick.getText() + "\n");
	obtnerFecha();
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK2);

    }

    /**
     * Calcula la fecha y la hora en el momento de invocarse para mostrarse al
     * iniciar un pedido. Se está actualizando constantemente si la cabecera cambia.
     */
    void obtnerFecha() {

	LocalDateTime ahora = LocalDateTime.now();

	int dia = ahora.getDayOfMonth();
	int mes = ahora.getMonthValue();
	int año = ahora.getYear();
	int hora = ahora.getHour();
	int minutos = ahora.getMinute();
	int segundos = ahora.getSecond();

	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + dia + "/" + mes + "/" + año + " - " + hora + ":"
		+ minutos + ":" + segundos);

    }

    /**
     * Añade a la cabecera de ticket (editorpane) lo que tenía previamente al
     * invocar cabeceraTickInicio () y suma la opción de servicio seleccionada.
     *
     * @see cabeceraTickInicio()
     */
    void cabeceraTickComi() {

	cabeceraTickInicio();
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + cabeceraServComi);
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK);

    }

    /**
     * Añade a la cabecera de ticket (editorpane) lo que tenía previamente al
     * invocar cabeceraTickComi() y suma la opción de personal seleccionada.
     *
     * @see cabeceraTickComi()
     */
    void cabeceraPersonal() {

	cabeceraTickComi();
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Atendido por: " + camareroX);
	utlimoTicket = editPanTick.getText();

    }

    /**
     * Añade a la cabecera de ticket (editorpane) lo que tenía previamente al
     * invocar cabeceraPersona() y suma la opción de mesa seleccionada.
     *
     * @see cabeceraPersona()
     */
    void cabeceraMesa() {

	cabeceraPersonal();
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Mesa: " + mesaX);
	utlimoTicket = editPanTick.getText();

    }

    /**
     * Añade a la cabecera de ticket (editorpane) lo que tenía previamente al
     * invocar cabeceraMesa() y suma la opción de nº de comensales seleccionados.
     *
     * @see cabeceraMesa()
     */
    void cabeceraComensales() {

	cabeceraMesa();
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Comensales: " + nComensalesX);
	utlimoTicket = editPanTick.getText();

    }

    /**
     * Añade a la cabecera de ticket (editorpane) lo que tenía previamente al
     * invocar cabeceraComensales() y suma las líneas de conceptos para formato del
     * ticket.
     *
     * @see cabeceraComensales()
     */
    void cabeceraConceptos() {

	cabeceraComensales();
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK2);
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + cabeceraConcept);
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK2);
	utlimoTicket = editPanTick.getText();

    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea de primer plato
     * (opción1), la cantidad 1 y el precio del plato.
     */
    void imprimePrimerPlato1() {

	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + prim1Str);
	editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_PRIM_1);
    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea de primer plato
     * (opción2), la cantidad 1 y el precio del plato.
     */
    void imprimePrimerPlato2() {
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + prim2Str);
	editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_PRIM_2);

    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea de primer plato
     * (opción3), la cantidad 1 y el precio del plato.
     */
    void imprimePrimerPlato3() {
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + prim3Str);
	editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_PRIM_3);

    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea de segundo plato
     * (opción1), e invoca precioSegundos() para añadir la cantidad 1 y precio fijo.
     *
     * @see precioSegundos()
     */
    void imprimeSegPlato1() {

	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + seg1Str);
	precioSegundos();
    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea de segundo plato
     * (opción2), e invoca precioSegundos() para añadir la cantidad 1 y precio fijo.
     *
     * @see precioSegundos()
     */
    void imprimeSegPlato2() {
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + seg2Str);
	precioSegundos();
    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea de segundo plato
     * (opción3), e invoca precioSegundos() para añadir la cantidad 1 y precio fijo.
     *
     * @see precioSegundos()
     */
    void imprimeSegPlato3() {
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + seg3Str);
	precioSegundos();
    }

    /**
     * Muestra la línea preformateada con la cantidad de platos y precios fijos de
     * los segundos platos.
     */
    void precioSegundos() {

	editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_SEG);

    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea postre (opción1), e
     * invoca precioPostres() para añadir la cantidad 1 y precio fijo.
     *
     * @see precioPostres()
     */
    void imprimePostre1() {
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + post1Str);
	precioPostres();
    }

    /**
     * Recoge lo que muestra el editorpane y suma la línea postre (opción2), e
     * invoca precioPostres() para añadir la cantidad 1 y precio fijo.
     *
     * @see precioPostres()
     */
    void imprimePostre2() {
	editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + post2Str);
	precioPostres();
    }

    /**
     * Muestra la línea preformateada con la cantidad de platos y precios fijos de
     * los postres.
     */
    void precioPostres() {

	editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_POSTR);

    }

    /**
     * Inicializa los contenidos de la ventana principal.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initialize() {

	frameRestaurantePDA = new JFrame();
	frameRestaurantePDA.getContentPane().setEnabled(false);
	frameRestaurantePDA.getContentPane().setFont(new Font("Dialog", Font.ITALIC, 19));
	frameRestaurantePDA.getContentPane().setBackground(new Color(247, 247, 247));
	frameRestaurantePDA.setBounds(100, 100, 1165, 950);
	frameRestaurantePDA.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frameRestaurantePDA.getContentPane().setLayout(null);

	/*
	 * Permite modificar el aspecto de la ventana al desarrollar en mac os.
	 */
	try {
	    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	/*
	 * Componentes incluidos en la ventana: EditorPanels para ticket y comentarios
	 * con scroll. Etiquetas de texto para mostrar: primeros, segundos, postres,
	 * bebida, café, comentarios, pago. Etiquetas de texto para mostrar los precios
	 */
	editPanTick.setFont(new Font("Dialog", Font.BOLD, 16));
	editPanTick.setForeground(Color.BLUE);
	editPanTick.setEditable(false);
	editPanTick.setBounds(370, 4, 45, 71);
	frameRestaurantePDA.getContentPane();

	JScrollPane scrollPane1 = new JScrollPane();
	scrollPane1.setBounds(54, 604, 396, 49);
	frameRestaurantePDA.getContentPane().add(scrollPane1);

	JEditorPane editPanCom = new JEditorPane();
	editPanCom.setEnabled(false);
	editPanCom.setFont(new Font("Open Sans", Font.PLAIN, 15));
	scrollPane1.setViewportView(editPanCom);

	JScrollPane scrollPane = new JScrollPane(editPanTick);
	scrollPane.setBounds(662, 104, 444, 534);
	frameRestaurantePDA.getContentPane().add(scrollPane);

	JTextArea textAreaTot = new JTextArea();
	textAreaTot.setEditable(false);
	textAreaTot.setEnabled(false);
	textAreaTot.setBorder(new LineBorder(new Color(255, 253, 248)));
	textAreaTot.setFont(new Font("Dialog", Font.BOLD, 38));
	textAreaTot.setForeground(new Color(54, 248, 9));
	textAreaTot.setBackground(Color.DARK_GRAY);
	textAreaTot.setBounds(662, 641, 444, 65);
	frameRestaurantePDA.getContentPane().add(textAreaTot);

	JLabel lblInfoPla1 = new JLabel("Primeros");
	lblInfoPla1.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoPla1.setBounds(54, 217, 93, 25);
	frameRestaurantePDA.getContentPane().add(lblInfoPla1);
	lblInfoPla1.setVisible(true);

	JLabel lblInfoPla2 = new JLabel("Segundos");
	lblInfoPla2.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoPla2.setBounds(54, 316, 93, 25);
	frameRestaurantePDA.getContentPane().add(lblInfoPla2);
	lblInfoPla2.setVisible(true);

	JLabel lblInfoPostre = new JLabel("Postres");
	lblInfoPostre.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoPostre.setBounds(54, 409, 93, 25);
	frameRestaurantePDA.getContentPane().add(lblInfoPostre);
	lblInfoPostre.setVisible(true);

	JLabel lblInfoBebi = new JLabel("Bebida");
	lblInfoBebi.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoBebi.setBounds(54, 475, 93, 25);
	frameRestaurantePDA.getContentPane().add(lblInfoBebi);
	lblInfoBebi.setVisible(true);

	JLabel lblInfoCafe = new JLabel("Café");
	lblInfoCafe.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoCafe.setBounds(54, 514, 93, 25);
	frameRestaurantePDA.getContentPane().add(lblInfoCafe);
	lblInfoCafe.setVisible(true);

	JLabel lblPrecPrim = new JLabel(PVP_PRIM_1);
	lblPrecPrim.setFont(new Font("Dialog", Font.PLAIN, 18));
	lblPrecPrim.setBounds(537, 209, 74, 39);
	frameRestaurantePDA.getContentPane().add(lblPrecPrim);
	lblPrecPrim.setVisible(false);

	JLabel lblPrecSeg = new JLabel(PVP_SEG);
	lblPrecSeg.setFont(new Font("Dialog", Font.PLAIN, 18));
	lblPrecSeg.setBounds(537, 306, 74, 49);
	frameRestaurantePDA.getContentPane().add(lblPrecSeg);
	lblPrecSeg.setVisible(false);

	JLabel lblPrecPostr = new JLabel(PVP_POSTR);
	lblPrecPostr.setFont(new Font("Dialog", Font.PLAIN, 18));
	lblPrecPostr.setBounds(537, 402, 74, 37);
	frameRestaurantePDA.getContentPane().add(lblPrecPostr);
	lblPrecPostr.setVisible(false);

	JLabel lblPrecBebi = new JLabel("");
	lblPrecBebi.setFont(new Font("Dialog", Font.PLAIN, 18));
	lblPrecBebi.setBounds(543, 482, 84, 19);
	frameRestaurantePDA.getContentPane().add(lblPrecBebi);
	lblPrecBebi.setVisible(false);

	JLabel lblPrecCafe = new JLabel("");
	lblPrecCafe.setFont(new Font("Dialog", Font.PLAIN, 18));
	lblPrecCafe.setBounds(543, 521, 84, 19);
	frameRestaurantePDA.getContentPane().add(lblPrecCafe);

	JLabel lblInfoComent = new JLabel("Comentarios al chef");
	lblInfoComent.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoComent.setBounds(54, 577, 189, 15);
	frameRestaurantePDA.getContentPane().add(lblInfoComent);

	JLabel lblInfoPromo = new JLabel("Promociones");
	lblInfoPromo.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoPromo.setBounds(54, 691, 130, 15);
	frameRestaurantePDA.getContentPane().add(lblInfoPromo);

	JLabel lblInfoPago = new JLabel("Pago");
	lblInfoPago.setFont(new Font("Silom", Font.BOLD, 16));
	lblInfoPago.setBounds(54, 752, 93, 25);
	frameRestaurantePDA.getContentPane().add(lblInfoPago);
	lblInfoPago.setVisible(true);

	/*
	 * RadioButtons de selección de platos: primeros. Carácteristicas Indicador de
	 * atajo. Mnemonic con letra inicial.
	 */
	JRadioButton rdbtnPrim1 = new JRadioButton("RISOTTO DE LANGOSTA Y PERCEBES");
	JRadioButton rdbtnPrim2 = new JRadioButton("PASTA DE ANGUILAS GLASEADAS");
	JRadioButton rdbtnPrim3 = new JRadioButton("ARROZ DE CARABINEROS CON VIEIRAS");
	rdbtnPrim1.setEnabled(false);
	// Agrupamos los objetos para poder usar los eventos y que no me de error por no
	// reconocerlos
	// Ponemos un radio button por defecto al abrir la app.
	rdbtnPrim1.setSelected(true);
	rdbtnPrim1.setMnemonic(KeyEvent.VK_R);
	rdbtnPrim2.setEnabled(false);
	rdbtnPrim2.setMnemonic(KeyEvent.VK_P);
	rdbtnPrim3.setEnabled(false);
	rdbtnPrim3.setMnemonic(KeyEvent.VK_A);
	// Permite que se seleccione solo un radiobutton.
	ButtonGroup group = new ButtonGroup();
	group.add(rdbtnPrim1);
	group.add(rdbtnPrim2);
	group.add(rdbtnPrim3);

	/**
	 * Evento: Al seleccionar radiobutton, suma línea de primer plato sin borrar lo
	 * anterior al ticket, invocando los métodos imprimePrimerPlato1(),2(),3()
	 *
	 * Se guarda la última cadena de texto del editor pane para borrar última
	 * acción.
	 *
	 * @see imprimePrimerPlato1()
	 * @see imprimePrimerPlato2()
	 * @see imprimePrimerPlato3()
	 *
	 */

	rdbtnPrim1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");

		} else {

		    if (rdbtnPrim1.isSelected()) {
			lblPrecPrim.setText(PVP_PRIM_1);
			utlimoTicket = editPanTick.getText();
			imprimePrimerPlato1();
			sumaTot += DPVP_PRIM_1;

		    }
		}

	    }
	});
	rdbtnPrim2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {
		    if (rdbtnPrim2.isSelected()) {

			lblPrecPrim.setText(PVP_PRIM_2);
			utlimoTicket = editPanTick.getText();
			imprimePrimerPlato2();
			sumaTot += DPVP_PRIM_2;
		    }
		}

	    }
	});
	rdbtnPrim3.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {
		    if (rdbtnPrim3.isSelected()) {
			utlimoTicket = editPanTick.getText();
			lblPrecPrim.setText(PVP_PRIM_3);
			imprimePrimerPlato3();
			sumaTot += DPVP_PRIM_3;
		    }
		}

	    }
	});

	rdbtnPrim1.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnPrim1.setBackground(new Color(247, 247, 247));
	rdbtnPrim1.setBounds(159, 188, 309, 25);
	frameRestaurantePDA.getContentPane().add(rdbtnPrim1);
	rdbtnPrim1.setVisible(true);

	rdbtnPrim2.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnPrim2.setBackground(new Color(247, 247, 247));
	rdbtnPrim2.setBounds(159, 215, 309, 27);
	frameRestaurantePDA.getContentPane().add(rdbtnPrim2);
	rdbtnPrim2.setVisible(true);

	rdbtnPrim3.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnPrim3.setBackground(new Color(247, 247, 247));
	rdbtnPrim3.setBounds(159, 246, 309, 23);
	frameRestaurantePDA.getContentPane().add(rdbtnPrim3);
	rdbtnPrim3.setVisible(true);

	/*
	 * RadioButtons de selección de platos: segundos. Carácteristicas indicador de
	 * atajo. Mnemonic con letra inicial.
	 */
	JRadioButton rdbtnSeg1 = new JRadioButton("SOLOMILLO DE VACA VIEJA AL CARBÓN");
	JRadioButton rdbtnSeg2 = new JRadioButton("TACOS DE ATÚN CON SALA DE ALMEJAS");
	JRadioButton rdbtnSeg3 = new JRadioButton("PICHÓN DE LAS LANDAS EN SU JUGO");
	rdbtnSeg1.setEnabled(false);
	rdbtnSeg1.setMnemonic(KeyEvent.VK_S);
	rdbtnSeg1.setSelected(true);
	rdbtnSeg2.setEnabled(false);
	rdbtnSeg2.setMnemonic(KeyEvent.VK_T);
	rdbtnSeg3.setEnabled(false);
	rdbtnSeg3.setMnemonic(KeyEvent.VK_P);
	ButtonGroup group2 = new ButtonGroup();
	group2.add(rdbtnSeg1);
	group2.add(rdbtnSeg2);
	group2.add(rdbtnSeg3);

	/**
	 * Evento: Al seleccionar radiobutton, suma línea de segundo plato sin borrar lo
	 * anterior, invocando los métodos imprimeSegPlato1(),2(),3()
	 *
	 * Se guarda la última cadena de texto del editor pane para borrar última acción
	 *
	 * @see imprimeSegPlato1();
	 * @see imprimeSegPlato2();
	 * @see imprimeSegPlato3();
	 */
	rdbtnSeg1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {
		    if (rdbtnSeg1.isSelected()) {
			utlimoTicket = editPanTick.getText();
			lblPrecSeg.setText(PVP_SEG);
			imprimeSegPlato1();
			sumaTot += DPVP_SEG;
		    }
		}

	    }
	});
	rdbtnSeg2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {
		    if (rdbtnSeg2.isSelected()) {
			utlimoTicket = editPanTick.getText();
			lblPrecSeg.setText(PVP_SEG);
			imprimeSegPlato2();
			sumaTot += DPVP_SEG;
		    }
		}

	    }
	});
	rdbtnSeg3.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {
		    if (rdbtnSeg3.isSelected()) {
			utlimoTicket = editPanTick.getText();
			lblPrecSeg.setText(PVP_SEG);
			imprimeSegPlato3();
			sumaTot += DPVP_PRIM_3;
		    }
		}

	    }
	});

	rdbtnSeg1.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnSeg1.setBackground(new Color(247, 247, 247));
	rdbtnSeg1.setBounds(159, 290, 309, 23);
	frameRestaurantePDA.getContentPane().add(rdbtnSeg1);
	rdbtnSeg1.setVisible(true);

	rdbtnSeg2.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnSeg2.setBackground(new Color(247, 247, 247));
	rdbtnSeg2.setBounds(159, 317, 309, 26);
	frameRestaurantePDA.getContentPane().add(rdbtnSeg2);
	rdbtnSeg2.setVisible(true);

	rdbtnSeg3.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnSeg3.setBackground(new Color(247, 247, 247));
	rdbtnSeg3.setBounds(159, 347, 309, 25);
	frameRestaurantePDA.getContentPane().add(rdbtnSeg3);
	rdbtnSeg3.setVisible(true);

	/*
	 * RadioButtons de selección de platos: postres. Carácteristicas indicador de
	 * atajo. Mnemonic con letra inicial
	 */
	JRadioButton rdbtnPostr1 = new JRadioButton("ESFERAS DE CANELA CON MANDARINA");
	JRadioButton rdbtnPostr2 = new JRadioButton("FLAN DE JENGIBRE CON MELÓN");
	rdbtnPostr1.setEnabled(false);
	rdbtnPostr1.setMnemonic(KeyEvent.VK_E);
	rdbtnPostr1.setSelected(true);
	rdbtnPostr2.setEnabled(false);
	rdbtnPostr2.setMnemonic(KeyEvent.VK_F);
	ButtonGroup group3 = new ButtonGroup();
	group3.add(rdbtnPostr1);
	group3.add(rdbtnPostr2);

	/**
	 * Evento: Al seleccionar radiobutton, suma línea de postres sin borrar lo
	 * anterior, invocando los métodos imprimePostre1(), 2(). Suma precios para el
	 * cálculo del total de los postres.
	 *
	 * Se guarda la última cadena de texto del editor pane para borrar última
	 * acción.
	 *
	 * @see imprimePostre1();
	 * @see imprimePostre2();
	 */
	rdbtnPostr1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");

		} else {
		    if (rdbtnPostr1.isSelected()) {
			lblPrecPostr.setText(PVP_POSTR);
			utlimoTicket = editPanTick.getText();
			imprimePostre1();
			sumaTot += DPVP_POSTR;
		    }
		}
	    }
	});
	rdbtnPostr2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {
		    if (rdbtnPostr2.isSelected()) {
			lblPrecPostr.setText(PVP_POSTR);
			utlimoTicket = editPanTick.getText();
			imprimePostre2();
			sumaTot += DPVP_POSTR;
		    }
		}

	    }
	});

	rdbtnPostr1.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnPostr1.setBackground(new Color(247, 247, 247));
	rdbtnPostr1.setBounds(159, 394, 309, 23);
	frameRestaurantePDA.getContentPane().add(rdbtnPostr1);
	rdbtnPostr1.setVisible(true);

	rdbtnPostr2.setFont(new Font("Open Sans", Font.PLAIN, 14));
	rdbtnPostr2.setBackground(new Color(247, 247, 247));
	rdbtnPostr2.setBounds(159, 423, 309, 27);
	frameRestaurantePDA.getContentPane().add(rdbtnPostr2);
	rdbtnPostr2.setVisible(true);

	/*
	 * Combobox para la selección de bebida.
	 */
	JComboBox comBoxBebi = new JComboBox();
	comBoxBebi.setBackground(new Color(247, 247, 247));
	comBoxBebi.setModel(new DefaultComboBoxModel(new String[] { "Agua", "Copa de vino", "Cerveza", "Refresco" }));
	comBoxBebi.setEnabled(false);

	// Muestra el precio cuadno el agua esté seleccionada por defecto
	if (comBoxBebi.getSelectedIndex() == 0) {
	    lblPrecBebi.setText(PVP_AGUA);
	}

	/**
	 * Evento: Al seleccionar la opción del combobox bebida, suma línea sin borrar
	 * lo anterior.
	 *
	 * Se guarda la última cadena de texto del editor pane para borrar última acción
	 *
	 */
	comBoxBebi.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {

		    switch (comBoxBebi.getSelectedIndex()) {
		    case 0:
			lblPrecBebi.setText(PVP_AGUA);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Agua");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_AGUA);
			sumaTot += DPVP_AGUA;
			break;
		    case 1:
			lblPrecBebi.setText(PVP_COP_VINO);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Copa de vino");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_COP_VINO);
			sumaTot += DPVP_COP_VINO;
			break;
		    case 2:
			lblPrecBebi.setText(PVP_CERVE);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Cerveza");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_CERVE);
			sumaTot += DPVP_CERVE;
			break;
		    default:
			lblPrecBebi.setText(PVP_REFRES);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Refresco");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_REFRES);
			sumaTot += DPVP_REFRES;
			break;
		    }
		}
	    }
	});

	comBoxBebi.setFont(new Font("Open Sans", Font.PLAIN, 15));
	comBoxBebi.setBounds(159, 479, 291, 24);
	frameRestaurantePDA.getContentPane().add(comBoxBebi);

	/*
	 * Combobox para la selección de café
	 */
	JComboBox comBoxCafe = new JComboBox();
	comBoxCafe.setEnabled(false);
	comBoxCafe
		.setModel(new DefaultComboBoxModel(new String[] { "Café cortado", "Café espresso", "Café con leche" }));

	/**
	 * Evento: Al seleccionar la opción del combobox café, suma línea sin borrar lo
	 * anterior.
	 *
	 * Se guarda la última cadena de texto del editor pane para borrar última
	 * acción.
	 *
	 */
	comBoxCafe.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de comenzar el pedido.");
		} else {

		    switch (comBoxCafe.getSelectedIndex()) {
		    case 0:
			lblPrecCafe.setText(PVP_CAFE_CORT);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Café cortado");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_CAFE_CORT);
			sumaTot += DPVP_CAFE_CORT;
			break;
		    case 1:
			lblPrecCafe.setText(PVP_CAFE_EXP);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Copa espresso");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_CAFE_EXP);
			sumaTot += DPVP_CAFE_EXP;
			break;
		    default:
			lblPrecCafe.setText(PVP_CAFE_LECH);
			utlimoTicket = editPanTick.getText();
			editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + "Copa con leche");
			editPanTick.setText(editPanTick.getText() + LINEA_PREC_CANT + DPVP_CAFE_LECH);
			sumaTot += DPVP_CAFE_LECH;
			break;
		    }
		}
	    }
	});

	comBoxCafe.setFont(new Font("Open Sans", Font.PLAIN, 15));
	comBoxCafe.setBackground(new Color(247, 247, 247));
	comBoxCafe.setBounds(196, 518, 254, 24);
	frameRestaurantePDA.getContentPane().add(comBoxCafe);
	comBoxCafe.setEnabled(false);

	/*
	 * Checkbox para activar o desacrivar el combobox de café.
	 */
	JCheckBox chckbxCafe = new JCheckBox("");
	chckbxCafe.setEnabled(false);
	chckbxCafe.setFont(new Font("Silom", Font.BOLD, 16));
	chckbxCafe.setBackground(new Color(247, 247, 247));

	/**
	 * Muestra precio café por defecto si se activa el combobox.
	 */
	chckbxCafe.addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {

		if (chckbxCafe.isSelected()) {
		    comBoxCafe.setEnabled(true);
		    if (comBoxCafe.getSelectedIndex() == 0) {
			lblPrecCafe.setText(PVP_CAFE_CORT);
			lblPrecCafe.setVisible(true);
		    }
		} else {
		    comBoxCafe.setEnabled(false);
		    lblPrecCafe.setVisible(false);
		}
	    }
	});
	chckbxCafe.setBounds(159, 519, 31, 23);
	frameRestaurantePDA.getContentPane().add(chckbxCafe);

	/**
	 * Botón para añadir comentario al ticket a modo de mensaje a cocina. Crea una
	 * línea debajo del producto previamente añadido
	 */
	JButton btnAñadir = new JButton("Añadir");
	btnAñadir.setEnabled(false);
	btnAñadir.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de añadir comentario.");
		} else {

		    editPanTick.setText(editPanTick.getText() + "\n" + ESPAG + "********" + editPanCom.getText());
		    editPanCom.setText(null);

		}
	    }
	});
	btnAñadir.setForeground(new Color(5, 195, 38));
	btnAñadir.setFont(new Font("Silom", Font.BOLD, 15));
	btnAñadir.setBounds(512, 604, 99, 34);
	frameRestaurantePDA.getContentPane().add(btnAñadir);

	/**
	 * Botón para borrar comentario al editorpane de mensaje al chef. Borra caracter
	 * por caracter
	 */
	JButton btnBorrarComen = new JButton("<");
	btnBorrarComen.setEnabled(false);
	btnBorrarComen.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (editPanCom.getText().length() != 0) {
		    editPanCom.setText(editPanCom.getText().substring(0, editPanCom.getText().length() - 1));
		}

	    }
	});
	btnBorrarComen.setForeground(new Color(255, 0, 0));
	btnBorrarComen.setFont(new Font("Dialog", Font.BOLD, 18));
	btnBorrarComen.setBounds(461, 604, 49, 34);
	frameRestaurantePDA.getContentPane().add(btnBorrarComen);

	/*
	 * Combobox para la selección de promoción.
	 */
	JComboBox comboBoxPromo = new JComboBox();
	comboBoxPromo
		.setModel(new DefaultComboBoxModel(new String[] { "Promo cumpleaños 15%", "Promo mesa 6 pax 10%" }));
	comboBoxPromo.setEnabled(false);
	comboBoxPromo.setFont(new Font("Open Sans", Font.PLAIN, 15));
	comboBoxPromo.setBackground(new Color(247, 247, 247));
	comboBoxPromo.setBounds(180, 686, 270, 24);
	frameRestaurantePDA.getContentPane().add(comboBoxPromo);

	/*
	 * Retorna al ticket al último elemento que se añadió, por tanto borra última
	 * acción
	 */
	JButton btnBorrAccTick = new JButton("<");
	btnBorrAccTick.addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (pendienteCobro > 0) {
		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Hay un ticket pendiente de cobro, por favor, pulse cobrar para finalizar o anular para cancelar.");

		} else if (sumaTot == 0) {
		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "No hay pedido pendiente para borrar última orden, por favor, añada ordenes.");
		} else {

		    editPanTick.setText(utlimoTicket);
		    descuentoApli = 0;

		}

	    }
	});
	btnBorrAccTick.setEnabled(false);
	btnBorrAccTick.setForeground(Color.ORANGE);
	btnBorrAccTick.setFont(new Font("Silom", Font.BOLD, 35));
	btnBorrAccTick.setBounds(874, 726, 63, 75);
	frameRestaurantePDA.getContentPane().add(btnBorrAccTick);

	/*
	 * Aplica los descuentos de las promociones.
	 */
	JButton btnAplicarProm = new JButton("Aplicar");
	btnAplicarProm.setEnabled(false);
	btnAplicarProm.setForeground(new Color(5, 195, 38));
	btnAplicarProm.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		double descuentCumple;
		double descuentMesa6;

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de aplicar promoción.");
		} else {

		    if (descuentoApli != 0) {
			JOptionPane.showMessageDialog(frameRestaurantePDA,
				"Ya se ha aplicado un descuento en este ticket.");

		    } else if (sumaTot == 0) {

			JOptionPane.showMessageDialog(frameRestaurantePDA,
				"No existe un pedido actualmente para aplicar descuento, inserte ordenes por favor.");
		    } else {

			if (comboBoxPromo.getSelectedIndex() == 0) {
			    descuentCumple = (sumaTot * 15.0) / 100;
			    sumaTot = sumaTot - descuentCumple;
			    editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK);
			    editPanTick
				    .setText(editPanTick.getText() + "\n" + ESPAG + "15% Descuento cumpleaños apli.: "
					    + (formatoSumaTot.format(descuentCumple) + " €"));
			    descuentoApli++;

			} else {
			    descuentMesa6 = (sumaTot * 10.0) / 100;
			    sumaTot = sumaTot - descuentMesa6;
			    editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK);
			    editPanTick
				    .setText(editPanTick.getText() + "\n" + ESPAG + "10% Descuento mesa de 6pax apli.: "
					    + (formatoSumaTot.format(descuentMesa6) + " €"));
			    descuentoApli++;

			}
		    }

		}

	    }
	});
	btnAplicarProm.setFont(new Font("Silom", Font.BOLD, 14));
	btnAplicarProm.setBounds(512, 686, 99, 24);
	frameRestaurantePDA.getContentPane().add(btnAplicarProm);

	/**
	 * Módulo de pago con casilla textfield para el número de tarjeta y radiobuttons
	 * efectivo y tarjeta.
	 *
	 * Radiobuttos seleccionables uno y otro apareciendo casilla textfield al
	 * seleccionar tarjeta.
	 */
	JTextField textFieldNumTarj = new JTextField();
	textFieldNumTarj.setFont(new Font("Al Bayan", Font.PLAIN, 22));
	textFieldNumTarj.setBounds(273, 763, 338, 27);
	frameRestaurantePDA.getContentPane().add(textFieldNumTarj);
	textFieldNumTarj.setColumns(10);
	textFieldNumTarj.setVisible(false);

	JRadioButton rdbtnPagoTarj = new JRadioButton(" Tarjeta");
	rdbtnPagoTarj.setEnabled(false);
	JRadioButton rdbtnPagoEfec = new JRadioButton(" Efectivo");
	rdbtnPagoEfec.setEnabled(false);
	rdbtnPagoEfec.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (rdbtnPagoEfec.isSelected()) {
		    rdbtnPagoTarj.setSelected(false);
		    textFieldNumTarj.setVisible(false);
		}
	    }
	});
	rdbtnPagoEfec.setFont(new Font("Silom", Font.PLAIN, 14));
	rdbtnPagoEfec.setBackground(new Color(247, 247, 247));
	rdbtnPagoEfec.setBounds(159, 739, 106, 23);
	frameRestaurantePDA.getContentPane().add(rdbtnPagoEfec);
	rdbtnPagoEfec.setSelected(true);

	rdbtnPagoTarj.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (rdbtnPagoTarj.isSelected()) {
		    rdbtnPagoEfec.setSelected(false);
		    textFieldNumTarj.setVisible(true);
		}
	    }
	});
	rdbtnPagoTarj.setFont(new Font("Silom", Font.PLAIN, 14));
	rdbtnPagoTarj.setBackground(new Color(247, 247, 247));
	rdbtnPagoTarj.setBounds(159, 766, 106, 23);
	frameRestaurantePDA.getContentPane().add(rdbtnPagoTarj);

	/*
	 * Objetos de la barra de menú y botones acción, agrupados para trabajar con
	 * eventos entre ambos objetos.
	 */
	JMenu menuTema = new JMenu("Tema");
	JMenu menuNuevoPe = new JMenu("Nuevo Pedido");
	JMenu menuPersonal = new JMenu("Personal");
	JMenu menuMesa = new JMenu("Mesa");
	JMenu menuComensales = new JMenu("Nº Comensales");
	JButton btnReset = new JButton("Reset");

	/**
	 * Calcula el total del pedido del ticket en el editorpane ticket. Se comprueba
	 * el patrón de tarjeta (16digitos) y muestra ventana popup con 3 opciones: >16,
	 * <16 o precio correcto. Muestra el número de tarjeta oculto entré asteríscos
	 * mostranso solo los último 4 dígitos. Se indica paso a seguir con JOptionpane
	 * en caso de pulsar Ticket por segunda vez y se le indica que tiene 2 opciónes,
	 * cobrar pedido o anularlo, y se retoma la pantalla de pedido Si se cobra se
	 * pone en pantalla de Nuevo Pedido.
	 */
	JButton btnTicket = new JButton("Ticket");
	btnTicket.setEnabled(false);
	btnTicket.setForeground(Color.BLUE);
	btnTicket.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		int longitud;
		char ultDig;
		String ntarjeta;
		String tarjEncrip;
		String sumaTotal;

		if (camareroX == "" || mesaX == "" || nComensalesX == "") {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "Por favor, seleccione nombre de personal, nº de mesa o nº de comensales antes de generar ticket.");
		} else {

		    if (pendienteCobro > 0) {
			JOptionPane.showMessageDialog(frameRestaurantePDA,
				"Por favor, pulse cobrar para finalizar o anular para cancelar el pedido.");

		    } else if (sumaTot == 0) {
			JOptionPane.showMessageDialog(frameRestaurantePDA,
				"No hay pedido pendiente de envío a cocina o de cobro, por favor, añada ordenes.");
		    } else {

			if (rdbtnPagoTarj.isSelected()) {

			    ntarjeta = textFieldNumTarj.getText();
			    longitud = ntarjeta.length();
			    mat = pat.matcher(textFieldNumTarj.getText());

			    if (!mat.matches()) {
				if (longitud < 16) {
				    JOptionPane.showMessageDialog(frameRestaurantePDA,
					    "El nº introducido tiene menos de 16 digitos o formato incorrecto, introdúzcalo de nuevo.");

				} else {
				    JOptionPane.showMessageDialog(frameRestaurantePDA,
					    "El nº introducido tiene más de 16 digitos o formato incorrecto, introdúzcalo de nuevo.");
				}
			    } else {
				btnReset.setText("Cobrar");
				JOptionPane.showMessageDialog(frameRestaurantePDA,
					"Tarjeta aceptada, pulse cobrar para finalizar.");

				editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK2);
				sumaTotal = String.format("%.2f", sumaTot);
				if (sumaTot > 999) {
				    textAreaTot.setText(total2 + ESPAP + sumaTotal + " €");
				} else {
				    textAreaTot.setText(total + ESPAP + sumaTotal + " €");
				}
				editPanTick.setText(editPanTick.getText() + "\n\n" + ESPAP + "***PAGO CON TARJETA***");
				tarjEncrip = textFieldNumTarj.getText();

				for (int i = 12; i <= tarjEncrip.length() - 1; i++) {
				    ultDig = tarjEncrip.charAt(i);
				    ultDigts += ultDig;
				}
				editPanTick.setText(
					editPanTick.getText() + "\n" + ESPAP + "TARJETA Nº:************" + ultDigts);
				editPanTick.setText(editPanTick.getText() + "\n\n");
				pendienteCobro++;

			    }
			} else {

			    editPanTick.setText(editPanTick.getText() + "\n" + ESPAP + SEPATICK2);
			    sumaTotal = String.format("%.2f", sumaTot);
			    // Condicional para poder mostrar el precio sin que se salga de la pantalla en
			    // caso de más de xxx,xx € cifras.
			    if (sumaTot > 999) {
				textAreaTot.setText(total2 + ESPAP + sumaTotal + " €");
			    } else {
				textAreaTot.setText(total + ESPAP + sumaTotal + " €");
			    }
			    editPanTick.setText(editPanTick.getText() + "\n\n" + ESPAP + "***PAGO EN EFECTIVO***");
			    editPanTick.setText(editPanTick.getText() + "\n\n");
			    pendienteCobro++;

			    lblPrecPrim.setVisible(false);
			    lblPrecSeg.setVisible(false);
			    lblPrecPostr.setVisible(false);
			    lblPrecBebi.setVisible(false);
			    lblPrecCafe.setVisible(false);
			    chckbxCafe.setSelected(false);
			    comBoxCafe.setEnabled(false);
			    rdbtnPrim1.setEnabled(false);
			    rdbtnPrim2.setEnabled(false);
			    rdbtnPrim3.setEnabled(false);
			    rdbtnSeg1.setEnabled(false);
			    rdbtnSeg2.setEnabled(false);
			    rdbtnSeg3.setEnabled(false);
			    rdbtnPostr1.setEnabled(false);
			    rdbtnPostr2.setEnabled(false);
			    comBoxBebi.setEnabled(false);
			    comBoxCafe.setEnabled(false);
			    chckbxCafe.setEnabled(false);
			    btnAñadir.setEnabled(false);
			    btnBorrarComen.setEnabled(false);
			    btnReset.setText("Cobrar");
			    comboBoxPromo.setEnabled(false);
			    editPanCom.setEnabled(false);
			    btnAplicarProm.setEnabled(false);
			    rdbtnPagoEfec.setEnabled(false);
			    rdbtnPagoTarj.setEnabled(false);
			    textFieldNumTarj.setText(null);
			    textFieldNumTarj.setEnabled(false);
			    menuNuevoPe.setForeground(Color.BLUE);
			    menuPersonal.setForeground(new Color(51, 51, 51));
			    menuMesa.setForeground(new Color(51, 51, 51));
			    menuComensales.setForeground(new Color(51, 51, 51));

			    // Al pulsar Ticker por primera vez se muestra ventana emergente para dar
			    // indicaciones y poder cerrar el pedido o anular y seguir con el mismo pedido
			    // desde 0
			    JOptionPane.showMessageDialog(frameRestaurantePDA,
				    "Por favor, pulse cobrar para finalizar o anular para cancelar pedido.");
			}

		    }

		}

	    }
	});

	btnTicket.setFont(new Font("Silom", Font.BOLD, 19));
	btnTicket.setBounds(937, 726, 170, 75);
	frameRestaurantePDA.getContentPane().add(btnTicket);

	/**
	 * Borra el ticket sin resetear el pedido completo (sin borrar servicio,
	 * camarero, número de mesa, comensales)
	 */
	JButton btnAnular = new JButton("Anular");
	btnAnular.setEnabled(false);
	btnAnular.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		if (pendienteCobro > 0) {

		    cabeceraConceptos();
		    btnReset.setText("Reset");
		    menuNuevoPe.setForeground(Color.BLUE);
		    menuPersonal.setForeground(new Color(51, 51, 51));
		    menuMesa.setForeground(new Color(51, 51, 51));
		    menuComensales.setForeground(new Color(51, 51, 51));
		    rdbtnPrim1.setEnabled(true);
		    rdbtnPrim2.setEnabled(true);
		    rdbtnPrim3.setEnabled(true);
		    rdbtnSeg1.setEnabled(true);
		    rdbtnSeg2.setEnabled(true);
		    rdbtnSeg3.setEnabled(true);
		    rdbtnPostr1.setEnabled(true);
		    rdbtnPostr2.setEnabled(true);
		    comBoxBebi.setEnabled(true);
		    comBoxCafe.setEnabled(true);
		    chckbxCafe.setEnabled(true);
		    btnAnular.setEnabled(true);
		    rdbtnPrim1.setSelected(true);
		    rdbtnPrim2.setSelected(false);
		    rdbtnPrim3.setSelected(false);
		    rdbtnSeg1.setSelected(true);
		    rdbtnSeg2.setSelected(false);
		    rdbtnSeg3.setSelected(false);
		    rdbtnPostr1.setSelected(true);
		    rdbtnPostr2.setSelected(false);
		    lblPrecPrim.setVisible(true);
		    lblPrecSeg.setVisible(true);
		    lblPrecPostr.setVisible(true);
		    lblPrecBebi.setVisible(true);
		    editPanCom.setEnabled(true);
		    btnBorrarComen.setEnabled(true);
		    btnAñadir.setEnabled(true);
		    comboBoxPromo.setEnabled(true);
		    btnAplicarProm.setEnabled(true);
		    rdbtnPagoEfec.setEnabled(true);
		    rdbtnPagoTarj.setEnabled(true);
		    btnReset.setEnabled(true);
		    btnBorrAccTick.setEnabled(true);
		    btnTicket.setEnabled(true);
		    textAreaTot.setEnabled(true);
		    sumaTot = 0;
		    ultDigts = "";
		    utlimoTicket = "";
		    pendienteCobro = 0;
		    descuentoApli = 0;
		    aplicadoDescuent = 0;
		    textAreaTot.setText(null);

		} else if (sumaTot == 0) {
		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "No hay pedido pendiente para anular, por favor, añada ordenes.");
		} else {

		    textFieldNumTarj.setText(null);
		    editPanTick.setText(null);
		    sumaTot = 0;
		    ultDigts = "";
		    editPanCom.setText(null);
		    comboBoxPromo.setSelectedIndex(0);
		    utlimoTicket = "";
		    menuNuevoPe.setForeground(Color.BLUE);
		    menuPersonal.setForeground(new Color(51, 51, 51));
		    menuMesa.setForeground(new Color(51, 51, 51));
		    menuComensales.setForeground(new Color(51, 51, 51));
		    cabeceraConceptos();
		    textAreaTot.setText(null);
		    descuentoApli = 0;

		}

	    }
	});
	btnAnular.setForeground(Color.RED);
	btnAnular.setFont(new Font("Silom", Font.BOLD, 18));
	btnAnular.setBounds(768, 726, 106, 75);
	frameRestaurantePDA.getContentPane().add(btnAnular);

	/**
	 * Borra el ticket reseteando el pedido completo y ocultando todo para crear
	 * padido nuevo.
	 */
	btnReset.setEnabled(false);
	btnReset.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		btnReset.setText("Reset");
		editPanTick.setForeground(Color.BLUE);
		rdbtnPrim1.setSelected(true);
		rdbtnPrim2.setSelected(false);
		rdbtnPrim3.setSelected(false);
		rdbtnSeg1.setSelected(true);
		rdbtnSeg2.setSelected(false);
		rdbtnSeg3.setSelected(false);
		rdbtnPostr1.setSelected(true);
		rdbtnPostr2.setSelected(false);
		lblPrecPrim.setVisible(false);
		lblPrecSeg.setVisible(false);
		lblPrecPostr.setVisible(false);
		lblPrecBebi.setVisible(false);
		lblPrecCafe.setVisible(false);
		comBoxBebi.setSelectedIndex(0);
		comBoxCafe.setSelectedIndex(0);
		chckbxCafe.setSelected(false);
		comBoxCafe.setEnabled(false);
		rdbtnPagoEfec.setSelected(true);
		rdbtnPagoTarj.setSelected(false);
		textFieldNumTarj.setVisible(false);
		textFieldNumTarj.setText(null);
		editPanTick.setText(null);
		sumaTot = 1;
		ultDigts = "";
		editPanCom.setText(null);
		comboBoxPromo.setSelectedIndex(0);
		utlimoTicket = "";
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
		pantallaInicio();
		rdbtnPrim1.setEnabled(false);
		rdbtnPrim2.setEnabled(false);
		rdbtnPrim3.setEnabled(false);
		rdbtnSeg1.setEnabled(false);
		rdbtnSeg2.setEnabled(false);
		rdbtnSeg3.setEnabled(false);
		rdbtnPostr1.setEnabled(false);
		rdbtnPostr2.setEnabled(false);
		comBoxBebi.setEnabled(false);
		comBoxCafe.setEnabled(false);
		chckbxCafe.setEnabled(false);
		btnBorrarComen.setEnabled(false);
		btnAñadir.setEnabled(false);
		comboBoxPromo.setEnabled(false);
		editPanCom.setEnabled(false);
		btnAplicarProm.setEnabled(false);
		rdbtnPagoEfec.setEnabled(false);
		rdbtnPagoTarj.setEnabled(false);
		btnReset.setEnabled(false);
		btnBorrAccTick.setEnabled(false);
		btnTicket.setEnabled(false);
		btnAnular.setEnabled(false);
		textAreaTot.setEnabled(false);
		textAreaTot.setText(null);
		pendienteCobro = 0;
		descuentoApli = 0;

	    }
	});
	btnReset.setFont(new Font("Silom", Font.BOLD, 18));
	btnReset.setBounds(662, 726, 106, 75);
	frameRestaurantePDA.getContentPane().add(btnReset);

	/*
	 * Barra de menú y separadores.
	 */
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBackground(UIManager.getColor("Button.disabledText"));
	menuBar.setLocation(0, 0);
	frameRestaurantePDA.getContentPane().add(menuBar);
	menuBar.setFont(new Font("Dialog", Font.BOLD, 29));
	menuBar.setForeground(SystemColor.window);
	menuBar.setSize(1165, 39);

	JSeparator sep1 = new JSeparator();
	sep1.setForeground(Color.LIGHT_GRAY);
	sep1.setBackground((Color) null);
	sep1.setBounds(389, 104, 711, 2);
	frameRestaurantePDA.getContentPane().add(sep1);

	JSeparator sep2 = new JSeparator();
	sep2.setForeground(Color.LIGHT_GRAY);
	sep2.setBackground(UIManager.getColor("ArrowButton.background"));
	sep2.setBounds(54, 563, 558, 2);
	frameRestaurantePDA.getContentPane().add(sep2);

	JSeparator sep3 = new JSeparator();
	sep3.setForeground(Color.LIGHT_GRAY);
	sep3.setBackground(UIManager.getColor("ArrowButton.background"));
	sep3.setBounds(56, 665, 557, 2);
	frameRestaurantePDA.getContentPane().add(sep3);

	JSeparator sep4 = new JSeparator();
	sep4.setForeground(Color.LIGHT_GRAY);
	sep4.setBackground((Color) null);
	sep4.setBounds(54, 722, 1052, 2);
	frameRestaurantePDA.getContentPane().add(sep4);

	/*
	 * Menú tema para el cambio de tema claro a oscuro
	 */
	menuTema.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuTema);

	JMenuItem mntmModoClaro = new JMenuItem("Modo claro");
	mntmModoClaro.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		frameRestaurantePDA.getContentPane().setBackground(UIManager.getColor("ArrowButton.background"));
		lblInfoPla1.setForeground(null);
		lblInfoPla2.setForeground(null);
		lblInfoPostre.setForeground(null);
		lblInfoBebi.setForeground(null);
		lblInfoCafe.setForeground(null);
		lblInfoPago.setForeground(null);
		rdbtnPrim1.setForeground(null);
		rdbtnPrim1.setBackground(null);
		rdbtnPrim2.setForeground(null);
		rdbtnPrim2.setBackground(null);
		rdbtnPrim3.setForeground(null);
		rdbtnPrim3.setBackground(null);
		rdbtnSeg1.setForeground(null);
		rdbtnSeg1.setBackground(null);
		rdbtnSeg2.setForeground(null);
		rdbtnSeg2.setBackground(null);
		rdbtnSeg3.setForeground(null);
		rdbtnSeg3.setBackground(null);
		rdbtnPrim3.setBackground(null);
		rdbtnPostr1.setForeground(null);
		rdbtnPostr1.setBackground(null);
		rdbtnPostr2.setForeground(null);
		rdbtnPostr2.setBackground(null);
		comBoxBebi.setForeground(null);
		comBoxBebi.setBackground(null);
		comBoxCafe.setForeground(null);
		comBoxCafe.setBackground(null);
		lblPrecPrim.setForeground(null);
		lblPrecSeg.setForeground(null);
		lblPrecPostr.setForeground(null);
		lblPrecBebi.setForeground(null);
		lblPrecCafe.setForeground(null);
		chckbxCafe.setForeground(null);
		chckbxCafe.setBackground(null);
		rdbtnPagoEfec.setForeground(null);
		rdbtnPagoEfec.setBackground(null);
		rdbtnPagoTarj.setForeground(null);
		rdbtnPagoTarj.setBackground(null);
		lblInfoPromo.setForeground(null);
		lblInfoPromo.setForeground(null);
		lblInfoComent.setForeground(null);
		lblInfoComent.setBackground(null);
		comboBoxPromo.setForeground(null);
		comboBoxPromo.setBackground(null);

	    }
	});
	menuTema.add(mntmModoClaro);

	JMenuItem mntmModoOscu = new JMenuItem("Modo oscuro");
	mntmModoOscu.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		frameRestaurantePDA.getContentPane().setBackground(new Color(30, 30, 30));
		lblInfoPla1.setForeground(Color.YELLOW);
		lblInfoPla2.setForeground(Color.YELLOW);
		lblInfoPostre.setForeground(Color.YELLOW);
		lblInfoBebi.setForeground(Color.YELLOW);
		lblInfoCafe.setForeground(Color.YELLOW);
		lblInfoPago.setForeground(Color.YELLOW);
		lblInfoPromo.setForeground(Color.YELLOW);
		lblInfoPromo.setForeground(Color.YELLOW);
		lblInfoComent.setForeground(Color.YELLOW);
		lblInfoComent.setBackground(Color.YELLOW);
		lblPrecPrim.setForeground(Color.WHITE);
		lblPrecSeg.setForeground(Color.WHITE);
		lblPrecPostr.setForeground(Color.WHITE);
		lblPrecBebi.setForeground(Color.WHITE);
		lblPrecCafe.setForeground(Color.WHITE);
		rdbtnPrim1.setForeground(Color.WHITE);
		rdbtnPrim1.setBackground(new Color(30, 30, 30));
		rdbtnPrim2.setForeground(Color.WHITE);
		rdbtnPrim2.setBackground(new Color(30, 30, 30));
		rdbtnPrim3.setForeground(Color.WHITE);
		rdbtnPrim3.setBackground(new Color(30, 30, 30));
		rdbtnSeg1.setForeground(Color.WHITE);
		rdbtnSeg1.setBackground(new Color(30, 30, 30));
		rdbtnSeg2.setForeground(Color.WHITE);
		rdbtnSeg2.setBackground(new Color(30, 30, 30));
		rdbtnSeg3.setForeground(Color.WHITE);
		rdbtnSeg3.setBackground(new Color(30, 30, 30));
		rdbtnPrim3.setBackground(new Color(30, 30, 30));
		rdbtnPostr1.setForeground(Color.WHITE);
		rdbtnPostr1.setBackground(new Color(30, 30, 30));
		rdbtnPostr2.setForeground(Color.WHITE);
		rdbtnPostr2.setBackground(new Color(30, 30, 30));
		comBoxBebi.setForeground(Color.WHITE);
		comBoxBebi.setBackground(new Color(30, 30, 30));
		comBoxCafe.setForeground(Color.WHITE);
		comBoxCafe.setBackground(new Color(30, 30, 30));
		chckbxCafe.setForeground(Color.WHITE);
		chckbxCafe.setBackground(new Color(30, 30, 30));
		rdbtnPagoEfec.setForeground(Color.WHITE);
		rdbtnPagoEfec.setBackground(new Color(30, 30, 30));
		rdbtnPagoTarj.setForeground(Color.WHITE);
		rdbtnPagoTarj.setBackground(new Color(30, 30, 30));
		comboBoxPromo.setForeground(Color.WHITE);
		comboBoxPromo.setBackground(new Color(30, 30, 30));

	    }
	});

	JSeparator sep1Item = new JSeparator();
	sep1Item.setBackground(UIManager.getColor("CheckBox.background"));
	sep1Item.setForeground(Color.LIGHT_GRAY);
	menuTema.add(sep1Item);
	menuTema.add(mntmModoOscu);

	JSeparator sep1Men = new JSeparator();
	sep1Men.setBackground(SystemColor.text);
	sep1Men.setForeground(SystemColor.menu);
	menuBar.add(sep1Men);

	/*
	 * Menú selección de servicio: Enabled solo comida
	 */
	menuNuevoPe.setForeground(Color.BLUE);
	menuNuevoPe.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuNuevoPe);

	JMenuItem mntmSerDesa = new JMenuItem("Servicio Desayuno");
	mntmSerDesa.setEnabled(false);
	menuNuevoPe.add(mntmSerDesa);

	JSeparator sep2Item = new JSeparator();
	sep2Item.setForeground(Color.LIGHT_GRAY);
	menuNuevoPe.add(sep2Item);

	JMenuItem mntmSerComi = new JMenuItem("Servicio Comida");
	mntmSerComi.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		cabeceraTickComi();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(Color.BLUE);
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
		editPanTick.setForeground(Color.BLACK);
		rdbtnPrim1.setEnabled(true);
		rdbtnPrim2.setEnabled(true);
		rdbtnPrim3.setEnabled(true);
		rdbtnSeg1.setEnabled(true);
		rdbtnSeg2.setEnabled(true);
		rdbtnSeg3.setEnabled(true);
		rdbtnPostr1.setEnabled(true);
		rdbtnPostr2.setEnabled(true);
		comBoxBebi.setEnabled(true);
		comBoxCafe.setEnabled(true);
		chckbxCafe.setEnabled(true);
		btnAnular.setEnabled(true);
		lblPrecPrim.setVisible(true);
		lblPrecSeg.setVisible(true);
		lblPrecPostr.setVisible(true);
		lblPrecBebi.setVisible(true);
		editPanCom.setEnabled(true);
		btnBorrarComen.setEnabled(true);
		btnAñadir.setEnabled(true);
		comboBoxPromo.setEnabled(true);
		btnAplicarProm.setEnabled(true);
		rdbtnPagoEfec.setEnabled(true);
		rdbtnPagoTarj.setEnabled(true);
		btnReset.setEnabled(true);
		btnBorrAccTick.setEnabled(true);
		btnTicket.setEnabled(true);
		textAreaTot.setEnabled(true);

	    }
	});
	menuNuevoPe.add(mntmSerComi);

	JSeparator sep3Item = new JSeparator();
	sep3Item.setForeground(Color.LIGHT_GRAY);
	menuNuevoPe.add(sep3Item);

	JMenuItem mntmSerCen = new JMenuItem("Servicio Cena");
	mntmSerCen.setEnabled(false);
	menuNuevoPe.add(mntmSerCen);

	JSeparator sep2Men = new JSeparator();
	sep2Men.setForeground(SystemColor.menu);
	menuBar.add(sep2Men);

	/*
	 * Botón de barra de menú selección de personal
	 */
	menuPersonal.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuPersonal);

	/*
	 * Asigna camarero para invocar el método cabeceraPersonal() y mostrar el
	 * camarero seleccionado. Guía coloreando en azúl al usuario en función del
	 * botón de menú donde esté 1. Nuevo pedido 2. Personal 3. Mesa 4. Comensales
	 *
	 * @see cabeceraPersonal()
	 */
	JMenuItem mntmCamaerero1 = new JMenuItem(CAMARERO1);
	mntmCamaerero1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		camareroX = CAMARERO1;
		cabeceraPersonal();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(Color.BLUE);
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuPersonal.add(mntmCamaerero1);

	JSeparator sep4Item = new JSeparator();
	sep4Item.setForeground(Color.LIGHT_GRAY);
	menuPersonal.add(sep4Item);

	JMenuItem mntmCamaerero2 = new JMenuItem(CAMARERO2);
	mntmCamaerero2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		camareroX = CAMARERO2;
		cabeceraPersonal();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(Color.BLUE);
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuPersonal.add(mntmCamaerero2);

	JSeparator sep5Item = new JSeparator();
	sep5Item.setForeground(Color.LIGHT_GRAY);
	menuPersonal.add(sep5Item);

	JMenuItem mntmCamaerero3 = new JMenuItem(CAMARERO3);
	mntmCamaerero3.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		camareroX = CAMARERO3;
		cabeceraPersonal();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(Color.BLUE);
		menuComensales.setForeground(new Color(51, 51, 51));

	    }
	});
	menuPersonal.add(mntmCamaerero3);

	JSeparator sep3Men = new JSeparator();
	sep3Men.setForeground(SystemColor.menu);
	menuBar.add(sep3Men);

	/*
	 * Botón de barra de menú selección de mesa
	 */
	menuMesa.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuMesa);

	/*
	 * Asigna mesa para invocar el método cabeceraMesa() y mostrar el camarero
	 * seleccionado Guía coloreando en azúl al usuario en función del botón de menú
	 * donde esté 1. Nuevo pedido 2. Personal 3. Mesa 4. Comensales
	 */
	JMenuItem mntmNumMesa = new JMenuItem("01");
	mntmNumMesa.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		mesaX = "01";
		cabeceraMesa();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(Color.BLUE);

	    }
	});

	JMenuItem mntmRango1 = new JMenuItem("Rango 01");
	menuMesa.add(mntmRango1);

	JSeparator separator = new JSeparator();
	separator.setForeground(Color.LIGHT_GRAY);
	menuMesa.add(separator);
	menuMesa.add(mntmNumMesa);

	JSeparator sep6Item = new JSeparator();
	sep6Item.setForeground(Color.LIGHT_GRAY);
	menuMesa.add(sep6Item);

	JMenuItem mntmNumMesa02 = new JMenuItem("02");
	mntmNumMesa02.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		mesaX = "02";
		cabeceraMesa();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(Color.BLUE);
	    }
	});
	menuMesa.add(mntmNumMesa02);

	JSeparator sep7Item = new JSeparator();
	sep7Item.setForeground(Color.LIGHT_GRAY);
	menuMesa.add(sep7Item);

	JMenuItem mntmNumMesa03 = new JMenuItem("03");
	mntmNumMesa03.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		mesaX = "03";
		cabeceraMesa();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(Color.BLUE);
	    }
	});
	menuMesa.add(mntmNumMesa03);

	JSeparator sep8Item = new JSeparator();
	sep8Item.setForeground(Color.LIGHT_GRAY);
	menuMesa.add(sep8Item);

	JMenuItem mntmNumMesa04 = new JMenuItem("04");
	mntmNumMesa04.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		mesaX = "04";
		cabeceraMesa();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(Color.BLUE);
	    }
	});
	menuMesa.add(mntmNumMesa04);

	JSeparator sep9Item = new JSeparator();
	sep9Item.setForeground(Color.LIGHT_GRAY);
	menuMesa.add(sep9Item);

	JMenuItem mntmNumMesa05 = new JMenuItem("05");
	mntmNumMesa05.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		mesaX = "05";
		cabeceraMesa();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(Color.BLUE);
	    }
	});
	menuMesa.add(mntmNumMesa05);

	JSeparator sep10Item = new JSeparator();
	sep10Item.setForeground(Color.LIGHT_GRAY);
	menuMesa.add(sep10Item);

	JMenuItem mntmNumMesa06 = new JMenuItem("06");
	mntmNumMesa06.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		mesaX = "06";
		cabeceraMesa();
		menuNuevoPe.setForeground(new Color(51, 51, 51));
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(Color.BLUE);
	    }
	});
	menuMesa.add(mntmNumMesa06);

	JSeparator sep4Men = new JSeparator();
	sep4Men.setForeground(SystemColor.menu);
	menuBar.add(sep4Men);

	/*
	 * Botón de menú nº de comensales
	 */
	menuComensales.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuComensales);

	/*
	 * Asigna número de comensales para invocar el método cabeceraConceptos() y
	 * mostrar toda la cabecera con todos los datos. Guía coloreando en azúl al
	 * usuario en función del botón de menú donde esté 1. Nuevo pedido 2. Personal
	 * 3. Mesa 4. Comensales
	 */
	JMenuItem mntmComensal1 = new JMenuItem("1");
	mntmComensal1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		nComensalesX = "1";
		cabeceraConceptos();
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuComensales.add(mntmComensal1);

	JSeparator sep11Item = new JSeparator();
	sep11Item.setForeground(Color.LIGHT_GRAY);
	menuComensales.add(sep11Item);

	JMenuItem mntmComensal2 = new JMenuItem("2");
	mntmComensal2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		nComensalesX = "2";
		cabeceraConceptos();
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuComensales.add(mntmComensal2);

	JSeparator sep12Item = new JSeparator();
	sep12Item.setForeground(Color.LIGHT_GRAY);
	menuComensales.add(sep12Item);

	JMenuItem mntmComensal3 = new JMenuItem("3");
	mntmComensal3.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		nComensalesX = "3";
		cabeceraConceptos();
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuComensales.add(mntmComensal3);

	JSeparator sep13Item = new JSeparator();
	sep13Item.setForeground(Color.LIGHT_GRAY);
	menuComensales.add(sep13Item);

	JMenuItem mntmComensal4 = new JMenuItem("4");
	mntmComensal4.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		nComensalesX = "4";
		cabeceraConceptos();
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuComensales.add(mntmComensal4);

	JSeparator sep14Item = new JSeparator();
	sep14Item.setForeground(Color.LIGHT_GRAY);
	menuComensales.add(sep14Item);

	JMenuItem mntmComensal5 = new JMenuItem("5");
	mntmComensal5.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		nComensalesX = "5";
		cabeceraConceptos();
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuComensales.add(mntmComensal5);

	JSeparator sep15Item = new JSeparator();
	sep15Item.setForeground(Color.LIGHT_GRAY);
	menuComensales.add(sep15Item);

	JMenuItem mntmComensal6 = new JMenuItem("6");
	mntmComensal6.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		nComensalesX = "6";
		cabeceraConceptos();
		menuNuevoPe.setForeground(Color.BLUE);
		menuPersonal.setForeground(new Color(51, 51, 51));
		menuMesa.setForeground(new Color(51, 51, 51));
		menuComensales.setForeground(new Color(51, 51, 51));
	    }
	});
	menuComensales.add(mntmComensal6);

	JSeparator sep5Men = new JSeparator();
	sep5Men.setForeground(SystemColor.menu);
	menuBar.add(sep5Men);

	/*
	 * Botón de menú añadir reservas
	 */
	JMenu menuReseva = new JMenu("Reservas");
	menuReseva.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuReseva);

	textFieldFechaRese = new JTextField();
	textFieldFechaRese.setFont(new Font("Dialog", Font.BOLD, 20));
	textFieldFechaRese.setBounds(159, 823, 291, 25);
	frameRestaurantePDA.getContentPane().add(textFieldFechaRese);
	textFieldFechaRese.setColumns(10);

	JLabel lblReservas = new JLabel("Reservas");
	lblReservas.setFont(new Font("Silom", Font.BOLD, 16));
	lblReservas.setBounds(54, 828, 93, 15);
	frameRestaurantePDA.getContentPane().add(lblReservas);

	/*
	 * Añade una fecha al textfield para guardarla en un String y mostrar
	 */
	JCalendar calendar = new JCalendar();
	calendar.getDayChooser().getDayPanel().addPropertyChangeListener(new PropertyChangeListener() {
	    @Override
	    public void propertyChange(PropertyChangeEvent evt) {

		textFieldFechaRese.setText(fechaRese.format(calendar.getCalendar().getTime()));

	    }
	});
	menuReseva.add(calendar);

	JButton btnGuardarRese = new JButton("Guardar");
	btnGuardarRese.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (textFieldFechaRese.getText() != null) {

		    fechaReserva = textFieldFechaRese.getText();
		    textFieldFechaRese.setText(null);

		} else {

		    JOptionPane.showMessageDialog(frameRestaurantePDA,
			    "No hay fecha de reserva seleccionada, por favor, añada reserva.");
		}

	    }
	});
	btnGuardarRese.setFont(new Font("Silom", Font.BOLD, 14));
	btnGuardarRese.setBounds(461, 823, 150, 25);
	frameRestaurantePDA.getContentPane().add(btnGuardarRese);
	/*
	 * Botón de menú de salir
	 */
	JMenu menuSalir = new JMenu("Salir");
	menuSalir.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

		frameRestaurantePDA.dispose();

	    }
	});

	JSeparator separator_1 = new JSeparator();
	separator_1.setForeground(UIManager.getColor("Menu.background"));
	menuBar.add(separator_1);
	menuSalir.setFont(new Font("Silom", Font.BOLD, 20));
	menuBar.add(menuSalir);

	JSeparator sep5 = new JSeparator();
	sep5.setForeground(Color.LIGHT_GRAY);
	sep5.setBackground((Color) null);
	sep5.setBounds(54, 802, 1052, 2);
	frameRestaurantePDA.getContentPane().add(sep5);

	/*
	 * Logo de imagen
	 */
	JLabel lblLogo = new JLabel("");
	lblLogo.setIcon(new ImageIcon(Tema4Entrega4_GUI.class.getResource("/Images/logi.png")));
	lblLogo.setBounds(38, 51, 350, 104);
	frameRestaurantePDA.getContentPane().add(lblLogo);

    }
}
