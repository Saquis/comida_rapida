package utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ValidarCadenas {
    
    public static void validar_longitud_password(JPasswordField textField, int caracteres){
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String texto = String.valueOf(textField.getPassword());

                // Chequea que sean letras
                if (texto.length() >= caracteres) {
                    e.consume();
                }
            }
        });
    }
    
    public static void validar_input_letrasnumeros(JTextField textField, int caracteres){
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String texto = textField.getText();

                // Chequea que sean letras
                if ((!Character.isLetter(c) && !Character.isISOControl(c) && !Character.isDigit(c)) || texto.length() >= caracteres) {
                    e.consume();
                }
            }
        });
    }
        
    public static void validar_input_letras(JTextField textField, int caracteres){
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String texto = textField.getText();

                // Chequea que sean letras
                if ((!Character.isLetter(c) && !Character.isISOControl(c)) || texto.length() >= caracteres) {
                    e.consume();
                }
            }
        });
    }
    
    public static void validar_input_numeros(JTextField textField, int digitos) {
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String texto = textField.getText();

                // Chequea que sean números
                if (!Character.isDigit(c) || texto.length() >= digitos) {
                    e.consume();
                }
            }
        });
    }
    
    public static void validar_input_moneda(JTextField textField, int digitos) {
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String texto = textField.getText();
                
                // Supera la cantidad de digitos
                if (texto.length() >= digitos) {
                    e.consume();
                    return;
                }

                // Permitir solo números, una coma, y hasta dos decimales
                if (!Character.isDigit(c) && c != ',' && !Character.isISOControl(c)) {
                    e.consume();
                    return;
                }

                // Permitir solo una coma
                if (c == ',' && (texto.contains(",") || texto.isEmpty())) {
                    e.consume();
                    return;
                }

                // Permitir solo dos decimales
                if (texto.contains(",")) {
                    int index = texto.indexOf(",");
                    String decimalPart = texto.substring(index + 1);

                    if (decimalPart.length() >= 2 && !Character.isISOControl(c)) {
                        e.consume();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String texto = textField.getText();
                if (texto.contains(",")) {
                    int index = texto.indexOf(",");
                    String decimalPart = texto.substring(index + 1);

                    if (decimalPart.length() > 2) {
                        textField.setText(texto.substring(0, index + 3));
                    }
                }
            }
        });
    }
    
    public static void validar_input_email(JTextField textField, int caracteres) {
        textField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Se permiten caracteres validos para email
                if (!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != '_' && c != '-' && !Character.isISOControl(c)) {
                    e.consume();
                }
            }
        });
    }
    
    public static boolean validar_email(String email) {
        // Patrón de correo
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
