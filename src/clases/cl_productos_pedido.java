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
 * @author luis
 */
public class cl_productos_pedido {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_producto;
    private int id_pedido;
    private int cantidad;
    private double precio;

    public cl_productos_pedido() {
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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
        String query = "insert into productos_pedidos "
                + "Values ('" + id_producto + "', '" + id_pedido + "', '" + cantidad + "', '" + precio + "')";
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
        String query = "delete "
                + "from productos_pedidos "
                + "where id_pedido = '" + id_pedido + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
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
            String query = "select pp.id_producto, p.descripcion, p.cod_externo, pp.cantidad, pp.precio  "
                    + "from productos_pedidos as pp "
                    + "inner join productos as p on p.id_producto = pp.id_producto "
                    + "where id_pedido = '" + id_pedido + "' "
                    + "order by p.descripcion asc";
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Producto");
            modelo.addColumn("CodExterno");
            modelo.addColumn("Cant.");
            modelo.addColumn("Precio");
            modelo.addColumn("Subtotal");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() + " x UND").trim();
                fila[2] = rs.getString("cod_externo").trim();
                int pcantidad = rs.getInt("cantidad");
                double pprecio = rs.getDouble("precio");
                double pparcial = pcantidad * pprecio;
                fila[3] = pcantidad;
                fila[4] = c_varios.formato_totales(pprecio);
                fila[5] = c_varios.formato_totales(pparcial);
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
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
}
