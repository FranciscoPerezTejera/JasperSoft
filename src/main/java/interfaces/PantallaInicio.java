package interfaces;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class PantallaInicio extends javax.swing.JFrame {

    private Connection con;

    public PantallaInicio(Connection con) {
        initComponents();
        this.con = con;
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parametrosComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        imprimirNuevoInformeButton = new javax.swing.JButton();
        SalirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        parametrosComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reporte del principio", "Todos los juegos con codigo de creador 1" }));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecciona un parámetro para realizar informe");

        imprimirNuevoInformeButton.setText("IMPRIMIR");
        imprimirNuevoInformeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirNuevoInformeButtonActionPerformed(evt);
            }
        });

        SalirButton.setText("SALIR");
        SalirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(imprimirNuevoInformeButton)
                        .addGap(31, 31, 31)
                        .addComponent(SalirButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(parametrosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(parametrosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imprimirNuevoInformeButton)
                    .addComponent(SalirButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirButtonActionPerformed
        try {
            con.close();
            System.out.println("Conexion cerrado con exito.");
        } catch (SQLException ex) {
            System.err.println("Ha habido un problema con el cierre de la conexi´´on.");
        }
        this.dispose();
    }//GEN-LAST:event_SalirButtonActionPerformed

    private void imprimirNuevoInformeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirNuevoInformeButtonActionPerformed
        String parametroSeleccionado = (String) parametrosComboBox.getSelectedItem();
        generarInforme(parametroSeleccionado);
    }//GEN-LAST:event_imprimirNuevoInformeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SalirButton;
    private javax.swing.JButton imprimirNuevoInformeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> parametrosComboBox;
    // End of variables declaration//GEN-END:variables

    private void generarInforme(String parametroSeleccionado) {

        if (parametroSeleccionado.equalsIgnoreCase("Reporte del principio")) {

            try {
                String reportSrcFile = "src/main/resources/creadores.jrxml";
                JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);

                JasperPrint print = JasperFillManager.fillReport(jasperReport, null, con);

                JasperViewer.viewReport(print, false);
            } catch (JRException e) {
                e.printStackTrace();
            }

        } else if (parametroSeleccionado.equalsIgnoreCase("Todos los juegos con codigo de creador 1")){

            try {
                generarContenidoJRXML(parametroSeleccionado);

                String rutaArchivoJRXML = "src/main/resources/jasperConParametro.jrxml";
                JasperReport jasperReport = JasperCompileManager.compileReport(rutaArchivoJRXML);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
                JasperViewer.viewReport(jasperPrint, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void generarContenidoJRXML(String parametroSeleccionado) {

        String contenidoJRXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\"\n" +
                    "              xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "              xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\"\n" +
                    "              name=\"InformeDinamico\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\">\n" +
                    "\n" +
                    "    <queryString>\n" +
                    "           <![CDATA[SELECT * FROM juegos WHERE id_creador = 1 ORDER BY id_creador ASC]]>\n" +
                    "    </queryString>\n" +
                    "\n" +
                    "    <field name=\"IDJ\" class=\"java.lang.Integer\"/>\n" +
                    "    <field name=\"ID_CREADOR\" class=\"java.lang.Integer\"/>\n" +
                    "    <field name=\"JUEGO\" class=\"java.lang.String\"/>\n" +
                    "    <field name=\"FECHACREACION\" class=\"java.util.Date\"/>\n" +
                    "    <field name=\"ID_TIPO\" class=\"java.lang.Integer\"/>\n" +
                    "    <field name=\"COSTE\" class=\"java.math.BigDecimal\"/>\n" +
                    "    <!-- Otros campos según tu base de datos -->\n" +
                    "\n" +
                    "    <detail>\n" +
                    "        <band height=\"20\">\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"0\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{IDJ}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"100\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{ID_CREADOR}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"200\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{JUEGO}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"300\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{FECHACREACION}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"400\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{ID_TIPO}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <textField>\n" +
                    "                <reportElement x=\"500\" y=\"0\" width=\"100\" height=\"20\"/>\n" +
                    "                <textFieldExpression><![CDATA[$F{COSTE}]]></textFieldExpression>\n" +
                    "            </textField>\n" +
                    "            <!-- Otros textField según tus campos -->\n" +
                    "        </band>\n" +
                    "    </detail>\n" +
                    "\n" +
                    "</jasperReport>";

        try {
            String rutaArchivoJRXML = "src/main/resources/jasperConParametro.jrxml";
            Files.write(Paths.get(rutaArchivoJRXML), contenidoJRXML.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
