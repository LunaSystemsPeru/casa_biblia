/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_productos_clasificacion;
import clases.cl_varios;
import javax.swing.JOptionPane;
import casa_biblia.frm_principal;
import java.awt.event.KeyEvent;

/**
 *
 * @author luis
 */
public class frm_ver_clasificacion_productos extends javax.swing.JInternalFrame {

    cl_varios c_varios = new cl_varios();
    cl_productos_clasificacion c_clasificacion = new cl_productos_clasificacion();

    int fila_seleccionada;

    /**
     * Creates new form frm_ver_usuarios
     */
    public frm_ver_clasificacion_productos() {
        initComponents();
        c_clasificacion.mostrar(t_clasificacion);
    }

    private void activar_botones() {
        btn_modificar.setEnabled(true);
    }

    private void desactivar_botones() {
        btn_modificar.setEnabled(false);
    }

    private void activar_campos() {
        txt_reg_nombre.setEnabled(true);
        btn_j_grabar.setEnabled(true);
    }

    private void reiniciar_campos() {
        txt_reg_nombre.setEnabled(true);
        btn_j_grabar.setEnabled(false);
        txt_reg_nombre.setText("");
        txt_reg_nombre.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_reg_clasificacion = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txt_reg_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_reg_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btn_j_grabar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_j_cerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_clasificacion = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_cerrar = new javax.swing.JButton();

        jd_reg_clasificacion.setTitle("Registrar Usuario");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        txt_reg_id.setEnabled(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Codigo:");

        txt_reg_nombre.setEnabled(false);
        txt_reg_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_reg_nombreKeyPressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombre:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_reg_nombre)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_reg_id, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 493, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_reg_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_reg_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btn_j_grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_j_grabar.setText("Guardar");
        btn_j_grabar.setEnabled(false);
        btn_j_grabar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_j_grabar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_j_grabar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_j_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j_grabarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_j_grabar);
        jToolBar1.add(jSeparator1);

        btn_j_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_j_cerrar.setText("Cerrar");
        btn_j_cerrar.setFocusable(false);
        btn_j_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_j_cerrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_j_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_j_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j_cerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_j_cerrar);

        javax.swing.GroupLayout jd_reg_clasificacionLayout = new javax.swing.GroupLayout(jd_reg_clasificacion.getContentPane());
        jd_reg_clasificacion.getContentPane().setLayout(jd_reg_clasificacionLayout);
        jd_reg_clasificacionLayout.setHorizontalGroup(
            jd_reg_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_reg_clasificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_reg_clasificacionLayout.setVerticalGroup(
            jd_reg_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_reg_clasificacionLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setTitle("Ver Clasificacion de Productos");

        t_clasificacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_clasificacion.setGridColor(new java.awt.Color(255, 255, 255));
        t_clasificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_clasificacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_clasificacion);
        if (t_clasificacion.getColumnModel().getColumnCount() > 0) {
            t_clasificacion.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_clasificacion.getColumnModel().getColumn(1).setPreferredWidth(100);
            t_clasificacion.getColumnModel().getColumn(2).setPreferredWidth(350);
            t_clasificacion.getColumnModel().getColumn(3).setPreferredWidth(150);
            t_clasificacion.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        jToolBar2.setFloatable(false);
        jToolBar2.setOpaque(false);

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_agregar);

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application_edit.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setEnabled(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_modificar);
        jToolBar2.add(jSeparator2);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_cerrar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        jd_reg_clasificacion.setModal(true);
        jd_reg_clasificacion.setSize(721, 239);
        jd_reg_clasificacion.setLocationRelativeTo(null);
        reiniciar_campos();
        jd_reg_clasificacion.setVisible(true);
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_j_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j_cerrarActionPerformed
        jd_reg_clasificacion.dispose();
    }//GEN-LAST:event_btn_j_cerrarActionPerformed

    private void t_clasificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_clasificacionMouseClicked
        if (evt.getClickCount() == 2) {
            fila_seleccionada = t_clasificacion.getSelectedRow();
            activar_botones();
        }
    }//GEN-LAST:event_t_clasificacionMouseClicked

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        desactivar_botones();
        c_clasificacion.setId_clasificacion(Integer.parseInt(t_clasificacion.getValueAt(fila_seleccionada, 0).toString()));
        c_clasificacion.obtener_datos();
        txt_reg_nombre.setText(c_clasificacion.getDescripcion());
        txt_reg_id.setText(c_clasificacion.getId_clasificacion() + "");

        jd_reg_clasificacion.setModal(true);
        jd_reg_clasificacion.setSize(721, 239);
        jd_reg_clasificacion.setLocationRelativeTo(null);
        jd_reg_clasificacion.setVisible(true);
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_j_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j_grabarActionPerformed
        c_clasificacion.setDescripcion(txt_reg_nombre.getText());
        if (c_clasificacion.getId_clasificacion() != 0) {
            c_clasificacion.obtener_codigo();
            c_clasificacion.registrar();
        } else {
            c_clasificacion.modificar();
        }

        reiniciar_campos();
        jd_reg_clasificacion.dispose();

        c_clasificacion.mostrar(t_clasificacion);
    }//GEN-LAST:event_btn_j_grabarActionPerformed

    private void txt_reg_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_reg_nombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_reg_nombre.getText().length() > 0) {
                btn_j_grabar.setEnabled(true);
                btn_j_grabar.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_reg_nombreKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_j_cerrar;
    private javax.swing.JButton btn_j_grabar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JDialog jd_reg_clasificacion;
    private javax.swing.JTable t_clasificacion;
    private javax.swing.JTextField txt_reg_id;
    private javax.swing.JTextField txt_reg_nombre;
    // End of variables declaration//GEN-END:variables
}