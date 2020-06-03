/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author admin
 */
public interface ClienteInterface {
    public String registrarCliente(Cliente clienten);
    public ArrayList<Cliente> listaClientes();
    public String eliminarCliente(int codigo);
    public String daoModificarCliente(Cliente datos);
    
}
