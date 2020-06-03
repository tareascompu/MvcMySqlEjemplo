package controlador;

import java.awt.Color;
import vista.frmCliente;
import modelo.Cliente;
import dao.ClienteDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorCliente1 implements ActionListener, MouseListener {

    frmCliente vistaCliente = new frmCliente();
    ClienteDao modeloCliente = new ClienteDao();
    Cliente cliente = new Cliente();

    public ControladorCliente1(frmCliente vistaCliente, ClienteDao modeloCliente) {
        this.modeloCliente = modeloCliente;
        this.vistaCliente = vistaCliente;

        this.vistaCliente.btnInsert.setActionCommand("Insertar");
        this.vistaCliente.btnSelect.setActionCommand("Seleccionar");
        this.vistaCliente.btnInsert.addActionListener(this);
        this.vistaCliente.btnSelect.addActionListener(this);

        this.vistaCliente.btnDelete.setActionCommand("Eliminar");
        this.vistaCliente.btnDelete.addActionListener(this);
        this.vistaCliente.btnUpdate.addActionListener(this);
        this.vistaCliente.tblClientes.addMouseListener(this);

        LlenarTabla(vistaCliente.tblClientes);
    }

    public void LlenarTabla(JTable tablaDatos) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);

        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("NIT");

        Object[] columna = new Object[6];

        int numRegistros = modeloCliente.listaClientes().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCliente.listaClientes().get(i).getCodigo();
            columna[1] = modeloCliente.listaClientes().get(i).getNombre();
            columna[2] = modeloCliente.listaClientes().get(i).getApellido();
            columna[3] = modeloCliente.listaClientes().get(i).getDireccion();
            columna[4] = modeloCliente.listaClientes().get(i).getTelefono();
            columna[5] = modeloCliente.listaClientes().get(i).getNit();
            modeloTabla.addRow(columna);
        }
        
        //dar color a la tabla
        ColoresTabla color = new ColoresTabla();
        
        tablaDatos.setDefaultRenderer(tablaDatos.getColumnClass(0), color);

    }

    

    public void insertCliente() {
        //asigno al objeto cliente los valores de la vista
        cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));
        cliente.setNombre(vistaCliente.txtNombre.getText());
        cliente.setApellido(vistaCliente.txtApellido.getText());
        cliente.setDireccion(vistaCliente.txtDirecion.getText());
        cliente.setTelefono(Integer.parseInt(vistaCliente.txtTelefono.getText()));
        cliente.setNit(vistaCliente.txtNit.getText());

        String respuestaInsert = this.modeloCliente.registrarCliente(cliente);
        System.out.println(respuestaInsert);
        if (respuestaInsert != null) {
            JOptionPane.showMessageDialog(null, respuestaInsert);
            limpiarControles();

        } else {
            JOptionPane.showMessageDialog(null, respuestaInsert);

        }
    }

    public void limpiarControles() {
        vistaCliente.txtCodigo.setText(null);
        vistaCliente.txtNombre.setText(null);
        vistaCliente.txtApellido.setText(null);
        vistaCliente.txtDirecion.setText(null);
        vistaCliente.txtTelefono.setText(null);
        vistaCliente.txtNit.setText(null);

        vistaCliente.txtCodigo.requestFocus();
    }

    public void eliminarCliente() {
        cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));
        String mensaje = modeloCliente.eliminarCliente(cliente.getCodigo());

        JOptionPane.showMessageDialog(vistaCliente, mensaje);
    }

    public void modificarCliente() {
        cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));
        cliente.setNombre(vistaCliente.txtNombre.getText());
        cliente.setApellido(vistaCliente.txtApellido.getText());
        cliente.setDireccion(vistaCliente.txtDirecion.getText());
        cliente.setNit(vistaCliente.txtNit.getText());
        cliente.setTelefono(Integer.parseInt(vistaCliente.txtTelefono.getText()));

        String mensaje = modeloCliente.daoModificarCliente(cliente);
        JOptionPane.showMessageDialog(vistaCliente, mensaje);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();

        if (evento.equals("Insertar")) {
            insertCliente();
            LlenarTabla(vistaCliente.tblClientes);
        }
        if (e.getSource() == vistaCliente.btnSelect) {
            LlenarTabla(vistaCliente.tblClientes);
        }
        if (e.getSource() == vistaCliente.btnDelete) {
            eliminarCliente();
            LlenarTabla(vistaCliente.tblClientes);
        }
        if (e.getSource() == vistaCliente.btnUpdate) {
            modificarCliente();
            LlenarTabla(vistaCliente.tblClientes);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistaCliente.tblClientes) {
            vistaCliente.txtCodigo.setText(vistaCliente.tblClientes.getValueAt(vistaCliente.tblClientes.getSelectedRow(), 0).toString());
            vistaCliente.txtNombre.setText(vistaCliente.tblClientes.getValueAt(vistaCliente.tblClientes.getSelectedRow(), 1).toString());
            vistaCliente.txtApellido.setText(vistaCliente.tblClientes.getValueAt(vistaCliente.tblClientes.getSelectedRow(), 2).toString());
            vistaCliente.txtDirecion.setText(vistaCliente.tblClientes.getValueAt(vistaCliente.tblClientes.getSelectedRow(), 3).toString());
            vistaCliente.txtTelefono.setText(vistaCliente.tblClientes.getValueAt(vistaCliente.tblClientes.getSelectedRow(), 4).toString());
            vistaCliente.txtNit.setText(vistaCliente.tblClientes.getValueAt(vistaCliente.tblClientes.getSelectedRow(), 5).toString());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
