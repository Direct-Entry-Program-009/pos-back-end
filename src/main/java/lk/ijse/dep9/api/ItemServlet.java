package lk.ijse.dep9.api;

import jakarta.annotation.Resource;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep9.dto.CustomerDTO;
import lk.ijse.dep9.dto.ItemDTO;
import lk.ijse.dep9.util.HTTPServlet2;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "ItemServlet", value = "/items")
public class ItemServlet extends HTTPServlet2 {

    @Resource(lookup = "java:/comp/env/jdbc/dep9_pos")
    private DataSource pool;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getPathInfo()==null || request.getPathInfo().equals("/")){
//            String query = request.getParameter("q");
//            String page = request.getParameter("page");
//            String size = request.getParameter("size");
//
//            if (query!=null && page!=null && size!=null){
//                if(!(page.matches("\\d+")) || !(size.matches("\\d+"))){
//                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid page or size");
//                }else {
//
//                }
//            }else{
//                try {
//                    //loadAllItems(response);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failed to load customers");
//                }
//            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        try(Connection connection = pool.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM item");

            ArrayList<ItemDTO> items = new ArrayList<>();

            while (rst.next()) {
                int stock = Integer.parseInt(rst.getString("stock"));
                String unit_price = rst.getString("unit_price");
                BigDecimal description = rst.getBigDecimal("description");
                String code = rst.getString("code");

                ItemDTO dto = new ItemDTO(stock, unit_price, description, code);
                items.add(dto);
            }

            JsonbConfig config = new JsonbConfig();
            config.withPropertyOrderStrategy();
            Jsonb jsonb = JsonbBuilder.create();
            response.setContentType("application/json");
            jsonb.toJson(items, response.getWriter());

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"Failed to load items");
            throw new RuntimeException(e);
        }

    }

    private void searchPaginatedItems(String query,int size, int page, HttpServletResponse response){

    }
    private void getItemDetails(String customerId, HttpServletResponse response){

    }
}

