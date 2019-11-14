/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_producto;
import clases.cl_productos_clasificacion;
import clases.cl_productos_sub_clasificacion;
import clases.cl_proveedor;
import clases.cl_varios;
import clases_autocomplete.cla_producto_clasificacion;
import clases_autocomplete.cla_unidad_medida;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import models.m_empresas;
import models.m_producto_clasificacion;
import models.m_producto_subclasificacion;
import models.m_unidades;

/**
 *
 * @author luis
 */
public class frm_reg_producto extends javax.swing.JDialog {

    //clases principales    
    public static cl_producto c_producto = new cl_producto();

    //clases secundarias
    cl_varios c_varios = new cl_varios();
    cl_productos_clasificacion c_clasificacion;
    cl_productos_sub_clasificacion c_subclasificacion;

    //autollenado clasificacon
    m_producto_clasificacion m_clasificacion = new m_producto_clasificacion();
    m_producto_subclasificacion m_subclasificacion = new m_producto_subclasificacion();
    m_empresas m_empresa = new m_empresas();
    m_unidades m_unidad = new m_unidades();

    //variables publicas    
    static DefaultTableModel detalle;
    static int fila_seleccionada;
    public static boolean registrar;
    boolean modificar_presentacion = false;

    /**
     * Creates new form frm_reg_producto
     */
    public frm_reg_producto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        m_clasificacion.cbx_clasificaciones(cbx_clasificacion);

        if (!registrar) {
            this.setTitle("Modificar Producto");

            cl_proveedor c_proveedor = new cl_proveedor();

            c_producto.validar_id();

            //cargar datos
            c_proveedor.setId_proveedor(c_producto.getId_proveedor());
            c_proveedor.cargar_datos();

            txt_descripcion.setText(c_producto.getDescripcion());
            txt_cod_barra.setText(c_producto.getCod_externo());
            txt_precio_minimo.setText(c_varios.formato_numero(c_producto.getPrecio()));
            txt_proveedor.setText(c_proveedor.getRuc() + " | " + c_proveedor.getRazon_social());

            //obtener modelo clasificacion
            c_subclasificacion = new cl_productos_sub_clasificacion();
            c_subclasificacion.setId_subclasificacion(c_producto.getId_sub_clasificacion());
            c_subclasificacion.validar_datos();

            c_clasificacion = new cl_productos_clasificacion();
            c_clasificacion.setId_clasificacion(c_subclasificacion.getId_clasificacion());
            c_clasificacion.obtener_datos();

            cbx_clasificacion.setEnabled(true);
            cbx_clasificacion.getModel().setSelectedItem(new cla_producto_clasificacion(c_clasificacion.getId_clasificacion(), c_clasificacion.getDescripcion()));

            m_subclasificacion.llenar_combobox(cbx_sub_clasificacion, c_subclasificacion.getId_clasificacion());
            cbx_sub_clasificacion.setEnabled(true);
            cbx_sub_clasificacion.getModel().setSelectedItem(new cla_producto_clasificacion(c_subclasificacion.getId_subclasificacion(), c_subclasificacion.getNombre()));

            txt_descripcion.setEnabled(true);
            txt_cod_barra.setEnabled(true);
            cbx_afecto.setEnabled(true);
            txt_precio_minimo.setEnabled(true);
            btn_guardar.setEnabled(true);
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

        jToolBar1 = new javax.swing.JToolBar();
        btn_guardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        cbx_clasificacion = new javax.swing.JComboBox<>();
        txt_proveedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_cod_barra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbx_tipo_producto = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_precio_minimo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbx_sub_clasificacion = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbx_afecto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar Producto");
        setResizable(false);

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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jLabel1.setText("Descripcion:");

        jLabel8.setText("Clasificacion:");

        jLabel9.setText("Proveedor:");

        txt_descripcion.setEnabled(false);
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });

        cbx_clasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_clasificacion.setEnabled(false);
        cbx_clasificacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_clasificacionItemStateChanged(evt);
            }
        });
        cbx_clasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_clasificacionKeyPressed(evt);
            }
        });

        txt_proveedor.setEnabled(false);

        jLabel7.setText("Cod. Externo");

        txt_cod_barra.setEnabled(false);
        txt_cod_barra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_barraKeyPressed(evt);
            }
        });

        jLabel2.setText("Tipo Producto:");

        cbx_tipo_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BIEN" }));
        cbx_tipo_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_productoKeyPressed(evt);
            }
        });

        jLabel11.setText("Precio:");

        txt_precio_minimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio_minimo.setEnabled(false);
        txt_precio_minimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precio_minimoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_minimoKeyTyped(evt);
            }
        });

        jLabel4.setText("Sub Clasificacion:");

        cbx_sub_clasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_sub_clasificacion.setEnabled(false);
        cbx_sub_clasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_sub_clasificacionKeyPressed(evt);
            }
        });

        jLabel13.setText("Afecto al IGV:");

        cbx_afecto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));
        cbx_afecto.setEnabled(false);
        cbx_afecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_afectoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_tipo_producto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbx_clasificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbx_sub_clasificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_descripcion)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_afecto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precio_minimo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 168, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_sub_clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_afecto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio_minimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        c_producto.setDescripcion(txt_descripcion.getText());
        c_producto.setCod_externo(txt_cod_barra.getText());
        c_producto.setCosto(0);
        c_producto.setPrecio(Double.parseDouble(txt_precio_minimo.getText()));

        int tipo_producto = cbx_tipo_producto.getSelectedIndex();
        c_producto.setTipo_producto(tipo_producto);

        int afecto_igv = cbx_afecto.getSelectedIndex();
        c_producto.setAfecto_igv(afecto_igv);

        cla_producto_clasificacion cla_clasificacion = (cla_producto_clasificacion) cbx_clasificacion.getSelectedItem();
        c_producto.setId_sub_clasificacion(cla_clasificacion.getId_clasificacion());

        boolean realizado = false;

        if (registrar) {
            c_producto.obtener_codigo();
            realizado = c_producto.registrar();
        } else {
            realizado = c_producto.modificar();
        }

        if (realizado) {
            this.dispose();
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void txt_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_descripcion.getText();
            if (texto.length() > 0) {
                txt_cod_barra.setEnabled(true);
                txt_cod_barra.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_descripcionKeyPressed

    private void cbx_clasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_clasificacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cla_producto_clasificacion cla_clasificacion = (cla_producto_clasificacion) cbx_clasificacion.getSelectedItem();
            m_subclasificacion.llenar_combobox(cbx_sub_clasificacion, cla_clasificacion.getId_clasificacion());
            cbx_sub_clasificacion.setEnabled(true);
            cbx_sub_clasificacion.requestFocus();
        }
    }//GEN-LAST:event_cbx_clasificacionKeyPressed

    private void cbx_clasificacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_clasificacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_clasificacionItemStateChanged

    private void txt_cod_barraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_barraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_afecto.setEnabled(true);
            cbx_afecto.requestFocus();
        }
    }//GEN-LAST:event_txt_cod_barraKeyPressed

    private void cbx_tipo_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int tipo_producto = cbx_tipo_producto.getSelectedIndex();
            if (tipo_producto == 0) {
                cbx_clasificacion.setEnabled(true);
                cbx_clasificacion.requestFocus();
            } else {
                txt_descripcion.setEnabled(true);
                txt_descripcion.requestFocus();
            }
        }
    }//GEN-LAST:event_cbx_tipo_productoKeyPressed

    private void txt_precio_minimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_minimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_precio_minimo.getText();
            if (c_varios.esDecimal(texto)) {
                btn_guardar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txt_precio_minimoKeyPressed

    private void txt_precio_minimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_minimoKeyTyped
        c_varios.solo_precio(evt);
    }//GEN-LAST:event_txt_precio_minimoKeyTyped

    private void cbx_afectoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_afectoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_precio_minimo.setEnabled(true);
            txt_precio_minimo.requestFocus();
        }
    }//GEN-LAST:event_cbx_afectoKeyPressed

    private void cbx_sub_clasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_sub_clasificacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_descripcion.setEnabled(true);
            txt_descripcion.requestFocus();
        }
    }//GEN-LAST:event_cbx_sub_clasificacionKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_reg_producto dialog = new frm_reg_producto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JComboBox<String> cbx_afecto;
    private javax.swing.JComboBox<String> cbx_clasificacion;
    private javax.swing.JComboBox<String> cbx_sub_clasificacion;
    private javax.swing.JComboBox<String> cbx_tipo_producto;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txt_cod_barra;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_precio_minimo;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}
