/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import sig.user.management.UserManagementPanel;

public class Installer extends ModuleInstall {

    private LoginFrame passwordFromForm = new LoginFrame();

    @Override
    public void restored() {
        createLoginDialog();
    }

    private void createLoginDialog() {
        JButton ok = new JButton();
        ok.setText("OK");
        JButton cancel = new JButton();
        cancel.setText("Cancel");

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                exit();
            }
        });

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                authenticate();
            }
        });

        NotifyDescriptor nd = new NotifyDescriptor.Confirmation(passwordFromForm, "Login");
        nd.setOptions(new Object[]{ok, cancel});
        nd.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (NotifyDescriptor.CLOSED_OPTION.equals(evt.getNewValue())) {
                    exit();
                }
            }
        });
        DialogDisplayer.getDefault().notifyLater(nd);
    }

    private void authenticate() {
        if (NbPreferences.forModule(UserManagementPanel.class).get("user", "").equals(this.passwordFromForm.getUsername())) {
            try {
                char[] passwordFromForm = null;
                char[] passwordFromPref = NbPreferences.forModule(UserManagementPanel.class).get("pass", "").toCharArray();

                String passwordPref = new String(this.passwordFromForm.getPassword());
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] tmp = passwordPref.getBytes();
                md5.update(tmp);
                passwordFromForm = byteArrToString(md5.digest()).toCharArray();
                int correctCount = 0;
                if (passwordFromForm.length != passwordFromPref.length) {
                    exit();
                }
                for (int i = 0; i < passwordFromPref.length; i++) {
                    if (passwordFromPref[i] == passwordFromForm[i]) {
                        correctCount++;
                    }
                }
                if (passwordFromPref.length == correctCount) {
                    //do nothing here
                } else {
                    exit();
                }
            } catch (NoSuchAlgorithmException ex) {
                Exceptions.printStackTrace(ex);
            }
        } else {
            exit();
        }
    }

    private static String byteArrToString(byte[] b) {
        String res = null;
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int j = b[i] & 0xff;
            if (j < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(j));
        }
        res = sb.toString();
        return res.toUpperCase();
    }

    private void exit() {
        LifecycleManager.getDefault().exit();
    }
}
