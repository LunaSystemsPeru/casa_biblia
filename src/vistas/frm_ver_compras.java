/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_compra;
import clases.cl_compra_pago;
import clases.cl_varios;
import forms.frm_reg_compra;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class frm_ver_compras extends javax.swing.JInternalFrame {

    //clases principales
    cl_varios c_varios = new cl_varios();
    cl_compra c_compra = new cl_compra();
    cl_compra_pago c_pago = new cl_compra_pago();

    //variables publicas
    String query = "";
    int fila_seleccionada;

    /**
     *
     * Creates new form frm_ver_compras
     */
    public frm_ver_compras() {
        initComponents();
        query = "SELECT c.id_compra, c.fecha, em.ruc as ruc_empresa, c.estado, p.nro_documento, "
                + "p.razon_social, ds.abreviado as doc_sunat, c.serie, c.numero, c.total, c.pagado "
                + "FROM compras as c "
                + "inner join proveedor as p on p.id_proveedor = c.id_proveedor "
                + "inner join empresa as em on em.id_empresa = c.id_empresa "
                + "inner join documentos_sunat as ds on ds.id_tido = c.id_tido "
                + "where c.estado = 0 "
                + "order by c.fecha asc ";
        cargar_tabla();
    }

    private void activar_botones() {
        btn_detalle.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_pagos.setEnabled(true);
    }

    private void desactivar_botones() {
        btn_detalle.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_pagos.setEnabled(false);
    }

    private void cargar_tabla() {
        c_compra.mostrar(t_compras, query);
        int total_compras = t_compras.getRowCount();
        double suma_deuda = 0;
        for (int i = 0; i < total_compras; i++) {
            suma_deuda += Double.parseDouble(t_compras.getValueAt(i, 6).toString());
        }
        lbl_total_deuda.setText(c_varios.formato_totales(suma_deuda));
    }

    private void verificar_deuda() {
        double total_compra = Double.parseDouble(t_compras.getValueAt(fila_seleccionada, 5).toString());
        double total_pagos = 0;
        int filas_pagos = t_pagos.getRowCount();
        for (int i = 0; i < filas_pagos; i++) {
            total_pagos += Double.parseDouble(t_pagos.getValueAt(i, 2).toString());
        }
        if (total_compra > total_pagos) {
            btn_pago_agregar.setEnabled(true);
        } else {
            btn_pago_agregar.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_ver_pagos = new javax.swing.JDialog();
        jToolBar2 = new javax.swing.JToolBar();
        btn_pago_agregar = new javax.swing.JButton();
        btn_pago_eliminar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btn_pago_salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_pago_documento = new javax.swing.JTextField();
        txt_pago_proveedor = new javax.swing.JTextField();
        txt_pago_fecha = new javax.swing.JTextField();
        txt_pago_total = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_pagos = new javax.swing.JTable();
        jd_reg_pago = new javax.swing.JDialog();
        jToolBar3 = new javax.swing.JToolBar();
        btn_reg_agregar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btn_reg_salir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_registrar_monto = new javax.swing.JTextField();
        txt_registrar_fecha = new javax.swing.JFormattedTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btn_agregar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btn_detalle = new javax.swing.JButton();
        btn_pagos = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_eliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        cbx_tipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_compras = new javax.swing.JTable();
        lbl_ayuda = new javax.swing.JLabel();
        lbl_total_deuda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jd_ver_pagos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jd_ver_pagos.setTitle("Ver Pago de Documento de Compra");

        jToolBar2.setFloatable(false);

        btn_pago_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_pago_agregar.setText("Agregar");
        btn_pago_agregar.setEnabled(false);
        btn_pago_agregar.setFocusable(false);
        btn_pago_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pago_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_pago_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pago_agregarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_pago_agregar);

        btn_pago_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_pago_eliminar.setText("Eliminar");
        btn_pago_eliminar.setEnabled(false);
        btn_pago_eliminar.setFocusable(false);
        btn_pago_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pago_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_pago_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pago_eliminarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_pago_eliminar);
        jToolBar2.add(jSeparator4);

        btn_pago_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_pago_salir.setText("Salir");
        btn_pago_salir.setFocusable(false);
        btn_pago_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pago_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_pago_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pago_salirActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_pago_salir);

        jLabel2.setText("Documento:");

        jLabel4.setText("Proveedor:");

        jLabel5.setText("Fecha.");

        jLabel6.setText("Total:");

        txt_pago_documento.setEditable(false);
        txt_pago_documento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_pago_proveedor.setEditable(false);

        txt_pago_fecha.setEditable(false);
        txt_pago_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_pago_total.setEditable(false);
        txt_pago_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        t_pagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_pagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_pagosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(t_pagos);

        javax.swing.GroupLayout jd_ver_pagosLayout = new javax.swing.GroupLayout(jd_ver_pagos.getContentPane());
        jd_ver_pagos.getContentPane().setLayout(jd_ver_pagosLayout);
        jd_ver_pagosLayout.setHorizontalGroup(
            jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jd_ver_pagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jd_ver_pagosLayout.createSequentialGroup()
                        .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pago_proveedor)
                            .addGroup(jd_ver_pagosLayout.createSequentialGroup()
                                .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_pago_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_pago_total, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 112, Short.MAX_VALUE))))
                    .addGroup(jd_ver_pagosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pago_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jd_ver_pagosLayout.setVerticalGroup(
            jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_ver_pagosLayout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pago_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pago_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pago_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_ver_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pago_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        jd_reg_pago.setTitle("Agregar Pago");

        jToolBar3.setFloatable(false);

        btn_reg_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_reg_agregar.setText("Agregar");
        btn_reg_agregar.setEnabled(false);
        btn_reg_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_reg_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_reg_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reg_agregarActionPerformed(evt);
            }
        });
        jToolBar3.add(btn_reg_agregar);
        jToolBar3.add(jSeparator5);

        btn_reg_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_reg_salir.setText("Salir");
        btn_reg_salir.setFocusable(false);
        btn_reg_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_reg_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_reg_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reg_salirActionPerformed(evt);
            }
        });
        jToolBar3.add(btn_reg_salir);

        jLabel7.setText("Fecha.");

        jLabel8.setText("Monto:");

        txt_registrar_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_registrar_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_registrar_montoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_registrar_montoKeyPressed(evt);
            }
        });

        try {
            txt_registrar_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_registrar_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_registrar_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_registrar_fechaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jd_reg_pagoLayout = new javax.swing.GroupLayout(jd_reg_pago.getContentPane());
        jd_reg_pago.getContentPane().setLayout(jd_reg_pagoLayout);
        jd_reg_pagoLayout.setHorizontalGroup(
            jd_reg_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jd_reg_pagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_reg_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(50, 50, 50)
                .addGroup(jd_reg_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_registrar_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(txt_registrar_monto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jd_reg_pagoLayout.setVerticalGroup(
            jd_reg_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_reg_pagoLayout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_reg_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_registrar_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_reg_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_registrar_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setTitle("Ver Documentos de Compras y Pagos");

        jToolBar1.setFloatable(false);

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setFocusable(false);
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_agregar);
        jToolBar1.add(jSeparator3);

        btn_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment.png"))); // NOI18N
        btn_detalle.setText("Ver Detalle");
        btn_detalle.setEnabled(false);
        btn_detalle.setFocusable(false);
        btn_detalle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_detalle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_detalle);

        btn_pagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coins.png"))); // NOI18N
        btn_pagos.setText("Ver Pagos");
        btn_pagos.setEnabled(false);
        btn_pagos.setFocusable(false);
        btn_pagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pagos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_pagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagosActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_pagos);
        jToolBar1.add(jSeparator2);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setFocusable(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_eliminar);
        jToolBar1.add(jSeparator1);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
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

        jLabel1.setText("Buscar:");

        txt_buscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscarFocusGained(evt);
            }
        });
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        cbx_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha", "Proveedor", "Periodo", "Nro Documento" }));
        cbx_tipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbx_tipoFocusGained(evt);
            }
        });
        cbx_tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipoKeyPressed(evt);
            }
        });

        t_compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_comprasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_compras);

        lbl_ayuda.setText("Ayuda:");

        lbl_total_deuda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lbl_total_deuda.setText("0.00");

        jLabel3.setText("Total Deuda:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_ayuda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_total_deuda, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_total_deuda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        frm_reg_compra formulario = new frm_reg_compra();
        c_varios.llamar_ventana_normal(formulario);
        this.dispose();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void cbx_tipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_buscar.setText("");
            txt_buscar.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipoKeyPressed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_buscar.getText();
            if (texto.length() > 1) {
                int tipo_busqueda = cbx_tipo.getSelectedIndex();
                if (tipo_busqueda == 0) {
                    if (texto.length() == 10) {
                        texto = c_varios.fecha_myql(texto);
                        query = "SELECT c.id_compra, c.fecha, em.ruc as ruc_empresa, c.estado, p.nro_documento, "
                                + "p.razon_social, ds.abreviado as doc_sunat, c.serie, c.numero, c.total, c.pagado "
                                + "FROM compras as c "
                                + "inner join proveedor as p on p.id_proveedor = c.id_proveedor "
                                + "inner join empresa as em on em.id_empresa = c.id_empresa "
                                + "inner join documentos_sunat as ds on ds.id_tido = c.id_tido "
                                + "where c.fecha = '" + texto + "' "
                                + "order by p.razon_social asc";
                    } else {
                        JOptionPane.showMessageDialog(null, "Fecha no es correcta");
                        txt_buscar.selectAll();
                        txt_buscar.requestFocus();
                    }
                }

                if (tipo_busqueda == 1) {
                    query = "SELECT c.id_compra, c.fecha, em.ruc as ruc_empresa, c.estado, p.nro_documento, "
                            + "p.razon_social, ds.abreviado as doc_sunat, c.serie, c.numero, c.total, c.pagado "
                            + "FROM compras as c "
                            + "inner join proveedor as p on p.id_proveedor = c.id_proveedor "
                            + "inner join empresa as em on em.id_empresa = c.id_empresa "
                            + "inner join documentos_sunat as ds on ds.id_tido = c.id_tido "
                            + "where p.nro_documento like '%" + texto + "%' or p.razon_social like '%" + texto + "%' "
                            + "order by c.fecha asc ";
                }

                if (tipo_busqueda == 2) {
                    query = "SELECT c.id_compra, c.fecha, em.ruc as ruc_empresa, c.estado, p.nro_documento, "
                            + "p.razon_social, ds.abreviado as doc_sunat, c.serie, c.numero, c.total, c.pagado "
                            + "FROM compras as c "
                            + "inner join proveedor as p on p.id_proveedor = c.id_proveedor "
                            + "inner join empresa as em on em.id_empresa = c.id_empresa "
                            + "inner join documentos_sunat as ds on ds.id_tido = c.id_tido "
                            + "where concat(year(c.fecha), LPAD(month(c.fecha), 2, 0)) = '" + texto + "' "
                            + "order by c.fecha asc ";
                }

                if (tipo_busqueda == 3) {
                    query = "SELECT c.id_compra, c.fecha, em.ruc as ruc_empresa, c.estado, p.nro_documento, "
                            + "p.razon_social, ds.abreviado as doc_sunat, c.serie, c.numero, c.total, c.pagado "
                            + "FROM compras as c "
                            + "inner join proveedor as p on p.id_proveedor = c.id_proveedor "
                            + "inner join empresa as em on em.id_empresa = c.id_empresa "
                            + "inner join documentos_sunat as ds on ds.id_tido = c.id_tido "
                            + "where c.numero = '" + texto + "' "
                            + "order by c.fecha asc ";
                }
                cargar_tabla();
                txt_buscar.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "ESTA VACIO");
                txt_buscar.selectAll();
                txt_buscar.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void cbx_tipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbx_tipoFocusGained
        lbl_ayuda.setText("PRESIONE ENTER PARA ESCRIBIR TEXTO DE BUSQUEDA");
    }//GEN-LAST:event_cbx_tipoFocusGained

    private void txt_buscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscarFocusGained
        lbl_ayuda.setText("PRESIONE ENTER PARA BUSCAR, SOLO SI EL TEXTO NO ESTA VACIO");
    }//GEN-LAST:event_txt_buscarFocusGained

    private void t_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_comprasMouseClicked
        fila_seleccionada = t_compras.getSelectedRow();
        activar_botones();
    }//GEN-LAST:event_t_comprasMouseClicked

    private void btn_pagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagosActionPerformed
        desactivar_botones();
        txt_pago_documento.setText(t_compras.getValueAt(fila_seleccionada, 3).toString());
        txt_pago_fecha.setText(t_compras.getValueAt(fila_seleccionada, 2).toString());
        txt_pago_proveedor.setText(t_compras.getValueAt(fila_seleccionada, 4).toString());
        txt_pago_total.setText(t_compras.getValueAt(fila_seleccionada, 5).toString());
        int idcompra = Integer.parseInt(t_compras.getValueAt(fila_seleccionada, 0).toString());
        c_pago.setId_compra(idcompra);
        c_pago.mostrar(t_pagos);

        verificar_deuda();

        jd_ver_pagos.setModal(true);
        jd_ver_pagos.setSize(400, 494);
        jd_ver_pagos.setLocationRelativeTo(null);
        jd_ver_pagos.setVisible(true);

    }//GEN-LAST:event_btn_pagosActionPerformed

    private void btn_pago_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pago_salirActionPerformed
        jd_ver_pagos.dispose();
        cargar_tabla();
    }//GEN-LAST:event_btn_pago_salirActionPerformed

    private void btn_reg_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reg_salirActionPerformed
        jd_reg_pago.dispose();
        c_pago.mostrar(t_pagos);
        verificar_deuda();
    }//GEN-LAST:event_btn_reg_salirActionPerformed

    private void btn_pago_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pago_agregarActionPerformed
        txt_registrar_fecha.setText(c_varios.fecha_usuario(c_varios.getFechaActual()));
        jd_reg_pago.setModal(true);
        jd_reg_pago.setSize(295, 186);
        jd_reg_pago.setLocationRelativeTo(null);
        jd_reg_pago.setVisible(true);
        txt_registrar_monto.requestFocus();
    }//GEN-LAST:event_btn_pago_agregarActionPerformed

    private void txt_registrar_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_registrar_montoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String numero = txt_registrar_monto.getText();
            if (c_varios.esDecimal(numero)) {
                btn_reg_agregar.setEnabled(true);
                btn_reg_agregar.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_registrar_montoKeyPressed

    private void txt_registrar_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_registrar_montoKeyTyped
        c_varios.solo_precio(evt);
        c_varios.limitar_caracteres(evt, txt_registrar_monto, 10);
    }//GEN-LAST:event_txt_registrar_montoKeyTyped

    private void btn_reg_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reg_agregarActionPerformed
        c_pago.setFecha(c_varios.fecha_myql(txt_registrar_fecha.getText()));
        c_pago.setMonto(Double.parseDouble(txt_registrar_monto.getText()));
        c_pago.obtener_codigo();
        if (c_pago.registrar()) {
            btn_reg_salir.doClick();
        }
    }//GEN-LAST:event_btn_reg_agregarActionPerformed

    private void txt_registrar_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_registrar_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_registrar_monto.requestFocus();
        }
    }//GEN-LAST:event_txt_registrar_fechaKeyPressed

    private void t_pagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_pagosMouseClicked
        int fila_pago = t_pagos.getSelectedRow();
        btn_pago_eliminar.setEnabled(true);
        c_pago.setId_pago(Integer.parseInt(t_pagos.getValueAt(fila_pago, 0).toString()));
    }//GEN-LAST:event_t_pagosMouseClicked

    private void btn_pago_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pago_eliminarActionPerformed
        btn_pago_eliminar.setEnabled(false);
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Eliminar el Pago?");

        if (JOptionPane.OK_OPTION == confirmado) {
            c_pago.eliminar();
            c_pago.mostrar(t_pagos);
            verificar_deuda();
        }
    }//GEN-LAST:event_btn_pago_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_detalle;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_pago_agregar;
    private javax.swing.JButton btn_pago_eliminar;
    private javax.swing.JButton btn_pago_salir;
    private javax.swing.JButton btn_pagos;
    private javax.swing.JButton btn_reg_agregar;
    private javax.swing.JButton btn_reg_salir;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cbx_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JDialog jd_reg_pago;
    private javax.swing.JDialog jd_ver_pagos;
    private javax.swing.JLabel lbl_ayuda;
    private javax.swing.JTextField lbl_total_deuda;
    private javax.swing.JTable t_compras;
    private javax.swing.JTable t_pagos;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_pago_documento;
    private javax.swing.JTextField txt_pago_fecha;
    private javax.swing.JTextField txt_pago_proveedor;
    private javax.swing.JTextField txt_pago_total;
    private javax.swing.JFormattedTextField txt_registrar_fecha;
    private javax.swing.JTextField txt_registrar_monto;
    // End of variables declaration//GEN-END:variables
}
