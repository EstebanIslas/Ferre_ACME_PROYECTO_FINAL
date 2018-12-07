package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelLogin;
import views.ViewLoggin;
import views.ViewMain;

/**
 *
 * @author EstebanIslas
 */
public class ControllerLogin {

    public ModelLogin modelLogin;
    public ViewLoggin viewLoggin;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewLoggin.jb_entrar) {
                //System.out.println(modelLogin.isValue());
                loguearDatos();
                
            }
        }
    };

    public ControllerLogin(ModelLogin modelLogin, ViewLoggin viewLoggin) {
        this.modelLogin = modelLogin;
        this.viewLoggin = viewLoggin;
        setActionListener();
        initComponents();
    }

    public void setActionListener() {
        viewLoggin.jb_entrar.addActionListener(actionListener);
    }

    private void loguearDatos() {
        modelLogin.loguear(viewLoggin.jtf_usuario.getText(), viewLoggin.jtf_password.getText());
    }

    private void initComponents() {
        viewLoggin.setLocationRelativeTo(null);
        viewLoggin.setVisible(true);

    }
}
