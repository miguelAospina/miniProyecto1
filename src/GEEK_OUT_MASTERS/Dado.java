package GEEK_OUT_MASTERS;

import java.util.Random;

/**
 * class Dado generate a Random value between 1 and 6
 * @author Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 * @Version v.1.0.0 date 11/01/2022
 */
public class Dado {
    private int cara;
    private String posicion;
    /**
     * class Constructor
     *
     */
    public Dado(String lugar){
        posicion=lugar;
        Random aleatorio = new Random();
        cara= aleatorio.nextInt(6)+1;
    }

    public void cambiarPosicion(String cualPosicion){
        posicion= cualPosicion;
    }
    /**
     * Method that generate an random value to cara
     * @return number between (1,6)
     */
    public int getCara() {
        return cara;
    }
    public String getPosicion(){
        return posicion;
    }
    //volte el dado a su cara opuesta
    public int getCaraOpuesta(){
        int caraOpuesta;
        switch(cara){
            case 1:
                caraOpuesta= 4;
                break;
            case 2:
                caraOpuesta=5;
                break;
            case 3:
                caraOpuesta= 6;
                break;
            case 4:
                caraOpuesta=1;
                break;
            case 5:
                caraOpuesta=2;
                break;
            case 6:
                caraOpuesta= 3;
                break;
            default:
                caraOpuesta= 0;
                break;
        }
        cara=caraOpuesta;
        return caraOpuesta;
    }
    /**
     * Method that generate an random value to cara
     */
    public void lanzarDado() {
        Random aleatorio = new Random();
        cara= aleatorio.nextInt(6)+1;
    }

    public String getImagen(){
        switch (cara){
            case 1:
                return "meeple";
            case 2:
                return "Dragon";
            case 3:
                return "Corazon";
            case 4:
                return "cohete";
            case 5:
                return "superheroe";
            case 6:
                return "42";
            default:
                return "";
        }
    }



}
