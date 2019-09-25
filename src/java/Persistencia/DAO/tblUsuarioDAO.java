/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO;

import Beans.Tblusuario;
import Helpers.SecurityHelper;
import Persistencia.JDBCMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
                    + " AND contrasena = ?";

            List datos = this.jdbctemplate.queryForList(query, usuario.getEmail(),
                    SecurityHelper.encrypt(usuario.getContrasena(), usuario.getLlave()));
            //TODO: enviar key mediante otro select
            //TODO: generar el key[] mediante el select y el método generateKey()

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
                byte[] llave = SecurityHelper.generateKey();
                String query = "INSERT INTO tblusuario "
                        + "  (email        "
                        + "  ,usuario      "
                        + "  ,contrasena"
                        + "  ,llave)    "
                        + " VALUES (?,?,?,?) ";

                int registroAfectado = this.jdbctemplate.update(query,
                        usuario.getEmail(),
                        usuario.getUsuario(),
                        SecurityHelper.encrypt(usuario.getContrasena(), llave), llave);

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
    public Tblusuario obtenerKeyUsuario(Tblusuario usuario) {
        return (Tblusuario) jdbctemplate.queryForObject("SELECT * FROM tblusuario WHERE usuario = '".concat(usuario.getUsuario()).concat("'"), new UsuarioRowMapper());
    }

    /**
     *
     * @param usuario
     * @return
     */
    @RequestMapping("registro.htm")
    public Boolean validarUsuario(Tblusuario usuario) {

        String query = "SELECT *  "
                + " FROM tblusuario "
                + " WHERE email = ? "
                + " OR usuario = ?  ";

        List datos = this.jdbctemplate.queryForList(query, usuario.getEmail(), usuario.getUsuario());

        if (1 == datos.size()) {
            return true;
        }
        return false;
    }

    class UsuarioRowMapper implements RowMapper {

        @Override
        public Tblusuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tblusuario usuario = new Tblusuario();
            usuario.setEmail(rs.getString("email"));
            usuario.setLlave(rs.getBytes("llave"));
            usuario.setContrasena(rs.getBytes("contrasena"));
            usuario.setUsuario(rs.getString("usuario"));
            return usuario;
        }

    }

}
