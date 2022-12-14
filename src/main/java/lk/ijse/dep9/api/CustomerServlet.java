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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "CustomerServlet", value = "/customers/*")
public class CustomerServlet extends HTTPServlet2 {
    @Resource(lookup = "java:/comp/env/jdbc/dep9_pos")
    private DataSource pool;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getPathInfo()==null || request.getPathInfo().equals("/")){
            String query = request.getParameter("q");
            String page = request.getParameter("page");
            String size = request.getParameter("size");

            if (query!=null && page!=null && size!=null){
                if(!(page.matches("\\d+")) || !(size.matches("\\d+"))){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid page or size");
                }else {
                    searchPaginatedCustomers(query,Integer.parseInt(size),Integer.parseInt(page),response);
                }
            }else{
                try {
                    loadAllCustomers(response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failed to load customers");
                }
            }

        }else{
            Matcher matcher = Pattern.compile("^/([A-Fa-f0-9]{8}(-[A-Fa-f0-9]{4}){3}-[A-Fa-f0-9]{12})/?$").matcher(request.getPathInfo());
            if(matcher.matches()){
                getCustomerDetails(matcher.group(1),response);
            }else{
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED,"Expected valid UUID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getPathInfo() == null || request.getPathInfo().equals("/")){
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
            return;
        }
        Matcher matcher = Pattern.compile("^/([A-Fa-f0-9]{8}(-[A-Fa-f0-9]{4}){3}-[A-Fa-f0-9]{12})/?$")
                .matcher(request.getPathInfo());
        if (matcher.matches()){
            deleteCustomer(matcher.group(1), response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "Expected valid UUID");
        }

    }
    private  void deleteCustomer(String customerId, HttpServletResponse response){
        try (Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
            stm.setString(1,customerId);
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"Invalid customer Id");
            }else{
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doPatch(request, response);
    }

    private void loadAllCustomers(HttpServletResponse response) throws SQLException, IOException {
        try {
            Connection connection = pool.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM customer");

            ArrayList<CustomerDTO> customers = new ArrayList<>();
            while (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");

                CustomerDTO dto = new CustomerDTO(id, name, address);
                customers.add(dto);
            }

            connection.close();

            Jsonb jsonb = JsonbBuilder.create();
            response.setContentType("application/json");
            jsonb.toJson(customers,response.getWriter());


        } catch (SQLException |IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failed to Load Customers");
        }


    }

    private void searchPaginatedCustomers(String query,int size, int page, HttpServletResponse response) throws IOException {
        try (Connection connection = pool.getConnection()) {
            PreparedStatement stm1 = connection.prepareStatement("SELECT COUNT(id) AS count FROM dep9_pos.customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ?");
            PreparedStatement stm2 = connection.prepareStatement("SELECT * FROM dep9_pos.customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ? LIMIT ? OFFSET ?");
            query = "%"+query+"%";
            for (int i = 1; i <= 3; i++) {
                stm1.setString(i,query);
                stm2.setString(i,query);
            }
            stm2.setInt(4,size);
            stm2.setInt(5,(page - 1) * size);

            ResultSet rst = stm1.executeQuery();
            rst.next();
            response.setIntHeader("X-Total-Count",rst.getInt("count"));

            rst = stm2.executeQuery();

            ArrayList<CustomerDTO> customers = new ArrayList<>();
            while(rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");

                customers.add(new CustomerDTO(id,name,address));
            }
            response.setContentType("application/json");
            JsonbBuilder.create().toJson(customers,response.getWriter());


        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failed to load the customers");
        }
    }
    private void getCustomerDetails(String customerId, HttpServletResponse response) throws IOException {
        try(Connection connection = pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
            stm.setString(1, customerId);
            ResultSet rst = stm.executeQuery();

            if (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                response.setContentType("application/json");
                JsonbBuilder.create().toJson(new CustomerDTO(id, name, address), response.getWriter());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid customer id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to execute the query");
        }
    }
}
