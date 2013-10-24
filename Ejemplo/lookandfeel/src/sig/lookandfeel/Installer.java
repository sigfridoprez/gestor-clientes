/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.lookandfeel;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.io.File;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.openide.modules.InstalledFileLocator;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        try {
//            File theme = InstalledFileLocator.getDefault().locate(
//                    "NimRODThemeFile.theme",
//                    "NimrodLAF",
//                    false);
//            NimRODTheme nt = new NimRODTheme(theme.toString());
//            NimRODLookAndFeel nf = new NimRODLookAndFeel();
//            NimRODLookAndFeel.setCurrentTheme(nt);
            NimbusLookAndFeel nlaf = new NimbusLookAndFeel();
            UIManager.setLookAndFeel(nlaf);
        } catch (UnsupportedLookAndFeelException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
