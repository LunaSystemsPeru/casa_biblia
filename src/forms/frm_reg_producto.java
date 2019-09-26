/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_producto;
import clases.cl_productos_clasificacion;
import clases.cl_proveedor;
import clases.cl_varios;
import clases_autocomplete.cla_producto_clasificacion;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.m_empresas;
import models.m_producto_clasificacion;

/**
 *
 * @author luis
 */
public class frm_reg_producto extends javax.swing.JDialog {

    public static cl_producto c_producto = new cl_producto();

    cl_varios c_varios = new cl_varios();

    //autollenado clasificacon
    m_producto_clasificacion m_clasificacion = new m_producto_clasificacion();
    m_empresas m_empresa = new m_empresas();

    cl_productos_clasificacion c_clasificacion;

    static DefaultTableModel detalle;
    static int fila_seleccionada;

    public static boolean registrar;

    /**
     * Creates new form frm_reg_producto
     */
    public frm_reg_producto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        m_clasificacion.cbx_clasificaciones(cbx_clasificacion);

        if (registrar == false) {
            this.setTitle("Modificar Producto");
            cl_proveedor c_proveedor = new cl_proveedor();
            c_producto.validar_id();
            c_proveedor.setId_proveedor(c_producto.getId_proveedor());
            c_proveedor.cargar_datos();
            txt_descripcion.setText(c_producto.getDescripcion());
            txt_marca.setText(c_producto.getMarca());
            txt_cod_barra.setText(c_varios.formato_numero(c_producto.getComision()));
            txt_costo.setText(c_varios.formato_numero(c_producto.getCosto()));
            txt_precio.setText(c_varios.formato_numero(c_producto.getPrecio()));
            txt_proveedor.setText(c_proveedor.getRuc() + " | " + c_proveedor.getRazon_social());

            c_clasificacion = new cl_productos_clasificacion();
            c_clasificacion.setId_clasificacion(c_producto.getId_clasificacion());
            c_clasificacion.obtener_datos();
            System.out.println(c_clasificacion.getDescripcion());
            cbx_clasificacion.setEnabled(true);
            cbx_clasificacion.getModel().setSelectedItem(new cla_producto_clasificacion(c_clasificacion.getId_clasificacion(), c_clasificacion.getDescripcion()));

            txt_cod_barra.setEnabled(true);
            txt_marca.setEnabled(true);
            txt_costo.setEnabled(true);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        txt_marca = new javax.swing.JTextField();
        txt_costo = new javax.swing.JTextField();
        cbx_clasificacion = new javax.swing.JComboBox<>();
        txt_proveedor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_cod_barra = new javax.swing.JTextField();

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

        jLabel3.setText("Marca:");

        jLabel4.setText("Costo:");

        jLabel8.setText("Clasificacion:");

        jLabel9.setText("Proveedor:");

        txt_descripcion.setEnabled(false);
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });

        txt_marca.setEnabled(false);
        txt_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_marcaKeyPressed(evt);
            }
        });

        txt_costo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_costo.setEnabled(false);
        txt_costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_costoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_costoKeyPressed(evt);
            }
        });

        cbx_clasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        jLabel12.setText("Precio Venta:");

        txt_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio.setText("0.00");
        txt_precio.setEnabled(false);
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });

        jLabel7.setText("Cod. Barra");

        txt_cod_barra.setEnabled(false);
        txt_cod_barra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_barraKeyPressed(evt);
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
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_descripcion)
                    .addComponent(txt_proveedor)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbx_clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        c_producto.setMarca(txt_marca.getText());
        c_producto.setCod_barra(txt_cod_barra.getText());
        c_producto.setComision(0);
        c_producto.setPrecio(Double.parseDouble(txt_precio.getText()));
        c_producto.setCosto(Double.parseDouble(txt_costo.getText()));
        cla_producto_clasificacion cla_clasificacion = (cla_producto_clasificacion) cbx_clasificacion.getSelectedItem();
        c_producto.setId_clasificacion(cla_clasificacion.getId_clasificacion());

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
                txt_marca.setEnabled(true);
                txt_marca.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_descripcionKeyPressed

    private void txt_marcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_marcaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_marca.getText();
            if (texto.length() > 0) {
                txt_cod_barra.setEnabled(true);
                txt_cod_barra.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_marcaKeyPressed

    private void txt_costoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costoKeyTyped
        c_varios.solo_precio(evt);
        c_varios.limitar_caracteres(evt, txt_costo, 15);
    }//GEN-LAST:event_txt_costoKeyTyped

    private void txt_costoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_costo.getText();
            if (c_varios.esDecimal(texto)) {
                txt_precio.setEnabled(true);
                txt_precio.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_costoKeyPressed

    private void cbx_clasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_clasificacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_descripcion.setEnabled(true);
            txt_descripcion.requestFocus();
        }
    }//GEN-LAST:event_cbx_clasificacionKeyPressed

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_precio.getText();
            if (c_varios.esDecimal(texto)) {
                if (Double.parseDouble(texto) > 0) {
                    btn_guardar.setEnabled(true);
                    btn_guardar.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "INGRESE UN PRECIO CORRECTO");
                    txt_precio.selectAll();
                    txt_precio.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_precioKeyPressed

    private void cbx_clasificacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_clasificacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_clasificacionItemStateChanged

    private void txt_cod_barraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_barraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_costo.setEnabled(true);
            txt_costo.requestFocus();
        }
    }//GEN-LAST:event_txt_cod_barraKeyPressed

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
    private javax.swing.JComboBox<String> cbx_clasificacion;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txt_cod_barra;
    private javax.swing.JTextField txt_costo;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}