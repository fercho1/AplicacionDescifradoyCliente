public class Chat {
    public static void main(String[] args) {
		String dispositivoAlla = args[0];
        int puertoAlla = Integer.parseInt(args[1]);
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
		Cliente c = new Cliente(dispositivoAlla, puertoAlla);
		c.conectar();
    }
}
