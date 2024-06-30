/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

/**
 *
 * @author princ
 */
public class FacturacionUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form FacturacionUI
     */
    public FacturacionUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRecibo = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        lblDatosPedido = new javax.swing.JLabel();
        lblIdPedido = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        lblFechaPedido = new javax.swing.JLabel();
        txtFechaPedido = new javax.swing.JTextField();
        lblEstadoPedido = new javax.swing.JLabel();
        txtEstadoPedido = new javax.swing.JTextField();
        lblDatosPedido1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblIngreseMesa = new javax.swing.JLabel();
        jcbjMesaAsignada = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));
        setTitle("FACTURA");

        lblRecibo.setBackground(new java.awt.Color(255, 255, 255));
        lblRecibo.setFont(new java.awt.Font("Sylfaen", 3, 48)); // NOI18N
        lblRecibo.setText("RECIBO");

        lblNombreCliente.setText("NOMBRE DEL CLIENTE:");

        lblCedula.setText("CÉDULA/RUC:");

        lblCorreo.setText("CORREO:");

        lblDireccion.setText("DIRECCIÓN:");

        lblDatosPedido.setBackground(new java.awt.Color(0, 0, 0));
        lblDatosPedido.setFont(new java.awt.Font("Sylfaen", 3, 18)); // NOI18N
        lblDatosPedido.setText("DESCRIPCIÓN PEDIDO");

        lblIdPedido.setText("ID PEDIDO:");

        lblFechaPedido.setText("FECHA PEDIDO:");

        lblEstadoPedido.setText("ESTADO PEDIDO:");

        lblDatosPedido1.setBackground(new java.awt.Color(0, 0, 0));
        lblDatosPedido1.setFont(new java.awt.Font("Sylfaen", 3, 18)); // NOI18N
        lblDatosPedido1.setText("DATOS DEL PEDIDO");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        lblIngreseMesa.setText("INGRESE LA MESA ASIGNADA:");

        jcbjMesaAsignada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setText("GUARDAR");

        btnNuevo.setText("NUEVO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(lblRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEstadoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(txtFechaPedido)
                            .addComponent(txtIdPedido))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdPedido)
                            .addComponent(lblFechaPedido)
                            .addComponent(lblEstadoPedido)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreCliente)
                                    .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblIngreseMesa))
                                .addGap(103, 103, 103)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDireccion)
                                    .addComponent(txtCorreo)
                                    .addComponent(txtCedula)
                                    .addComponent(txtNombreCliente)
                                    .addComponent(jcbjMesaAsignada, 0, 166, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDatosPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatosPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(43, 43, 43)
                        .addComponent(btnNuevo)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblRecibo)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCliente)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIngreseMesa)
                    .addComponent(jcbjMesaAsignada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(lblDatosPedido1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPedido)
                    .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaPedido)
                    .addComponent(txtFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoPedido)
                    .addComponent(txtEstadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(lblDatosPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbjMesaAsignada;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDatosPedido;
    private javax.swing.JLabel lblDatosPedido1;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEstadoPedido;
    private javax.swing.JLabel lblFechaPedido;
    private javax.swing.JLabel lblIdPedido;
    private javax.swing.JLabel lblIngreseMesa;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblRecibo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEstadoPedido;
    private javax.swing.JTextField txtFechaPedido;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
