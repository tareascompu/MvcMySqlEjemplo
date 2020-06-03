/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import modelo.Persona;

/**
 *
 * @author admin
 */
public interface PersonaInterface {
    public String registrarPersona(Persona clienten);
    public ArrayList<Persona> listaClientes();
    public String eliminarPersona(int codigo);
    public String daoModificarPersona(Persona datos);
}
