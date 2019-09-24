/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO;

import Beans.Tblusuario;
import Helpers.SecurityHelper;
import Persistencia.JDBCMySQL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author Proyectos
 */
public class tblUsuarioDAO {

    JDBCMySQL conexion = new JDBCMySQL();
    JdbcTemplate jdbctemplate = new JdbcTemplate(conexion.Conectar());
    ModelAndView modelandview = new ModelAndView();

    @RequestMapping("login.htm")
    public Boolean autenticarUsuario(Tblusuario usuario) {
        try {
            String query = "SELECT email, usuario "
                    + " FROM tblusuario "
                    + " WHERE email = ? "
                    + " AND password = ?";

            List datos = this.jdbctemplate.queryForList(query, usuario.getEmail(),
                    SecurityHelper.encrypt(usuario.getPassword()));
            //TODO: enviar key mediante otro select

            if (1 == datos.size()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            Logger.getLogger(tblUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @RequestMapping("registro.htm")
    public Boolean registrarUsuario(Tblusuario usuario) {
        if (!validarUsuario(usuario)) {
            try {
                String query = "INSERT INTO tblusuario "
                        + "  (email        "
                        + "  ,usuario      "
                        + "  ,password)    "
                        + " VALUES (?,?,?) ";

                int registroAfectado = this.jdbctemplate.update(query,
                        usuario.getEmail(),
                        usuario.getUsuario(),
                        SecurityHelper.encrypt(usuario.getPassword()));

                if (1 == registroAfectado) {
                    return true;
                }
            } catch (Exception ex) {
                Logger.getLogger(tblUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     *
     * @param usuario
     * @return
     */
    @RequestMapping("login.htm")
    public Byte obtenerKeyUsuario(Tblusuario usuario) {

        Byte key = null;
        String query = "SELECT key  "
                + " FROM tblusuario "
                + " WHERE email = ? "
                + " OR usuario = ?  ";

        List datos = this.jdbctemplate.queryForList(query, usuario.getEmail(), usuario.getUsuario());

        if (1 == datos.size()) {
            key = Byte.valueOf(datos.get(0).toString());
        }
        return key;
    }

    /**
     *
     * @param usuario
     * @return
     */
    @RequestMapping("registro.htm")
    public Boolean validarUsuario(Tblusuario usuario) {

        String query = "SELECT nombre  "
                + " FROM tblusuario "
                + " WHERE email = ? "
                + " OR usuario = ?  ";

        List datos = this.jdbctemplate.queryForList(query, usuario.getEmail(), usuario.getUsuario());

        if (1 == datos.size()) {
            return true;
        }
        return false;
    }

}
