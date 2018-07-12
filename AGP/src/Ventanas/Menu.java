/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Modelo.DTO.Cerrar; //PARA USAR EL METODO CERRAR()
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon; //PARA ASIGNARLE UN ICONO A NUESTRA APP
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane; //PARA LA VENTANA DE CONFIRMACION EN CASO DE QUE QUERAMOS SALIR
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Angelo
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    private String nombreUsuario; //PARA OBTENER EL NOMBRE COMPLETO DE USUARIO QUE INGRESO
    
    public Menu() {
        initComponents();
        this.setMinimumSize(new Dimension(250, 220));
        setLocationRelativeTo(null);  //CENTRAMOS LA PANTALA
        setResizable(false);  //Deshabilita modificar el tamaño de la pantalla
        nombreUsuario = Iniciar_Sesion.nombre; //RESCATAMOS EL NOMBRE DEL USUARIO
        jlNombre.setText(nombreUsuario); //MOSTRAMOS EL NOMBRE DEL USUARIO
        setIconImage(new ImageIcon(getClass().getResource("/Imagen/LogoSGP.PNG")).getImage()); //ESTABLECER ICONO DEL SW
    }
    
    private void setupMenuKey(final JFrame frame) {
        Action menuAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JRootPane rootPane = frame.getRootPane();
                JMenuBar jMenuBar = rootPane.getJMenuBar();
                JMenu menu = jMenuBar.getMenu(0);
                menu.doClick();
            }
        };

        JRootPane rootPane = frame.getRootPane();
        ActionMap actionMap = rootPane.getActionMap();

        final String MENU_ACTION_KEY = "expand_that_first_menu_please";
        actionMap.put(MENU_ACTION_KEY, menuAction);
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), MENU_ACTION_KEY);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlNombre = new javax.swing.JLabel();
        jmMenu = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jmInicio = new javax.swing.JMenuItem();
        jmCerrarSesion = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmMotivo = new javax.swing.JMenuItem();
        jmTipo = new javax.swing.JMenuItem();
        jmUnidad = new javax.swing.JMenuItem();
        jmUsuario = new javax.swing.JMenuItem();
        jmFeriado = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Administración Gestión Permiso");
        setBackground(new java.awt.Color(255, 255, 51));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel1.setText("Bienvenido:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/LogoSGP.PNG"))); // NOI18N

        jlNombre.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jlNombre.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 165, Short.MAX_VALUE))
                    .addComponent(jlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jMenu3.setText("Sistema");

        jmInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/day-view-16.png"))); // NOI18N
        jmInicio.setText("Inicio");
        jmInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmInicioActionPerformed(evt);
            }
        });
        jMenu3.add(jmInicio);

        jmCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/close-window-16.png"))); // NOI18N
        jmCerrarSesion.setText("Cerrar Sesión");
        jmCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCerrarSesionActionPerformed(evt);
            }
        });
        jMenu3.add(jmCerrarSesion);

        jmMenu.add(jMenu3);

        jMenu4.setText("Servicio");

        jmMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/day-view-16.png"))); // NOI18N
        jmMotivo.setText("Motivo");
        jmMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMotivoActionPerformed(evt);
            }
        });
        jMenu4.add(jmMotivo);

        jmTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/day-view-16.png"))); // NOI18N
        jmTipo.setText("Tipo");
        jmTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmTipoActionPerformed(evt);
            }
        });
        jMenu4.add(jmTipo);

        jmUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/day-view-16.png"))); // NOI18N
        jmUnidad.setText("Departamento");
        jmUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUnidadActionPerformed(evt);
            }
        });
        jMenu4.add(jmUnidad);

        jmUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/day-view-16.png"))); // NOI18N
        jmUsuario.setText("Usuario");
        jmUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUsuarioActionPerformed(evt);
            }
        });
        jMenu4.add(jmUsuario);

        jmFeriado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/day-view-16.png"))); // NOI18N
        jmFeriado.setText("Feriado");
        jmFeriado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmFeriadoActionPerformed(evt);
            }
        });
        jMenu4.add(jmFeriado);

        jmMenu.add(jMenu4);

        setJMenuBar(jmMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmInicioActionPerformed
            dispose(); //DESTRUYE LA VENTANA QUE ESTA ABIERTA
            Menu m = new Menu(); //ABRIMOS LA VENTANA MENU
            m.setLocationRelativeTo(null);  //CENTRAMOS LA PANTALA
            m.setVisible(true); //HACEMOS VISIBLE LA VENTANA MENU
            m.setResizable(false);  //Deshabilita modificar el tamaño de la pantalla
    }//GEN-LAST:event_jmInicioActionPerformed

    private void jmCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCerrarSesionActionPerformed
        Cerrar_Sesion cs = new Cerrar_Sesion(this,true,Menu.this); //LLAMO AL JDIALOG CERRAR_SESION
        cs.setVisible(true); //LA HAGO VISIBLE
    }//GEN-LAST:event_jmCerrarSesionActionPerformed

    private void jmMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMotivoActionPerformed
        this.dispose(); //DESTRUYO LA VENTANA MENU
        Motivo m = new Motivo(); //CREO UNA INSTANCIA DE MOTIVO
        m.setVisible(true); //LA HAGO VISIBLE
    }//GEN-LAST:event_jmMotivoActionPerformed

    private void jmTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmTipoActionPerformed
        this.dispose(); //DESTRUYO LA VENTANA MENU
        Tipo t = new Tipo(); //CREO UNA INSTANCIA DE TIPO
        t.setVisible(true); //LA HAGO VISIBLE
    }//GEN-LAST:event_jmTipoActionPerformed

    private void jmUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUnidadActionPerformed
        dispose(); //DESTRUYO LA VENTANA MENU
        Departamento u = new Departamento(); //CREO UNA INSTANCIA UNIDAD
        u.setVisible(true); //LA HAGO VISIBLE
    }//GEN-LAST:event_jmUnidadActionPerformed

    private void jmUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUsuarioActionPerformed
        dispose(); //DESTRUYO LA VENTANA MENU
        Usuario u = new Usuario(); //CREO UNA INSTANCIA USUARIO
        u.setVisible(true); //LA HAGO VISIBLE
    }//GEN-LAST:event_jmUsuarioActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //EN CASO DE QUE EL USUARIO, CIERRE LA VENTANA HACIENDO CLIC EN "X"
        Cerrar c = new Cerrar();  //CREO UNA INSTANCIA DE CERRAR
        c.cerrar(); //CONVOCO AL METODO CERRAR
    }//GEN-LAST:event_formWindowClosing

    private void jmFeriadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmFeriadoActionPerformed
    dispose(); 
    Feriado f = new Feriado(); 
    f.setVisible(true);
    }//GEN-LAST:event_jmFeriadoActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JMenuItem jmCerrarSesion;
    private javax.swing.JMenuItem jmFeriado;
    private javax.swing.JMenuItem jmInicio;
    private javax.swing.JMenuBar jmMenu;
    private javax.swing.JMenuItem jmMotivo;
    private javax.swing.JMenuItem jmTipo;
    private javax.swing.JMenuItem jmUnidad;
    private javax.swing.JMenuItem jmUsuario;
    // End of variables declaration//GEN-END:variables
}
