const server = require('ws').Server
const wss = new server({port:4000})
wss.on('connection',function(ws){
    ws.on('message',function(data){
        console.log(data)
        wss.clients.forEach(ws_ =>{
            ws_.send(data)
            
        })
        
    })
    
    ws.send("hellow")
})
