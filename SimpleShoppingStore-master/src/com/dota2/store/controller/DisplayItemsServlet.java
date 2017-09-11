package com.dota2.store.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dota2.store.beans.Hero;
import com.dota2.store.beans.SetDetails;
import com.dota2.store.cart.ShoppingCart;
import com.dota2.store.model.DBConnection;
import com.dota2.store.model.StoreController;

/**
 * Servlet implementation class ItemCatalogServlet
 */
public class DisplayItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession hs;
	ShoppingCart cart;
	private static Connection conn;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request the present active Session.
		hs = request.getSession();

		StoreController ic = new StoreController();
		// returns the List of heros
		List<Hero> hero = ic.getHerosList();

		// set the list of heros to the session.
		hs.setAttribute("herosList", hero);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/store.jsp?page=herosList");
		rd.forward(request, response);

	}
	
	public static List<SetDetails> getSetDetailsList(int id) throws SQLException {

		List<SetDetails> lists = new ArrayList<SetDetails>();

		conn = DBConnection.connect();

		String sql = "select HeroName from herodetails";

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			lists.add(new SetDetails(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
		}

		ps.close();
		return lists;

	}


}
