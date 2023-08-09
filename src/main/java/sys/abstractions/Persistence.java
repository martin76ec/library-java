/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sys.abstractions;

import java.sql.ResultSet;

/**
 *
 * @author martin
 */
public interface Persistence {
    ResultSet run(String sql);
    int save(String sql);
    boolean update(String sql);
    boolean delete(String sql);
}
