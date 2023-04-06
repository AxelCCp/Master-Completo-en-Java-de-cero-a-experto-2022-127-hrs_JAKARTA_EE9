package jee.master.models.dao;

import jee.master.models.entity.Division;
import jee.master.models.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DivisionRepository implements IGenericRepository <Division> {

    public DivisionRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Division> list() throws SQLException {
        List<Division> divisions = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from division")){
            while(rs.next()){
                Division division = DivisionRepository.getDivision(rs);
                divisions.add(division);
            }
        }
        return divisions;
    }

    @Override
    public Division getById(Long id) throws SQLException {
        Division division = null;
        try(PreparedStatement pst = connection.prepareStatement("select * from division where id=?")){
            pst.setLong(1, id);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    division = getDivision(rs);
                }
            }
        }
        return division;
    }

    @Override
    public void save(Division division) throws SQLException {}
        /*String sql;
        if(division.getId() != null &&  division.getId() > 0){
            sql = "update division set name=? where id=?";
        }else{
          sql = "insert into division (name) values(?)";
        }
        try(PreparedStatement st = connection.prepareStatement(sql)){
            st.setString(1, division.getName());
            if(division.getId() != null && division.getId() > 0){
                st.setLong(1, division.getId());
            }
            st.executeUpdate();
        }
    }*/

    @Override
    public void deleteById(Long id) throws SQLException {

    }

    private static Division getDivision(ResultSet rs) throws SQLException {
        Division division = new Division();
        division.setId(rs.getLong("id"));
        division.setName(rs.getString("name"));
        return division;
    }

    private Connection connection;
}
