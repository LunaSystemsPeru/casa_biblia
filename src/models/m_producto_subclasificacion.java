/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_producto_clasificacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class m_producto_subclasificacion {

    cl_conectar c_conectar = new cl_conectar();

    public void llenar_combobox(JComboBox cbx, int idclasificacion) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select * "
                    + "from productos_sub_clasificacion "
                    + "where id_clasificacion = '" + idclasificacion + "' "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_producto_clasificacion(rs.getInt("id_subclasificacion"), rs.getString("nombre")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
