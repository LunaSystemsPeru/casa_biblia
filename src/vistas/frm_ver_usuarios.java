/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_almacen;
import clases.cl_usuario;
import clases.cl_varios;
import clases_autocomplete.cla_almacen;
import forms.frm_reg_permiso_usuario;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import json.cl_json_entidad;
import models.m_almacen;
import org.json.simple.parser.ParseException;
import casa_biblia.frm_principal;

/**
 *
 * @author luis
 */
public class frm_ver_usuarios extends javax.swing.JInternalFrame {

    cl_varios c_varios = new cl_varios();
    cl_usuario c_usuario = new cl_usuario();

    cl_almacen c_almacen = new cl_almacen();

    int fila_seleccionada;

    /**
     * Creates new form frm_ver_usuarios
     */
    public frm_ver_usuarios() {
        initComponents();
        mostrar_tablar();
    }

    private void mostrar_tablar() {
        String query = "select u.id_usuarios, u.username, u.nro_documento, u.datos, u.estado, a.nombre as nalmacen "
                + "from usuarios as u "
                + "inner join almacen as a on a.id_almacen = u.id_almacen "
                + "where u.estado = 1 "
                + "order by datos asc";
        c_usuario.mostrar(t_usuarios, query);
        
        cargar_permisos();
    }
    
    private void cargar_permisos() {
        frm_principal.c_permiso.setId_permiso(19);
        boolean permitido = frm_principal.c_permiso.validar();

        if (permitido) {
            btn_agregar.setEnabled(true);
        } else {
            btn_agregar.setEnabled(false);
        }
    }

    private void activar_botones() {
        btn_modificar.setEnabled(true);
        btn_permisos.setEnabled(true);
    }

    private void desactivar_botones() {
        btn_modificar.setEnabled(false);
        btn_permisos.setEnabled(false);
    }

    private void activar_campos() {
        chx_estado.setEnabled(true);
        txt_j_contrasena.setEnabled(true);
        txt_j_usuario.setEnabled(true);
        txt_j_email.setEnabled(true);
        txt_j_documento.setEnabled(true);
        txt_j_celular.setEnabled(true);
        txt_j_datos.setEnabled(true);
        cbx_tiendas.setEnabled(true);
        btn_j_grabar.setEnabled(true);
        txt_j_documento.requestFocus();
    }

    private void reiniciar_campos() {
        chx_estado.setEnabled(false);
        txt_j_contrasena.setEnabled(false);
        txt_j_usuario.setEnabled(false);
        txt_j_email.setEnabled(false);
        txt_j_documento.setEnabled(true);
        txt_j_celular.setEnabled(false);
        txt_j_datos.setEnabled(false);
        cbx_tiendas.setEnabled(false);
        btn_j_grabar.setEnabled(false);

        txt_j_contrasena.setText("");
        txt_j_usuario.setText("");
        txt_j_email.setText("");
        txt_j_celular.setText("");
        txt_j_datos.setText("");
        txt_j_documento.setText("");

        txt_j_documento.requestFocus();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_reg_usuario = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_j_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_j_documento = new javax.swing.JTextField();
        txt_j_datos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_j_email = new javax.swing.JTextField();
        txt_j_celular = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_tiendas = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        chx_estado = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_j_usuario = new javax.swing.JTextField();
        txt_j_contrasena = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btn_j_grabar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_j_cerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_usuarios = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_permisos = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_busqueda = new javax.swing.JTextField();
        cbx_estado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        jd_reg_usuario.setTitle("Registrar Usuario");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Email");

        jLabel7.setText("enter para obtener datos");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Celular");

        txt_j_id.setEnabled(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Codigo:");

        txt_j_documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_j_documentoKeyPressed(evt);
            }
        });

        txt_j_datos.setEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("DNI");

        txt_j_email.setEnabled(false);
        txt_j_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_j_emailKeyPressed(evt);
            }
        });

        txt_j_celular.setEnabled(false);
        txt_j_celular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_j_celularKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_j_celularKeyPressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Datos");

        jLabel9.setText("Tienda:");

        cbx_tiendas.setEnabled(false);
        cbx_tiendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tiendasActionPerformed(evt);
            }
        });
        cbx_tiendas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tiendasKeyPressed(evt);
            }
        });

        jLabel11.setText("Dar de Baja:");

        chx_estado.setText("Desactivar Usuario");
        chx_estado.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_j_datos)
                    .addComponent(txt_j_email)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_j_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_j_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_j_id, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(cbx_tiendas, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chx_estado))
                        .addGap(0, 244, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbx_tiendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Acceso"));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Contraseña");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Usuario");

        txt_j_usuario.setEnabled(false);
        txt_j_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_j_usuarioKeyPressed(evt);
            }
        });

        txt_j_contrasena.setEnabled(false);
        txt_j_contrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_j_contrasenaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_j_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout jd_reg_usuarioLayout = new javax.swing.GroupLayout(jd_reg_usuario.getContentPane());
        jd_reg_usuario.getContentPane().setLayout(jd_reg_usuarioLayout);
        jd_reg_usuarioLayout.setHorizontalGroup(
            jd_reg_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_reg_usuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_reg_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_reg_usuarioLayout.setVerticalGroup(
            jd_reg_usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_reg_usuarioLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setTitle("Ver Usuarios");

        t_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "loyangureng", "4699329 | LUIS ENRIQUE OYANGUREN GIRON", "PRINCIPAL", "ACTIVO"},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID.", "Alias", "Datos", "Tienda", "Estado"
            }
        ));
        t_usuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_usuarios.setGridColor(new java.awt.Color(255, 255, 255));
        t_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_usuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_usuarios);
        if (t_usuarios.getColumnModel().getColumnCount() > 0) {
            t_usuarios.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_usuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
            t_usuarios.getColumnModel().getColumn(2).setPreferredWidth(350);
            t_usuarios.getColumnModel().getColumn(3).setPreferredWidth(150);
            t_usuarios.getColumnModel().getColumn(4).setPreferredWidth(80);
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

        btn_permisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application_add.png"))); // NOI18N
        btn_permisos.setText("Permisos");
        btn_permisos.setEnabled(false);
        btn_permisos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_permisos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_permisosActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_permisos);

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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        jLabel10.setText("Buscar:");

        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
        });

        cbx_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVOS", "DE BAJA" }));
        cbx_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_estadoActionPerformed(evt);
            }
        });

        jLabel12.setText("Seleccionar Estados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        frm_principal.c_permiso.setId_permiso(19);
        boolean permitido = frm_principal.c_permiso.validar();

        if (permitido) {
            jd_reg_usuario.setModal(true);
            jd_reg_usuario.setSize(721, 500);
            jd_reg_usuario.setLocationRelativeTo(null);
            reiniciar_campos();
            m_almacen m_almacen = new m_almacen();
            m_almacen.cbx_todos_almacenes(cbx_tiendas);
            c_usuario.setId_usuario(0);
            jd_reg_usuario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usted no tiene permiso para realizar esta operacion!!");
        }
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_j_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j_cerrarActionPerformed
        jd_reg_usuario.dispose();
    }//GEN-LAST:event_btn_j_cerrarActionPerformed

    private void txt_j_documentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_documentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String documento = txt_j_documento.getText();
            if (documento.length() == 8) {
                try {
                    String json = cl_json_entidad.getJSONDNI_LUNASYSTEMS(documento);
                    //Lo mostramos
                    String datos = cl_json_entidad.showJSONDNIL(json);
                    txt_j_datos.setText(datos);
                    txt_j_datos.setEnabled(true);
                    txt_j_email.setEnabled(true);
                    txt_j_email.requestFocus();

                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "ERROR EN BUSCAR RUC " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_txt_j_documentoKeyPressed

    private void t_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_usuariosMouseClicked
        if (evt.getClickCount() == 2) {
            fila_seleccionada = t_usuarios.getSelectedRow();
            activar_botones();
        }
    }//GEN-LAST:event_t_usuariosMouseClicked

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        desactivar_botones();
        frm_principal.c_permiso.setId_permiso(20);
        boolean permitido = frm_principal.c_permiso.validar();

        if (permitido) {
            c_usuario.setId_usuario(Integer.parseInt(t_usuarios.getValueAt(fila_seleccionada, 0).toString()));
            c_usuario.validar_usuario();

            jd_reg_usuario.setModal(true);
            jd_reg_usuario.setSize(721, 500);
            jd_reg_usuario.setLocationRelativeTo(null);

            m_almacen m_almacen = new m_almacen();
            m_almacen.cbx_todos_almacenes(cbx_tiendas);

            activar_campos();

            txt_j_datos.setText(c_usuario.getNombre());
            txt_j_celular.setText(c_usuario.getCelular());
            txt_j_id.setText(c_usuario.getId_usuario() + "");
            txt_j_documento.setText(c_usuario.getDocumento());
            txt_j_email.setText(c_usuario.getEmail());
            txt_j_usuario.setText(c_usuario.getUsername());
            txt_j_contrasena.setText(c_usuario.getPassword());
            c_almacen.setId(c_usuario.getId_almacen());
            c_almacen.validar_almacen();
            cbx_tiendas.getModel().setSelectedItem(new cla_almacen(c_almacen.getId(), c_almacen.getNombre()));
            if (c_usuario.getEstado() == 1) {
                chx_estado.setSelected(false);
            } else {
                chx_estado.setSelected(true);
            }

            jd_reg_usuario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usted no tiene permiso para realizar esta operacion!!");
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_j_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_emailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_j_email.getText().length() > 0) {
                txt_j_celular.setEnabled(true);
                txt_j_celular.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_j_emailKeyPressed

    private void txt_j_celularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_celularKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_j_celular.getText().length() > 0) {
                cbx_tiendas.setEnabled(true);
                cbx_tiendas.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_j_celularKeyPressed

    private void txt_j_celularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_celularKeyTyped
        c_varios.solo_numeros(evt);
        c_varios.limitar_caracteres(evt, txt_j_celular, 9);
    }//GEN-LAST:event_txt_j_celularKeyTyped

    private void cbx_tiendasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tiendasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_j_usuario.setEnabled(true);
            txt_j_usuario.requestFocus();
        }
    }//GEN-LAST:event_cbx_tiendasKeyPressed

    private void txt_j_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_usuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String usuario = txt_j_usuario.getText().trim();
            if (usuario.length() > 0) {
                txt_j_contrasena.setEnabled(true);
                txt_j_contrasena.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_j_usuarioKeyPressed

    private void txt_j_contrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_contrasenaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String contrasena = txt_j_contrasena.getText().trim();
            if (contrasena.length() > 0) {
                btn_j_grabar.setEnabled(true);
                btn_j_grabar.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_j_contrasenaKeyPressed

    private void btn_j_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j_grabarActionPerformed
        c_usuario.setDocumento(txt_j_documento.getText().trim());
        c_usuario.setNombre(txt_j_datos.getText().trim());
        c_usuario.setEmail(txt_j_email.getText().trim());
        c_usuario.setCelular(txt_j_celular.getText().trim());
        c_usuario.setUsername(txt_j_usuario.getText().trim());
        c_usuario.setPassword(txt_j_contrasena.getText().trim());
        cla_almacen o_almacen = (cla_almacen) cbx_tiendas.getSelectedItem();
        c_usuario.setId_almacen(o_almacen.getId_almacen());

        if (c_usuario.getId_usuario() > 0) {
            if (chx_estado.isSelected()) {
                c_usuario.setEstado(2);
            } else {
                c_usuario.setEstado(1);
            }
            c_usuario.modificar();
        } else {
            c_usuario.obtener_codigo();
            c_usuario.registrar();
        }
        c_usuario.setId_usuario(0);
        mostrar_tablar();
        jd_reg_usuario.dispose();
    }//GEN-LAST:event_btn_j_grabarActionPerformed

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_busqueda.getText().trim();
            String query = "select u.id_usuarios, u.username, u.nro_documento, u.datos, u.estado, a.nombre as nalmacen "
                    + "from usuarios as u "
                    + "inner join almacen as a on a.id_almacen = u.id_almacen "
                    + "where u.username like '%" + texto + "%' or u.nro_documento = '" + texto + "' or datos like '%" + texto + "%' "
                    + "order by datos asc";
            c_usuario.mostrar(t_usuarios, query);
        }
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void cbx_tiendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tiendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_tiendasActionPerformed

    private void cbx_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_estadoActionPerformed
        int vestado = cbx_estado.getSelectedIndex();
        String query = "";
        if (vestado == 0) {
            query = "select u.id_usuarios, u.username, u.nro_documento, u.datos, u.estado, a.nombre as nalmacen "
                    + "from usuarios as u "
                    + "inner join almacen as a on a.id_almacen = u.id_almacen "
                    + "where u.estado = 1 "
                    + "order by datos asc";
        }
        if (vestado == 1) {
            query = "select u.id_usuarios, u.username, u.nro_documento, u.datos, u.estado, a.nombre as nalmacen "
                    + "from usuarios as u "
                    + "inner join almacen as a on a.id_almacen = u.id_almacen "
                    + "where u.estado = 2 "
                    + "order by datos asc";
        }
        c_usuario.mostrar(t_usuarios, query);
    }//GEN-LAST:event_cbx_estadoActionPerformed

    private void btn_permisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_permisosActionPerformed
        //preguntar si tiene permiso
        frm_principal.c_permiso.setId_permiso(21);
        boolean permitido = frm_principal.c_permiso.validar();

        if (permitido) {
            //mostrar formulacion permisos por usuario
            Frame f = JOptionPane.getRootFrame();
            int id_usuario = Integer.parseInt(t_usuarios.getValueAt(fila_seleccionada, 0).toString());
            frm_reg_permiso_usuario.id_usuario = id_usuario;
            frm_reg_permiso_usuario dialog = new frm_reg_permiso_usuario(f, true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usted no tiene permiso para realizar esta operacion!!");
        }
    }//GEN-LAST:event_btn_permisosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_j_cerrar;
    private javax.swing.JButton btn_j_grabar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_permisos;
    private javax.swing.JComboBox<String> cbx_estado;
    private javax.swing.JComboBox<String> cbx_tiendas;
    private javax.swing.JCheckBox chx_estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JDialog jd_reg_usuario;
    private javax.swing.JTable t_usuarios;
    private javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_j_celular;
    private javax.swing.JTextField txt_j_contrasena;
    private javax.swing.JTextField txt_j_datos;
    private javax.swing.JTextField txt_j_documento;
    private javax.swing.JTextField txt_j_email;
    private javax.swing.JTextField txt_j_id;
    private javax.swing.JTextField txt_j_usuario;
    // End of variables declaration//GEN-END:variables
}
