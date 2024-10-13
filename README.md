## Prerequisites

- JDK 17
- Gradle 7.6

## Run the app

```./gradlew bootRun``` will start the app on port ```3000```.

Application is configured to use H2 in-memory database; database is already created with order_details,customer table.

Below are the api created:

#####  An endpoint for adding items to the queue. 
http://localhost:3000/donut-store/api/v1/addOrder

```
Request:
{
  "customerId": 1005,
  "quantity": 2
}
Response:
{
    "code": "01",
    "message": "Order added successfully"
}
```
##### An endpoint for clients to check their queue position and approximate wait time
http://localhost:3000/donut-store/api/v1/getQueuePositionAndWaitTime/{Cust_id}
``` 
Response:
{
"creationTime": null,
"waitTime": 1,
"position": 1,
"orderId": 1005,
"quantity": 2,
"customerId": 1005
}
```
##### An endpoint which allows Jim’s manager to see all entries in the queue with the
approximate wait time.
http://localhost:3000/donut-store/api/v1/getAllOrdersAndWaitTime

``` 
Response:
[
    {
    "orderId": 1005,
    "customer": {
    "custId": 1005,
    "premium": false
    },
    "quantity": 2,
    "createTime": 1728842982623,
    "waitTime": 1,
    "position": 1
    }
]
```
##### An endpoint to retrieve Jim’s next delivery which should be placed in the cart.

http://localhost:3000/donut-store/api/v1/nextDelivery

``` 
Response:
[
    {
    "creationTime": null,
    "waitTime": 1,
    "position": 1,
    "orderId": 1005,
    "quantity": 2,
    "customerId": 1005
    }
]
```
##### An endpoint to cancel an order.
http://localhost:3000/donut-store/api/v1/cancelOrder/{Cust_id}

``` 
Response:
{
"code": "02",
"message": "Order deleted successfully"
}
```


