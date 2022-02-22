/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class cl_productos_sub_clasificacion {

    cl_conectar c_conectar = new cl_conectar();
    
    private int id_subclasificacion;
    private String nombre;
    private int id_clasificacion;

    public cl_productos_sub_clasificacion() {
    }

    public int getId_subclasificacion() {
        return id_subclasificacion;
    }

    public void setId_subclasificacion(int id_subclasificacion) {
        this.id_subclasificacion = id_subclasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public int getId_clasificacion() {
        return id_clasificacion;
    }

    public void setId_clasificacion(int id_clasificacion) {
        this.id_clasificacion = id_clasificacion;
    }

    
    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos_sub_clasificacion "
                + "Values ('" + id_subclasificacion + "', '" + nombre + "', '" + id_clasificacion + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean modificar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update productos_sub_clasificacion "
                + "set nombre = '" + nombre + "' "
                + "where id_subclasificacion = '" + id_subclasificacion + "'";
        int resultado = c_conectar.actualiza(st, query);
        // System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }
    
     public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_subclasificacion) + 1, 1) as codigo "
                    + "from productos_sub_clasificacion ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_subclasificacion = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }
    
     public boolean validar_datos() {
        boolean existe = false;
        try {

            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from productos_sub_clasificacion "
                    + "where id_subclasificacion = '" + id_subclasificacion + "' ";
            //System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                this.nombre = rs.getString("nombre");
                this.id_clasificacion = rs.getInt("id_clasificacion");
                existe = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    
    public void mostrar(JTable tabla) {
        DefaultTableModel modelo;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            String query = "select * "
                    + "from productos_sub_clasificacion "
                    + "order by nombre asc";
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Nombre");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[2];
                fila[0] = rs.getInt("id_subclasificacion");
                fila[1] = rs.getString("nombre");
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
}
