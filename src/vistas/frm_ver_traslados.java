/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_productos_traslado;
import clases.cl_traslados;
import clases.cl_varios;
import forms.frm_reg_traslado;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import casa_biblia.frm_principal;

/**
 *
 * @author luis
 */
public class frm_ver_traslados extends javax.swing.JInternalFrame {

    cl_varios c_varios = new cl_varios();
    cl_traslados c_traslado = new cl_traslados();
    cl_productos_traslado c_detalle = new cl_productos_traslado();

    int id_almacen = frm_principal.c_almacen.getId();
    int fila_seleccionada = -1;

    /**
     * Creates new form frm_ver_ingresos
     */
    public frm_ver_traslados() {
        initComponents();

        String query = "select t.id_traslado, t.fecha, t.fecha_recepcion, ao.nombre as tienda_origen, ad.nombre as tienda_destino, "
                + "uo.username as usu_envia, ud.username as usu_recibe, t.u_envia, t.u_recibe, t.estado "
                + "from traslados as t "
                + "inner join almacen as ao on ao.id_almacen = t.a_origen "
                + "inner join almacen as ad on ad.id_almacen = t.a_destino "
                + "inner join usuarios as uo on uo.id_usuarios = t.u_envia "
                + "inner join usuarios as ud on ud.id_usuarios = t.u_recibe "
                + "where t.estado in(0,1) and (t.a_origen = '" + id_almacen + "' or t.a_destino = '" + id_almacen + "') "
                + "order by t.fecha desc, t.id_traslado desc";
        c_traslado.mostrar(t_traslados, query);
    }

    private void activar_botones() {
        String estado = t_traslados.getValueAt(fila_seleccionada, 7).toString();
        btn_modificar.setEnabled(false);
        btn_recibir.setEnabled(false);
        btn_ver_pdf.setEnabled(true);
        btn_eliminar.setEnabled(false);
        btn_detalle.setEnabled(true);
        if (estado.equals("PENDIENTE")) {
            btn_eliminar.setEnabled(true);
            btn_recibir.setEnabled(true);
        }
        if (estado.equals("BORRADOR")) {
            btn_eliminar.setEnabled(true);
            btn_recibir.setEnabled(false);
            btn_modificar.setEnabled(true);
        }

    }

    private void desactivar_botones() {
        btn_modificar.setEnabled(false);
        btn_recibir.setEnabled(false);
        btn_ver_pdf.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_detalle.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_detalle = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_traslados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        cbx_busqueda = new javax.swing.JComboBox<>();
        jToolBar1 = new javax.swing.JToolBar();
        btn_agregar = new javax.swing.JButton();
        btn_recibir = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btn_detalle = new javax.swing.JButton();
        btn_ver_pdf = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_cerrar = new javax.swing.JButton();

        jd_detalle.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jd_detalle.setTitle("Ver Detalle de Traslado");

        t_detalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(t_detalle);

        jLabel2.setText("Origen:");

        jLabel4.setText("Fecha:");

        javax.swing.GroupLayout jd_detalleLayout = new javax.swing.GroupLayout(jd_detalle.getContentPane());
        jd_detalle.getContentPane().setLayout(jd_detalleLayout);
        jd_detalleLayout.setHorizontalGroup(
            jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(jd_detalleLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jd_detalleLayout.setVerticalGroup(
            jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        setTitle("Ver Traslados");

        t_traslados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"201810-001", "15/10/2018", "PRINCIPAL", "CHIMBOTE 720", "loyagureng", "adelacruze", "PENDIENTE"},
                {"201810-002", "17/10/2018", "CHIMBOTE 720", "CHIMBOTE 760", "adelacruze", "loyangureng", "PENDIENTE"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID.", "Fecha", "T. Origen", "T. Destino", "Usu. Envia", "Usu. Recibe", "Estado"
            }
        ));
        t_traslados.setShowVerticalLines(false);
        t_traslados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_trasladosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_traslados);
        if (t_traslados.getColumnModel().getColumnCount() > 0) {
            t_traslados.getColumnModel().getColumn(0).setPreferredWidth(100);
            t_traslados.getColumnModel().getColumn(1).setPreferredWidth(100);
            t_traslados.getColumnModel().getColumn(2).setPreferredWidth(150);
            t_traslados.getColumnModel().getColumn(3).setPreferredWidth(150);
            t_traslados.getColumnModel().getColumn(4).setPreferredWidth(150);
            t_traslados.getColumnModel().getColumn(5).setPreferredWidth(150);
            t_traslados.getColumnModel().getColumn(6).setPreferredWidth(120);
        }

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        jLabel1.setText("Buscar por:");

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        cbx_busqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FECHA", "CODIGO", "PERIODO" }));
        cbx_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_busquedaActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_agregar);

        btn_recibir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_recibir.setText("Recibir");
        btn_recibir.setEnabled(false);
        btn_recibir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_recibir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_recibir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_recibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recibirActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_recibir);
        jToolBar1.add(jSeparator2);

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application_edit.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setEnabled(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_modificar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_eliminar);
        jToolBar1.add(jSeparator3);

        btn_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard_text.png"))); // NOI18N
        btn_detalle.setText("ver Detalle");
        btn_detalle.setEnabled(false);
        btn_detalle.setFocusable(false);
        btn_detalle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_detalle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detalleActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_detalle);

        btn_ver_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exportar.png"))); // NOI18N
        btn_ver_pdf.setText("ver PDF");
        btn_ver_pdf.setEnabled(false);
        btn_ver_pdf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ver_pdf.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_ver_pdf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_ver_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_pdfActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_ver_pdf);
        jToolBar1.add(jSeparator1);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_cerrar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        frm_reg_traslado.tipo_operacion = 1;
        frm_reg_traslado formulario = new frm_reg_traslado();
        c_varios.llamar_ventana(formulario);
        this.dispose();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void t_trasladosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_trasladosMouseClicked
        int contar_filas = t_traslados.getRowCount();
        if (contar_filas > -1) {
            fila_seleccionada = t_traslados.getSelectedRow();
            int id_traslado = Integer.parseInt(t_traslados.getValueAt(fila_seleccionada, 8).toString());
            c_traslado.setId_traslado(id_traslado);
            c_detalle.setId_traslado(id_traslado);
            activar_botones();
        }
    }//GEN-LAST:event_t_trasladosMouseClicked

    private void btn_recibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recibirActionPerformed
        if (fila_seleccionada > -1) {
            int id_traslado = Integer.parseInt(t_traslados.getValueAt(fila_seleccionada, 8).toString());
            c_traslado.setId_traslado(id_traslado);
            boolean existet = c_traslado.validar_datos();
            if (existet) {
                frm_reg_traslado.c_traslado.setId_traslado(id_traslado);
                frm_reg_traslado.tipo_operacion = 2;
                frm_reg_traslado formulario = new frm_reg_traslado();
                c_varios.llamar_ventana(formulario);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONA FILA");
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO UNA FILA");
        }
    }//GEN-LAST:event_btn_recibirActionPerformed

    private void cbx_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_busquedaActionPerformed
        txt_buscar.selectAll();
        txt_buscar.requestFocus();
    }//GEN-LAST:event_cbx_busquedaActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_buscar.getText();
            int tipo_buscar = cbx_busqueda.getSelectedIndex();
            String query = "";

            if (tipo_buscar == 0) {
                texto = c_varios.fecha_myql(texto);
                query = "select t.id_traslado, t.fecha, t.fecha_recepcion, ao.nombre as tienda_origen, ad.nombre as tienda_destino, "
                        + "uo.username as usu_envia, ud.username as usu_recibe, t.u_envia, t.u_recibe, t.estado "
                        + "from traslados as t "
                        + "inner join almacen as ao on ao.id_almacen = t.a_origen "
                        + "inner join almacen as ad on ad.id_almacen = t.a_destino "
                        + "inner join usuarios as uo on uo.id_usuarios = t.u_envia "
                        + "inner join usuarios as ud on ud.id_usuarios = t.u_recibe "
                        + "where t.fecha = '" + texto + "' and (t.a_origen = '" + id_almacen + "' or t.a_destino = '" + id_almacen + "') "
                        + "order by t.fecha desc, t.id_traslado desc";
            }

            if (tipo_buscar == 1) {
                query = "select t.id_traslado, t.fecha, t.fecha_recepcion, ao.nombre as tienda_origen, ad.nombre as tienda_destino, "
                        + "uo.username as usu_envia, ud.username as usu_recibe, t.u_envia, t.u_recibe, t.estado "
                        + "from traslados as t "
                        + "inner join almacen as ao on ao.id_almacen = t.a_origen "
                        + "inner join almacen as ad on ad.id_almacen = t.a_destino "
                        + "inner join usuarios as uo on uo.id_usuarios = t.u_envia "
                        + "inner join usuarios as ud on ud.id_usuarios = t.u_recibe "
                        + "where concat(lpad(t.id_traslado, 5, '0')) = '" + texto + "' and (t.a_origen = '" + id_almacen + "' or t.a_destino = '" + id_almacen + "') "
                        + "order by t.fecha desc, t.id_traslado desc";
            }

            if (tipo_buscar == 2) {
                query = "select t.id_traslado, t.fecha, t.fecha_recepcion, ao.nombre as tienda_origen, ad.nombre as tienda_destino, "
                        + "uo.username as usu_envia, ud.username as usu_recibe, t.u_envia, t.u_recibe, t.estado "
                        + "from traslados as t "
                        + "inner join almacen as ao on ao.id_almacen = t.a_origen "
                        + "inner join almacen as ad on ad.id_almacen = t.a_destino "
                        + "inner join usuarios as uo on uo.id_usuarios = t.u_envia "
                        + "inner join usuarios as ud on ud.id_usuarios = t.u_recibe "
                        + "where (t.a_origen = '" + id_almacen + "' or t.a_destino = '" + id_almacen + "') and concat(year(t.fecha), LPAD(month(t.fecha), 2, '0')) = '" + texto + "' "
                        + "order by t.fecha desc, t.id_traslado desc";
            }

            c_traslado.mostrar(t_traslados, query);
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        if (fila_seleccionada > -1) {
            int id_traslado = Integer.parseInt(t_traslados.getValueAt(fila_seleccionada, 8).toString());
            c_traslado.setId_traslado(id_traslado);
            boolean existet = c_traslado.validar_datos();
            if (existet) {
                frm_reg_traslado.c_traslado.setId_traslado(id_traslado);
                frm_reg_traslado.tipo_operacion = 3;
                frm_reg_traslado formulario = new frm_reg_traslado();
                c_varios.llamar_ventana(formulario);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONA FILA");
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO UNA FILA");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        frm_principal.c_permiso.setId_permiso(12);
        boolean permitido = frm_principal.c_permiso.validar();

        if (permitido) {

            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Eliminar el Traslado?");
            desactivar_botones();

            if (JOptionPane.OK_OPTION == confirmado) {
                int id_traslado = Integer.parseInt(t_traslados.getValueAt(fila_seleccionada, 8).toString());
                c_traslado.setId_traslado(id_traslado);
                c_detalle.setId_traslado(id_traslado);
                c_detalle.eliminar();

                c_traslado.eliminar();

                String query = "select t.id_traslado, t.fecha, t.fecha_recepcion, ao.nombre as tienda_origen, ad.nombre as tienda_destino, "
                        + "uo.username as usu_envia, ud.username as usu_recibe, t.u_envia, t.u_recibe, t.estado "
                        + "from traslados as t "
                        + "inner join almacen as ao on ao.id_almacen = t.a_origen "
                        + "inner join almacen as ad on ad.id_almacen = t.a_destino "
                        + "inner join usuarios as uo on uo.id_usuarios = t.u_envia "
                        + "inner join usuarios as ud on ud.id_usuarios = t.u_recibe "
                        + "where t.estado in(0,1) and (t.a_origen = '" + id_almacen + "' or t.a_destino = '" + id_almacen + "') "
                        + "order by t.fecha desc, t.id_traslado desc";
                c_traslado.mostrar(t_traslados, query);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Usted no tiene permiso para realizar esta operacion!!");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_ver_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_pdfActionPerformed
        int id_traslado = Integer.parseInt(t_traslados.getValueAt(fila_seleccionada, 0).toString());
        desactivar_botones();
        // TODO add your handling code here:
        File miDir = new File(".");
        try {
            Map<String, Object> parametros = new HashMap<>();
            String path = miDir.getCanonicalPath();
            String direccion = path + "//reports//subreports//";
            System.out.println(direccion);
            parametros.put("SUBREPORT_DIR", direccion);
            parametros.put("JRParameter.REPORT_LOCALE", Locale.ENGLISH);
            parametros.put("id_traslado", id_traslado);
            //c_varios.imp_reporte("rpt_documento_venta", parametros);
            c_varios.ver_reporte("report_traslado", parametros);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_ver_pdfActionPerformed

    private void btn_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detalleActionPerformed
        desactivar_botones();
        jd_detalle.setModal(true);
        jd_detalle.setSize(900, 600);
        jd_detalle.setLocationRelativeTo(null);
        txt_proveedor.setText(t_traslados.getValueAt(fila_seleccionada, 2).toString());
        txt_fecha.setText(t_traslados.getValueAt(fila_seleccionada, 1).toString());
        c_detalle.mostrar(t_detalle);
        jd_detalle.setVisible(true);
    }//GEN-LAST:event_btn_detalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_detalle;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_recibir;
    private javax.swing.JButton btn_ver_pdf;
    private javax.swing.JComboBox<String> cbx_busqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JDialog jd_detalle;
    private javax.swing.JTable t_detalle;
    private javax.swing.JTable t_traslados;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}
