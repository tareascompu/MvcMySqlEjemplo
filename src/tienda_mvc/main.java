package tienda_mvc;
import dao.ClienteDao;
import vista.frmCliente;
import controlador.*;


public class main {

    public static void main(String[] args) {
        frmCliente vistaCli = new frmCliente();
        ClienteDao modeloCli = new ClienteDao();
        ControladorCliente1 controlaCli = new ControladorCliente1(vistaCli, modeloCli);
        
        vistaCli.setTitle("Clientes");
        vistaCli.setLocationRelativeTo(null);
        vistaCli.setVisible(true);
        
    }
    
}
