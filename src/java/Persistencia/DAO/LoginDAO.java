/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO;

import Beans.Tblusuario;
import Persistencia.JDBCMySQL;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author Proyectos
 */
public class LoginDAO {

    JDBCMySQL conexion = new JDBCMySQL();
    JdbcTemplate jdbctemplate = new JdbcTemplate(conexion.Conectar());
    ModelAndView modelandview = new ModelAndView();

    @RequestMapping("login.htm")
    public Boolean autenticarUsuario(Tblusuario usuario) {

        String query = "SELECT email, usuario "
                + " FROM tblusuario "
                + " WHERE email = ? "
                + " AND password = ?";

        List datos = this.jdbctemplate.queryForList(query, usuario.getEmail(), usuario.getPassword());

        if (1 == datos.size()) {
            return true;
        }
        return false;
    }
}
