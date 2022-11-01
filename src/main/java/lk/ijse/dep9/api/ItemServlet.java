package lk.ijse.dep9.api;

import jakarta.annotation.Resource;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep9.dto.CustomerDTO;
import lk.ijse.dep9.util.HTTPServlet2;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ItemServlet", value = "/items")
public class ItemServlet extends HTTPServlet2 {

    @Resource(lookup = "java:/comp/env/jdbc/dep9_pos")
    private DataSource pool;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("doPost");
    }



    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doPatch(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
    private void loadAllItems(HttpServletResponse response) throws SQLException, IOException {
    }

    private void searchPaginatedItems(String query,int size, int page, HttpServletResponse response){

    }
    private void getItemDetails(String itemCode, HttpServletResponse response){
//        try {
//            Connection connection = pool.getConnection();
//            PreparedStatement stm = connection.prepareStatement("SELECT * FROM item WHERE id=?");
//            stm.setString(1,itemCode);
//            ResultSet rst = stm.executeQuery();
//
//            if(rst.next()){
//                String id = rst.getString("id");
//                String name = rst.getString("name");
//                String address = rst.getString("address");
//                String contact = rst.getString("contact");
//                response.setContentType("application/json");
//                JsonbBuilder.create().toJson(new MemberDTO(id, name, address, contact),response.getWriter());
//            }else {
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid member id");
//            }


//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Fail to Fetch members");
//        }
    }
}
