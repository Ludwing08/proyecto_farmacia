package Vista;

import Metodos.Metodos_Ventas;
import Conexion.ConexionBD;
import Metodos.Metodos_Productos;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Ventas extends javax.swing.JInternalFrame {

    public String estado = "EMITIDO";

    private Connection connection = new ConexionBD().conectar();
    String Total;
    String strCodigo;
    String accion;
    String numVenta, tipoDocumento;

    int registros;
    String id[] = new String[50];
    int num = 0;

    static int intContador;
    public String IdEmpleado, NombreEmpleado;
    int idventa, nidventa;
    String idventa_print;

    public String codigo;
    static Connection conn = null;

    static ResultSet rs = null;
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtmDetalle = new DefaultTableModel();

    String criterio, busqueda;

    public Ventas() {
        initComponents();
        txtFechas.setDisabledTextColor(Color.blue);
        txtFechas.setText(fecha());
        txtFechas.setVisible(false);

        txtFecha.setEnabled(false);
        txtFecha.setDisabledTextColor(Color.blue);
        txtFecha.setText(fechaactual());

        numVenta = generarNumFactura();

        numVenta = generaNumVenta();
        txtNumero.setText(numVenta);

        numVenta = generaIdVenta();
        txtUltimoId.setText(numVenta);

        this.setSize(860, 723);

        mirar();
        txtIdEmpleado.setVisible(false);
        btnNuevo.requestFocus();
        btnEliminarProducto.setEnabled(false);
        btnLimpiarTabla.setEnabled(false);

        jpnImporte.setVisible(false);

        txtUltimoId.setVisible(false);
        txtCategoria.setVisible(false);

        String titulos[] = {"ID", "ID", "PRODUCTO", "DESCRIPCIÓN", "CATEGORÍA.", "CANTIDAD", "PRECIO", "TOTAL", "COSTO"};
        dtmDetalle.setColumnIdentifiers(titulos);
        tblDetalleProducto.setModel(dtmDetalle);
        CrearTablaDetalleProducto();
    }

    public String generaNumVenta() {

        Metodos_Ventas oVenta = new Metodos_Ventas();
        try {

            rs = oVenta.obtenerUltimoIdVenta();
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    Scanner s = new Scanner(rs.getString(1));
                    int c = s.useDelimiter("C").nextInt() + 1;

                    if (c < 10) {
                        return "C0000" + c;
                    }
                    if (c < 100) {
                        return "C000" + c;
                    }
                    if (c < 1000) {
                        return "C00" + c;
                    }
                    if (c < 10000) {
                        return "C0" + c;
                    } else {
                        return "C" + c;
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "C00001";

    }

    void limpiarCampos() {

        txtTotalVenta.setText("0.0");
        txtSubTotal.setText("0.0");
        txtTotalPagar.setText("0.0");

        txtCodigoProducto.setText("");
        txtCodigoProducto.setText("");
        txtNombreProducto.setText("");
        txtStockProducto.setText("");
        txtPrecioProducto.setText("");
        txtCantidadProducto.setText("");
        txtTotalProducto.setText("");
        txtCodigoProducto.requestFocus();
    }

    void mirar() {
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnComprobante.setEnabled(false);
        btnImporte.setEnabled(false);

        txtCodigoProducto.setEnabled(false);
        txtCantidadProducto.setEnabled(false);
        txtFecha.setEnabled(false);
        txtNumero.setEnabled(false);

        btnclientes.setEnabled(false);
        btnBuscarProducto.setEnabled(false);
        btnAgregarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        btnLimpiarTabla.setEnabled(false);

        txtTotalVenta.setText("0.0");
        txtSubTotal.setText("0.0");
        txtTotalPagar.setText("0.0");
        txtCodigoProducto.setText("");
        txtCodigoProducto.setText("");
        txtNombreProducto.setText("");
        txtStockProducto.setText("");
        txtPrecioProducto.setText("");
        txtCantidadProducto.setText("");
        txtTotalProducto.setText("");
        txtNombre.setText("");
        txtDni.setText("");
        txtDireccion.setText("");
        txtRuc.setText("");
        txtIdCliente.setText("");
        txtComprobante.setText("");
        txtCategoria.setText("");
        txtDescripcionProducto.setText("");
        btnNuevo.requestFocus();
    }

    void modificar() {

        btnNuevo.setEnabled(false);

        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        btnComprobante.setEnabled(true);

        txtCodigoProducto.setEnabled(true);
        txtCantidadProducto.setEnabled(true);
        txtFecha.setEnabled(true);
        txtNumero.setEnabled(true);

        btnclientes.setEnabled(true);
        btnBuscarProducto.setEnabled(true);
        btnAgregarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(false);
        btnLimpiarTabla.setEnabled(false);

        txtCodigoProducto.requestFocus();
    }

    private void setOcultarColumnasJTable(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleProducto = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtTotalPagar = new javax.swing.JTextField();
        txtTotalVenta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jpnImporte = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTotalProducto = new javax.swing.JTextField();
        btnImporte = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        btnclientes = new javax.swing.JButton();
        txtRuc = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnBuscarProducto = new javax.swing.JButton();
        txtPrecioProducto = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        txtStockProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFechas = new javax.swing.JTextField();
        txtUltimoId = new javax.swing.JTextField();
        txtIdComprobante = new javax.swing.JTextField();
        txtIdEmpleado = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        txtDescripcionProducto = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtComprobante = new javax.swing.JTextField();
        btnComprobante = new javax.swing.JButton();
        txtCategoria = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnAgregarProducto = new javax.swing.JButton();
        btnLimpiarTabla = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(860, 723));
        getContentPane().setLayout(null);

        tblDetalleProducto.setBackground(new java.awt.Color(204, 204, 204));
        tblDetalleProducto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDetalleProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblDetalleProducto.setRowHeight(22);
        jScrollPane3.setViewportView(tblDetalleProducto);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 440, 822, 165);

        jPanel4.setBackground(new java.awt.Color(251, 248, 248));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mas.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setIconTextGap(0);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Pguar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIconTextGap(0);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 102, 102));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/can.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIconTextGap(0);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(690, 60, 139, 200);

        jPanel6.setBackground(new java.awt.Color(251, 248, 248));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SUB TOTAL");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 100, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL A PAGAR");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 130, 20));

        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setEnabled(false);
        jPanel6.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 100, 30));

        txtTotalPagar.setBackground(new java.awt.Color(204, 255, 255));
        txtTotalPagar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        txtTotalPagar.setForeground(new java.awt.Color(0, 153, 0));
        txtTotalPagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPagar.setDisabledTextColor(new java.awt.Color(0, 153, 0));
        txtTotalPagar.setEnabled(false);
        jPanel6.add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 135, 30));

        txtTotalVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalVenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalVenta.setEnabled(false);
        jPanel6.add(txtTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("VALOR VENTA");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        txtIGV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIGV.setEnabled(false);
        jPanel6.add(txtIGV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 100, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("I.G.V.");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 100, 20));

        getContentPane().add(jPanel6);
        jPanel6.setBounds(320, 620, 510, 65);

        jpnImporte.setBackground(new java.awt.Color(251, 248, 248));
        jpnImporte.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnImporte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("IMPORTE");
        jpnImporte.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, 80, 20));

        txtImporte.setBackground(new java.awt.Color(204, 204, 204));
        txtImporte.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtImporte.setForeground(new java.awt.Color(255, 255, 255));
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporte.setText("0.0");
        txtImporte.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtImporte.setEnabled(false);
        jpnImporte.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CAMBIO");
        jpnImporte.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 3, 80, 20));

        txtCambio.setBackground(new java.awt.Color(204, 204, 204));
        txtCambio.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        txtCambio.setForeground(new java.awt.Color(0, 153, 0));
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCambio.setText("0.0");
        txtCambio.setDisabledTextColor(new java.awt.Color(0, 102, 0));
        txtCambio.setEnabled(false);
        jpnImporte.add(txtCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 25, 80, 30));

        getContentPane().add(jpnImporte);
        jpnImporte.setBounds(10, 616, 190, 65);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText("CANTIDAD:");

        txtCantidadProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidadProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("TOTAL:");

        txtTotalProducto.setBackground(new java.awt.Color(204, 255, 255));
        txtTotalProducto.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        txtTotalProducto.setForeground(new java.awt.Color(0, 102, 102));
        txtTotalProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalProducto.setDisabledTextColor(new java.awt.Color(0, 102, 102));
        txtTotalProducto.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(87, 356, 290, 66);

        btnImporte.setForeground(new java.awt.Color(0, 102, 102));
        btnImporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/vuelto.png"))); // NOI18N
        btnImporte.setText("Importe");
        btnImporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImporteActionPerformed(evt);
            }
        });
        getContentPane().add(btnImporte);
        btnImporte.setBounds(10, 356, 71, 66);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Realizar Venta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(363, 363, 363)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 850, 51);

        jPanel10.setBackground(new java.awt.Color(251, 248, 248));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del Cliente:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 102));
        jLabel28.setText("Cliente:");

        btnclientes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/VCli.png"))); // NOI18N
        btnclientes.setText("...");
        btnclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        txtRuc.setEditable(false);

        txtDireccion.setEditable(false);
        txtDireccion.setBorder(null);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 102));
        jLabel32.setText("RUC:");

        txtNombre.setEditable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel32)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel10);
        jPanel10.setBounds(10, 65, 530, 78);

        jPanel11.setBackground(new java.awt.Color(251, 248, 248));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Producto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        btnBuscarProducto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/VBus.png"))); // NOI18N
        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        txtPrecioProducto.setEditable(false);
        txtPrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProductoKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 102));
        jLabel33.setText("Precio S/ :");

        txtNombreProducto.setEditable(false);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 102));
        jLabel34.setText("Producto:");

        txtCodigoProducto.setEditable(false);
        txtCodigoProducto.setBorder(null);

        txtStockProducto.setEditable(false);
        txtStockProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockProductoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Stock:");

        txtFechas.setEditable(false);
        txtFechas.setBorder(null);

        txtUltimoId.setEditable(false);
        txtUltimoId.setBorder(null);

        txtIdComprobante.setEditable(false);
        txtIdComprobante.setBorder(null);
        txtIdComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdComprobanteActionPerformed(evt);
            }
        });

        txtIdEmpleado.setEditable(false);
        txtIdEmpleado.setBorder(null);

        txtCosto.setEditable(false);
        txtCosto.setBorder(null);
        txtCosto.setOpaque(false);

        txtDescripcionProducto.setEditable(false);
        txtDescripcionProducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 102));
        jLabel35.setText("Concentración:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(323, 323, 323)
                        .addComponent(txtIdComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProducto))
                            .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPrecioProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(txtStockProducto))
                        .addGap(34, 34, 34)
                        .addComponent(txtFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel5)
                    .addComponent(txtStockProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33))))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtIdComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        getContentPane().add(jPanel11);
        jPanel11.setBounds(10, 154, 530, 100);

        txtIdCliente.setEditable(false);
        txtIdCliente.setBorder(null);
        getContentPane().add(txtIdCliente);
        txtIdCliente.setBounds(848, 65, 0, 70);

        txtDni.setEditable(false);
        txtDni.setBorder(null);
        getContentPane().add(txtDni);
        txtDni.setBounds(854, 103, 35, 14);

        jPanel2.setBackground(new java.awt.Color(251, 248, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Comprobante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(0, 102, 102))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 102));
        jLabel40.setText("Comprobante:");

        txtComprobante.setEditable(false);

        btnComprobante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/VBus.png"))); // NOI18N
        btnComprobante.setText("...");
        btnComprobante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobanteActionPerformed(evt);
            }
        });

        txtCategoria.setEditable(false);
        txtCategoria.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addGap(10, 10, 10)
                .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnComprobante)
                .addGap(116, 205, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 265, 530, 85);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregarProducto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(0, 102, 102));
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/add.png"))); // NOI18N
        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarProductoMouseClicked(evt);
            }
        });
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        btnAgregarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnAgregarProductoKeyReleased(evt);
            }
        });

        btnLimpiarTabla.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnLimpiarTabla.setForeground(new java.awt.Color(0, 102, 102));
        btnLimpiarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Pnew.png"))); // NOI18N
        btnLimpiarTabla.setText("Limpiar");
        btnLimpiarTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTablaActionPerformed(evt);
            }
        });

        btnEliminarProducto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnEliminarProducto.setForeground(new java.awt.Color(0, 102, 102));
        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/del.png"))); // NOI18N
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 102, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Pvolv.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiarTabla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(13, 13, 13))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(380, 360, 450, 66);

        txtFecha.setEditable(false);
        txtFecha.setForeground(new java.awt.Color(51, 0, 0));
        getContentPane().add(txtFecha);
        txtFecha.setBounds(570, 90, 86, 20);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 102, 102));
        jLabel37.setText("FECHA:");
        getContentPane().add(jLabel37);
        jLabel37.setBounds(590, 70, 50, 14);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 102));
        jLabel20.setText("VENTA:");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(590, 120, 40, 14);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Nº");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(550, 140, 20, 20);
        getContentPane().add(txtNumero);
        txtNumero.setBounds(570, 140, 86, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyy");
        return formatofecha.format(fecha);

    }

    public static String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyy-MM-dd");
        return formatofecha.format(fecha);

    }

    public int recorrer(int id) {
        int fila = 0, valor = -1;

        fila = tblDetalleProducto.getRowCount();

        for (int f = 0; f < fila; f++) {
            if (Integer.parseInt(String.valueOf(dtmDetalle.getValueAt(f, 0))) == id) {

                valor = f;
                break;

            } else {
                valor = -1;
            }

        }
        return valor;
    }

    void agregardatos(int item, String cod, String nom, String descrip, String cat, double cant, double pre, double tot, double cos) {

        int p = recorrer(item);
        double n_cant, n_total;
        if (p > -1) {

            n_cant = Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(p, 5))) + cant;
            tblDetalleProducto.setValueAt(n_cant, p, 5);

            n_total = Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(p, 5))) * Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(p, 6)));
            tblDetalleProducto.setValueAt(n_total, p, 7);

        } else {
            String Datos[] = {String.valueOf(item), cod, nom, descrip, String.valueOf(cat), String.valueOf(cant), String.valueOf(pre), String.valueOf(tot), String.valueOf(cos)};
            dtmDetalle.addRow(Datos);
        }
        tblDetalleProducto.setModel(dtmDetalle);
    }

    void CalcularTotal() {
        if (!txtPrecioProducto.equals("") || !txtCantidadProducto.equals("")) {
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
            double precio_prod = 0, cant_prod = 0, total_prod = 0;
            precio_prod = Double.valueOf(txtPrecioProducto.getText());
            cant_prod = Double.valueOf(txtCantidadProducto.getText());
            total_prod = precio_prod * cant_prod;
            txtTotalProducto.setText(String.valueOf(formateador.format(total_prod)));
        }
    }

    void CalcularValor_Venta() {
        int fila = 0;
        double valorVenta = 0;
        fila = dtmDetalle.getRowCount();
        for (int f = 0; f < fila; f++) {
            valorVenta += Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 7)));
        }
        txtTotalVenta.setText(String.valueOf(valorVenta));
    }

    void CalcularSubTotal() {
        double subtotal = 0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        subtotal = Double.parseDouble(txtTotalPagar.getText()) / 1.12;
        txtSubTotal.setText(String.valueOf(formateador.format(subtotal)));
    }

    void CalcularIGV() {
        double igv = 0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        igv = Double.parseDouble(txtSubTotal.getText()) * 0.12;
        txtIGV.setText(String.valueOf(formateador.format(igv)));
    }

    void CalcularTotal_Pagar() {
        double totalpagar = 0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
        totalpagar = Double.parseDouble(txtTotalVenta.getText());
        txtTotalPagar.setText(String.valueOf(formateador.format(totalpagar)));
    }

    void limpiarTabla() {
        try {
            int filas = tblDetalleProducto.getRowCount();
            for (int i = 0; filas > i; i++) {
                dtmDetalle.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        double stock, cant;
        if (txtStockProducto.getText().isEmpty() || txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor agregue un cliente y un artículo");
            btnBuscarProducto.requestFocus();
        } else {
            if (!txtCantidadProducto.getText().equals("")) {
                if (txtCantidadProducto.getText().equals("")) {
                    txtCantidadProducto.setText("0");
                    cant = Double.parseDouble(txtCantidadProducto.getText());
                } else {
                    cant = Double.parseDouble(txtCantidadProducto.getText());
                }

                if (cant > 0) {
                    stock = Double.parseDouble(txtStockProducto.getText());
                    cant = Double.parseDouble(txtCantidadProducto.getText());
                    if (cant <= stock) {
                        int d1 = Integer.parseInt(txtCodigoProducto.getText());
                        String d2 = txtCodigoProducto.getText();
                        String d3 = txtNombreProducto.getText();
                        String d4 = txtDescripcionProducto.getText();
                        String d5 = txtCategoria.getText();
                        double d6 = Double.parseDouble(txtCantidadProducto.getText());
                        double d7 = Double.parseDouble(txtPrecioProducto.getText());
                        double d8 = Double.parseDouble(txtTotalProducto.getText());
                        double d9 = Double.parseDouble(txtCosto.getText());
                        agregardatos(d1, d2, d3, d4, d5, d6, d7, d8, d9);

                        CalcularValor_Venta();
                        CalcularTotal_Pagar();
                        CalcularSubTotal();
                        CalcularIGV();

                        txtCantidadProducto.setText("");
                        txtTotalProducto.setText("");
                        txtImporte.setText("");
                        txtCambio.setText("");
//
//                    txtCodigoProducto.setText("");
//                    txtNombreProducto.setText("");
//                    txtStockProducto.setText("");
//                    txtPrecioProducto.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Stock Insuficiente");
                        txtCantidadProducto.setText("");
                        txtTotalProducto.setText("");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese Cantidad mayor a 0");
                    txtCantidadProducto.setText("");
                    txtTotalProducto.setText("");
                    txtCantidadProducto.requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad");
                txtCantidadProducto.requestFocus();
            }

            btnGuardar.setEnabled(true);
            btnEliminarProducto.setEnabled(true);
            btnLimpiarTabla.setEnabled(true);
            btnImporte.setEnabled(true);
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed
    private static boolean band() {
        if (Math.random() > .5) {
            return true;
        } else {
            return false;
        }
    }
    private void txtCantidadProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyReleased
        CalcularTotal();
    }//GEN-LAST:event_txtCantidadProductoKeyReleased

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int fila = tblDetalleProducto.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (fila > 0) {
                dtmDetalle.removeRow(fila);
                CalcularValor_Venta();
                CalcularSubTotal();
                CalcularIGV();
            } else if (fila == 0) {
                dtmDetalle.removeRow(fila);
                txtIGV.setText("0.0");
                txtTotalVenta.setText("0.0");
                txtSubTotal.setText("0.0");
                txtTotalPagar.setText("0.0");
                CalcularValor_Venta();
                CalcularTotal_Pagar();
                CalcularSubTotal();
                CalcularIGV();
            }
        }
        CalcularValor_Venta();
        CalcularTotal_Pagar();
        CalcularSubTotal();
        CalcularIGV();
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnLimpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTablaActionPerformed
        limpiarTabla();
        txtTotalVenta.setText("0.0");
        txtSubTotal.setText("0.0");
        txtTotalPagar.setText("0.0");
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnLimpiarTablaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        limpiarTabla();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImporteActionPerformed
        jpnImporte.setVisible(true);
        String ingreso = JOptionPane.showInputDialog(null, "Ingrese Importe a Cancelar", "0.0");
        Double importe, cambio;
        if (ingreso.compareTo("") != 0) {
            importe = Double.parseDouble(ingreso);
            txtImporte.setText(String.valueOf(importe));
            cambio = Double.parseDouble(txtImporte.getText()) - Double.parseDouble(txtTotalPagar.getText());
            txtCambio.setText(String.valueOf(cambio));
        } else {
            txtImporte.setText("0.0");
        }
    }//GEN-LAST:event_btnImporteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtComprobante.getText().isEmpty() || txtNumero.getText().isEmpty() || txtStockProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor rellene todos los campos");
            //--------------------------------------------------
        } else {

            int result = JOptionPane.showConfirmDialog(this, "¿Desea Generar la venta?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                Metodos_Ventas ventas = new Metodos_Ventas();

                String Numero = txtNumero.getText();
                String Fecha = txtFechas.getText();
                String VentaTotal = txtTotalVenta.getText();
                String SubTotal = txtSubTotal.getText();
                String Total = txtTotalPagar.getText();
                String IVA = txtIGV.getText();
                String Estado = estado;
                String IdCliente = txtIdCliente.getText();
                String IdEmpleado = txtIdEmpleado.getText();
                String IdTipoComprobante = txtIdComprobante.getText();
                //+++++++++++++++++++++++
                if (num == 0) {
                    ventas.GuardarVentas(Fecha, VentaTotal, SubTotal, IVA, Total, Estado, IdCliente, IdEmpleado, IdTipoComprobante);
                    guardarDetalle();
                }
                numVenta = generaNumVenta();
                txtNumero.setText(numVenta);

                numVenta = generarNumFactura();

            }
            if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Venta Anulada!");
            }

        }

        btnNuevo.setEnabled(true);
        btnCancelar.setEnabled(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "Nuevo";
        modificar();
        limpiarCampos();
        limpiarTabla();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    private void btnAgregarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregarProductoKeyReleased

    }//GEN-LAST:event_btnAgregarProductoKeyReleased

    private void btnAgregarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoMouseClicked

    }//GEN-LAST:event_btnAgregarProductoMouseClicked

    private void btnComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobanteActionPerformed

        BuscarComprobante Comprobante = new BuscarComprobante();
        CentrarVentana(Comprobante);
        txtCantidadProducto.setText("");
    }//GEN-LAST:event_btnComprobanteActionPerformed

    private void txtIdComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdComprobanteActionPerformed

    private void txtStockProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockProductoActionPerformed

    private void txtPrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProductoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null, "Ingrese solo Números");
        }
    }//GEN-LAST:event_txtPrecioProductoKeyTyped

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        accion = "Buscar";
        BuscarProductosVentas Pro = new BuscarProductosVentas();
        frmPrincipal.tbn_escritorio.add(Pro);
        Pro.toFront();
        Pro.setVisible(true);
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
        // TODO add your handling code here:
        BuscarClientes cli = new BuscarClientes();
        frmPrincipal.tbn_escritorio.add(cli);
        cli.toFront();
        cli.setVisible(true);
    }//GEN-LAST:event_btnclientesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    public void CentrarVentana(JInternalFrame internalFrame) {
        int x = (frmPrincipal.tbn_escritorio.getWidth() / 2) - internalFrame.getWidth() / 2;
        int y = (frmPrincipal.tbn_escritorio.getHeight() / 2) - internalFrame.getHeight() / 2;
        if (internalFrame.isShowing()) {
            internalFrame.setLocation(x, y);
        } else {
            frmPrincipal.tbn_escritorio.add(internalFrame);
            internalFrame.setLocation(x, y);
            internalFrame.show();
        }
    }

    void obtenerUltimoIdVenta() {
        try {
            Metodos_Ventas oVenta = new Metodos_Ventas();
            rs = oVenta.obtenerUltimoIdVenta();
            while (rs.next()) {
                idventa = rs.getInt(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    void guardarDetalle() {
        obtenerUltimoIdVenta();
        Metodos_Ventas DetalleVentas = new Metodos_Ventas();

        String strId;
        int fila = 0;
        double cant = 0, ncant, stock;
        fila = tblDetalleProducto.getRowCount();
        for (int f = 0; f < fila; f++) {
            String IdVentas = String.valueOf(idventa);
            String IdProducto = String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 1));
            String Costo = String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 8));
            String Cantidad = String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 5));
            String precio = String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 6));
            String Total = String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 7));

            if (num == 0) {
                DetalleVentas.GuardarDetalleVentas(IdVentas, IdProducto, Cantidad, Costo, precio, Total);

                try {
                    Metodos_Productos oProducto = new Metodos_Productos();

                    rs = oProducto.listarProductoActivoPorParametro("id", ((String) tblDetalleProducto.getValueAt(f, 0)));
                    while (rs.next()) {
                        cant = Double.parseDouble(rs.getString(4));
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                    System.out.println(ex.getMessage());
                }

                Metodos_Productos oProducto = new Metodos_Productos();

                strId = ((String) tblDetalleProducto.getValueAt(f, 0));

                ncant = Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 5)));

                stock = cant - ncant;
                String Producto = String.valueOf(stock);
                if (num == 0) {
                    oProducto.actualizarProductoStock(strId, Producto);
                }

            }
        }
    }

    void CrearTablaDetalleProducto() {

        TableCellRenderer render = new DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (column == 0 || column == 1 || column == 4 || column == 5 || column == 6 || column == 7) {
                    l.setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    l.setHorizontalAlignment(SwingConstants.LEFT);
                }

                if (isSelected) {
                    l.setBackground(new Color(0, 102, 102));
                    l.setForeground(Color.WHITE);
                } else {
                    l.setForeground(Color.BLACK);
                    if (row % 2 == 0) {
                        l.setBackground(Color.WHITE);
                    } else {
                        l.setBackground(new Color(240, 240, 240));
                    }
                }
                return l;
            }
        };

        for (int i = 0; i < tblDetalleProducto.getColumnCount(); i++) {
            tblDetalleProducto.getColumnModel().getColumn(i).setCellRenderer(render);
        }

        tblDetalleProducto.setAutoResizeMode(tblDetalleProducto.AUTO_RESIZE_OFF);

        int[] anchos = {50, 70, 170, 170, 170, 80, 80, 80, 80};
        for (int i = 0; i < tblDetalleProducto.getColumnCount(); i++) {
            tblDetalleProducto.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        setOcultarColumnasJTable(tblDetalleProducto, new int[]{0, 8});

    }

    public String generarNumFactura() {

        Metodos_Ventas oVenta = new Metodos_Ventas();
        try {

            rs = oVenta.obtenerUltimoIdVenta();
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    Scanner s = new Scanner(rs.getString(1));
                    int c = s.useDelimiter("C").nextInt() + 1;

                    if (c < 10) {
                        return "000000" + c;
                    }
                    if (c < 100) {
                        return "00000" + c;
                    }
                    if (c < 1000) {
                        return "0000" + c;
                    }
                    if (c < 10000) {
                        return "000" + c;
                    } else {
                        return "00" + c;
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "000001";

    }

    public String generaIdVenta() {

        Metodos_Ventas oVenta = new Metodos_Ventas();
        try {

            rs = oVenta.obtenerUltimoIdVenta();
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    Scanner s = new Scanner(rs.getString(1));
                    int c = s.useDelimiter("C").nextInt() + 1;

                    if (c < 10) {
                        return "" + c;
                    }
                    if (c < 100) {
                        return "" + c;
                    }
                    if (c < 1000) {
                        return "" + c;
                    }
                    if (c < 10000) {
                        return "" + c;
                    } else {
                        return "C" + c;
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "1";

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    public static javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnComprobante;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImporte;
    private javax.swing.JButton btnLimpiarTabla;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnclientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpnImporte;
    private javax.swing.JTable tblDetalleProducto;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidadProducto;
    public static javax.swing.JTextField txtCategoria;
    public static javax.swing.JTextField txtCodigoProducto;
    public static javax.swing.JTextField txtComprobante;
    public static javax.swing.JTextField txtCosto;
    public static javax.swing.JTextField txtDescripcionProducto;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtFechas;
    private javax.swing.JTextField txtIGV;
    public static javax.swing.JTextField txtIdCliente;
    public static javax.swing.JTextField txtIdComprobante;
    public static javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtImporte;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtPrecioProducto;
    public static javax.swing.JTextField txtRuc;
    public static javax.swing.JTextField txtStockProducto;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalPagar;
    private javax.swing.JTextField txtTotalProducto;
    private javax.swing.JTextField txtTotalVenta;
    public static javax.swing.JTextField txtUltimoId;
    // End of variables declaration//GEN-END:variables
}
