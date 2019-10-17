/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_varios;

import br.com.adilson.util.PrinterMatrix;
import clases.cl_almacen;
import clases.cl_conectar;
import clases.cl_documento_sunat;
import clases.cl_empresa;
import clases.cl_salida;
import clases.cl_varios;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

/**
 *
 * @author gerenciatecnica
 */
public class Print_Nota_Salida {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    cl_salida c_salida = new cl_salida();
    cl_empresa c_empresa = new cl_empresa();
    cl_almacen c_almacen = new cl_almacen();
    cl_documento_sunat c_sunat = new cl_documento_sunat();
    leer_numeros leer = new leer_numeros();

    private int id_salida;

    public Print_Nota_Salida() {
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public void generar_ticket() {
        PrinterMatrix printer = new PrinterMatrix();

        c_salida.setId_salida(id_salida);
        c_salida.obtener_datos();

        c_sunat.setId(1);
        c_sunat.validar_documento();

        String serie = c_varios.ceros_izquieda_numero(4, c_salida.getId_almacen());
        String numero = c_varios.ceros_izquieda_numero(7, c_salida.getId_salida());

        c_almacen.setId(c_salida.getId_almacen());
        c_almacen.validar_almacen();

        c_empresa.setId(c_almacen.getEmpresa());
        c_empresa.validar_empresa();

        //  Extenso e = new Extenso();
        //   e.setNumber(101.85);
        printer.setOutSize(65, 80);

        //imprimir cabezera
        printer.printCharAtCol(1, 0, 80, "=");
        printer.printTextLinCol(2, 1, "FECHA DE ENVIO:" + c_varios.fecha_usuario(c_salida.getFecha()));
        printer.printTextLinCol(3, 1, c_empresa.getRazon());
        printer.printTextLinCol(4, 1, c_empresa.getRuc());
        printer.printTextWrap(4, 5, 0, 80, c_empresa.getDireccion());

        printer.printTextLinCol(7, 1, "Partida: " + c_almacen.getDireccion());
        printer.printTextLinCol(8, 1, "Destino: " + c_salida.getDireccion());

        printer.printTextLinCol(8, 1, varios_impresion.centrar_texto(40, "NOTA VENTA"));
        printer.printTextLinCol(9, 1, varios_impresion.centrar_texto(40, c_almacen.getTicketera() + " - " + numero));
        printer.printTextLinCol(10, 1, "FECHA EMISION: " + c_varios.getFechaHora());

        //cargar detalle de productos
        int add_filas = 0;
        try {
            Statement st = c_conectar.conexion();

            /*String query = "select p.id_producto, p.descripcion, p.marca, p.modelo, pv.cantidad, pv.precio "
                    + "from productos_ventas as pv "
                    + "inner join productos as p on p.id_producto = pv.id_producto "
                    + "where pv.id_almacen = '" + c_venta.getId_almacen() + "' and id_ventas = '" + c_venta.getId_venta() + "'";*/
            String query = "SELECT ps.cantidad, p.descripcion, ps.precio "
                    + "FROM productos_salida as ps "
                    + "inner join productos as p on p.id_producto = ps.id_producto "
                    + "where ps.id_salida = '" + c_salida.getId_salida() + "' "
                    + "order by p.descripcion asc";

            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                String pdescripcion = (rs.getString("descripcion").trim()).trim();
                //si cantidad de letras de descripcion es mayor a 68 , aplicar substring a 67
                if (pdescripcion.length() > 68) {
                    pdescripcion = pdescripcion.substring(0, 66);
                }
                if (pdescripcion.length() < 29) {
                    pdescripcion = pdescripcion.substring(0, 26);
                }

                int pcantidad = rs.getInt("cantidad");
                double pprecio = rs.getDouble("precio");

                String texto_linea = pcantidad + " " + pdescripcion;

                //imprimir linea producto
                printer.printTextWrap(11 + add_filas, 15 + add_filas + 1, 0, 40, texto_linea);
                add_filas++;

                //si cantidad de letras de descripcion es mayor a 28 saltar una linea
                if (texto_linea.length() > 28) {
                    add_filas++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //imprimir pie de ticket
        add_filas++;
        add_filas++;

        //mostrar en consola
        printer.show();

        //grabar en txt
        printer.toFile("impresion.txt");

        //enviar a imprimir
        //leer archivo
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        //comandos impresora para reiniciar y cortar
        byte[] initEP = new byte[]{0x1b, '@'};
        byte[] cutP = new byte[]{0x1d, 'V', 1};

        //inciiar servicio impresion
        PrinterService printerService = new PrinterService();
        printerService.printString("BIXOLON SRP-270 (Copiar 3)", new String(initEP));

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "error al imprimir \n" + ex.getLocalizedMessage());
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //enviar comando de corte
        printerService.printBytes("BIXOLON SRP-270 (Copiar 3)", cutP);

    }
}
