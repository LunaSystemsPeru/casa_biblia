
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casa_biblia;

import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
/**
 *
 * @author DELACRUZ
 * 
 * 
 */
public class Casa_Biblia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
        frm_principal menu = new frm_principal();
        //frm_login menu = new frm_login(null, true);
        menu.setVisible(true);
    }
    
}
