# Stock Exchange Service

This project is a simple Stock Exchange Service which provides REST API and WebSocket support to handle stock orders (buy/sell) and real-time notifications for connected users.

Users can to connect to the service using REST API or WebSocket. The service can receive, save, and notify about stock orders (buy/sell) in JSON format.


## Before running the project, make sure you have the following software installed:

 > Java 17 or higher

 > Maven for building the project

 > PostgreSQL database (Additionally, you will need to set up the application.properties file with your database credentials)
 
 
 
## Building the Project

To build the project, run the following Maven command from the project root directory:

	mvn clean install

This will compile the project and generate the executable JAR file.


## Running the Project

Once the project is built, you can run the application using the following Maven command:

	mvn spring-boot:run

By default, the application will run on http://localhost:8080.


## Testing the API Endpoints

Here are the available REST API endpoints:

###Create a new order (POST)

Endpoint: /orders

Request Body:

	{
	  "price": 100.5,
	  "amount": 10,
	  "type": "BUY"
	}

or

	{
	  "price": 50,
	  "amount": 10,
	  "type": "SELL"
	}

Response:

	{
	  "status": "success",
	  "message": "Order created successfully",
	  "order": {
	    "id": 1,
	    "price": 100.5,
	    "amount": 10,
	    "type": "BUY"
	  }
	}

###Get all buy orders (GET)

Endpoint: /orders/buy

Response:

	[
	  {
	    "id": 3,
	    "price": 200,
	    "amount": 15,
	    "type": "BUY"
	  },
	  {
	    "id": 1,
	    "price": 100.5,
	    "amount": 10,
	    "type": "BUY"
	  }
	]

###Get all sell orders (GET)

Endpoint: /orders/sell

	[
	  {
	    "id": 2,
	    "price": 50,
	    "amount": 10,
	    "type": "SELL"
	  },
	  {
	    "id": 4,
	    "price": 200,
	    "amount": 15,
	    "type": "SELL"
	  }
	]

###Test WebSocket notifications via Postman

To test WebSocket notifications through Postman, follow these steps:

* Prepare WebSocket Endpoint ( it should be running at `ws://localhost:8080/ws/orders` )

* Open Postman and click on 'New Request', select 'WebSocket Request' in the new window.

* Enter the WebSocket endpoint URL and establish the connection to the WebSocket server.

* Once you are connected to the WebSocket endpoint, Postman will display a successful connection message. After the connection is established, any new data sent from the REST API (e.g., a new **order**) will be broadcast to all connected WebSocket sessions in real-time.

* If you add a new order on the backend, the WebSocket server will send a notification to all connected clients. In Postman, you will see the message as a JSON object, such as:

	{
	  "status": "success",
	  "message": "Order created successfully",
	  "order": {
	    "id": 1,
	    "price": 100.5,
	    "amount": 10,
	    "type": "buy"
	  }
	}

## Contact
If you have any questions or need further assistance, feel free to reach out to 
> djukicnikola00@gmail.com
