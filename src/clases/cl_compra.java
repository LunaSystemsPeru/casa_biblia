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
public class cl_compra {

    cl_conectar c_conectar = new cl_conectar();

    private int id_compra;
    private String fecha;
    private int id_proveedor;
    private int id_tido;
    private String serie;
    private int numero;
    private double total;
    private double pagado;
    private int estado;
    private int id_usuario;
    private int id_empresa;
    private String glosa;

    public cl_compra() {
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_tido() {
        return id_tido;
    }

    public void setId_tido(int id_tido) {
        this.id_tido = id_tido;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_compra) + 1, 1) as codigo "
                    + "from compras ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_compra = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public boolean validar_almacen() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from compras "
                    + "where id_compra = '" + id_compra + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                fecha = rs.getString("fecha");
                id_proveedor = rs.getInt("id_proveedor");
                id_tido = rs.getInt("id_tido");
                serie = rs.getString("serie");
                numero = rs.getInt("numero");
                total = rs.getDouble("total");
                pagado = rs.getDouble("pagado");
                estado = rs.getInt("estado");
                id_usuario = rs.getInt("id_usuarios");
                id_empresa = rs.getInt("id_empresa");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean validar_documento() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select id_compra "
                    + "from compras "
                    + "where id_tido = '" + id_tido + "' and serie = '" + serie + "' and numero = '" + numero + "' and id_proveedor = '" + id_proveedor + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                id_compra = rs.getInt("id_compra");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into compras "
                + "Values ('" + id_compra + "', '" + fecha + "', '" + id_proveedor + "', '" + id_tido + "', '" + serie + "', '" + numero + "', '" + total + "', '0', '2', '" + id_usuario + "', '" + id_empresa + "')";
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
        String query = "update compras "
                + "set total = '" + total + "', fecha = '" + fecha + "', id_empresa = '" + id_empresa + "' "
                + "where id_compra = '" + id_compra + "'";
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
            modelo.addColumn("Empresa");
            modelo.addColumn("Fecha");
            modelo.addColumn("Documento");
            modelo.addColumn("Proveedor");
            modelo.addColumn("Total");
            modelo.addColumn("Por Pagar");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_almacen");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("direccion");
                fila[3] = rs.getString("ruc") + " | " + rs.getString("razon");
                fila[4] = rs.getString("ciudad");
                if (rs.getString("estado").equals("1")) {
                    fila[5] = "ACTIVO";
                } else {
                    fila[5] = "-";
                }

                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(350);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }
}
