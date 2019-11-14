/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_productos_almacen;
import clases_autocomplete.cla_almacen;
import java.awt.event.KeyEvent;
import models.m_almacen;

/**
 *
 * @author luis
 */
public class frm_ver_productos_tiendas extends javax.swing.JInternalFrame {

    cl_productos_almacen c_mis_productos = new cl_productos_almacen();
    m_almacen m_almacen = new m_almacen();

    public frm_ver_productos_tiendas() {
        initComponents();
        String query = "select pa.id_producto, p.descripcion, p.cod_externo, p.precio, pa.cactual, a.nombre as nalmacen "
                + "from productos_almacen as pa "
                + "inner join productos as p on p.id_producto = pa.id_producto "
                + "inner join almacen as a on a.id_almacen = pa.id_almacen "
                + "order by p.descripcion asc , p.cod_externo asc "
                + "limit 0";
        c_mis_productos.productos_tiendas(query, t_productos);

        m_almacen.cbx_todos_almacenes(cbx_tiendas);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbx_tiendas = new javax.swing.JComboBox<>();
        txt_buscar = new javax.swing.JTextField();
        btn_cerrar = new javax.swing.JButton();
        chx_todos = new javax.swing.JCheckBox();

        setTitle("Ver Productos en Tiendas");

        t_productos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(t_productos);

        jLabel1.setText("Buscar en: ");

        cbx_tiendas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));
        cbx_tiendas.setEnabled(false);
        cbx_tiendas.setFocusable(false);
        cbx_tiendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tiendasActionPerformed(evt);
            }
        });

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        chx_todos.setSelected(true);
        chx_todos.setText("Todos");
        chx_todos.setFocusable(false);
        chx_todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chx_todosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chx_todos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbx_tiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tiendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chx_todos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_buscar.getText();
            String query;
            if (chx_todos.isSelected()) {
                query = "select pa.id_producto, p.descripcion, p.cod_externo, p.precio, pa.cactual, a.nombre as nalmacen "
                        + "from productos_almacen as pa "
                        + "inner join productos as p on p.id_producto = pa.id_producto "
                        + "inner join almacen as a on a.id_almacen = pa.id_almacen "
                        + "where concat (descripcion, ' ',  cod_externo) like '%" + texto + "%' "
                        + "order by p.descripcion asc , p.cod_externo asc ";
            } else {
                cla_almacen o_almacen = (cla_almacen) cbx_tiendas.getSelectedItem();
                int id_almacen = o_almacen.getId_almacen();

                query = "select pa.id_producto, p.descripcion, p.cod_externo, p.precio, pa.cactual, a.nombre as nalmacen "
                        + "from productos_almacen as pa "
                        + "inner join productos as p on p.id_producto = pa.id_producto "
                        + "inner join almacen as a on a.id_almacen = pa.id_almacen "
                        + "where concat (descripcion, ' ', cod_externo) like '%" + texto + "%' and pa.id_almacen = '" + id_almacen + "' "
                        + "order by p.descripcion asc , p.cod_externo asc ";

            }

            c_mis_productos.productos_tiendas(query, t_productos);
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void chx_todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chx_todosActionPerformed
        if (chx_todos.isSelected()) {
            cbx_tiendas.setEnabled(false);
            txt_buscar.selectAll();
            txt_buscar.requestFocus();
        } else {
            cbx_tiendas.setEnabled(true);
            cbx_tiendas.requestFocus();
        }
    }//GEN-LAST:event_chx_todosActionPerformed

    private void cbx_tiendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tiendasActionPerformed
        txt_buscar.selectAll();
        txt_buscar.requestFocus();
    }//GEN-LAST:event_cbx_tiendasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JComboBox<String> cbx_tiendas;
    private javax.swing.JCheckBox chx_todos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_productos;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
