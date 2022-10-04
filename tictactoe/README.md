# Tic Tac Toe Game

### Setup
 - navigate to this folder on terminal
 - type "mvn package" command
 - navigate to GameApplication.java file and run it as a normal java class
 - install postman
 - import the "tictactoe.postman_collection.json" file 
 - start game with "start game" request
 - it will return a random gameId
 - with this gameId edit the "move" command, edit the gameId value on the request. Also specify the movement in coordinates. Coordinates mapping is ;
 
 - 0,2 - 1,2 - 2,2
 - 0,1 - 1,1 - 2,1
 - 0,0 - 1,0 - 2,0

- query the board status with "Get Board status" request. Dont forget to edit gameId at the end of the request



### Endpoints
http://localhost:8080/tictactoe/game
http://localhost:8080/tictactoe/game/TQHeS
http://localhost:8080/tictactoe/game/TQHeS/move
{
    "row":1,
    "col":2
}


### OpenAPI endpoint

http://localhost:8080/swagger-ui/index.html