/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_conectar;
import clases.cl_documento_almacen;
import clases.cl_producto;
import clases.cl_productos_almacen;
import clases.cl_productos_salida;
import clases.cl_salida;
import clases.cl_varios;
import clases_autocomplete.cla_mis_documentos;
import clases_autocomplete.cla_producto;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.m_almacen;
import models.m_documentos_sunat;
import nicon.notify.core.Notification;
import casa_biblia.frm_principal;
import clases.cl_tipo_salida;
import json.cl_json_entidad;
import org.json.simple.parser.ParseException;
import vistas.frm_buscar_mis_productos;
import vistas.frm_ver_salidas;

/**
 *
 * @author luis
 */
public class frm_reg_salida extends javax.swing.JInternalFrame {
    
    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();
    
    cl_salida c_salida = new cl_salida();
    cl_productos_salida c_detalle = new cl_productos_salida();
    cl_producto c_producto = new cl_producto();
    cl_productos_almacen c_producto_almacen = new cl_productos_almacen();
    cl_documento_almacen c_doc_tienda = new cl_documento_almacen();
    cl_tipo_salida c_tipo_salida = new cl_tipo_salida();
    
    m_documentos_sunat m_documentos = new m_documentos_sunat();
    m_almacen m_almacen = new m_almacen();
    
    DefaultTableModel detalle;
    TextAutoCompleter tac_productos = null;
    TextAutoCompleter tac_proveedores = null;
    
    int fila_seleccionada;
    int id_almacen = frm_principal.c_almacen.getId();
    public static int productoid = 0;
    
    String query;

    /**
     * Creates new form frm_reg_ingreso
     */
    public frm_reg_salida() {
        initComponents();
        txt_fecha.setText(c_varios.fecha_usuario(c_varios.getFechaActual()));
        txt_fecha.requestFocus();
        txt_nom_tienda.setText(frm_principal.c_almacen.getNombre());
        modelo_ingreso();
        c_tipo_salida.cbx_tipo_salida(cbx_tido);
        
    }
    
    private void modelo_ingreso() {
        //formato de tabla detalle de venta
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Producto");
        detalle.addColumn("cod_externo");
        detalle.addColumn("Cant.");
        detalle.addColumn("Costo");
        detalle.addColumn("Precio");
        detalle.addColumn("Parcial");
        t_detalle.setModel(detalle);
        t_detalle.getColumnModel().getColumn(0).setPreferredWidth(60);
        t_detalle.getColumnModel().getColumn(1).setPreferredWidth(450);
        t_detalle.getColumnModel().getColumn(2).setPreferredWidth(100);
        t_detalle.getColumnModel().getColumn(3).setPreferredWidth(50);
        t_detalle.getColumnModel().getColumn(4).setPreferredWidth(50);
        t_detalle.getColumnModel().getColumn(5).setPreferredWidth(50);
        t_detalle.getColumnModel().getColumn(6).setPreferredWidth(70);
        c_varios.centrar_celda(t_detalle, 0);
        c_varios.centrar_celda(t_detalle, 2);
        c_varios.derecha_celda(t_detalle, 3);
        c_varios.derecha_celda(t_detalle, 4);
        c_varios.derecha_celda(t_detalle, 5);
        c_varios.derecha_celda(t_detalle, 6);
    }
    
    private void cargar_proveedores() {
        try {
            if (tac_proveedores != null) {
                tac_proveedores.removeAllItems();
            }
            tac_proveedores = new TextAutoCompleter(txt_ruc);
            /* 
            tac_proveedores = new TextAutoCompleter(txt_ruc_proveedor, new AutoCompleterCallback() {
                @Override
                public void callback(Object selectedItem) {
                    Object itemSelected = selectedItem;
                    if (itemSelected instanceof cla_producto) {
                        int pcodigo = ((cla_producto) itemSelected).getId_producto();
                        String pnombre = ((cla_producto) itemSelected).getDescripcion();
                        System.out.println("producto seleccionado " + pnombre);
                        c_producto.setId(pcodigo);
                    } else {
                        System.out.println("El item es de un tipo desconocido");
                    }
                }
                
            });
             */
            tac_proveedores.setMode(0);
            tac_proveedores.setCaseSensitive(false);
            Statement st = c_conectar.conexion();
            String sql = "select distinct(nro_documento) as nro_documento "
                    + "from proveedor "
                    + "order by nro_documento asc";
            ResultSet rs = c_conectar.consulta(st, sql);
            while (rs.next()) {
                tac_proveedores.addItem(rs.getObject("nro_documento"));
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void cargar_productos() {
        Frame f = JOptionPane.getRootFrame();
        frm_buscar_mis_productos formulario = new frm_buscar_mis_productos(f, true);
        frm_buscar_mis_productos.tipoform = 2;
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);
        /*
        try {
            if (tac_productos != null) {
                tac_productos.removeAllItems();
            }
            tac_productos = new TextAutoCompleter(txt_buscar_productos, new AutoCompleterCallback() {
                @Override
                public void callback(Object selectedItem) {
                    Object itemSelected = selectedItem;
                    c_producto.setId(0);
                    if (itemSelected instanceof cla_producto) {
                        int pcodigo = ((cla_producto) itemSelected).getId_producto();
                        String pnombre = ((cla_producto) itemSelected).getDescripcion();
                        System.out.println("producto seleccionado " + pnombre);
                        c_producto.setId(pcodigo);
                    } else {
                        System.out.println("El item es de un tipo desconocido");
                    }
                }
            });

            tac_productos.setMode(0);
            tac_productos.setCaseSensitive(false);
            Statement st = c_conectar.conexion();
            String sql = "select p.descripcion, p.precio, p.costo, p.id_producto, p.cod_externo "
                    + "from productos as p ";
            ResultSet rs = c_conectar.consulta(st, sql);
            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion") + " | cod externo: " + rs.getString("cod_externo")
                        + "    |    Precio: S/ " + c_varios.formato_numero(rs.getDouble("precio")) + "    |    Costo: S/ " + c_varios.formato_numero(rs.getDouble("costo"));
                tac_productos.addItem(new cla_producto(id_producto, descripcion));
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
        }
         */
    }
    
    private boolean valida_tabla(int producto) {
        //estado de ingreso
        boolean ingresar = false;
        int cuenta_iguales = 0;

        //verificar fila no se repite
        int contar_filas = t_detalle.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        }
        
        if (contar_filas > 0) {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_detalle.getValueAt(j, 0).toString());
                if (producto == id_producto_fila) {
                    ingresar = false;
                    cuenta_iguales++;
                    JOptionPane.showMessageDialog(null, "El Producto a Ingresar ya existe en la lista");
                } else {
                    ingresar = true;
                }
            }
        }
        
        if (cuenta_iguales == 0) {
            ingresar = true;
        }
        return ingresar;
    }
    
    private void limpiar_buscar() {
        txt_buscar_productos.setText("");
        txt_cingreso.setText("");
        txt_cactual.setText("");
        txt_precio.setText("");
        txt_cingreso.setEnabled(false);
        txt_cactual.setEnabled(false);
        txt_precio.setEnabled(false);
        btn_agregar_producto.setEnabled(true);
        txt_buscar_productos.requestFocus();
    }
    
    private double calcular_total() {
        double total = 0;
        int contar_filas = t_detalle.getRowCount();
        for (int i = 0; i < contar_filas; i++) {
            total = total + Double.parseDouble(t_detalle.getValueAt(i, 6).toString());
        }
        
        if (contar_filas == 0) {
            btn_guardar.setEnabled(false);
        }
        
        txt_total.setText("S/ " + c_varios.formato_totales(total));
        jTextField12.setText(contar_filas + "");
        return total;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbx_tido = new javax.swing.JComboBox<>();
        txt_fecha = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_ruc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_razon_social = new javax.swing.JTextField();
        txt_nom_tienda = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_buscar_productos = new javax.swing.JTextField();
        txt_cactual = new javax.swing.JTextField();
        txt_cingreso = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btn_agregar_producto = new javax.swing.JButton();
        btn_recargar = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btn_guardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_salir = new javax.swing.JButton();

        setTitle("Reg. Documento de Salida de Mercaderia");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Documento"));

        jLabel1.setText("Fecha:");

        jLabel2.setText("Tipo Venta:");
        jLabel2.setToolTipText("");

        cbx_tido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GUIA REMISION" }));
        cbx_tido.setEnabled(false);
        cbx_tido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tidoActionPerformed(evt);
            }
        });
        cbx_tido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tidoKeyPressed(evt);
            }
        });

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fechaKeyPressed(evt);
            }
        });

        jLabel4.setText("DNI o RUC:");

        txt_ruc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ruc.setEnabled(false);
        txt_ruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rucActionPerformed(evt);
            }
        });
        txt_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rucKeyTyped(evt);
            }
        });

        jLabel5.setText("Tienda:");
        jLabel5.setToolTipText("Tienda de Destino");

        txt_razon_social.setEnabled(false);

        txt_nom_tienda.setEnabled(false);

        txt_direccion.setEnabled(false);
        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_direccionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nom_tienda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ruc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbx_tido, javax.swing.GroupLayout.Alignment.TRAILING, 0, 207, Short.MAX_VALUE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(txt_direccion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_razon_social))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_razon_social, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nom_tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Productos"));

        jLabel8.setText("Producto:");

        jLabel9.setText("C. Actual: ");

        jLabel10.setText("Cantidad:");

        txt_buscar_productos.setEnabled(false);
        txt_buscar_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productosKeyPressed(evt);
            }
        });

        txt_cactual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cactual.setEnabled(false);

        txt_cingreso.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cingreso.setEnabled(false);
        txt_cingreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cingresoKeyPressed(evt);
            }
        });

        txt_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio.setEnabled(false);
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Precio");

        btn_agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar_producto.setText("Agregar");
        btn_agregar_producto.setEnabled(false);
        btn_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_productoActionPerformed(evt);
            }
        });

        btn_recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/currency.png"))); // NOI18N
        btn_recargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recargarActionPerformed(evt);
            }
        });

        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cactual, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28)
                        .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_agregar_producto))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_recargar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_recargar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(btn_agregar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_buscar_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cactual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos a Ingresar"));

        t_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "GUITARRA ACUSTICA | XAMFER", "AUIONET", "15.00", "160.00", "190.00", "2850.00"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID.", "Producto", "Marca", "Cant.", "Costo", "Precio", "Parcial"
            }
        ));
        t_detalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_detalle.setShowVerticalLines(false);
        t_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_detalleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_detalle);
        if (t_detalle.getColumnModel().getColumnCount() > 0) {
            t_detalle.getColumnModel().getColumn(0).setPreferredWidth(40);
            t_detalle.getColumnModel().getColumn(1).setPreferredWidth(400);
            t_detalle.getColumnModel().getColumn(2).setPreferredWidth(150);
            t_detalle.getColumnModel().getColumn(3).setPreferredWidth(60);
            t_detalle.getColumnModel().getColumn(4).setPreferredWidth(60);
            t_detalle.getColumnModel().getColumn(5).setPreferredWidth(60);
            t_detalle.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton7.setText("Eliminar Producto");
        jButton7.setEnabled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel14.setText("Total Filas:");

        jLabel16.setText("Total Documento:");

        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total.setText("0.00");

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setText("0");

        jToolBar1.setFloatable(false);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setEnabled(false);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_guardar);
        jToolBar1.add(jSeparator1);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setFocusable(false);
        btn_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_salir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
        frm_ver_salidas formulario = new frm_ver_salidas();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fecha.getText().length() == 10) {
                
                cbx_tido.setEnabled(true);
                cbx_tido.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fechaKeyPressed

    private void cbx_tidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cl_tipo_salida tipo_salida = (cl_tipo_salida) cbx_tido.getSelectedItem();
            c_salida.setId_tipo(tipo_salida.getId_tipo_salida());
            c_doc_tienda.setId_tido(tipo_salida.getId_tipo_salida());
            c_doc_tienda.setId_almacen(id_almacen);
            c_doc_tienda.comprobar_documento();
            cargar_proveedores();
            txt_ruc.setEnabled(true);
            txt_ruc.requestFocus();
        }
    }//GEN-LAST:event_cbx_tidoKeyPressed

    private void txt_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String documento = txt_ruc.getText();
            
            if (documento.length() > 0) {
                if (documento.length() == 8) {
                    JOptionPane.showMessageDialog(null, "BUSCANDO DATOS DEL DOCUMENTO INGRESADO, POR FAVOR ESPERE");
                    
                    Object[] resultado = c_salida.obtener_persona(documento);
                    int respuesta = Integer.parseInt(resultado[0].toString());
                    
                    if (respuesta == 1) {
                        txt_razon_social.setText(resultado[1].toString());
                        txt_direccion.setText(resultado[2].toString());
                    } else {

                        //buscar dni en reniec
                        try {
                            String json = cl_json_entidad.getJSONDNI_LUNASYSTEMS(documento);
                            //Lo mostramos
                            String datos = cl_json_entidad.showJSONDNI(json);
                            txt_razon_social.setText(datos);
                            txt_direccion.setText("");
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "ERROR EN BUSCAR  " + e.getLocalizedMessage());
                            
                        }
                    }
                    txt_direccion.setEnabled(true);
                    txt_direccion.requestFocus();
                    
                }
                if (documento.length() == 11) {
                    JOptionPane.showMessageDialog(null, "BUSCANDO DATOS DEL DOCUMENTO INGRESADO, POR FAVOR ESPERE");
                    
                    Object[] resultado = c_salida.obtener_persona(documento);
                    int respuesta = Integer.parseInt(resultado[0].toString());
                    
                    if (respuesta == 1) {
                        txt_razon_social.setText(resultado[1].toString());
                        txt_direccion.setText(resultado[2].toString());
                    } else {
                        //buscar ruc en sunat
                        try {
                            String json = cl_json_entidad.getJSONRUC_LUNASYSTEMS(documento);
                            //Lo mostramos
                            String[] datos = cl_json_entidad.showJSONRUC_JMP(json);
                            txt_razon_social.setText(datos[0]);
                            txt_direccion.setText(datos[1]);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "ERROR EN BUSCAR RUC " + e.getLocalizedMessage());
                        }
                        
                    }
                    txt_direccion.setEnabled(true);
                    txt_direccion.requestFocus();
                }
                if (documento.length() == 8 || documento.length() == 11) {
                    cargar_productos();
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese numero de documento");
                txt_ruc.requestFocus();
            }
        }
        

    }//GEN-LAST:event_txt_rucKeyPressed

    private void txt_rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyTyped
        c_varios.limitar_caracteres(evt, txt_ruc, 11);
        c_varios.solo_numeros(evt);
    }//GEN-LAST:event_txt_rucKeyTyped

    private void txt_buscar_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_buscar_productos.getText().length() > 25) {
                //System.out.println("\033[32m "+ c_producto.getId()); 
                if (c_producto.validar_id()) {
                    //validar que no existe en la tabla
                    if (valida_tabla(c_producto.getId())) {
                        
                        c_producto_almacen.setProducto(c_producto.getId());
                        c_producto_almacen.validar_id();
                        txt_precio.setText(c_varios.formato_numero(c_producto.getPrecio()));
                        txt_cactual.setText(c_producto_almacen.getCtotal() + "");
                        txt_cingreso.setText("1");
                        txt_cingreso.setEnabled(true);
                        txt_cingreso.requestFocus();
                    } else {
                        c_producto.setId(0);
                        c_producto_almacen.setProducto(0);
                        limpiar_buscar();
                        JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA ESTA SELECCIONADO");
                    }
                } else {
                    c_producto.setId(0);
                    c_producto_almacen.setProducto(0);
                    limpiar_buscar();
                    JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR PRODUCTO");
                }
            }
            
            if (txt_buscar_productos.getText().length() == 0) {
                //si nro de filas es mayor a 0 entonces ir a datos generales
                int contar_filas = t_detalle.getRowCount();
                if (contar_filas > 0) {
                    btn_guardar.setEnabled(true);
                    btn_guardar.requestFocus();
                }
            }
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            limpiar_buscar();
        }
    }//GEN-LAST:event_txt_buscar_productosKeyPressed

    private void txt_cingresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cingresoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tcantidad = txt_cingreso.getText();
            if (c_varios.esEntero(tcantidad)) {
                btn_agregar_producto.setEnabled(true);
                btn_agregar_producto.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            limpiar_buscar();
        }
    }//GEN-LAST:event_txt_cingresoKeyPressed

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed

    }//GEN-LAST:event_txt_precioKeyPressed

    private void btn_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_productoActionPerformed
        if (valida_tabla(c_producto.getId())) {
            double costo = 1;
            int cantidad = Integer.parseInt(txt_cingreso.getText());
            double precio = Double.parseDouble(txt_precio.getText());
            double parcial = costo * cantidad;
            Object fila[] = new Object[7];
            fila[0] = c_producto.getId();
            fila[1] = c_producto.getDescripcion();
            fila[2] = c_producto.getCod_externo();
            fila[3] = cantidad;
            fila[4] = c_varios.formato_numero(costo);
            fila[5] = c_varios.formato_numero(precio);
            fila[6] = c_varios.formato_numero(parcial);
            
            detalle.addRow(fila);
            calcular_total();
            limpiar_buscar();
            btn_buscar.doClick();
            
            int contar_filas = t_detalle.getRowCount();
            if (contar_filas > 0) {
                btn_guardar.setEnabled(true);
            }
        } else {
            c_producto.setId(0);
            c_producto_almacen.setProducto(0);
            limpiar_buscar();
            JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA ESTA SELECCIONADO");
        }
    }//GEN-LAST:event_btn_agregar_productoActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Guardar el ingreso de Mercaderia?");
        
        if (JOptionPane.OK_OPTION == confirmado) {
            c_salida.setFecha(c_varios.fecha_myql(txt_fecha.getText()));
            c_salida.setId_almacen(id_almacen);
//            c_ingreso.setId_moneda(cbx_moneda.getSelectedIndex() + 1);

            c_salida.setId_usuario(frm_principal.c_usuario.getId_usuario());
//            c_salida.setSerie(txt_serie.getText());
//            c_salida.setNumero(Integer.parseInt(txt_numero.getText()));
//            c_ingreso.setTc(Double.parseDouble(txt_tc.getText()));
            c_salida.setDocumento(txt_ruc.getText());
            c_salida.setDatos(txt_razon_social.getText());
            c_salida.setDireccion(txt_direccion.getText());
            c_salida.obtener_codigo();
            
            boolean registrado = c_salida.registrar();
            
            c_detalle.setId_salida(c_salida.getId_salida());
            if (registrado) {
                int nro_filas = t_detalle.getRowCount();
                for (int i = 0; i < nro_filas; i++) {
                    c_detalle.setId_producto(Integer.parseInt(t_detalle.getValueAt(i, 0).toString()));
                    c_detalle.setCantidad(Integer.parseInt(t_detalle.getValueAt(i, 3).toString()));
                    c_detalle.setPrecio(Double.parseDouble(t_detalle.getValueAt(i, 5).toString()));
                    c_detalle.registrar();
                }
                
                Notification.show("La Salida de Mercaderia", "se guardo correctamente");
                
                frm_ver_salidas formulario = new frm_ver_salidas();
                c_varios.llamar_ventana(formulario);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        detalle.removeRow(fila_seleccionada);
        calcular_total();
        txt_buscar_productos.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void t_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMouseClicked
        if (evt.getClickCount() == 2) {
            fila_seleccionada = t_detalle.getSelectedRow();
            jButton7.setEnabled(true);
        }
    }//GEN-LAST:event_t_detalleMouseClicked

    private void cbx_tidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tidoActionPerformed
        
        int tipo_cliente = cbx_tido.getSelectedIndex();
        if (tipo_cliente == 0) {
            query = "select * "
                    + "from clientes "
                    + "order by nombre asc";
//            txt_serie.setEnabled(false);
//            txt_numero.setEnabled(false);
            txt_ruc.setEnabled(true);
            
        }
        if (tipo_cliente == 1) {
            query = "select * "
                    + "from clientes "
                    + "where pago<venta "
                    + "order by nombre asc";
//            txt_serie.setEnabled(false);
//            txt_numero.setEnabled(false);
            txt_ruc.setEnabled(true);
        }
        

    }//GEN-LAST:event_cbx_tidoActionPerformed

    private void txt_rucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rucActionPerformed

    private void txt_direccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_direccion.getText().length() > 5) {
                txt_buscar_productos.setEnabled(true);
                txt_buscar_productos.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_direccionKeyPressed

    private void btn_recargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recargarActionPerformed
        //        cargar_productos();
        //        txt_buscar_productos.requestFocus();

        c_producto.setId(productoid);
        c_producto.validar_id();
        txt_buscar_productos.setText(c_producto.getDescripcion());
        txt_precio.setText(c_producto.getPrecio() + "");
        txt_cingreso.setText("1");
        txt_buscar_productos.setEnabled(false);
        txt_cingreso.setEnabled(true);
        txt_cingreso.requestFocus();
    }//GEN-LAST:event_btn_recargarActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        this.cargar_productos();
    }//GEN-LAST:event_btn_buscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar_producto;
    public static javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_guardar;
    public static javax.swing.JButton btn_recargar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cbx_tido;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable t_detalle;
    private javax.swing.JTextField txt_buscar_productos;
    private javax.swing.JTextField txt_cactual;
    private javax.swing.JTextField txt_cingreso;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_nom_tienda;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_razon_social;
    private javax.swing.JTextField txt_ruc;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
