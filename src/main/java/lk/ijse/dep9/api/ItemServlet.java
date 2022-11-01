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
public class ItemServlet<ConnectionPool> extends HTTPServlet2 {

    @Resource(lookup = "java:/comp/env/jdbc/dep9_pos")
    private DataSource pool;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadAllItems(HttpServletRequest request, HttpServletResponse response);
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
    }

    private void searchPaginatedItems(String query,int size, int page, HttpServletResponse response){

    }
    private void getItemDetails(String customerId, HttpServletResponse response){

    }
}
