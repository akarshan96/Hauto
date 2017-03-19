import SocketServer
import living_room
from time import sleep
class MyTCPHandler(SocketServer.BaseRequestHandler):

    def handle(self):
        self.data = self.request.recv(1024).strip()
        print "{} wrote:".format(self.client_address[0])
        print self.data
        if self.data == "Living_Room":
            self.request.sendall(living_room.send())
        if self.data.startswith("{"):
            living_room.rec(self.data)
            print "---+++---"
            self.request.sendall("Done")
			

if __name__ == "__main__":
    HOST, PORT = "192.168.41.49", 9999
    server = SocketServer.TCPServer((HOST, PORT), MyTCPHandler)
    server.serve_forever()



