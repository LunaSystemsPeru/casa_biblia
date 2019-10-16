/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_cliente;
import clases.cl_conectar;
import clases.cl_pedido;
import clases.cl_usuario;
import clases.cl_varios;
import clases.cl_venta;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import json.cl_json_entidad;
import models.m_mis_documentos;
import nicon.notify.core.Notification;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Flavio
 */
public class frm_reg_cobro_pedido extends javax.swing.JInternalFrame {

    cl_conectar c_conectar = new cl_conectar();

    cl_varios c_varios = new cl_varios();

    cl_pedido cl_pedido = new cl_pedido();
    cl_usuario cl_usuario = new cl_usuario();
    cl_cliente c_cliente = new cl_cliente();
    cl_venta cl_venta=new cl_venta();

    m_mis_documentos m_mis_documentos = new m_mis_documentos();

    public frm_reg_cobro_pedido() {
        initComponents();

        m_mis_documentos.cbx_documentos_venta(cbx_tido);
    }

    private void calcular_suma() {
        double efectivo = 0;
        if (c_varios.esDecimal(txt_efectivo.getText())) {
            efectivo = Double.parseDouble(txt_efectivo.getText());
        }
        double tarjeta = 0;
        if (c_varios.esDecimal(txt_tarjeta.getText())) {
            tarjeta = Double.parseDouble(txt_tarjeta.getText());
        }

        double total = cl_pedido.getTotal();
        double suma = efectivo + tarjeta;

        double faltante = 0;
        if (total > suma) {
            faltante = total - suma;
        }

        double vuelto = 0;
        if (suma > total) {
            vuelto = suma - total;
        }

        lbl_suma_pago.setText("S/ " + c_varios.formato_totales(suma));
        lbl_faltante.setText("S/ " + c_varios.formato_totales(faltante));
        lbl_vuelto.setText("S/ " + c_varios.formato_totales(vuelto));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tex_cod_pedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tex_vendedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_fecha_pedido = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_doc_cliente = new javax.swing.JTextField();
        cbx_tipo_venta = new javax.swing.JComboBox<>();
        cbx_tido = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nom_cliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbl_faltante = new javax.swing.JLabel();
        btn_finalizar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbl_vuelto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_suma_pago = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_efectivo = new javax.swing.JTextField();
        txt_tarjeta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        setTitle("Registro Cobro Pedido");

        jToolBar1.setFloatable(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar pedido"));

        jLabel1.setText("Nro. Pedido:");

        tex_cod_pedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tex_cod_pedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tex_cod_pedidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tex_cod_pedidoKeyTyped(evt);
            }
        });

        jLabel2.setText("Vendedor:");

        tex_vendedor.setEditable(false);

        jLabel3.setText("Fecha:");

        txt_fecha_pedido.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tex_cod_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tex_vendedor))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tex_cod_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_fecha_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tex_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos pedidos"));

        jLabel4.setText("Tipo Venta:");

        txt_doc_cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_doc_cliente.setEnabled(false);
        txt_doc_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_doc_clienteKeyPressed(evt);
            }
        });

        cbx_tipo_venta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VENTA", "SEPARACION" }));
        cbx_tipo_venta.setEnabled(false);
        cbx_tipo_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_ventaKeyPressed(evt);
            }
        });

        cbx_tido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cbx_tido.setEnabled(false);
        cbx_tido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tidoKeyPressed(evt);
            }
        });

        jLabel5.setText("Tipo Documento:");

        jLabel6.setText("Datos Cliente:");

        txt_nom_cliente.setEnabled(false);
        txt_nom_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nom_clienteKeyPressed(evt);
            }
        });

        jLabel8.setText("Enter para buscar documento.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_doc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbx_tido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_tipo_venta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(158, 158, 158))
                    .addComponent(txt_nom_cliente))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbx_tipo_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_tido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_doc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nom_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Faltante:");

        lbl_faltante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_faltante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_faltante.setText("S/ 0.00");

        btn_finalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_finalizar.setText("Pagar");
        btn_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finalizarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Vuelto:");

        lbl_vuelto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_vuelto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_vuelto.setText("S/ 0.00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Suma Pago:");

        lbl_suma_pago.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_suma_pago.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_suma_pago.setText("S/ 0.00");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Total:");

        lbl_total.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total.setText("S/ 100.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_finalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_vuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_faltante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_suma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbl_total))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_suma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_faltante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_finalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Forma de Pago"));

        jLabel17.setText("Vale:");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setEnabled(false);

        jLabel15.setText("Efectivo:");

        txt_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_efectivo.setEnabled(false);
        txt_efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_efectivoKeyPressed(evt);
            }
        });

        txt_tarjeta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tarjeta.setEnabled(false);
        txt_tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tarjetaKeyPressed(evt);
            }
        });

        jLabel16.setText("Tarjeta:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_efectivo)
                    .addComponent(txt_tarjeta)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tex_cod_pedidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_cod_pedidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cl_pedido.setId_pedido(Integer.parseInt(tex_cod_pedido.getText()));
            if (cl_pedido.validar_pedido()) {
                cl_usuario.setId_usuario(cl_pedido.getId_usuario());
                cl_usuario.validar_usuario();
                tex_vendedor.setText(cl_usuario.getNombre());
                txt_fecha_pedido.setText(cl_pedido.getFecha());
                lbl_total.setText("S/ " + c_varios.formato_totales(cl_pedido.getTotal()));
                cbx_tipo_venta.setEnabled(true);
                cbx_tipo_venta.requestFocus();

            } else {
                JOptionPane.showMessageDialog(this, "Codigo no encontrado", "Alerta", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_tex_cod_pedidoKeyPressed

    private void tex_cod_pedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tex_cod_pedidoKeyTyped
        c_varios.solo_numeros(evt);
    }//GEN-LAST:event_tex_cod_pedidoKeyTyped

    private void cbx_tipo_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_ventaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_tido.setEnabled(true);
            cbx_tido.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipo_ventaKeyPressed

    private void cbx_tidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_doc_cliente.setEnabled(true);
            txt_doc_cliente.requestFocus();
        }
    }//GEN-LAST:event_cbx_tidoKeyPressed

    private void txt_doc_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_doc_clienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int largo = txt_doc_cliente.getText().length();
            if (largo == 8 || largo == 11) {
                if (largo == 8) {
                    System.out.println("buscar dni");
                    c_cliente.setDocumento(txt_doc_cliente.getText().trim());
                    if (c_cliente.comprobar_cliente_doc()) {
                        txt_nom_cliente.setText(c_cliente.getNombre());
                    } else {
                        try {
                            Notification.show("Validar Cliente", "Se esta validando el DNI en RENIEC", Notification.NICON_LIGHT_THEME);
                            String json = cl_json_entidad.getJSONDNI_LUNASYSTEMS(c_cliente.getDocumento());
                            //Lo mostramos
                            String datos = cl_json_entidad.showJSONDNIL(json);
                            txt_nom_cliente.setText(datos);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "ERROR EN BUSCAR DNI " + e.getLocalizedMessage());
                        }
                    }
                }
                if (largo == 11) {
                    System.out.println("buscar ruc");
                    c_cliente.setDocumento(txt_doc_cliente.getText().trim());
                    if (c_cliente.comprobar_cliente_doc()) {
                        txt_nom_cliente.setText(c_cliente.getNombre());
                    } else {
                        try {
                            Notification.show("Validar Cliente", "Se esta validando el RUC en SUNAT", Notification.NICON_LIGHT_THEME);
                            String json = cl_json_entidad.getJSONRUC_LUNASYSTEMS(c_cliente.getDocumento());
                            //Lo mostramos
                            String[] datos = cl_json_entidad.showJSONRUC_JMP(json);
                            txt_nom_cliente.setText(datos[0]);
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "ERROR EN BUSCAR RUC " + e.getLocalizedMessage());
                        }
                    }
                }
                txt_efectivo.setEnabled(true);
                txt_efectivo.requestFocus();
            }
            if (largo == 0) {
                SecureRandom sr = new SecureRandom();
                String codigo = "SD" + (sr.nextInt(99999) + 1000);
                txt_doc_cliente.setText(codigo);
                txt_nom_cliente.setEnabled(true);
                txt_nom_cliente.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_doc_clienteKeyPressed

    private void txt_nom_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nom_clienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_nom_cliente.getText().length() > 15) {
                txt_efectivo.setEnabled(true);
                txt_efectivo.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nom_clienteKeyPressed

    private void txt_efectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efectivoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_efectivo.getText().length() > 0) {
                if (c_varios.esDecimal(txt_efectivo.getText())) {
                    calcular_suma();
                    txt_tarjeta.setEnabled(true);
                    txt_tarjeta.requestFocus();
                } else {
                    txt_efectivo.selectAll();
                    txt_efectivo.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_efectivoKeyPressed

    private void txt_tarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarjetaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_tarjeta.getText().length() > 0) {
                if (c_varios.esDecimal(txt_tarjeta.getText())) {
                    calcular_suma();
                    btn_finalizar.setEnabled(true);
                    btn_finalizar.requestFocus();
                } else {
                    txt_tarjeta.selectAll();
                    txt_tarjeta.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_tarjetaKeyPressed

    private void btn_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finalizarActionPerformed
        
        double cnt_tarjeta=Double.parseDouble(txt_tarjeta.getText());
        double cnt_efectivo=Double.parseDouble(txt_efectivo.getText());
        int cnt_afecto=0;
        int cnt_noafecto=0;
        
        int venta1=-1;
        int venta2=-1;
        
        
        
        if (cnt_afecto>0&&cnt_noafecto==0) {
            venta1= cl_venta.regirtar_venta(cl_pedido.getId_pedido(), c_cliente.getCodigo(), cl_usuario.getId_usuario(), cnt_efectivo, cnt_tarjeta, 1);
        }else if(cnt_noafecto>0&&cnt_afecto==0){
            venta2= cl_venta.regirtar_venta(cl_pedido.getId_pedido(), c_cliente.getCodigo(), cl_usuario.getId_usuario(), cnt_efectivo, cnt_tarjeta, 0);
        }else{
            venta1= cl_venta.regirtar_venta(cl_pedido.getId_pedido(), c_cliente.getCodigo(), cl_usuario.getId_usuario(), cnt_efectivo, cnt_tarjeta, 1);
            venta2= cl_venta.regirtar_venta(cl_pedido.getId_pedido(), c_cliente.getCodigo(), cl_usuario.getId_usuario(), cnt_efectivo, cnt_tarjeta, 0);
        }
        
        
    }//GEN-LAST:event_btn_finalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_finalizar;
    private javax.swing.JComboBox<String> cbx_tido;
    private javax.swing.JComboBox<String> cbx_tipo_venta;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_faltante;
    private javax.swing.JLabel lbl_suma_pago;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_vuelto;
    private javax.swing.JTextField tex_cod_pedido;
    private javax.swing.JTextField tex_vendedor;
    private javax.swing.JTextField txt_doc_cliente;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JTextField txt_fecha_pedido;
    private javax.swing.JTextField txt_nom_cliente;
    private javax.swing.JTextField txt_tarjeta;
    // End of variables declaration//GEN-END:variables
}
