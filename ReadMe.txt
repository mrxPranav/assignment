

CURL commands to test api locally

1. Register
curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"pranav\", \"password\":\"123\"}" http://localhost:8080/register


2. Login
curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"pranav\", \"password\":\"123\"}" http://localhost:8080/login


3. Home(secured url)
curl -X GET -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJwcmFuYXYiLCJpYXQiOjE3MjQxNDQzMjEsImV4cCI6MTcyNDE0NDYyMX0.ogXtStclKWLbR8PT6nIQPQPssrHly3T9mf-zpIpbGhIhttp://localhost:8080/home


4. Refresh Token
curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJwcmFuYXYiLCJpYXQiOjE3MjQxNDQzMjEsImV4cCI6MTcyNDE0NDYyMX0.ogXtStclKWLbR8PT6nIQPQPssrHly3T9mf-zpIpbGhI-d "{\"refreshToken\":\"fc5a7349-c5ac-4d37-889c-bed93a4f2a97\"}" http://localhost:8080/refreshtoken

5. Revoke token
curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJwcmFuYXYiLCJpYXQiOjE3MjQxNDQzMjEsImV4cCI6MTcyNDE0NDYyMX0.ogXtStclKWLbR8PT6nIQPQPssrHly3T9mf-zpIpbGhI" http://localhost:8080/revoketoken

---------------------------------------------------------------------------------------------------------------------------------
I have deployed this project on Render.com as well
Here is the URL - "https://assignment-na2p.onrender.com" (It will take some time to start first time only.)

CURL commands to test api on "https://assignment-na2p.onrender.com"

1. Register
curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"pranav\", \"password\":\"123\"}" https://assignment-na2p.onrender.com/register


2. Login
curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"pranav\", \"password\":\"123\"}" https://assignment-na2p.onrender.com/login


3. Home(secured url)
curl -X GET -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJwcmFuYXYiLCJpYXQiOjE3MjQxNDQ4MTIsImV4cCI6MTcyNDE0NTExMn0.NDNKNcgi_eB5GTVNQfQyqxP9qXayiBXXZZKfB5kfPUk" https://assignment-na2p.onrender.com/home


4. Refresh Token
curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJwcmFuYXYiLCJpYXQiOjE3MjQxNDQ2OTMsImV4cCI6MTcyNDE0NDk5M30.9biTGBbovEvpi318A-kzicdLhD7Uq3_VmLRBNabWvGU" -d "{\"refreshToken\":\"84957739-5b18-4c64-b12e-03697e4e335a\"}" https://assignment-na2p.onrender.com/refreshtoken

5. Revoke token 
curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJwcmFuYXYiLCJpYXQiOjE3MjQxNDQ4MTIsImV4cCI6MTcyNDE0NTExMn0.NDNKNcgi_eB5GTVNQfQyqxP9qXayiBXXZZKfB5kfPUk" https://assignment-na2p.onrender.com/revoketoken
