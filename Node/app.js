// Declarations
const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const http = require('http');
const socket = require('socket.io');


// Configs

     // Body Parser
     app.use(bodyParser.urlencoded({ extended: false }));
     app.use(bodyParser.json());

     // Static File
     app.use(express.static(__dirname + '/src/view'));

     // Socket
     const httpServer = http.createServer(app);
     const io = socket(httpServer, {
          path: '/socket.io'
     })


// Client Socket
let clientSocket;

// Route

app.post('/chat_node', (req, res) => {
     if(clientSocket) clientSocket.emit('message', req.body);

     res.setHeader('content-type', 'application/json');
     res.send({message: "OK"});
})

// Initialize 
     
     // Connection Socket
     io.on('connection', (client) => {
          console.log(`Client ${client.id} connected`)
          clientSocket = client;

          client.on('disconnect', () => {
               console.log(`Client ${client.id} disconnect`)
               clientSocket = null;
          })
     })

     // Server
     httpServer.listen(3000, function () {
          console.log(`Servidor inicializado em: http://localhost:3000`);
     });