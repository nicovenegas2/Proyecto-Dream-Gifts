package Controlador;

import DreamsGifts.Login;
import Modelo.Conexion;
import Modelo.ConsultaUsuarios;
import Modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CtrLogin implements ActionListener{
    public static Login lgn;
    public static Conexion conn;
    public static ConsultaUsuarios conUser;
    public static CtrPrincipal ctrp;
    
    public CtrLogin() throws SQLException {
        conn = new Conexion();
        conUser = new ConsultaUsuarios();
        lgn = new Login();
        lgn.setVisible(true);
        ctrp = new CtrPrincipal(lgn);
        this.lgn.btnLogin.addActionListener(this);
        this.lgn.btnCancel.addActionListener(this);
    }
    
    public void limpiarCampos(){
        lgn.textPassw.setText("");
        lgn.textUser.setText("");
    }
    
    public void inicioSesion(){
        Usuario user = new Usuario();
        user.setContraseña(lgn.textPassw.getText());
        user.setUser(lgn.textUser.getText());
        if (conUser.iniciarSesion(user)){
            lgn.setVisible(false);
            this.limpiarCampos();
            ctrp.iniciar();
        } else {
            JOptionPane.showMessageDialog(lgn, "usuario o contraseña incorrecta");
        }
    }
    
       
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == lgn.btnLogin){
            this.inicioSesion();
        }
        
        if (e.getSource() == lgn.btnCancel){
            this.limpiarCampos();
        }
        
    }
}
