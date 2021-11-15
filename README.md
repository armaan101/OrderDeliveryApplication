# OrderDeliveryApplication
A springboot application to help farmers book,keep track and delete orders.

OrderDeliveryAPI
The aim is to implement a water ordering API so farmers can request water to irrigate their farms. Farmers can use this API to place water orders, view existing water orders and cancel water orders before they are delivered.
A basic water order has the following attributes:
farmId – A unique ID for identifying a farm.
Start date time – The date and time when water should be delivered.
Duration – The duration of the order (e.g. Duration of 3 hours means water will flow into the farm for 3 hours from the start date time).
Functionalities:
A REST API to accept new orders from a farmer. 
an API for cancelling an existing order if it hasn’t been delivered.
an API so farmers can query existing orders. When querying orders, the farmers are able to see the status of each order. Possible status of a water order:
Requested – “Order has been placed but not yet delivered.”
InProgress – “Order is being delivered right now.”
Delivered – “Order has been delivered.”
Cancelled – “Order was cancelled before delivery.”
The API ensures the water orders for a farm do not overlap. 
For example, if Farm X already has an order for 30 Jan 2019 starting at 6am with a 3 hours duration, it should not allow Farm X to place an order starting at 8am on the same day.
To simulate water delivery, the application outputs a line each time the status of a water order changes. This include –
When a new water order is placed;
When a water order starts (Start date time of the order);
When a water order is delivered (i.e. start date time + duration);
When a water order is cancelled;
As an example:
Assume it is currently 10am, and a water order is created with a start time of 12pm and finishing at 2pm.
At 10am, when the order is placed, your application’s log should print “New water order for farm xyz created”
At 12pm, log should print “Water delivery to farm xyz started.”
At 2pm, log should print “Water delivery to farm xyz stopped.”
For the purpose of this task, orders are stored in memory. Orders do not  persist between application restarts.



