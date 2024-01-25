
## Java Chat Application
This is a simple chat application implemented in Java using Swing for the graphical user interface and sockets for communication between a server and a client. The application allows two users to exchange text messages in real-time.

## Features

- **User Interface:** The application provides a clean and intuitive user interface for seamless interaction.
- **Real-time Messaging:** Users can send and receive messages in real-time.
- **Profile Images:** Each user is represented by a profile image.
- **Active Status:** The application displays the active status of the users.
- **Message Formatting:** Messages are displayed in a formatted manner with timestamps.

## Usage

1. **Server Side:**
   - Run the `Server.java` file to start the server.
   - The server listens on port 6001.

2. **Client Side:**
   - Run the `Client.java` file to start the client.
   - Connect to the server by providing the server's IP address (127.0.0.1 in this example) and port 6001.

3. **Chatting:**
   - Once connected, users can send messages by typing in the text field and clicking the "Send" button.
   - The chat history is displayed in the chat area.

4. **Exiting:**
   - Click the "Back" button on the top-left corner of the application window to exit.

## Screenshots

![op1](https://github.com/Dhamma-007/chatting-application-java/assets/88797388/33e5e991-fbdb-4452-be27-00274bb5b09f)


## Dependencies

- Java SDK
- Swing Library

## File Structure

- **Server.java:** Contains the server-side implementation.
- **Client.java:** Contains the client-side implementation.

## Notes

- This is a basic chat application and may not be suitable for production use.
- The application uses sockets for communication, and it's recommended to run the server on a machine with a static IP address.

