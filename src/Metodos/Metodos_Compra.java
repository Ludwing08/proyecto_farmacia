
package Metodos;

import Conexion.ConexionBD;
import static Metodos.Metodos_Ventas.GP;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Metodos_Compra {
    
private Connection connection=new ConexionBD().conectar();

    public ResultSet obtenerUltimoIdCompra() throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call UltimoIdCompra()}");
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }
    
    public int GuardarCompras(String Fecha, String SubTotal, String Total, String IVA, String Estado, String IdProveedor, String IdEmpleados, String IdTipoComprobante) {
        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = "INSERT INTO compra (Fecha, SubTotal, Total, IVA, Estado, idProveedor, idEmpleado, idTipoComprobante) VALUES (?,?,?,?,?,?,?,?)";

        try {
            conexion = ConexionBD.conectar();
            GP = conexion.prepareStatement(sentencia_guardar);            
            GP.setString(1, Fecha);            
            GP.setString(2, SubTotal);
            GP.setString(3, Total);
            GP.setString(4, IVA);
            GP.setString(5, Estado);
            GP.setString(6, IdProveedor);
            GP.setString(7, IdEmpleados);
            GP.setString(8, IdTipoComprobante);
            resultado = GP.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Compra realizada con Éxito");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }
    
    public int GuardarDetalleCompras( String IdCompra, String IdProducto, String Cantidad, String Costo, String Total) {
        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = "INSERT INTO detallecompra (idCompra, idProducto, Cantidad, Costo, Subtotal) VALUES (?,?,?,?,?)";

        try {
            conexion = ConexionBD.conectar();
            GP = conexion.prepareStatement(sentencia_guardar);
            GP.setString(1, IdCompra);
            GP.setString(2, IdProducto);
            GP.setString(3, Cantidad);
            GP.setString(4, Costo);
            GP.setString(5, Total);
            resultado = GP.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Compra realizada con Éxito");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }
    
    public ResultSet listarCompras(String criterio,java.util.Date fechaini, java.util.Date fechafin) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call ComprasPorFecha(?,?,?)}");
            statement.setString ("criterio", criterio);
            statement.setDate ("fechaIni", new java.sql.Date(fechaini.getTime()));
            statement.setDate ("fechaFin", new java.sql.Date(fechafin.getTime()));
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    } 


 public ResultSet listarDetalleCompraPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call DetalleCompraParametro(?,?)}");
            statement.setString("criterio", criterio);
            statement.setString("buscar", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }  
 
 public void actualizarCompraEstado(String codigo,String Anular){
        try{
            CallableStatement statement=connection.prepareCall("{call ActualizarCompraEstado(?,?)}");
            statement.setString("pidcompra",codigo);
            statement.setString("pestado",Anular);        
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Compra Anulada!","Mensaje del Sistema",1);
    }
}
