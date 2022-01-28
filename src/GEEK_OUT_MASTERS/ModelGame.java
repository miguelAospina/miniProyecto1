package GEEK_OUT_MASTERS;

import javax.swing.*;

public class ModelGame {
   private int[] caras, carasInactivas, carasActivas;
   private Dado[] dados;
   private Dado dado1,dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10;

   public ModelGame(){
      dados = new Dado[10]; //indicar la cantidad de elementos con los que se va a trabajar
      carasInactivas= new int[3];
      carasActivas= new int[7];

      //crear los 3 dados inactivos
      dado1= new Dado("inactivo");
      dado2= new Dado("inactivo");
      dado3= new Dado("inactivo");

      //crear los otros 7 dados activos
      dado4= new Dado("activo");
      dado5= new Dado("activo");
      dado6= new Dado("activo");
      dado7= new Dado("activo");
      dado8= new Dado("activo");
      dado9= new Dado("activo");
      dado10= new Dado("activo");

      //crear los 3 dados inactivos
      dados[0]= new Dado("inactivo");
      dados[1]= new Dado("inactivo");
      dados[2]= new Dado("inactivo");

      //crear los otros 7 dados activos
      dados[3]= new Dado("activo");
      dados[4]= new Dado("activo");
      dados[5]= new Dado("activo");
      dados[6]= new Dado("activo");
      dados[7]= new Dado("activo");
      dados[8]= new Dado("activo");
      dados[9]= new Dado("activo");

      carasInactivas[0]=dado1.getCara();
      carasInactivas[1]=dado2.getCara();
      carasInactivas[2]=dado3.getCara();
      carasActivas[0]=dado4.getCara();
      carasActivas[1]=dado5.getCara();
      carasActivas[2]=dado6.getCara();
      carasActivas[3]=dado7.getCara();
      carasActivas[4]=dado8.getCara();
      carasActivas[5]=dado9.getCara();
      carasActivas[6]=dado10.getCara();



   }
   public void superheroe(Dado dado){
      if(dado.getPosicion()=="activo"){
         dado.getCaraOpuesta();
      }else{
         JOptionPane.showMessageDialog(null, "no puedes mover este dado");
      }
   }

   public int[] getCarasInactivas(){
      return carasInactivas;
   }
   public int[] getCarasActivas(){
      return carasActivas;
   }
   public Dado[] getDados(){
      return dados;
   }

}
