/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Flavio
 */
public class cl_productos_salida {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_producto;
    private int id_salida;
    private int cantidad;
    private double precio;

    public cl_productos_salida() {
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos_salida "
                + "Values ('" + id_producto + "', '" + id_salida + "', '" + cantidad + "', '" + precio + "')";
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
        String query = "delete from productos_salida "
                + "where id_salida = '" + id_salida + "'";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public void mostrar_detalle(JTable tabla) {
        DefaultTableModel modelo;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            String query = "select ps.id_producto, p.descripcion, p.cod_externo, ps.cantidad, ps.precio "
                    + "from productos_salida as ps "
                    + "inner join productos as p on p.id_producto = ps.id_producto "
                    + "where ps.id_salida = '" + id_salida + "'";
            System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Producto");
            modelo.addColumn("Cod Externo");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Precio");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_producto");
                fila[1] = rs.getString("descripcion").trim();
                fila[2] = rs.getString("cod_externo").trim();
                fila[3] = rs.getInt("cantidad");
                fila[4] = c_varios.formato_totales(rs.getDouble("precio"));
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
            c_varios.centrar_celda(tabla, 0);
            c_varios.derecha_celda(tabla, 3);
            c_varios.derecha_celda(tabla, 4);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

}
