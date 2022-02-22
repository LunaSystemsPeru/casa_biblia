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
public class Print_Nota_Salida_prueba {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    public Print_Nota_Salida_prueba() {
    }

    public void generar_ticket() {
        PrinterMatrix printer = new PrinterMatrix();

        //  Extenso e = new Extenso();
        //   e.setNumber(101.85);
        printer.setOutSize(64, 80);

        for (int i = 1; i < 65; i++) {
            printer.printTextLinCol(i, 1, "=========" + i);
        }

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
        //printerService.printBytes(defaultPrintService.getName(), initEP);

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
        //printerService.printBytes(defaultPrintService.getName(), cutP);
    }

}
