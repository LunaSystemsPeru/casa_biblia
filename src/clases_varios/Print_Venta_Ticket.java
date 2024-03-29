/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_varios;

import br.com.adilson.util.PrinterMatrix;
import clases.cl_almacen;
import clases.cl_cliente;
import clases.cl_conectar;
import clases.cl_documento_sunat;
import clases.cl_empresa;
import clases.cl_productos_ventas;
import clases.cl_varios;
import clases.cl_venta;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
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
public class Print_Venta_Ticket {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    cl_venta c_venta = new cl_venta();
    cl_productos_ventas c_detalle = new cl_productos_ventas();
    cl_empresa c_empresa = new cl_empresa();
    cl_almacen c_almacen = new cl_almacen();
    cl_cliente c_cliente = new cl_cliente();
    cl_documento_sunat c_sunat = new cl_documento_sunat();
    leer_numeros leer = new leer_numeros();

    private int id_venta;
    private int id_almacen;

    public Print_Venta_Ticket() {
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public void generar_ticket() {
        PrinterMatrix printer = new PrinterMatrix();

        c_venta.setId_venta(id_venta);
        c_venta.setId_almacen(id_almacen);
        c_venta.validar_venta();

        c_detalle.setId_venta(id_venta);
        int contar = c_detalle.contar_lineas();

        c_sunat.setId(c_venta.getId_tido());
        c_sunat.validar_documento();

        //String serie = c_varios.ceros_izquieda_letras(4, c_venta.getSerie());
        String numero = c_varios.ceros_izquieda_numero(7, c_venta.getNumero());

        c_almacen.setId(c_venta.getId_almacen());
        c_almacen.validar_almacen();

        c_empresa.setId(c_almacen.getEmpresa());
        c_empresa.validar_empresa();

        c_cliente.setCodigo(c_venta.getId_cliente());
        c_cliente.comprobar_cliente();

        //  Extenso e = new Extenso();
        //   e.setNumber(101.85);
        printer.setOutSize(31 + contar, 40);

        //imprimir cabezera
        printer.printTextLinCol(1, 1, varios_impresion.centrar_texto(40, "** CASA DE LA BIBLIA **"));
        printer.printTextLinCol(2, 1, varios_impresion.centrar_texto(40, "RUC: " + c_empresa.getRuc()));
        printer.printTextLinCol(3, 1, varios_impresion.centrar_texto(40, c_empresa.getRazon()));
        printer.printTextWrap(3, 4, 0, 40, c_almacen.getDireccion());

        printer.printTextLinCol(7, 1, varios_impresion.centrar_texto(40, "SUCURSAL: " + c_almacen.getNombre()));
        /*dni  ticket boleta, ruc ticket factura*/
        String documentocliente = "";
        System.out.println(c_cliente.getCodigo());
        if (c_cliente.getDocumento().length() < 10) {
            documentocliente = "TICKET BOLETA";
        } else {
            documentocliente = "TICKET FACTURA";
        }
        printer.printTextLinCol(8, 1, varios_impresion.centrar_texto(40, documentocliente));
        printer.printTextLinCol(9, 1, varios_impresion.centrar_texto(40, c_almacen.getTicketera() + " - " + numero));
        //printer.printTextLinCol(10, 1, "FECHA EMISION: " + c_varios.fecha_usuario(c_venta.getFecha()));
        printer.printTextLinCol(10, 1, "FECHA EMISION: " + c_varios.getFechaHora());
        printer.printTextLinCol(11, 1, "CLIENTE DOC: " + c_cliente.getDocumento());
        printer.printTextLinCol(12, 1, c_cliente.getNombre());

        //cargar detalle de productos
        int add_filas = 0;
        double suma_total = 0;
        try {
            Statement st = c_conectar.conexion();

            /*String query = "select p.id_producto, p.descripcion, p.marca, p.modelo, pv.cantidad, pv.precio "
                    + "from productos_ventas as pv "
                    + "inner join productos as p on p.id_producto = pv.id_producto "
                    + "where pv.id_almacen = '" + c_venta.getId_almacen() + "' and id_ventas = '" + c_venta.getId_venta() + "'";*/
            String query = "SELECT p.id_producto, p.descripcion, pv.cantidad,"
                    + " pv.precio FROM productos_ventas AS pv "
                    + "INNER JOIN productos AS p ON p.id_producto = pv.id_producto "
                    + "WHERE  pv.id_ventas = '" + c_venta.getId_venta() + "'";

            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                String pdescripcion = (rs.getString("descripcion").trim()).trim();
                //si cantidad de letras de descripcion es mayor a 68 , aplicar substring a 67
                if (pdescripcion.length() > 68) {
                    pdescripcion = pdescripcion.substring(0, 66);
                }
                if (pdescripcion.length() > 26 & pdescripcion.length() < 29) {
                    pdescripcion = pdescripcion.substring(0, 26);
                }

                int pcantidad = rs.getInt("cantidad");
                double pprecio = rs.getDouble("precio");
                double pparcial = pprecio * pcantidad;
                suma_total += pparcial;
                String sparcial = c_varios.formato_totales(pparcial);

                String texto_linea = pcantidad + " " + pdescripcion;

                //imprimir linea producto
                printer.printTextWrap(13 + add_filas, 15 + add_filas + 1, 0, 40, texto_linea);
                add_filas++;

                //si cantidad de letras de descripcion es mayor a 28 saltar una linea
                if (texto_linea.length() > 28) {
                    add_filas++;
                }

                //imprimir linea parcial
                printer.printTextLinCol(13 + add_filas, 29, " x " + varios_impresion.texto_derecha(9, sparcial));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //imprimir pie de ticket
        double total = suma_total;
        double subtotal = total / 1.18;
        double igv = total / 1.18 * 0.18;
        String numeros_texto = leer.Convertir(total + "", true) + " SOLES";

        add_filas++;
        add_filas++;
        printer.printTextLinCol(13 + add_filas, 1, varios_impresion.texto_derecha(30, "SUB TOTAL"));
        printer.printTextLinCol(13 + add_filas, 31, varios_impresion.texto_derecha(10, c_varios.formato_totales(subtotal)));

        add_filas++;
        printer.printTextLinCol(13 + add_filas, 1, varios_impresion.texto_derecha(30, "IGV"));
        printer.printTextLinCol(13 + add_filas, 31, varios_impresion.texto_derecha(10, c_varios.formato_totales(igv)));

        add_filas++;
        printer.printTextLinCol(13 + add_filas, 1, varios_impresion.texto_derecha(30, "TOTAL"));
        printer.printTextLinCol(13 + add_filas, 31, varios_impresion.texto_derecha(10, c_varios.formato_totales(total)));

        add_filas++;
        printer.printTextWrap(13 + add_filas, 14 + add_filas + 1, 0, 40, numeros_texto);

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
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        printerService.printBytes(defaultPrintService.getName(), initEP);

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;

        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (PrintException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "error al imprimir \n" + ex.getLocalizedMessage());
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
        //enviar comando de corte
        printerService.printBytes(defaultPrintService.getName(), cutP);
    }
}
