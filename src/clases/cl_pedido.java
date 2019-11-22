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
import render_tablas.render_compras;
import render_tablas.render_pedidos;

/**
 *
 * @author luis
 */
public class cl_pedido {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_pedido;
    private String fecha;
    private String fecha_venta;
    private int id_usuario;
    private int id_cajero;
    private double total;
    private int id_almacen;
    private int estado;

    public cl_pedido() {
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_cajero() {
        return id_cajero;
    }

    public void setId_cajero(int id_cajero) {
        this.id_cajero = id_cajero;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_pedido) + 1, 1) as codigo "
                    + "from pedidos ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_pedido = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public boolean validar_pedido() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from pedidos "
                    + "where id_pedido = '" + id_pedido + "' and id_almacen = '" + id_almacen + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                fecha = rs.getString("fecha");
                fecha_venta = rs.getString("fecha_venta");
                id_usuario = rs.getInt("id_usuarios");
                id_cajero = rs.getInt("id_cajero");
                total = rs.getDouble("total");
                estado = rs.getInt("estado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into pedidos "
                + "Values ('" + id_pedido + "', '" + fecha + "', '1000-01-01', '" + id_usuario + "', '" + id_cajero + "', '" + total + "', '" + id_almacen + "', '0')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean anular() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update pedidos "
                + "set estado = '2' "
                + "where id_pedido = '" + id_pedido + "'";
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

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            modelo.addColumn("Id");
            modelo.addColumn("Fecha");
            modelo.addColumn("Vendedor");
            modelo.addColumn("Total");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                int iestado = rs.getInt("estado");
                String sestado = "";
                if (iestado == 0) {
                    sestado = "PENDIENTE";
                }
                if (iestado == 1) {
                    sestado = "VENDIDO";
                }
                if (iestado == 2) {
                    sestado = "ANULADO";
                }
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_pedido");
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getString("id_usuarios");
                fila[3] = rs.getDouble("total");
                fila[4] = sestado;

                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla.setDefaultRenderer(Object.class, new render_pedidos());
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    public void mostrarPedidoPendientes(JTable tabla) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

            Statement st = c_conectar.conexion();
            String query = "select fecha, id_pedido, total "
                    + "from pedidos "
                    + "where estado = 0 and id_almacen = '" + id_almacen + "' "
                    + "order by id_pedido asc";
            ResultSet rs = c_conectar.consulta(st, query);

            modelo.addColumn("Fecha");
            modelo.addColumn("Id");
            modelo.addColumn("Total");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("fecha");
                fila[1] = rs.getInt("id_pedido");
                fila[2] = rs.getDouble("total");
                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }
}
