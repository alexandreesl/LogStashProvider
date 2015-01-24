package com.technology.alexandreesl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class LogStashProvider {

	private static Logger logger = Logger.getLogger(LogStashProvider.class);

	public static void main(String[] args) throws IOException {

		try {

			logger.info("STARTING DATA COLLECTION");

			List<String> data = new ArrayList<String>();

			Customer customer = new Customer();
			customer.setName("Alexandre");
			customer.setAge(32);
			customer.setSex('M');
			customer.setIdentification("4434554567");

			List<Order> orders = new ArrayList<Order>();

			for (int counter = 1; counter < 10; counter++) {

				Order order = new Order();

				order.setOrderId(counter);
				order.setProductId(counter);
				order.setCustomerId(customer.getIdentification());
				order.setQuantity(counter);

				orders.add(order);

			}

			logger.info("FETCHING RESULTS INTO DESTINATION");

			PrintWriter file = new PrintWriter(new FileWriter(
					"/home/alexandreesl/logstashdataexample/data"
							+ new Date().getTime() + ".txt"));

			file.println("1" + customer.getName() + customer.getSex()
					+ customer.getAge() + customer.getIdentification());

			for (Order order : orders) {
				file.println("2" + order.getOrderId() + order.getCustomerId()
						+ order.getProductId() + order.getQuantity());
			}

			logger.info("CLEANING UP!");

			file.flush();
			file.close();

			// forcing a error to simulate stack traces
			PrintWriter fileError = new PrintWriter(new FileWriter(
					"/etc/nopermission.txt"));

		} catch (Exception e) {

			logger.error("ERROR!", e);
		}

	}

}
