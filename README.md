

1. You can access the POST identify endpoint through the below URL

  Brief Logic: You can use the below endpoint to create primary and secondary records in the database and return the same
  Method: POST
  API ENDPOINT: https://bitespeed-backend-task-nmwt.onrender.com/identify
  Sample RequestBody: 
  {
      "email": "email@gmail.com",
      "phoneNumber":"09876"
  }


2. For the reference purpose to fetch all the records that are present in the database you can use the below URL
   Method: GET
   API ENDPOINT: https://bitespeed-backend-task-nmwt.onrender.com/get/identities



Note (Please don't ignore 1st point):
1. If the service is inactive for some time on render.com,  it may take 50 secs or more to respond for the first time, as mentioned in the render.com website. Please wait patiently for the first request response.
2. This is developed using SPRINGBOOT and POSTGRESQL databases and hosted on render.com
3. please get in touch with me if you face any issues. email:nagellatarun@gmail.com
   
