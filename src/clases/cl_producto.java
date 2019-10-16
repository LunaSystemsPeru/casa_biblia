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
import javax.swing.table.TableRowSorter;
import render_tablas.render_productos_todos;

/**
 *
 * @author CALIDAD
 */
public class cl_producto {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id;
    private String descripcion;
    private String cod_externo;
    private double costo;
    private double precio;
    private int ctotal;
    private int tipo_producto;
    private int afecto_igv;
    private String estado;
    private int id_proveedor;
    private int id_sub_clasificacion;

    public cl_producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCod_externo() {
        return cod_externo;
    }

    public void setCod_externo(String cod_externo) {
        this.cod_externo = cod_externo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCtotal() {
        return ctotal;
    }

    public void setCtotal(int ctotal) {
        this.ctotal = ctotal;
    }

    public int getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(int tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public int getAfecto_igv() {
        return afecto_igv;
    }

    public void setAfecto_igv(int afecto_igv) {
        this.afecto_igv = afecto_igv;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_sub_clasificacion() {
        return id_sub_clasificacion;
    }

    public void setId_sub_clasificacion(int id_sub_clasificacion) {
        this.id_sub_clasificacion = id_sub_clasificacion;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel tmodelo;
            tmodelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            TableRowSorter sorter = new TableRowSorter(tmodelo);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            tmodelo.addColumn("Id");
            tmodelo.addColumn("Descripcion");//descripcion modelo serie
            tmodelo.addColumn("Cod Externo");
            tmodelo.addColumn("Precio");
            tmodelo.addColumn("Clasificacion");
            tmodelo.addColumn("Cant. Actual");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getObject("id_producto");
                fila[1] = rs.getString("descripcion").trim();
                fila[2] = rs.getString("cod_externo");
                fila[3] = c_varios.formato_numero(rs.getDouble("precio"));
                fila[4] = rs.getString("id_subclasificacion");
                fila[5] = rs.getInt("ctotal");

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
            tabla.setDefaultRenderer(Object.class, new render_productos_todos());
            tabla.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_producto) + 1, 1) as codigo "
                    + "from productos ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                id = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean validar_id() {
        boolean existe = false;
        try {

            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from productos "
                    + "where id_producto = '" + id + "'";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                descripcion = rs.getString("descripcion");
                cod_externo = rs.getString("cod_externo");
                costo = rs.getDouble("costo");
                precio = rs.getDouble("precio");
                ctotal = rs.getInt("ctotal");
                tipo_producto = rs.getInt("tipo_producto");
                afecto_igv = rs.getInt("afecto_igv");
                estado = rs.getString("estado");
                id_proveedor = rs.getInt("id_proveedor");
                id_sub_clasificacion = rs.getInt("id_subclasificacion");
                existe = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos "
                + "values ('" + id + "', '" + descripcion + "', '" + cod_externo + "', '" + costo + "', '" + precio + "', '0', "
                + "'" + tipo_producto + "', '" + afecto_igv + "','" + id_sub_clasificacion + "', '', '1', '1')";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }

    public boolean modificar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update productos "
                + "set descripcion = '" + descripcion + "', cod_externo = '" + cod_externo + "', precio = '" + precio + "', costo = '" + costo + "', "
                + "id_sub_clasificacion = '" + id_sub_clasificacion + "', afecto_igv = '" + afecto_igv + "' "
                + "where id_producto = '" + id + "'";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }
}
