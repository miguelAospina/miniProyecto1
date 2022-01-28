package GEEK_OUT_MASTERS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class is used for the GUI
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @version v.1.0.0 date: 11/01/2022
 */
public class GUI extends JFrame {
    private Header headerProject;
    private Escucha escucha;
    private ModelGame modelGame;
    private Mouse_listener mouse_listener;

    private int contadorDeClick=0,  ronda=1, puntos=0, cant42Ganados, cant42EnRonda;
    private JLabel[] imagenesDados, imagenesPuntucion;
    private JPanel titulo, dadosActivos, dadosInactivos,dadosUtilizados,tarjetaPuntuacion, consejo;
    private Dado[] dados;
    private JTextArea textoconsejos;
    private JButton jugar,instrucciones;
    private  JLabel dadoAnterior, labelRonda, labelPuntos, imagentitulo;
    private int caso=0, cantActivo, cantUtilizado, cantInactivo;
    boolean rondaTerminada=false;
    GridBagConstraints gridBagConstraints;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("GEEK OUT MASTER");
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); //centra la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); //este se va configurando a medida que se agregan los componentes

        //Create Listener Object and Control Object
        escucha = new Escucha();
        mouse_listener = new Mouse_listener();
        modelGame = new ModelGame(); //objeto modelo
        dados=new Dado[10];
        dados = modelGame.getDados();
        imagenesDados = new JLabel[10];

        //Set up JComponents

        //titulo del juego
        titulo= new JPanel();
        titulo.setPreferredSize(new Dimension(300, 45));
        titulo.setBackground(new Color(0, 32, 92));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        //arranca en la columna 0 y fila 0 y ocupa 2 espacios
        imagentitulo = new JLabel();
        imagentitulo.setIcon(new ImageIcon(getClass().getResource("/images2/titulo.png")));
        titulo.add(imagentitulo);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(titulo, constraints);
        //panel de informacion
        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(300, 50));
        info.setBackground(new Color(255, 255, 255));
        info.setBorder(BorderFactory.createTitledBorder("estado del juego"));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(info, constraints);

        labelRonda=new JLabel("ronda: "+ ronda);
        labelPuntos = new JLabel("puntos: " + puntos);
        info.add(labelRonda);
        info.add(labelPuntos);


        //panel consejo
        consejo = new JPanel();
        consejo.setPreferredSize(new Dimension(300, 50));
        consejo.setBackground(new Color(255,255,255));
        consejo.setBorder(BorderFactory.createTitledBorder("consejo"));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth =1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(consejo,constraints);
        //ayudas
        textoconsejos= new JTextArea(1,31);
        textoconsejos.setText("aqui encontrarás los consejos en caso de no saber que hacer");
        textoconsejos.setEditable(false);
        consejo.add(textoconsejos);


        //panel de dados activos
        dadosActivos = new JPanel();
        dadosActivos.setPreferredSize(new Dimension(350, 250));
        dadosActivos.setBackground(new Color(255,255,255));
        dadosActivos.setBorder(BorderFactory.createTitledBorder("Dados Activos "));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(dadosActivos,constraints);

        //panel de dadosInactivos
        dadosInactivos = new JPanel();
        dadosInactivos.setPreferredSize(new Dimension(350, 250));
        dadosInactivos.setBackground(new Color(255, 255,255));
        dadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados Inactivos "));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(dadosInactivos,constraints);

        //panel tarjeta de puntuacion
        tarjetaPuntuacion = new JPanel();
        tarjetaPuntuacion.setPreferredSize(new Dimension(350, 250));
        tarjetaPuntuacion.setBackground(new Color(255,255,255));
        tarjetaPuntuacion.setBorder(BorderFactory.createTitledBorder("Tarjeta de puntuación "));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth =1 ;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(tarjetaPuntuacion,constraints);
        //crear los jlabels de puntuacion

        imagenesPuntucion=new JLabel[10];
        for(int i=0; i<10;i++) {
            imagenesPuntucion[i]=new JLabel();
            imagenesPuntucion[i].setIcon(new ImageIcon(getClass().getResource("/images2/"+getNumPuntos(i+1)+".png")));
            tarjetaPuntuacion.add(imagenesPuntucion[i]);
        }
        //dados utilizados
        dadosUtilizados = new JPanel();
        dadosUtilizados.setPreferredSize(new Dimension(350, 250));
        dadosUtilizados.setBackground(new Color(255,255,255));
        dadosUtilizados.setBorder(BorderFactory.createTitledBorder("dados utilizados"));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth =1 ;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(dadosUtilizados,constraints);

        //botonLanzar
        jugar = new JButton("Jugar");
        jugar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(jugar,constraints);

        //boton Instrucciones
        instrucciones= new JButton("instrucciones");
        instrucciones.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;

        this.add(instrucciones,constraints);

        //crear los jlabels
        for(int i=0; i<10;i++) {
            imagenesDados[i]=new JLabel();
            imagenesDados[i].addMouseListener(mouse_listener);

        }

    }
    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }
    /**
     * getNumPuntos obtiene los puntos que equivalen una cantidad de dados 42
     * por ejemplo numDeDados=2 -> return 6
     * @param numDeDados
     * @return equivalencia de puntos
     */
    private int getNumPuntos(int numDeDados){
        return ((numDeDados*numDeDados)+numDeDados)/2;
    }
    /**
     * condicionesIniciales Establece las posiciones inciales de los dados
     * se utiliza al terminar una ronda y empezar otra
     */
    private void condicionesIniciales(){
        for(int i=0; i<3;i++) {
            dados[i].lanzarDado();
            dados[i].cambiarPosicion("inactivo");
            dadosInactivos.add(imagenesDados[i]);
            imagenesDados[i].setIcon(new ImageIcon(getClass().getResource("/images/" + (dados[i].getImagen()) + ".png")));
        }
        for(int i=3; i<10;i++) {
            dados[i].lanzarDado();
            dados[i].cambiarPosicion("activo");
            dadosActivos.add(imagenesDados[i]);
            imagenesDados[i].setIcon(new ImageIcon(getClass().getResource("/images/" + (dados[i].getImagen()) + ".png")));
        }
        dadosUtilizados.removeAll();
    }
    /**
     * identificar el indice del Jlabel (dado) al que se le dió click, a partir del evento
     * @param mouseEvent, label
     * @return indice
     */
    private int identificador(MouseEvent mouseEvent){
        JLabel seleccionado = new JLabel();
        int contador=0;
        while (mouseEvent.getSource()!= seleccionado && contador<10){
            //   System.out.println("el dado es " + imagenesDados[contador]);
            seleccionado=imagenesDados[contador];
            contador++;
        }
        return contador-1;
    }
    /**
     * identificar el indice del Jlabel (dado) al que se le dió click, a partir del evento
     * @param posicion
     * @return cantidad de dados que se encuentran en una posicion dada
     */
    public int cantidad(String posicion){
        int contador, cant=0;
        for( contador=0; contador<dados.length; contador++){
            if(dados[contador].getPosicion()==posicion){
                cant++;
            }
        }
        return cant;
    }
    /**
     * identificar si no hay más movimientos, es decir, si ya no hay dados que se puedan utilizar en la seccion dde los activos
     * @return boolean
     * true -> no hay más dados utilizables activos
     * false -> aun hay dados utilizables activos
     */
    private boolean noHayMasPosibilidades() {
        //   System.out.println("vamos a analizar si terminó la ronda");
        if (dadosActivos.getComponents().length == 1) {
            //queda un  dado
            // System.out.println("la ronda termina porque no hay más posibilidades y queda un dado");
            return true;

        } else if (dadosActivos.getComponents().length == 0) {
            //   System.out.println("la ronda termina porque no hay más dados");
            return true;

        } else {

            int cantEjecutable = 0;
            //hay al menos uno que se pueda ejecutar por lo que la ronda puede continuar
            for (int i = 0; i < dados.length; i++) {
                if (((dados[i].getCara() == 1 || dados[i].getCara() == 3 || dados[i].getCara() == 4 || dados[i].getCara() == 5) && dados[i].getPosicion() == "activo")) {
                    cantEjecutable++;
                }
            }
            if (cantEjecutable == 1) {
                int indiceDeDado = 0;
                Dado dadoEjecutable = new Dado(" ");
                //¿cual es el que es ejecutable
                for (int i = 0; i < dados.length; i++) {
                    if (dados[i].getPosicion() == "activo" && dados[i].getCara() != 2 && dados[i].getCara() != 6) {
                        dadoEjecutable = dados[indiceDeDado];
                    }
                    indiceDeDado++;
                }
                System.out.println("el dado ejecutable es: " + dadoEjecutable.getImagen());
                if (dadoEjecutable.getCara() == 3 && cantInactivo == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (cantEjecutable == 0) {
                return true;
            } else {
                if (cantInactivo == 0) {
                    for (int i = 0; i < dados.length; i++) {
                        if (((dados[i].getCara() == 1 || dados[i].getCara() == 4 || dados[i].getCara() == 5) && dados[i].getPosicion() == "activo")) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        }
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jugar) {
                puntos=0;
                jugar.setText("Volver a empezar");
                caso=0;
                contadorDeClick=0;
                ronda=1;
                labelRonda.setText("ronda: "+ronda);
                labelPuntos.setText("puntos: "+puntos);
                condicionesIniciales();
                for(int i=0; i<10;i++) {
                    imagenesPuntucion[i].setIcon(new ImageIcon(getClass().getResource("/images2/"+
                                                               getNumPuntos(i+1)+".png")));
                }
            }

            if (e.getSource() == instrucciones) {
                JOptionPane.showMessageDialog(null, "las intrucciones son: \n"
                        + "1. Oprime el botón \"jugar\" para iniciar el juego y verás los dados que trae, se toman 3 al" +
                        " sector de \"dados activos\""
                        + " y se coloca en el sector de \"Dados Inactivos\" los otros 7\n" +
                        "dados se tiran y pasan a ser los \"Dados Activos\"."
                        + "\n2. Se van eligiendo los dados a utilizar según las habilidades de sus caras y se pasan al " +
                        "sector de \"Dados\n" + "Utilizados\"."
                        + "\n3. Si selecciona el último dado activo queda un Dragón, " +
                        "se perderán todos los puntos acumulados.");

        }
            revalidate();
            repaint();
    }
    }


    private class Mouse_listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent event) {
            int dadoSeleccionado= identificador(event);
            if(contadorDeClick==0){ //si es la primera vez q da click a un dado
                if(dados[dadoSeleccionado].getPosicion()=="activo"){

                    switch (dados[dadoSeleccionado].getCara()){
                        case 1: //meeple
                            textoconsejos.setText("Meeple: puedes relanzar un dado activo");
                            JOptionPane.showMessageDialog(null, "relanza un dado activo");
                            //relanzar un dado activo
                            break;
                        case 2: //dragon
                            textoconsejos.setText("Dragon: quita puntos");
                            //quita puntos
                            break;
                        case 3: //corazon
                            textoconsejos.setText("Corazón: lanza un dado inactivo y vuelvelo activo");
                            break;
                        case 4: //nave
                            textoconsejos.setText("Nave: envia un dado activo a los ianctivos");
                            //envia un dado activo. a la de los inactivos
                            break;
                        case 5: //super
                            textoconsejos.setText("Superheroe: seleccione la carta a voltear");
                            JOptionPane.showMessageDialog(null, "seleccione la carta a voltear");
                            break;
                        case 6: //42
                            textoconsejos.setText("42: suma puntos");
                            break;
                    }
                    caso=dados[dadoSeleccionado].getCara();

                    if(caso==2|| caso==6){ //si es dradon o 42 solo se muestra un mensaje
                        textoconsejos.setText("Este dado no realiza ninguna acción");
                        contadorDeClick=0;
                        caso=0;
                    }else{
                        dadosUtilizados.add(imagenesDados[dadoSeleccionado]);
                        dados[dadoSeleccionado].cambiarPosicion("utilizado");
                        contadorDeClick=1;
                    }
                }else{
                    //no está activo
                    textoconsejos.setText("por favor selecciona un dado activo");
                    contadorDeClick=0;
                }
                revalidate();
                repaint();
                dadoAnterior=imagenesDados[dadoSeleccionado];
            }else{
                switch (caso) {
                    case 1: //meeple
                        if (dadoAnterior == imagenesDados[dadoSeleccionado]) {
                            JOptionPane.showMessageDialog(null, "no puedes relanzar el mismo dado, selecciona otro");
                            caso = 1;
                            contadorDeClick = 1;

                        }else{
                            //relanzar un dado activo
                            if (dados[dadoSeleccionado].getPosicion() == "activo") {
                                textoconsejos.setText("*seleccionaste el meple*");
                                System.out.println("cara antes " + dados[dadoSeleccionado].getCara());
                                dados[dadoSeleccionado].lanzarDado();
                                System.out.println("cara nueva " + dados[dadoSeleccionado].getCara());
                                imagenesDados[dadoSeleccionado].setIcon(new ImageIcon(getClass().getResource("/images/" + dados[dadoSeleccionado].getImagen() + ".png")));
                                caso = 0;
                                contadorDeClick = 0;
                            } else {
                                textoconsejos.setText("selecciona un dado activo por fa");
                                JOptionPane.showMessageDialog(null, "selcciona un dado activo por fa");
                                caso = 1;
                                contadorDeClick = 1;
                            }
                        }
                        break;
                    case 2: //dragon
                        //quita puntos
                        textoconsejos.setText("aun no está programado, pero quita puntos");
                        JOptionPane.showMessageDialog(null, "dragon seleccionado, no hace nada");
                        caso = 0;
                        contadorDeClick = 0;
                        break;
                    case 3: //corazon
                        textoconsejos.setText("lanza un dado inactivo y se vuelve activo");
                        //lanzar un daddo iactivo y pasarlo a los activos
                        dadosInactivos.remove(imagenesDados[dadoSeleccionado]);
                        //  System.out.println("cara anterior"+dados[dadoSeleccionado].getCara());
                        dados[dadoSeleccionado].lanzarDado();
                        //  System.out.println("cara nueva"+dados[dadoSeleccionado].getCara());
                        dados[dadoSeleccionado].cambiarPosicion("activo");
                        dadosActivos.add(imagenesDados[dadoSeleccionado]);
                        imagenesDados[dadoSeleccionado].setIcon(new ImageIcon(getClass().getResource("/images/" + dados[dadoSeleccionado].getImagen() + ".png")));
                        caso=0;
                        contadorDeClick=0;
                        break;
                    case 4: //nave
                        //envia un dado activo. a la de los utilizados
                        if(dados[dadoSeleccionado].getPosicion()=="activo"){
                            textoconsejos.setText("la nave envia un dado activo a donde los inactivos");
                            dadosActivos.remove(imagenesDados[dadoSeleccionado]);
                            dadosUtilizados.add(imagenesDados[dadoSeleccionado]);
                            dados[dadoSeleccionado].cambiarPosicion("utilizado");
                            caso=0;
                            contadorDeClick=0;
                        }else{ //se seleccionó un dado por fuera de los activos
                            textoconsejos.setText("debes seleccionar un dado activo");
                            caso=4;
                            contadorDeClick=1;
                        }
                        break;
                    case 5: //super
                        dados[dadoSeleccionado].getCaraOpuesta();
                        imagenesDados[dadoSeleccionado].setIcon(new ImageIcon(getClass().getResource("/images/" +
                                dados[dadoSeleccionado].getImagen() + ".png")));
                        caso=0;
                        contadorDeClick=0;
                        break;
                    case 6: //42
                        caso=0;
                        contadorDeClick=0;
                        break;
                    default:
                        caso=0;
                        contadorDeClick=0;
                }
                revalidate();
                repaint();
            }

            cantActivo=cantidad("activo");
            cantInactivo=cantidad("inactivo");
            cantUtilizado=cantidad("utilizado");
            System.out.println("cant activos= "+ cantActivo+ " cant utilizado= "+ cantUtilizado + " inactivo= "+cantInactivo);

            if(contadorDeClick==0){
                //si queda un cohete se auto destrulle
                //casos en los que se termina la ronda son:
                if(cantActivo==0){
                    JOptionPane.showMessageDialog(null,"no hay dados, \n no ganas ni pierdes punto"," ",JOptionPane.PLAIN_MESSAGE);
                    rondaTerminada=true;
                    cant42EnRonda=0;
                }else if(cantActivo==1){
                    int i=0;
                    //identificar cual es el indice del ultimo dado activo que queda
                    for(i=0;i<10;i++){
                        if(dados[i].getPosicion()=="activo") {
                            break;
                        }
                    }
                    System.out.println("RONDA TERMINADA, ultimo dado es"+ dados[i].getImagen());

                    if(dados[i].getImagen()=="42"){
                        JOptionPane.showMessageDialog(null,"Hiciste un punto");
                        cant42Ganados++;
                        cant42EnRonda=1;
                    }else if(dados[i].getImagen()=="Dragon"){
                        JOptionPane.showMessageDialog(null,"perdiste todos tus puntos");
                        cant42Ganados=0;
                        cant42EnRonda=0;
                    }else if(dados[i].getImagen()=="cohete"){
                        JOptionPane.showMessageDialog(null, "El cohete se auto destruye, \n no ganas ni pierdes puntos");
                        dados[i].cambiarPosicion("utilizado");
                        dadosActivos.add(imagenesDados[i]);
                        cant42EnRonda=0;
                        dadosUtilizados.add(imagenesDados[i]);
                    }else{
                        JOptionPane.showMessageDialog(null, "no sumaste punto");
                        cant42EnRonda=0;
                    }
                    rondaTerminada=true;
                }else if(noHayMasPosibilidades()){
                    JOptionPane.showMessageDialog(null,"vamos a sumar los puntos");
                    for(int i=0; i<dados.length;i++){
                        if(dados[i].getImagen()=="42"&&dados[i].getPosicion()=="activo"){
                            //     System.out.println("I"+ i);
                            cant42Ganados++;
                            cant42EnRonda++;
                        }
                    }
                    for(int i=0; i<dados.length;i++){
                        if(dados[i].getImagen()=="Dragon"&&dados[i].getPosicion()=="activo"){
                            //  System.out.println("quedaste con un dragon");
                            JOptionPane.showMessageDialog(null,"quedaste con un dragon, por lo cual pierdes todos tus puntps");
                            cant42Ganados=0;
                            cant42EnRonda=0;
                            break;
                        }
                    }
                    rondaTerminada=true;
                }else{

                    System.out.println("CONTINUA LA RONDA");
                    rondaTerminada=false;
                }
            }

            if(rondaTerminada){
                if(puntos>=55){
                    JOptionPane.showMessageDialog(null,  "Has ganado todos los puntos "+ cant42Ganados + "\ntus puntos son: " +puntos, "La ronda ha terminado",JOptionPane.PLAIN_MESSAGE);
                }

                ronda++;
                cant42EnRonda=0;
                puntos=getNumPuntos(cant42Ganados);
                JOptionPane.showMessageDialog(null,  "cantidad de 42 ganados: "+ cant42Ganados + "\ntus puntos son: " +puntos, "La ronda ha terminado",JOptionPane.PLAIN_MESSAGE);

                if(puntos==0){
                    for(int i=0; i<10;i++) {
                        imagenesPuntucion[i].setIcon(new ImageIcon(getClass().getResource("/images2/"+getNumPuntos(i+1)+".png")));
                    }
                }else{
                    for(int i=0; i<cant42Ganados;i++) {
                        imagenesPuntucion[i].setIcon(new ImageIcon(getClass().getResource("/images2/"+getNumPuntos(i+1)+"v.png")));
                    }
                }
                revalidate();
                repaint();

                condicionesIniciales();
                rondaTerminada=false;
            }
            labelRonda.setText("ronda: "+ronda);
            labelPuntos.setText("puntos: "+puntos);
            if(ronda==6){
                if(puntos >= 36){
                    JOptionPane.showMessageDialog(null, " FELICIDADES GANASTE EL JUEGO \n obtuviste "+ puntos + "puntos","El juego ha terminado", JOptionPane.PLAIN_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, " PERDISTE \n obtuviste "+ puntos + "puntos","El juego ha terminado",0);
                }

                for(int i=0; i<10;i++) {
                    imagenesPuntucion[i].setIcon(new ImageIcon(getClass().getResource("/images2/"+getNumPuntos(i+1)+".png")));
                }

                dadosActivos.removeAll();
                dadosInactivos.removeAll();
                dadosUtilizados.removeAll();
                jugar.setText("volver a Jugar");
                ronda=1;
                cant42Ganados=0;
                cant42EnRonda=0;
                labelRonda.setText("ronda: "+ronda);
                labelPuntos.setText("puntos: " + puntos);
                textoconsejos.setText("");
                contadorDeClick=0;
                caso=0;
                for(int i=0; i<cant42Ganados;i++) {
                    imagenesPuntucion[i].setIcon(new ImageIcon(getClass().getResource("/images2/"+getNumPuntos(i+1)+".png")));
                }
            }
            revalidate();
            repaint();

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            revalidate();
            repaint();
        }

    }
}

