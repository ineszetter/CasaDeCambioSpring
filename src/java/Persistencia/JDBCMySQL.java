/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Proyectos
 */
public class JDBCMySQL {

    public DriverManagerDataSource Conectar() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/casadecambio");
        datasource.setUsername("root");
        datasource.setPassword("passw0rd");
        return datasource;
    }

}
