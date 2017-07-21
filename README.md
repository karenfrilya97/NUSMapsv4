Scope of Project: NUS campus navigation service with informative functionality.

 

Our team aims to deliver an Android mobile application which provides a full NUS campus navigation system integrated with options for different modes of transport. The app will also have a search function where users can search for a NUS location and be returned with information regarding that location.
 

Target Audience & Motivation: Freshmen, Exchange students, Visitors, general student and staff population

These individuals may not know the fastest/most efficient way to get around NUS campus or simply do not know how to get from one point to another. Furthermore, from our own personal experience and student feedback, students are unsure about certain information pertaining to key locations within NUS and do not know where to find such information. These include the opening/closing timings of the various canteens in NUS, booking procedure for the NUS sporting facilities and many others. 
The app aims to combine the functionality of a navigation system and the convenience of providing essential information.
Prototype: (for a visual description of our prototype, please watch our video submission)

So far, our team has managed to implement the basic functionalities and UI for our app.
Features already implemented:
Location GPS settings detection – the app can automatically detect the GPS settings of the user’s device and switch it on if it was off at launch.
My Current Location button – Pans the map to the device’s current location and shows the current location with a blue dot. (similar to Google Maps original app)
Directional Feature – We included a directions button on the bottom right of the map to bring the user to a directions activity page where we have designed a rough UI. On this page, users can enter their desired starting position and destination, followed by choosing their desired mode of transport. So far, we have only implemented the walking mode of transport. When users click the get directions button, they will be shifted to the map page with the planned route highlighted and markers placed on the starting point and destination.
Informative Feature – We have implemented a search bar on top of the maps page with an autocomplete drop down feature to allow users to easily search their desired location. Search options are also biased to locations within the NUS vicinity. When searched, the app will pan to the searched location and place a marker. When the marker is clicked, the locations address will be shown.
Technologies/tools used:
Google Maps Android API
Google Maps Places API
Google Maps Directions Web Service API
Intent to transition between activities
Testing of prototype:

My current location feature has been tested to show high levels of reliability in its accuracy
Directional feature accurately plans optimised route from point to point
Search bar accurately marks searched location
No bugs in the currently implemented features
 

Development Plan:

Customised My Current Location button:
Presently, the button merely shows the current location without tracking the movements of the user. We are planning to implement a customised button where the user can toggle my location tracking on/off for a better navigation experience.
Improvements to directional feature:
Implement functionality for the other modes of transport (car & transit)
Implement feature to show estimated travel duration and distance for each mode of transport
Improve upon the UI of the directions activity page
Allow users to select my current location as their starting point
Implement an autocomplete drop-down function when entering starting point/destination (like the search bar), for easier selection of location
Improvements to informative feature:
Establish a CRUD database where we can store information about the various locations in NUS. When a location is called through the search bar, we can pull its respective information from the database and showcase it to the user.
Explore other means of showcasing the data to user other than information in the marker. (We are considering a popup-sidebar when the user clicks on the marker)
Information we plan to provide was covered in the milestone1 submission.
Bound the map to the NUS campus
We also plan to bound the map to the NUS compound (limiting map panning to the NUS vicinity) as our app is primarily targeted for NUS
 

Our team feels that in this month of June, we have become more fluent/familiar with how android studio and Google APIs work. Hence, development in July will be smoother and be met with fewer obstacles.
