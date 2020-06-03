package dao;

//Heredar conexion


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;



public class ClienteDao extends Conexion{

    private String sql;
    private PreparedStatement ejecutar;
    private String respuesta;
    Conexion conex = new Conexion();
    
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    public String registrarCliente(Cliente clienten) {
        respuesta = null;
        try {
            conex.Conectar();
            sql = "insert into cliente values(?,?,?,?,?,?)";
            ejecutar = conex.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, clienten.getCodigo());
            ejecutar.setString(2, clienten.getNombre());
            ejecutar.setString(3, clienten.getApellido());
            ejecutar.setString(4, clienten.getDireccion());
            ejecutar.setInt(5, clienten.getTelefono());
            ejecutar.setString(6, clienten.getNit());
            
            ejecutar.executeUpdate();
            
            respuesta="Registro Almacenado con Exito";
        } catch (SQLException ex) {
            System.out.println("Error en Statement" + ex);
            respuesta="No se pudo almacenar el registro";
        } finally {
            conex.cerrarConex();
        }
        return respuesta;
    }
    
    //BUSCAR DATOS
    public ArrayList<Cliente> listaClientes() {
        ArrayList<Cliente> lista = null;
        ResultSet resultado;
        try {
            conex.Conectar();
            sql = "select * from cliente";
            ejecutar = conex.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Cliente clienten = new Cliente();
                clienten.setCodigo(resultado.getInt("codigo"));
                clienten.setNombre(resultado.getString("nombre"));
                clienten.setApellido(resultado.getString("apellido"));
                clienten.setDireccion(resultado.getString("direccion"));
                clienten.setTelefono(resultado.getInt("telefono"));
                clienten.setNit(resultado.getString("nit"));
                
                
                lista.add(clienten);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            conex.cerrarConex();
        }
        return lista;
    }
    
    public String eliminarCliente(int codigo){
        try {
            conex.Conectar();
            sql="delete from cliente where codigo=?";
            ejecutar = conex.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, codigo);
            ejecutar.executeUpdate();
            respuesta="Registro Eliminado";
            
        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en Conexion: " + ex);
            respuesta="Error, no se puede eliminar el registro";
        }
         return respuesta;
    }
    
    public String daoModificarCliente(Cliente datos){
        try {
            conex.Conectar();
            sql="update cliente set nombre=?, apellido=? where codigo=?";
            ejecutar = conex.getMiconexion().prepareStatement(sql);
            ejecutar.setString(1, datos.getNombre());
            ejecutar.setString(2, datos.getApellido());
            ejecutar.setInt(3, datos.getCodigo());
            ejecutar.executeUpdate();
            respuesta = "datos modificados con exito";
            
        } catch (SQLException ex) {
            //Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en conexion: " + ex);
            respuesta="No se puede modificar";
        }
        return respuesta;
    }
}
