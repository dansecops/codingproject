#!/usr/bin/env python
import socket, signal
import sys

"""PlainSocket.py : Simulator of a plain socket on TCP port 8080"""

__author__ = "Skjallar"

port = 8080
host = socket.gethostbyname(socket.gethostname())
print(host)

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind((host, port))
s.listen(5)                 # Now wait for client connection.

while True:
    c, addr = s.accept()     # Establish connection with client.
    req = c.recv(1024).decode('utf-8')
    string_list = req.split(' ')  # Split request from spaces

    method = string_list[0]
    requesting_file = string_list[1]

    print('Client request ', requesting_file)

    try:
        response_header = 'HTTP/1.1 200 OK\nContent-Type: text/html\n\n'
        response_body = '<html><body>Hello World</body></html>'.encode('utf-8')

    except Exception as e:
        response_header = 'HTTP/1.1 404 Not Found\n\n'
        response_body = '<html><body><center><h3>Error 404: File not found</h3><p>Python HTTP Server</p></center></body></html>'.encode(
            'utf-8')

    final_response = response_header.encode('utf-8')
    final_response += response_body
    c.send(final_response)
    c.close()                # Close the connection