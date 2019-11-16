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
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Flavio
 */
public class cl_salida {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_salida;
    private String fecha;
    private int id_almacen;
    private int id_tipo;
    private String documento;
    private String datos;
    private String direccion;
    private int id_usuario;

    public cl_salida() {

    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_salida) + 1, 1) as codigo "
                    + "from salida ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_salida = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void obtener_datos() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from salida "
                    + "where id_salida = '" + id_salida + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                fecha = rs.getString("fecha");
                id_almacen = rs.getInt("id_almacen");
                documento = rs.getString("doc_destinatario");
                datos = rs.getString("nom_destinatario");
                direccion = rs.getString("direc_destinatario");
                id_tipo = rs.getInt("id_tipo_salida");
                id_usuario = rs.getInt("id_usuarios");

            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public Object[] obtener_persona(String ruc) {
        Object[] texto = new Object[3];

        texto[0] = 0;
        texto[1] = "";
        texto[1] = "";

        try {
            Statement st = c_conectar.conexion();
            String query = "select doc_destinatario, nom_destinatario, direc_destinatario "
                    + "from salida "
                    + "where doc_destinatario = '" + ruc + "' "
                    + "limit 1";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                texto[0] = 1;
                texto[1] = rs.getString("nom_destinatario");
                texto[2] = rs.getString("direc_destinatario");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
        return texto;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into salida "
                + "Values (" + id_salida + ", '" + fecha + "', " + id_almacen + ", '" + id_tipo + "', '" + documento + "', '" + datos + "', '" + direccion + "', '" + id_usuario + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from "
                + "where id_salida = '" + id_salida + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

            TableRowSorter sorter = new TableRowSorter(modelo);

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //nombre de las columnas de las tablas
            modelo.addColumn("Id");
            modelo.addColumn("Fecha");
            modelo.addColumn("Documento");
            modelo.addColumn("Persona");
            modelo.addColumn("Usuario");

            //Creando las filas para el JTable
            //nombre dela columna de la consulta
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_salida");
                fila[1] = rs.getString("fecha");
                fila[2] = "NSA" + " | " + rs.getString("id_almacen") + " - " + rs.getString("id_salida");
                fila[3] = rs.getString("doc_destinatario") + " | " + rs.getString("nom_destinatario");
                fila[4] = rs.getString("nomusuario");
                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 1);
            c_varios.centrar_celda(tabla, 2);
            c_varios.centrar_celda(tabla, 4);
            tabla.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
        }

    }

}
