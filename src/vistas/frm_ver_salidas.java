/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_ingresos;
import clases.cl_productos_ingresos;
import clases.cl_salida;
import clases.cl_varios;
import forms.frm_reg_ingreso;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import casa_biblia.frm_principal;
import clases_varios.Print_Nota_Salida;
import forms.frm_reg_salida;

/**
 *
 * @author luis
 */
public class frm_ver_salidas extends javax.swing.JInternalFrame {

    cl_varios c_varios = new cl_varios();
    cl_salida c_salida = new cl_salida();
    cl_productos_ingresos c_detalle = new cl_productos_ingresos();

    int fila_seleccionada;
    String query;

    public frm_ver_salidas() {
        initComponents();
        String periodo = c_varios.obtener_periodo();
        query = "SELECT s.id_salida, s.fecha, "
                + "s.id_almacen, s.id_salida, s.doc_destinatario,s.nom_destinatario, us.username AS nomusuario "
                + "FROM salida AS s "
                + "INNER JOIN usuarios AS us ON us.id_usuarios=s.id_usuarios";
        c_salida.mostrar(t_salidas, query);
    }

    private void activar_botones() {
        btn_detalle.setEnabled(true);
        btn_pdf.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_imprimir.setEnabled(true);
    }

    private void desactivar_botones() {
        btn_detalle.setEnabled(false);
        btn_pdf.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_imprimir.setEnabled(false);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        txt_documento = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_salidas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        cbx_buscar = new javax.swing.JComboBox<>();
        jToolBar1 = new javax.swing.JToolBar();
        btn_agregar = new javax.swing.JButton();
        btn_detalle = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_pdf = new javax.swing.JButton();
        btn_imprimir = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_cerrar = new javax.swing.JButton();

        jd_detalle.setTitle("Ver Detalle de Ingreso de Mercaderia");

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

        jLabel2.setText("Proveedor:");

        jLabel3.setText("Documento:");

        jLabel4.setText("Fecha:");

        javax.swing.GroupLayout jd_detalleLayout = new javax.swing.GroupLayout(jd_detalle.getContentPane());
        jd_detalle.getContentPane().setLayout(jd_detalleLayout);
        jd_detalleLayout.setHorizontalGroup(
            jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                    .addGroup(jd_detalleLayout.createSequentialGroup()
                        .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jd_detalleLayout.createSequentialGroup()
                                .addComponent(txt_documento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_proveedor))))
                .addContainerGap())
        );
        jd_detalleLayout.setVerticalGroup(
            jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        setTitle("Ver Salidas");

        t_salidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"201810-001", "15/10/2018", "FT / 001-00002451", "10469932091 | OYANGUREN GIRON LUIS ENRIQUE", "loyagureng"},
                {"201810-002", "17/10/2018", "BL / 002-00326541", "10718449559 | DE LA CRUZ ESPINOZA ANDREA TATIANA", "loyangureng"},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID.", "Fecha", "Documento", "Proveedor", "Usuario"
            }
        ));
        t_salidas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_salidas.setShowVerticalLines(false);
        t_salidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_salidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_salidas);
        if (t_salidas.getColumnModel().getColumnCount() > 0) {
            t_salidas.getColumnModel().getColumn(0).setPreferredWidth(100);
            t_salidas.getColumnModel().getColumn(1).setPreferredWidth(100);
            t_salidas.getColumnModel().getColumn(2).setPreferredWidth(150);
            t_salidas.getColumnModel().getColumn(3).setPreferredWidth(450);
            t_salidas.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jLabel1.setText("Buscar por:");

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        cbx_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PERIODO", "FECHA", "PROVEEDOR", "NRO DOCUMENTO" }));
        cbx_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_buscarActionPerformed(evt);
            }
        });
        cbx_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_buscarKeyPressed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_agregar);

        btn_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/blog.png"))); // NOI18N
        btn_detalle.setText("Ver Detalle");
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
        jToolBar1.add(jSeparator2);

        btn_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exportar.png"))); // NOI18N
        btn_pdf.setText("Ver PDF");
        btn_pdf.setEnabled(false);
        btn_pdf.setFocusable(false);
        btn_pdf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pdf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pdfActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_pdf);

        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printer.png"))); // NOI18N
        btn_imprimir.setText("Imprimir");
        btn_imprimir.setEnabled(false);
        btn_imprimir.setFocusable(false);
        btn_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_imprimir);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_eliminar);
        jToolBar1.add(jSeparator1);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cbx_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        this.dispose();
        frm_reg_salida formulario = new frm_reg_salida();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String buscar = txt_buscar.getText().trim();
            int tipo_busqueda = cbx_buscar.getSelectedIndex();

            if (tipo_busqueda == 0) {
                query = "select i.id_ingreso, i.fecha, p.nro_documento, p.razon_social, ds.abreviado, i.serie, i.numero, i.total, u.username "
                        + "from ingresos as i "
                        + "inner join proveedor as p on p.id_proveedor = i.id_proveedor "
                        + "inner join documentos_sunat as ds on ds.id_tido = i.id_tido "
                        + "inner join usuarios as u on u.id_usuarios = i.id_usuarios "
                        + "where concat(year(i.fecha), LPAD(month(i.fecha), 2, 0)) = '" + buscar + "' "
                        + "order by i.fecha asc, i.numero asc";
            }

            if (tipo_busqueda == 1) {
                buscar = c_varios.fecha_myql(buscar);
                query = "select i.id_ingreso, i.fecha, p.nro_documento, p.razon_social, ds.abreviado, i.serie, i.numero, i.total, u.username "
                        + "from ingresos as i "
                        + "inner join proveedor as p on p.id_proveedor = i.id_proveedor "
                        + "inner join documentos_sunat as ds on ds.id_tido = i.id_tido "
                        + "inner join usuarios as u on u.id_usuarios = i.id_usuarios "
                        + "where i.fecha = '" + buscar + "' "
                        + "order by i.numero asc";
            }

            if (tipo_busqueda == 2) {
                query = "select i.id_ingreso, i.fecha, p.nro_documento, p.razon_social, ds.abreviado, i.serie, i.numero, i.total, u.username "
                        + "from ingresos as i "
                        + "inner join proveedor as p on p.id_proveedor = i.id_proveedor "
                        + "inner join documentos_sunat as ds on ds.id_tido = i.id_tido "
                        + "inner join usuarios as u on u.id_usuarios = i.id_usuarios "
                        + "where p.nro_documento = '" + buscar + "' or p.razon_social like '%" + buscar + "%' "
                        + "order by i.fecha asc";
            }
            if (tipo_busqueda == 3) {
                query = "select i.id_ingreso, i.fecha, p.nro_documento, p.razon_social, ds.abreviado, i.serie, i.numero, i.total, u.username "
                        + "from ingresos as i "
                        + "inner join proveedor as p on p.id_proveedor = i.id_proveedor "
                        + "inner join documentos_sunat as ds on ds.id_tido = i.id_tido "
                        + "inner join usuarios as u on u.id_usuarios = i.id_usuarios "
                        + "where i.numero = '" + buscar + "' "
                        + "order by i.fecha asc";
            }
            c_salida.mostrar(t_salidas, query);
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void cbx_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_buscarActionPerformed
        txt_buscar.selectAll();
        txt_buscar.requestFocus();
    }//GEN-LAST:event_cbx_buscarActionPerformed

    private void t_salidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_salidasMouseClicked
        if (evt.getClickCount() == 2) {
            activar_botones();
            fila_seleccionada = t_salidas.getSelectedRow();
            c_salida.setId_salida(Integer.parseInt(t_salidas.getValueAt(fila_seleccionada, 0).toString()));
            c_detalle.setId_ingreso(c_salida.getId_salida());
        }
    }//GEN-LAST:event_t_salidasMouseClicked

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        frm_principal.c_permiso.setId_permiso(5);
        boolean permitido = frm_principal.c_permiso.validar();

        if (permitido) {
            if (fila_seleccionada > -1) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Eliminar el Documento de Ingreso de Mercaderia?");
                desactivar_botones();

                if (JOptionPane.OK_OPTION == confirmado) {
                    c_detalle.setId_ingreso(c_salida.getId_salida());
                    c_detalle.eliminar();
                    c_salida.anular();

                    c_salida.mostrar(t_salidas, query);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No ha seleccionado una fila");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Usted no tiene permiso para realizar esta operacion!!");
        }

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detalleActionPerformed
        desactivar_botones();
        jd_detalle.setModal(true);
        jd_detalle.setSize(900, 600);
        jd_detalle.setLocationRelativeTo(null);
        txt_proveedor.setText(t_salidas.getValueAt(fila_seleccionada, 3).toString());
        txt_documento.setText(t_salidas.getValueAt(fila_seleccionada, 2).toString());
        txt_fecha.setText(t_salidas.getValueAt(fila_seleccionada, 1).toString());
        c_detalle.mostrar_detalle(t_detalle);
        jd_detalle.setVisible(true);
    }//GEN-LAST:event_btn_detalleActionPerformed

    private void cbx_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_buscar.selectAll();
            txt_buscar.requestFocus();
        }
    }//GEN-LAST:event_cbx_buscarKeyPressed

    private void btn_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pdfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pdfActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
        desactivar_botones();
        int id_salida = Integer.parseInt(t_salidas.getValueAt(fila_seleccionada, 0).toString());
        Print_Nota_Salida print = new Print_Nota_Salida();
        print.setId_salida(id_salida);
        print.generar_ticket();
    }//GEN-LAST:event_btn_imprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_detalle;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JButton btn_pdf;
    private javax.swing.JComboBox<String> cbx_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JDialog jd_detalle;
    private javax.swing.JTable t_detalle;
    private javax.swing.JTable t_salidas;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_documento;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}
