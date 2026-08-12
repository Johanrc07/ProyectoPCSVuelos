/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aerofly;

import Modelo.SentenciasUsuario;
import Modelo.Usuario;
import Vista.frmLogin;
import Controlador.CtrlLogin;

public class principal {

    public static void main(String[] args) {

       // main ISABEL
       Usuario modelo = new Usuario();
        SentenciasUsuario consultas = new SentenciasUsuario();
        frmLogin vista = new frmLogin();

        CtrlLogin controlador = new CtrlLogin(modelo, consultas, vista);

        controlador.inicio();
        
        //******************************************************************
        
        
        //MAIN JOHAN RODRIGUEZ
        
        
        
        
        // MAIN JOHAN
        
        
        
        
        //MAIN VICTOR
       
    }
}




