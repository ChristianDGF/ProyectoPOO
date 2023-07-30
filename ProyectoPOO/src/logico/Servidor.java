package logico;

import java.io.*;
import java.net.*;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(7000);
			System.out.println("Servidor listo para recibir conexiones...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado desde: " + socket.getInetAddress());

				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Clinica clinica = (Clinica) ois.readObject();

				ois.close();
				socket.close();

				guardarCopiaRespaldo(clinica);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void guardarCopiaRespaldo(Clinica clinica) {
		try {
			FileOutputStream fileOut = new FileOutputStream("copia_respaldo_clinica.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);
			oos.writeObject(clinica);
			oos.close();
			fileOut.close();
			System.out.println("Copia de respaldo de la clínica guardada exitosamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
