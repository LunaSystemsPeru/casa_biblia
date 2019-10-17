/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import render_tablas.render_ventas;

/**
 *
 * @author luis
 */
public class cl_venta {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_venta;
    private int id_almacen;
    private String fecha;
    private int id_tido;
    private String serie;
    private int numero;
    private int id_cliente;
    private int id_usuario;
    private double total;
    private double pagado;
    private int afecto_igv;
    private int id_tipo_venta;
    private int estado;
    private int id_pedido;

    public cl_venta() {
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public int getId_tipo_venta() {
        return id_tipo_venta;
    }

    public void setId_tipo_venta(int id_tipo_venta) {
        this.id_tipo_venta = id_tipo_venta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getAfecto_igv() {
        return afecto_igv;
    }

    public void setAfecto_igv(int afecto_igv) {
        this.afecto_igv = afecto_igv;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int regirtar_venta(int pedido,int idcliente,int idusuario,double efectivo, double tarjeta, int afecto){
        
        int resultado = 0;
  
        try {

            CallableStatement  st = c_conectar.conx().prepareCall("call SP_INSERTAR_AFECTOS_NOAFECTOS(?,?,?,?,?,?,?)");

            st.setInt(1, pedido);
            st.setInt(2, idcliente);
            st.setInt(3, idusuario);
            st.setDouble(4, efectivo);
            st.setDouble(5, tarjeta);
            st.setInt(6, afecto);
            
            st.registerOutParameter(7, java.sql.Types.INTEGER);

            st.execute();

            resultado=st.getInt(7);
            
        }catch(Exception ex){
             System.out.println("error: "+ex.getMessage());
             System.out.println(ex);
        }
        return resultado;
    }
    public boolean validar_venta() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from ventas "
                    + "where id_almacen = '" + id_almacen + "' and id_ventas = '" + id_venta + "'";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                afecto_igv = rs.getInt("afecto_igv");
                id_pedido = rs.getInt("id_pedido");
                fecha = rs.getString("fecha");
                id_cliente = rs.getInt("id_cliente");
                id_tido = rs.getInt("id_tido");
                serie = rs.getString("serie");
                numero = rs.getInt("numero");
                id_almacen = rs.getInt("id_almacen");
                id_usuario = rs.getInt("id_usuarios");
                total = rs.getDouble("total");
                pagado = rs.getDouble("pagado");
                id_tipo_venta = rs.getInt("tipo_venta");
                estado = rs.getInt("estado");
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
            String query = "select id_ventas "
                    + "from ventas "
                    + "where id_almacen = '" + id_almacen + "' and id_tido = '" + id_tido + "' and serie = '" + serie + "' and numero = '" + numero + "' and fecha = '" + fecha + "'";
            //System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                this.id_venta = rs.getInt("id_ventas");
                existe = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public int obtener_codigo() {
        int codigo = 0;
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_ventas) + 1, 1) as codigo "
                    + "from ventas ";
            //System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                this.id_venta = rs.getInt("codigo");
                codigo = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into ventas "
                + "values ('" + id_venta + "', '" + id_almacen + "', '" + fecha + "', '" + id_tido + "', '" + serie + "', '" + numero + "', "
                + "'" + id_cliente + "', '" + id_usuario + "', '" + total + "', '0', '" + afecto_igv + "', '" + id_tipo_venta + "', '"+estado+"', '" + id_pedido + "')";
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
        String query = "update ventas set estado = 3 "
                + "where id_ventas = '" + id_venta + "' and id_almacen = '" + id_almacen + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean entregar_separacion() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update ventas set estado = 4 "
                + "where id_ventas = '" + id_venta + "' and id_almacen = '" + id_almacen + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
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
            //    TableRowSorter sorter = new TableRowSorter(mostrar);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            tmodelo.addColumn("ID.");
            tmodelo.addColumn("Fecha");
            tmodelo.addColumn("Documento");
            tmodelo.addColumn("Cliente");
            tmodelo.addColumn("Total");
            tmodelo.addColumn("Pagado");
            tmodelo.addColumn("Vendedor");
            tmodelo.addColumn("Estado");
            tmodelo.addColumn("");

            //Creando las filas para el JTable
            while (rs.next()) {
                int iestado = rs.getInt("estado");
                int itipo_venta = rs.getInt("tipo_venta");
                String sestado = "";

                if (itipo_venta == 1) {
                    if (iestado == 1) {
                        sestado = "PAGADO";
                    }
                    if (iestado == 2) {
                        sestado = "POR COBRAR";
                    }
                    if (iestado == 3) {
                        sestado = "ANULADO";
                    }
                }

                if (itipo_venta == 2) {
                    if (iestado == 1) {
                        sestado = "POR ENTREGAR";
                    }
                    if (iestado == 2) {
                        sestado = "SEPARADO";
                    }
                    if (iestado == 3) {
                        sestado = "ANULADO";
                    }
                    if (iestado == 4) {
                        sestado = "ENTREGADO";
                    }
                }
                Object[] fila = new Object[9];
                fila[0] = rs.getString("id_ventas");
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getString("abreviado") + " | " + c_varios.ceros_izquieda_letras(4, rs.getString("serie")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero"));
                fila[3] = rs.getString("documento") + " | " + rs.getString("nombre");
                fila[4] = c_varios.formato_numero(rs.getDouble("total"));
                fila[5] = c_varios.formato_numero(rs.getDouble("pagado"));
                fila[6] = rs.getString("username");
                fila[7] = sestado;
                fila[8] = rs.getInt("id_ventas");

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(350);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(90);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
            tabla.setDefaultRenderer(Object.class, new render_ventas());
            //   t_productos.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public Integer[] ventas_diaras() {
        int total_mes = cl_varios.diasDelMes(c_varios.obtener_mes(), c_varios.obtener_anio());
        Integer[] valor_x = new Integer[total_mes + 1];
        //System.out.println("total dias de este mes = " + total_mes);

        try {
            Statement st = c_conectar.conexion();
            String query = "select day(v.fecha) as dia, sum(v.total) as total_dia "
                    + "from ventas as v "
                    + "where v.id_almacen = '" + id_almacen + "' and month(v.fecha) = month(CURRENT_DATE()) and year(v.fecha) = year(CURRENT_DATE()) "
                    + "GROUP by day(v.fecha)";
            // System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                valor_x[rs.getInt("dia")] = rs.getInt("total_dia");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return valor_x;
    }

    public Integer[] ventas_mensuales() {
        Integer[] valor_x = new Integer[12];
        // System.out.println("total dias de este mes = " + total_mes);

        try {
            Statement st = c_conectar.conexion();
            String query = "select month(v.fecha) as mes, sum(v.total) as total_mes "
                    + "from ventas as v "
                    + "where v.id_almacen = '" + id_almacen + "' and year(v.fecha) = year(CURRENT_DATE()) "
                    + "GROUP by month(v.fecha)";
            //   System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                valor_x[rs.getInt("mes")] = rs.getInt("total_mes");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return valor_x;
    }

}
