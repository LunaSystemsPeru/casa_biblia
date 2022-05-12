/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public class cl_tipo_salida {
    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();
    
    private int id_tipo_salida;
    private String nombre;

    public cl_tipo_salida() {
    }

    public int getId_tipo_salida() {
        return id_tipo_salida;
    }

    public void setId_tipo_salida(int id_tipo_salida) {
        this.id_tipo_salida = id_tipo_salida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre ;
    }
    public void cbx_tipo_salida(JComboBox jComboBox){
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from tipo_salida";
            ResultSet rs = c_conectar.consulta(st, query);
            DefaultComboBoxModel<cl_tipo_salida> dfcm=new DefaultComboBoxModel();
            
            while (rs.next()) {
                cl_tipo_salida tiposalida=new cl_tipo_salida();
                
                tiposalida.setId_tipo_salida(rs.getInt("id_tipo_salida"));
                tiposalida.setNombre(rs.getString("nombre"));
                
                dfcm.addElement(tiposalida);
            }
            jComboBox.setModel(dfcm); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
